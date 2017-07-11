package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.payments.command.ApproveDeliveryDispatchCommand;
import com.affaince.subscription.payments.command.CorrectDuePaymentCommand;
import com.affaince.subscription.payments.command.event.DeliveryPreparedForDispatchEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 6/5/2017.
 */
public class DeliveryPreparedForDispatchEventListener {
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    @EventHandler
    public void on(DeliveryPreparedForDispatchEvent event) throws Exception {

        CorrectDuePaymentCommand command =
                new CorrectDuePaymentCommand(event.getSubscriptionId(), event.getDelivery().getDeliveryId(),
                        event.getDelivery().getStatus(), event.getDelivery().getDispatchDate(),
                        event.getDelivery().getDeliveryCharges(), event.getDelivery().getTotalDeliveryPrice());
        commandGateway.executeAsync(command);
        ApproveDeliveryDispatchCommand approveCommand= new ApproveDeliveryDispatchCommand(event.getSubscriptionId(), event.getDelivery().getDeliveryId(),event.getDelivery().getSequence());
        commandGateway.executeAsync(approveCommand);
    }

}
