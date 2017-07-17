package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.common.vo.DeliveryId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.payments.command.event.DeliveryCostAccountCreditedEvent;
import com.affaince.subscription.payments.query.repository.DeliveryCostAccountViewRepository;
import com.affaince.subscription.payments.query.view.DeliveryCostAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 7/17/2017.
 */
@Component
public class DeliveryCostAccountCreditedEventListener {
    private final DeliveryCostAccountViewRepository deliveryCostAccountViewRepository;
    @Autowired
    public DeliveryCostAccountCreditedEventListener(DeliveryCostAccountViewRepository deliveryCostAccountViewRepository) {
        this.deliveryCostAccountViewRepository = deliveryCostAccountViewRepository;
    }

    @EventHandler
    public void on(DeliveryCostAccountCreditedEvent event){
        DeliveryCostAccountView deliveryCostAccountView = deliveryCostAccountViewRepository.findOne(new DeliveryId(event.getId(), event.getSubscriberId(), event.getSubscriptionId()));
        if (deliveryCostAccountView == null) {
            //TODO:Presently delivery sequence is hardcoded just as a placeholder. It should come from Delivery AggregateEvent coming from subscriber domain
            deliveryCostAccountView = new DeliveryCostAccountView(event.getId(), event.getSubscriberId(), event.getSubscriptionId(), event.getSequence(), event.getAmountToCredit(), SysDate.now());
        }else{
            deliveryCostAccountView.credit(event.getAmountToCredit());
        }
        deliveryCostAccountViewRepository.save(deliveryCostAccountView);
    }
}
