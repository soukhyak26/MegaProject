package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.AddToPurchaseCostAccountCommand;
import com.affaince.subscription.business.command.event.ManualForecastAddedEvent;
import com.affaince.subscription.business.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.business.query.repository.ProductViewRepository;
import com.affaince.subscription.business.query.view.ProductForecastView;
import com.affaince.subscription.business.query.view.ProductView;
import com.affaince.subscription.common.vo.ProductForecastParameter;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
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
 * Created by mandar on 11-11-2016.
 */
@Component
public class ManualForecastAddedEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManualForecastAddedEventListener.class);
    private final ProductForecastViewRepository productForecastViewRepository;
    private final ProductViewRepository productViewRepository;
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public ManualForecastAddedEventListener(ProductForecastViewRepository productForecastViewRepository,ProductViewRepository productViewRepository,SubscriptionCommandGateway commandGateway) {
        this.productForecastViewRepository = productForecastViewRepository;
        this.productViewRepository=productViewRepository;
        this.commandGateway=commandGateway;
    }

    @EventHandler
    public void on(ManualForecastAddedEvent event) throws ProductForecastAlreadyExistsException,Exception {
        ProductForecastParameter[] forecastParameters = event.getProductForecastParameters();
        LocalDate firstStartDate = null;
        LocalDate lastEndDate = null;
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
            if (null== earlierForecastViews || earlierForecastViews.isEmpty()) {
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

        //retrieve product view for this product and set its budgeted purchase cost
        ProductView productView =productViewRepository.findByProductId(event.getProductId());
        double productPurchasePrice = productView.getPurchasePrice();

        AddToPurchaseCostAccountCommand command = new AddToPurchaseCostAccountCommand(SysDate.now().getYear(),event.getProductId(),totalSubscriptions, productPurchasePrice);
        commandGateway.executeAsync(command);

    }
}
