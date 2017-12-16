package com.affaince.subscription.payments.event.listener;

import com.affaince.subscription.common.vo.DeliveryId;
import com.affaince.subscription.payments.event.DeliveriesUpdatedWithCorrectedPaymentEvent;
import com.affaince.subscription.payments.query.repository.DeliveryCostAccountViewRepository;
import com.affaince.subscription.payments.query.repository.TotalReceivableCostAccountViewRepository;
import com.affaince.subscription.payments.query.repository.TotalSubscriptionCostAccountViewRepository;
import com.affaince.subscription.payments.query.view.DeliveryCostAccountView;
import com.affaince.subscription.payments.query.view.TotalReceivableCostAccountView;
import com.affaince.subscription.payments.query.view.TotalSubscriptionCostAccountView;
import com.affaince.subscription.payments.vo.ModifiedDeliveryContent;
import com.affaince.subscription.payments.vo.ModifiedSubscriptionContent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 7/17/2017.
 */
@Component
public class DeliveriesUpdatedWithCorrectedPaymentEventListener {
    private final TotalReceivableCostAccountViewRepository totalReceivableCostAccountViewRepository;
    private final TotalSubscriptionCostAccountViewRepository totalSubscriptionCostAccountViewRepository;
    private final DeliveryCostAccountViewRepository deliveryCostAccountViewRepository;
    @Autowired
    public DeliveriesUpdatedWithCorrectedPaymentEventListener(TotalReceivableCostAccountViewRepository totalReceivableCostAccountViewRepository, TotalSubscriptionCostAccountViewRepository totalSubscriptionCostAccountViewRepository, DeliveryCostAccountViewRepository deliveryCostAccountViewRepository) {
        this.totalReceivableCostAccountViewRepository = totalReceivableCostAccountViewRepository;
        this.totalSubscriptionCostAccountViewRepository = totalSubscriptionCostAccountViewRepository;
        this.deliveryCostAccountViewRepository = deliveryCostAccountViewRepository;
    }

    @EventHandler
    public void on(DeliveriesUpdatedWithCorrectedPaymentEvent event){
        ModifiedSubscriptionContent modifiedSubscriptionContent = event.getModifiedSubscriptionContent();
        TotalReceivableCostAccountView totalReceivableCostAccountView = totalReceivableCostAccountViewRepository.findOne(event.getSubscriptionId());
        TotalSubscriptionCostAccountView totalSubscriptionCostAccountView= totalSubscriptionCostAccountViewRepository.findOne(event.getSubscriptionId());
        totalReceivableCostAccountView.setOrOverride(modifiedSubscriptionContent.getModifiedTotalSubscriptionPayment());
        totalReceivableCostAccountViewRepository.save(totalReceivableCostAccountView);
        totalSubscriptionCostAccountView.setOrOverride(modifiedSubscriptionContent.getModifiedTotalSubscriptionPayment());
        totalSubscriptionCostAccountViewRepository.save(totalSubscriptionCostAccountView);

        List<ModifiedDeliveryContent> modifiedDeliveries = modifiedSubscriptionContent.getModifiedDeliveries();
        for (ModifiedDeliveryContent modifiedDeliveryContent : modifiedDeliveries) {
            DeliveryCostAccountView deliveryCostAccountView = deliveryCostAccountViewRepository.findOne(new DeliveryId(modifiedDeliveryContent.getDeliveryId(),event.getSubscriberId(),event.getSubscriptionId()));
            deliveryCostAccountView.setOrOverride(modifiedDeliveryContent.getCorrectedTotalPayment());
            deliveryCostAccountViewRepository.save(deliveryCostAccountView);
        }
    }
}
