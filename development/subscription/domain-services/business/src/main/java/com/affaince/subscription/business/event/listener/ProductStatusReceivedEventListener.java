package com.affaince.subscription.business.event.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.ChangePurchaseProvisionPerProductCommand;
import com.affaince.subscription.business.event.ProductStatusReceivedEvent;
import com.affaince.subscription.business.query.repository.ProductViewRepository;
import com.affaince.subscription.business.query.view.ProductView;
import com.affaince.subscription.date.SysDate;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductStatusReceivedEventListener {
    @Autowired
    private SubscriptionCommandGateway commandGateway;
    @Autowired
    private ProductViewRepository productViewRepository;

    //@Autowired
    public ProductStatusReceivedEventListener() {

    }

    @EventHandler
    public void on(ProductStatusReceivedEvent event) throws Exception {
        /*double currentPurchasePrice = productStatusReceivedEvent.getCurrentPurchasePrice();
        BusinessAccountView businessAccountView = businessAccountViewRepository.findById(
                Integer.valueOf(productStatusReceivedEvent.getCurrentPriceDate().getYear()).toString());
        businessAccountView.debitPurchaseCost(currentPurchasePrice);*/
        ProductView productView = productViewRepository.findByProductId(event.getProductId());
        double registeredPurchasePricePerUnit= productView.getPurchasePrice();
        double newPurchasePricePerUnit=event.getCurrentPurchasePricePerUnit();
        //Currently we are ignoring currentStockInUnits
        if(newPurchasePricePerUnit>registeredPurchasePricePerUnit) {
            double costAdjustment=(newPurchasePricePerUnit-registeredPurchasePricePerUnit)*productView.getTotalAnticipatedSubscriptions();
            ChangePurchaseProvisionPerProductCommand command = new ChangePurchaseProvisionPerProductCommand(SysDate.now().getYear(),event.getProductId(),costAdjustment);
            commandGateway.executeAsync(command);
        }
        long totalAnticipatedSubscriptions=productView.getTotalAnticipatedSubscriptions();
        long currentStockInUnits=event.getCurrentStockInUnits();

        //TODO: WE NEED SOME RULE TO DEFINE HOW MUCH OF THE TOTAL ANTICIAPTED SALE SHOULD BE MAINTAINED IN STOCK
        //for now I am hardcoding it to 1/3
        double anticipatedSaleToCurrentStockRatio = totalAnticipatedSubscriptions/currentStockInUnits;
        if(anticipatedSaleToCurrentStockRatio>3.0 ){
            //TODO: THIS SHOULD BE MOVED to PRODUCT!!!!
        }
    }
}
