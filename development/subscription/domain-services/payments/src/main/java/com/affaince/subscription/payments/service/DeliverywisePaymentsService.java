package com.affaince.subscription.payments.service;

import com.affaince.subscription.payments.query.repository.DeliveryCostAccountViewRepository;
import com.affaince.subscription.payments.query.view.DeliveryCostAccountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 5/28/2017.
 */
@Component
public class DeliverywisePaymentsService {
    private DeliveryCostAccountViewRepository deliveryCostAccountViewRepository;
    @Autowired
    public DeliverywisePaymentsService(DeliveryCostAccountViewRepository deliveryCostAccountViewRepository) {
        this.deliveryCostAccountViewRepository = deliveryCostAccountViewRepository;
    }

    public List<DeliveryCostAccountView> findDeliveriesBySubscriptionId(String subscriptionId){
            return deliveryCostAccountViewRepository.findByDeliveryId_SubscriptionId(subscriptionId);
    }

}
