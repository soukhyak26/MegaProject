package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.service.interpolate.InterpolatorChain;
import com.affaince.subscription.common.type.ProductForecastStatus;
import com.affaince.subscription.common.type.ProductReadinessStatus;
import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.command.event.ManualForecastAddedEvent;
import com.affaince.subscription.product.query.repository.ProductActivationStatusViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.query.repository.ProductPseudoActualsViewRepository;
import com.affaince.subscription.product.query.view.ProductActivationStatusView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.query.view.ProductPseudoActualsView;
import com.affaince.subscription.product.validator.ProductConfigurationValidator;
import com.affaince.subscription.common.vo.ProductForecastParameter;
import com.affaince.subscription.query.exception.ProductForecastAlreadyExistsException;
import com.affaince.subscription.product.web.exception.ProductReadinessException;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 10-10-2016.
 */
@Component
public class ManualForecastAddedEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManualForecastAddedEventListener.class);
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;
    private final ProductForecastViewRepository productForecastViewRepository;
    private final ProductPseudoActualsViewRepository productPseudoActualsViewRepository;
    //private final CubicSplineInterpolator interpolator;
    private final InterpolatorChain interpolatorChain;
    private static final int INTERPOLATE_NEW_SUBSCRIPTIONS = 1;
    private static final int INTERPOLATE_TOTAL_SUBSCRIPTIONS = 2;

    @Autowired
    public ManualForecastAddedEventListener(ProductActivationStatusViewRepository productActivationStatusViewRepository, ProductForecastViewRepository productForecastViewRepository, ProductPseudoActualsViewRepository productPseudoActualsViewRepository, InterpolatorChain interpolatorChain) {
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
        this.productForecastViewRepository = productForecastViewRepository;
        this.productPseudoActualsViewRepository = productPseudoActualsViewRepository;
        this.interpolatorChain = interpolatorChain;
    }

    @EventHandler
    public void on(ManualForecastAddedEvent event) throws ProductForecastAlreadyExistsException {
        final List<ProductStatus> productStatuses = productActivationStatusViewRepository.
                findByProductId(event.getProductId()).getProductStatuses();
        if (ProductConfigurationValidator.getProductReadinessStatus(productStatuses).contains(
                ProductReadinessStatus.FORECASTABLE
        )) {
            ProductForecastParameter[] forecastParameters = event.getProductForecastParameters();
            LocalDate firstStartDate = null;
            LocalDate lastEndDate = null;Sort endDateSort = new Sort(Sort.Direction.DESC, "endDate");

            long totalSubscriptions = 0;
            for (ProductForecastParameter parameter : forecastParameters) {
                List<ProductForecastView> existingForecastViews = this.productForecastViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(event.getProductId(), parameter.getStartDate(), parameter.getEndDate());
                //forecast should not be newly added if it already exists in the view
                if (null != existingForecastViews && existingForecastViews.size() > 0) {
                    throw ProductForecastAlreadyExistsException.build(event.getProductId(), parameter.getStartDate(), parameter.getEndDate());
                }
                //find forecasts entered earlier to current forecast entry
                List<ProductForecastView> earlierForecastViews = this.productForecastViewRepository.findByProductVersionId_ProductIdAndEndDateLessThan(event.getProductId(), parameter.getEndDate(), endDateSort);
                if (earlierForecastViews.isEmpty()) {
                    totalSubscriptions = parameter.getNumberOfNewSubscriptions() - parameter.getNumberOfChurnedSubscriptions();
                } else {
                    totalSubscriptions = earlierForecastViews.get(0).getTotalNumberOfExistingSubscriptions() + parameter.getNumberOfNewSubscriptions() - parameter.getNumberOfChurnedSubscriptions();
                }
                ProductForecastView productForecastView = new ProductForecastView(new ProductVersionId(event.getProductId(), parameter.getStartDate()), parameter.getEndDate(), parameter.getNumberOfNewSubscriptions(), parameter.getNumberOfChurnedSubscriptions(), totalSubscriptions, event.getForecastDate());
                productForecastViewRepository.save(productForecastView);
                if (null == firstStartDate) {
                    firstStartDate = parameter.getStartDate();
                }
                lastEndDate = parameter.getEndDate();
            }
            ProductActivationStatusView view = productActivationStatusViewRepository.findOne(event.getProductId());
            if (!view.getProductStatuses().contains(ProductStatus.PRODUCT_FORECASTED)) {
                view.addProductStatus(ProductStatus.PRODUCT_FORECASTED);
                productActivationStatusViewRepository.save(view);
            }

            derivePseudoActualsFromForecast(event.getProductId(),firstStartDate,event.getForecastDate());
        } else {
            ProductReadinessException.build(event.getProductId(), ProductStatus.PRODUCT_FORECASTED);
        }

    }

    private double[] interpolateStepForecastFromForecast(String productId, int whomToInterpolate) {
        List<ProductForecastView> registeredForecastValues = productForecastViewRepository.
                findByProductVersionId_ProductIdAndProductForecastStatusOrderByProductVersionId_FromDateAsc
                        (productId, ProductForecastStatus.ACTIVE);
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
            ProductPseudoActualsView productPseudoActualsView = new ProductPseudoActualsView(new ProductVersionId(productId, firstStartDate.plusDays(i)), firstStartDate.plusDays(i), Double.valueOf(interpolatedNewSubscriptionCount).longValue(), Double.valueOf(dailychurnedSubscriptionCount).longValue(), Double.valueOf(interpolatedTotalSubscriptionCount).longValue(),forecastDate);
            productPseudoActualsViewRepository.save(productPseudoActualsView);
        }
    }
}
