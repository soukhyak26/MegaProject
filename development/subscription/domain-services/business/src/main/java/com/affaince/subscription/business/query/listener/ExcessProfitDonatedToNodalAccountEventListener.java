package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.DonateExcessProfitForAProductToNodalAccountCommand;
import com.affaince.subscription.business.command.event.ExcessProfitDonatedToNodalAccountEvent;
import com.cloudera.org.joda.time.YearMonth;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 30-01-2017.
 */
@Component
public class ExcessProfitDonatedToNodalAccountEventListener {
    private SubscriptionCommandGateway commandGateway;

    @Autowired
    public ExcessProfitDonatedToNodalAccountEventListener(SubscriptionCommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void on (ExcessProfitDonatedToNodalAccountEvent event) throws Exception{
        DonateExcessProfitForAProductToNodalAccountCommand command= new DonateExcessProfitForAProductToNodalAccountCommand(YearMonth.now().getYear(),event.getProductId(),event.getExcessProfit());
        commandGateway.executeAsync(command);
    }
}