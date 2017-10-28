package com.affaince.subscription.product.vo;

import com.affaince.subscription.common.service.interpolate.InterpolatorChain;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.type.ProductReadinessStatus;
import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.common.vo.ProductForecastParameter;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.query.repository.ProductActivationStatusViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.query.repository.ProductPseudoActualsViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductActivationStatusView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.query.view.ProductPseudoActualsView;
import com.affaince.subscription.product.query.view.ProductView;
import com.affaince.subscription.product.validator.ProductConfigurationValidator;
import com.affaince.subscription.product.web.exception.ProductNotFoundException;
import com.affaince.subscription.product.web.exception.ProductReadinessException;
import com.affaince.subscription.product.web.request.AddForecastParametersRequest;
import com.affaince.subscription.query.exception.ProductForecastAlreadyExistsException;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by mandar on 10/28/2017.
 */
public class ManualProductForecastHelper {
    private final ProductViewRepository productViewRepository;
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;
    private final ProductForecastViewRepository productForecastViewRepository;
    private final ProductPseudoActualsViewRepository productPseudoActualsViewRepository;
    private final InterpolatorChain interpolatorChain;
    private static final int INTERPOLATE_NEW_SUBSCRIPTIONS = 1;
    private static final int INTERPOLATE_TOTAL_SUBSCRIPTIONS = 2;

    @Autowired
    public ManualProductForecastHelper(ProductViewRepository productViewRepository, ProductActivationStatusViewRepository productActivationStatusViewRepository, ProductForecastViewRepository productForecastViewRepository, ProductPseudoActualsViewRepository productPseudoActualsViewRepository,InterpolatorChain interpolatorChain) {
        this.productViewRepository = productViewRepository;
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
        this.productForecastViewRepository = productForecastViewRepository;
        this.productPseudoActualsViewRepository = productPseudoActualsViewRepository;
        this.interpolatorChain = interpolatorChain;
    }

    public ResponseEntity<Object> addForecast(String productId, ProductForecastParameter[] productForecastParameters, LocalDate forecastDate
                                              ) throws Exception {
        ProductView productView = this.productViewRepository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }
/*
        AddManualForecastCommand command = new AddManualForecastCommand(productId, request.getProductForecastParameters(),SysDate.now());
        commandGateway.executeAsync(command);
*/

