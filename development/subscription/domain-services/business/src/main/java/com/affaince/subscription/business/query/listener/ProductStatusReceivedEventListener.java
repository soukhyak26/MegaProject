package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.ProductStatusReceivedEvent;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.business.query.view.BusinessAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 29/4/16.
 */
@Component
public class ProductStatusReceivedEventListener {
    private final BusinessAccountViewRepository businessAccountViewRepository;

    @Autowired
    public ProductStatusReceivedEventListener(BusinessAccountViewRepository businessAccountViewRepository) {
        this.businessAccountViewRepository = businessAccountViewRepository;
    }

    @EventHandler
    public void on(ProductStatusReceivedEvent productStatusReceivedEvent) {
        double currentPurchasePrice = productStatusReceivedEvent.getCurrentPurchasePrice();
        //TODO : fix id for business account aggregate (must be year of provision)
        BusinessAccountView businessAccountView = businessAccountViewRepository.findById(BusinessAccountView.BA_VIEW_ID);
        businessAccountView.debitPurchaseCost(currentPurchasePrice);
    }
}
