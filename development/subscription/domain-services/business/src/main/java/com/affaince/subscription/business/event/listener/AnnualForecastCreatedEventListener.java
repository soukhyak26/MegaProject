package com.affaince.subscription.business.event.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.AddToPurchaseCostAccountCommand;
import com.affaince.subscription.business.event.AnnualForecastCreatedEvent;
import com.affaince.subscription.business.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.business.query.repository.ProductViewRepository;
import com.affaince.subscription.business.query.view.ProductForecastView;
import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.date.SysDate;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 2/23/2017.
 */
@Component
public class AnnualForecastCreatedEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnnualForecastCreatedEventListener.class);
    private final ProductForecastViewRepository productForecastViewRepository;
    private final ProductViewRepository productViewRepository;
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public AnnualForecastCreatedEventListener(ProductForecastViewRepository productForecastViewRepository, ProductViewRepository productViewRepository, SubscriptionCommandGateway commandGateway) {
        this.productForecastViewRepository = productForecastViewRepository;
        this.productViewRepository = productViewRepository;
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void on(AnnualForecastCreatedEvent event) throws Exception {
/*
        LocalDate firstStartDate = null;
        LocalDate lastEndDate = null;
        Sort endDateSort = new Sort(Sort.Direction.DESC, "endDate");
*/
        List<ProductForecastView> existingForecastViews = this.productForecastViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(event.getProductId(), event.getStartDate(), event.getEndDate());
        //forecast should not be newly added if it already exists in the view
        if (null != existingForecastViews && !existingForecastViews.isEmpty()) {
            ProductForecastView currentForecastView = existingForecastViews.get(0);
            long currentTotalSubscriptions=currentForecastView.getTotalNumberOfExistingSubscriptions();
            if(event.getRevisedTotalSubscriptionCount()>currentTotalSubscriptions) {
                currentForecastView.setTotalNumberOfExistingSubscriptions(event.getRevisedTotalSubscriptionCount());
                productForecastViewRepository.save(currentForecastView);
            }
        } else {
            ProductForecastView productForecastView = new ProductForecastView(new ForecastVersionId(event.getProductId(), event.getStartDate(),SysDate.now()), event.getEndDate(), event.getRevisedTotalSubscriptionCount());
            productForecastViewRepository.save(productForecastView);
        }

        //retrieve product view for this product and set its budgeted purchase cost
/*
        ProductView productView = productViewRepository.findByProductId(event.getProductId());
        double productPurchasePrice = productView.getPurchasePrice();
*/
        AddToPurchaseCostAccountCommand command = new AddToPurchaseCostAccountCommand(SysDate.now().getYear(), event.getProductId(), event.getRevisedTotalSubscriptionCount(), event.getPurchasePricePerUnit(),SysDate.now());
        commandGateway.executeAsync(command);
    }

}
