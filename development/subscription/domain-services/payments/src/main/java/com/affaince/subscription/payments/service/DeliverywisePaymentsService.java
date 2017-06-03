package com.affaince.subscription.payments.service;

import com.affaince.subscription.payments.query.repository.DeliveryCostViewRepository;
import com.affaince.subscription.payments.query.repository.DeliveryDetailsViewRepository;
import com.affaince.subscription.payments.query.view.DeliveryCostView;
import com.affaince.subscription.payments.query.view.DeliveryDetailsView;
import com.affaince.subscription.payments.vo.DeliveredProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 5/28/2017.
 */
@Component
public class DeliverywisePaymentsService {
    private DeliveryCostViewRepository deliveryCostViewRepository;
    @Autowired
    public DeliverywisePaymentsService(DeliveryCostViewRepository deliveryCostViewRepository) {
        this.deliveryCostViewRepository = deliveryCostViewRepository;
    }

    public List<DeliveryCostView> findDeliveriesBySubscriptionId(String subscriptionId){
            return deliveryCostViewRepository.findBySubscriptionwiseDeliveryId_SubscriptionId(subscriptionId);
    }

}