        final List<ProductStatus> productStatuses = productActivationStatusViewRepository.
                findByProductId(productId).getProductStatuses();
        if (ProductConfigurationValidator.getProductReadinessStatus(productStatuses).contains(
                ProductReadinessStatus.FORECASTABLE
        )) {
            LocalDate firstStartDate = null;
            LocalDate lastEndDate = null;Sort endDateSort = new Sort(Sort.Direction.DESC, "endDate");

            long totalSubscriptions = 0;
            for (ProductForecastParameter parameter : productForecastParameters) {
                List<ProductForecastView> existingForecastViews = this.productForecastViewRepository.findByForecastVersionId_ProductIdAndEndDateBetween(productId, parameter.getStartDate(), parameter.getEndDate());
                //forecast should not be newly added if it already exists in the view
                if (null != existingForecastViews && existingForecastViews.size() > 0) {
                    throw ProductForecastAlreadyExistsException.build(productId, parameter.getStartDate(), parameter.getEndDate());
                }
                //find forecasts entered earlier to current forecast entry
                List<ProductForecastView> earlierForecastViews = this.productForecastViewRepository.findByForecastVersionId_ProductIdAndEndDateLessThan(productId, parameter.getEndDate(), endDateSort);
                if (earlierForecastViews.isEmpty()) {
                    totalSubscriptions = parameter.getNumberOfNewSubscriptions() - parameter.getNumberOfChurnedSubscriptions();
                } else {
                    totalSubscriptions = earlierForecastViews.get(0).getTotalNumberOfExistingSubscriptions() + parameter.getNumberOfNewSubscriptions() - parameter.getNumberOfChurnedSubscriptions();
                }
                ProductForecastView productForecastView = new ProductForecastView(new ForecastVersionId(productId, parameter.getStartDate(),forecastDate), parameter.getEndDate(), parameter.getNumberOfNewSubscriptions(), parameter.getNumberOfChurnedSubscriptions(), totalSubscriptions);
                productForecastViewRepository.save(productForecastView);
                if (null == firstStartDate) {
                    firstStartDate = parameter.getStartDate();
                }
                lastEndDate = parameter.getEndDate();
            }
            ProductActivationStatusView view = productActivationStatusViewRepository.findOne(productId);
            if (!view.getProductStatuses().contains(ProductStatus.PRODUCT_FORECASTED)) {
                view.addProductStatus(ProductStatus.PRODUCT_FORECASTED);
                productActivationStatusViewRepository.save(view);
            }
            derivePseudoActualsFromForecast(productId,firstStartDate,forecastDate);
        } else {
            ProductReadinessException.build(productId, ProductStatus.PRODUCT_FORECASTED);
        }

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    private double[] interpolateStepForecastFromForecast(String productId, int whomToInterpolate) {
        List<ProductForecastView> registeredForecastValues = productForecastViewRepository.
                findByForecastVersionId_ProductIdAndForecastContentStatusOrderByForecastVersionId_FromDateAsc
                        (productId, ForecastContentStatus.ACTIVE);
        ProductForecastView firstForecastView = registeredForecastValues.get(0);
        LocalDate dateOfPlatformBeginning = firstForecastView.getProductVersionId().getFromDate();
        double[] x = new double[registeredForecastValues.size()];     //day on which interpolated value has been taken
        double[] y = new double[registeredForecastValues.size()];     //interpolated value of total subscription
        int count = 0;
        for (ProductForecastView previousView : registeredForecastValues) {
            LocalDate endDate = previousView.getEndDate();
            int day = Days.daysBetween(dateOfPlatformBeginning, endDate).getDays(); //TODO- should we add/subtract 1 in the value?
            x[count] = day;
            if (whomToInterpolate == INTERPOLATE_TOTAL_SUBSCRIPTIONS) {
                y[count] = previousView.getTotalNumberOfExistingSubscriptions();
            } else if (whomToInterpolate == INTERPOLATE_NEW_SUBSCRIPTIONS) {
                y[count] = previousView.getNewSubscriptions();
            }
            count++;
        }
        double[] interpolatedSubscriptionsPerDay = interpolatorChain.interpolate(x, y);
        return interpolatedSubscriptionsPerDay;
    }

    private void derivePseudoActualsFromForecast(String productId,LocalDate firstStartDate,LocalDate forecastDate){

        //Now add PseudoActuals by interpolating manual forecastAdded
        double[] interpolatedPseudoActualsTotalSubscriptions = interpolateStepForecastFromForecast(productId, INTERPOLATE_TOTAL_SUBSCRIPTIONS);
        double[] interpolatedPseudoActualsNewSubscriptions = interpolateStepForecastFromForecast(productId, INTERPOLATE_NEW_SUBSCRIPTIONS);
        double previousDayTotalSubcriptionCount = 0;
        double dailychurnedSubscriptionCount = 0;
        for (int i = 0; i < interpolatedPseudoActualsTotalSubscriptions.length; i++) {
            double interpolatedTotalSubscriptionCount = interpolatedPseudoActualsTotalSubscriptions[i];
            double interpolatedNewSubscriptionCount = interpolatedPseudoActualsNewSubscriptions[i];
            if (i == 0) {
                dailychurnedSubscriptionCount = interpolatedTotalSubscriptionCount - interpolatedNewSubscriptionCount;
            } else {
                dailychurnedSubscriptionCount = previousDayTotalSubcriptionCount + interpolatedNewSubscriptionCount - interpolatedTotalSubscriptionCount;
            }
            previousDayTotalSubcriptionCount=interpolatedTotalSubscriptionCount;
            ProductPseudoActualsView productPseudoActualsView = new ProductPseudoActualsView(new ForecastVersionId(productId, firstStartDate.plusDays(i),forecastDate), firstStartDate.plusDays(i), Double.valueOf(interpolatedNewSubscriptionCount).longValue(), Double.valueOf(dailychurnedSubscriptionCount).longValue(), Double.valueOf(interpolatedTotalSubscriptionCount).longValue());
            productPseudoActualsViewRepository.save(productPseudoActualsView);
        }
    }

}
