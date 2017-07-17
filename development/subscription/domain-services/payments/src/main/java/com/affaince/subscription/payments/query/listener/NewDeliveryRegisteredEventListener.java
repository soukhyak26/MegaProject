package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.vo.DeliveryId;
import com.affaince.subscription.payments.command.CorrectDuePaymentCommand;
import com.affaince.subscription.payments.command.event.NewDeliveryRegisteredEvent;
import com.affaince.subscription.payments.query.repository.DeliveryCostViewRepository;
import com.affaince.subscription.payments.query.repository.TotalReceivableCostAccountViewRepository;
import com.affaince.subscription.payments.query.repository.TotalSubscriptionCostAccountViewRepository;
import com.affaince.subscription.payments.query.view.DeliveryCostView;
import com.affaince.subscription.payments.query.view.TotalReceivableCostAccountView;
import com.affaince.subscription.payments.query.view.TotalSubscriptionCostAccountView;
import org.apache.activemq.broker.region.Subscription;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewDeliveryRegisteredEventListener {
    private final DeliveryCostViewRepository deliveryCostViewRepository;
    private final TotalReceivableCostAccountViewRepository totalReceivableCostAccountViewRepository;
    private final TotalSubscriptionCostAccountViewRepository totalSubscriptionCostAccountViewRepository;
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public NewDeliveryRegisteredEventListener(DeliveryCostViewRepository deliveryCostViewRepository,
                                              TotalReceivableCostAccountViewRepository totalReceivableCostAccountViewRepository,
                                              TotalSubscriptionCostAccountViewRepository totalSubscriptionCostAccountViewRepository,
                                              SubscriptionCommandGateway commandGateway) {

        this.deliveryCostViewRepository = deliveryCostViewRepository;
        this.totalReceivableCostAccountViewRepository = totalReceivableCostAccountViewRepository;
        this.totalSubscriptionCostAccountViewRepository = totalSubscriptionCostAccountViewRepository;
        this.commandGateway=commandGateway;
    }

    @EventHandler
    public void on(NewDeliveryRegisteredEvent event) throws Exception {
        DeliveryCostView deliveryCostView = deliveryCostViewRepository.findOne(new DeliveryId(event.getDeliveryId(), event.getSubscriberId(), event.getSubscriptionId()));
        if (deliveryCostView == null) {
            //TODO:Presently delivery sequence is hardcoded just as a placeholder. It should come from Delivery AggregateEvent coming from subscriber domain
            deliveryCostView = new DeliveryCostView(event.getDeliveryId(), event.getSubscriberId(), event.getSubscriptionId(), event.getSequence(), event.getTotalDeliveryCost(), event.getDeliveryDate());
        }
        deliveryCostViewRepository.save(deliveryCostView);
        TotalReceivableCostAccountView totalReceivableCostAccountView= totalReceivableCostAccountViewRepository.findOne(event.getSubscriptionId());
        if(null== totalReceivableCostAccountView){
            totalReceivableCostAccountView= new TotalReceivableCostAccountView(event.getSubscriptionId(),event.getTotalDeliveryCost());
        }else {
            totalReceivableCostAccountView.credit(event.getTotalDeliveryCost());
        }

        //TODO:After adding reward points the total receivable cost should automatically adjust after deducting amount equivalent to reward points
        totalReceivableCostAccountView.addToRewardPoints(event.getRewardPoints());
        totalReceivableCostAccountViewRepository.save(totalReceivableCostAccountView);
        TotalSubscriptionCostAccountView totalSubscriptionCostAccountView= totalSubscriptionCostAccountViewRepository.findOne(event.getSubscriptionId());
        if(null== totalSubscriptionCostAccountView){
            totalSubscriptionCostAccountView= new TotalSubscriptionCostAccountView(event.getSubscriptionId(),event.getTotalDeliveryCost());
        }else{
            totalSubscriptionCostAccountView.credit(event.getTotalDeliveryCost());
        }
        totalSubscriptionCostAccountViewRepository.save(totalSubscriptionCostAccountView);

        CorrectDuePaymentCommand command=new CorrectDuePaymentCommand(event.getSubscriptionId(),event.getDeliveryCreationDate());
        commandGateway.executeAsync(command);

    }
}
