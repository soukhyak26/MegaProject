package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.OperatingExpenseReceivedCommand;
import com.affaince.subscription.business.command.event.OperatingExpenseReceivedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 8/5/16.
 */
@Component
public class OperatingExpenseReceivedEventListener {
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    //@Autowired
    public OperatingExpenseReceivedEventListener() {

    }

    @EventHandler
    public void on(OperatingExpenseReceivedEvent event) throws Exception {
        OperatingExpenseReceivedCommand command = new OperatingExpenseReceivedCommand(event.getExpenseType(), event.getExpenseId(), event.getExpenseHeader(), event.getExpenseAmount(), event.getPeriod(), event.getForMonth(), event.getForYear());
        commandGateway.executeAsync(command);
    }
}
