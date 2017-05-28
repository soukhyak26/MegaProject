package com.affaince.subscription.payments.service;

import com.affaince.subscription.payments.query.repository.DeliveryDetailsViewRepository;
import com.affaince.subscription.payments.query.view.DeliveryDetailsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 5/28/2017.
 */
@Component
public class DeliveriesDetailsService {
    private DeliveryDetailsViewRepository deliveryDetailsViewRepository;
    @Autowired
    public DeliveriesDetailsService(DeliveryDetailsViewRepository deliveryDetailsViewRepository) {
        this.deliveryDetailsViewRepository = deliveryDetailsViewRepository;
    }

    public List<DeliveryDetailsView> findDeliveriesBySubscriptionId(String subscriptionId){
            return deliveryDetailsViewRepository.findBySubscriptionwiseDeliveryId_SubscriptionId(subscriptionId);
    }
}
