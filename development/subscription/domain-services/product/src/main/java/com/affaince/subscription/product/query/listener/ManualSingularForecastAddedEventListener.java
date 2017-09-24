package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.service.interpolate.InterpolatorChain;
import com.affaince.subscription.common.type.ProductReadinessStatus;
import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.command.event.ManualForecastAddedEvent;
import com.affaince.subscription.product.command.event.ManualSingularForecastAddedEvent;
import com.affaince.subscription.product.query.repository.ProductActivationStatusViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.query.repository.ProductPseudoActualsViewRepository;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.validator.ProductConfigurationValidator;
import com.affaince.subscription.query.exception.ProductForecastAlreadyExistsException;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 2/28/2017.
 */
@Component
public class ManualSingularForecastAddedEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManualForecastAddedEventListener.class);
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;
    private final ProductForecastViewRepository productForecastViewRepository;
    private final ProductPseudoActualsViewRepository productPseudoActualsViewRepository;
    //private final CubicSplineInterpolator interpolator;
    private final InterpolatorChain interpolatorChain;
    private static final int INTERPOLATE_NEW_SUBSCRIPTIONS = 1;
    private static final int INTERPOLATE_TOTAL_SUBSCRIPTIONS = 2;

    @Autowired
    public ManualSingularForecastAddedEventListener(ProductActivationStatusViewRepository productActivationStatusViewRepository, ProductForecastViewRepository productForecastViewRepository, ProductPseudoActualsViewRepository productPseudoActualsViewRepository, InterpolatorChain interpolatorChain) {
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
        this.productForecastViewRepository = productForecastViewRepository;
        this.productPseudoActualsViewRepository = productPseudoActualsViewRepository;
        this.interpolatorChain = interpolatorChain;
    }

    @EventHandler
    public void on(ManualSingularForecastAddedEvent event) throws ProductForecastAlreadyExistsException {
        final List<ProductStatus> productStatuses = productActivationStatusViewRepository.
                findByProductId(event.getProductId()).getProductStatuses();
        if (ProductConfigurationValidator.getProductReadinessStatus(productStatuses).contains(
                ProductReadinessStatus.FORECASTABLE
        )) {
            LocalDate firstStartDate = null;
            LocalDate lastEndDate = null;Sort endDateSort = new Sort(Sort.Direction.DESC, "endDate");

            List<ProductForecastView> existingForecastViews = this.productForecastViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(event.getProductId(), event.getStartDate(), event.getEndDate());
            //forecast should not be newly added if it already exists in the view
            if (null != existingForecastViews && existingForecastViews.size() > 0) {
                throw ProductForecastAlreadyExistsException.build(event.getProductId(), event.getStartDate(), event.getEndDate());
            }
            //find forecasts entered earlier to current forecast entry
            List<ProductForecastView> earlierForecastViews = this.productForecastViewRepository.findByProductVersionId_ProductIdAndEndDateLessThan(event.getProductId(), event.getEndDate(), endDateSort);
            long totalSubscriptions = 0;
            if (earlierForecastViews.isEmpty()) {
                totalSubscriptions = event.getNumberOfNewSubscriptions() - event.getNumberOfChurnedSubscriptions();
            } else {
                totalSubscriptions = earlierForecastViews.get(0).getTotalNumberOfExistingSubscriptions() + event.getNumberOfNewSubscriptions() - event.getNumberOfChurnedSubscriptions();
            }
            ProductForecastView productForecastView = new ProductForecastView(new ForecastVersionId(event.getProductId(), event.getStartDate(), SysDate.now()), event.getEndDate(), event.getNumberOfNewSubscriptions(), event.getNumberOfChurnedSubscriptions(), totalSubscriptions);
            productForecastViewRepository.save(productForecastView);
            if (null == firstStartDate) {
                firstStartDate = event.getStartDate();
            }
            lastEndDate = event.getEndDate();

        }


    }
}
