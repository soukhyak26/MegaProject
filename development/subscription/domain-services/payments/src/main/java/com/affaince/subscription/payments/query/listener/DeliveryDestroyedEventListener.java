package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.payments.command.CorrectDuePaymentCommand;
import com.affaince.subscription.payments.command.event.DeliveryDestroyedEvent;
import com.affaince.subscription.payments.query.repository.DeliveryCostViewRepository;
import com.affaince.subscription.payments.query.repository.RefundTransactionsViewRepository;
import com.affaince.subscription.payments.query.repository.RefundViewRepository;
import com.affaince.subscription.payments.query.view.DeliveryCostView;
import com.affaince.subscription.payments.query.view.RefundTransactionsView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
//TODO: NOT Authentic implementaiton.. needs to be corrected
@Component
public class DeliveryDestroyedEventListener {
    private final DeliveryCostViewRepository deliveryCostViewRepository;
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public DeliveryDestroyedEventListener(DeliveryCostViewRepository deliveryCostViewRepository,SubscriptionCommandGateway commandGateway) {
        this.deliveryCostViewRepository = deliveryCostViewRepository;
        this.commandGateway=commandGateway;
    }

    @EventHandler
    public void on(DeliveryDestroyedEvent event) throws Exception {
        List<DeliveryCostView> deliveryCostView = deliveryCostViewRepository.findByDeliveryId_DeliveryId(event.getDeliveryId());
        deliveryCostViewRepository.save(deliveryCostView);
        CorrectDuePaymentCommand command=new CorrectDuePaymentCommand(event.getSubscriptionId(),event.getDeletionDate());
        commandGateway.executeAsync(command);
    }
}
