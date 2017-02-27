package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.AddProductContributionToRevenueAndProfitCommand;
import com.affaince.subscription.business.command.event.ProductContributionToPurchaseExpenseRevenueAndProfitAddedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 29-01-2017.
 */
@Component
public class ProductContributionToRevenueAndProfitAddedEventListener {
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public ProductContributionToRevenueAndProfitAddedEventListener(SubscriptionCommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void on(ProductContributionToPurchaseExpenseRevenueAndProfitAddedEvent event) throws Exception {
        AddProductContributionToRevenueAndProfitCommand command = new AddProductContributionToRevenueAndProfitCommand(event.getProductId(), YearMonth.now().getYear(),event.getPurchaseCostContribution(),event.getRevenueContribution(),event.getProfitContribution());
        commandGateway.executeAsync(command);
    }
}
