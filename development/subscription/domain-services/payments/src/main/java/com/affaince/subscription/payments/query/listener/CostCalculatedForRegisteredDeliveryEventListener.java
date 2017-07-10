package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.common.vo.DeliveryId;
import com.affaince.subscription.payments.command.event.CostCalculatedForRegisteredDeliveryEvent;
import com.affaince.subscription.payments.query.repository.DeliveryCostViewRepository;
import com.affaince.subscription.payments.query.repository.TotalReceivableCostAccountViewRepository;
import com.affaince.subscription.payments.query.repository.TotalSubscriptionCostAccountViewRepository;
import com.affaince.subscription.payments.query.view.DeliveryCostView;
import com.affaince.subscription.payments.query.view.TotalReceivableCostAccountView;
import com.affaince.subscription.payments.query.view.TotalSubscriptionCostAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CostCalculatedForRegisteredDeliveryEventListener {
    private final DeliveryCostViewRepository deliveryCostViewRepository;
    private final TotalReceivableCostAccountViewRepository totalReceivableCostAccountViewRepository;
    private final TotalSubscriptionCostAccountViewRepository totalSubscriptionCostAccountViewRepository;

    @Autowired
    public CostCalculatedForRegisteredDeliveryEventListener(DeliveryCostViewRepository deliveryCostViewRepository,
                                                            TotalReceivableCostAccountViewRepository totalReceivableCostAccountViewRepository,
                                                            TotalSubscriptionCostAccountViewRepository totalSubscriptionCostAccountViewRepository) {

        this.deliveryCostViewRepository = deliveryCostViewRepository;
        this.totalReceivableCostAccountViewRepository = totalReceivableCostAccountViewRepository;
        this.totalSubscriptionCostAccountViewRepository = totalSubscriptionCostAccountViewRepository;
    }

    @EventHandler
    public void on(CostCalculatedForRegisteredDeliveryEvent event) {
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
    }
}
