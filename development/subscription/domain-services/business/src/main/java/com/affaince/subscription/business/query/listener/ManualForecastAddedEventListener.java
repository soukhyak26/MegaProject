package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.ManualForecastAddedEvent;
import com.affaince.subscription.business.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.business.query.view.ProductForecastView;
import com.affaince.subscription.common.type.ProductReadinessStatus;
import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.common.vo.ProductForecastParameter;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.query.exception.ProductForecastAlreadyExistsException;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mandar on 11-11-2016.
 */
@Component
public class ManualForecastAddedEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManualForecastAddedEventListener.class);
    private final ProductForecastViewRepository productForecastViewRepository;

    @Autowired
    public ManualForecastAddedEventListener(ProductForecastViewRepository productForecastViewRepository) {
        this.productForecastViewRepository = productForecastViewRepository;
    }

    @EventHandler
    public void on(ManualForecastAddedEvent event) throws ProductForecastAlreadyExistsException {
        ProductForecastParameter[] forecastParameters = event.getProductForecastParameters();
        LocalDateTime firstStartDate = null;
        LocalDateTime lastEndDate = null;
        Sort endDateSort = new Sort(Sort.Direction.DESC, "endDate");
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
            ProductForecastView productForecastView = new ProductForecastView(new ProductVersionId(event.getProductId(), parameter.getStartDate()), parameter.getEndDate(), parameter.getNumberOfNewSubscriptions(), parameter.getNumberOfChurnedSubscriptions(), totalSubscriptions);
            productForecastViewRepository.save(productForecastView);
            if (null == firstStartDate) {
                firstStartDate = parameter.getStartDate();
            }
            lastEndDate = parameter.getEndDate();
        }
    }
}
