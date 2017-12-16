package com.affaince.subscription.payments.event.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.payments.command.CorrectDuePaymentCommand;
import com.affaince.subscription.payments.event.DeliveryDestroyedEvent;
import com.affaince.subscription.payments.query.repository.DeliveryCostAccountViewRepository;
import com.affaince.subscription.payments.query.view.DeliveryCostAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
//TODO: NOT Authentic implementaiton.. needs to be corrected
@Component
public class DeliveryDestroyedEventListener {
    private final DeliveryCostAccountViewRepository deliveryCostAccountViewRepository;
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public DeliveryDestroyedEventListener(DeliveryCostAccountViewRepository deliveryCostAccountViewRepository, SubscriptionCommandGateway commandGateway) {
        this.deliveryCostAccountViewRepository = deliveryCostAccountViewRepository;
        this.commandGateway=commandGateway;
    }

    @EventHandler
    public void on(DeliveryDestroyedEvent event) throws Exception {
        List<DeliveryCostAccountView> deliveryCostAccountView = deliveryCostAccountViewRepository.findByDeliveryId_DeliveryId(event.getDeliveryId());
        deliveryCostAccountViewRepository.delete(deliveryCostAccountView);
        CorrectDuePaymentCommand command=new CorrectDuePaymentCommand(event.getSubscriptionId(),event.getDeletionDate());
        commandGateway.executeAsync(command);
    }
}
