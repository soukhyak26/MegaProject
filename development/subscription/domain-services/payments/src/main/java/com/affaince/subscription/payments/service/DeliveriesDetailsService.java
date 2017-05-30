package com.affaince.subscription.payments.service;

import com.affaince.subscription.payments.query.repository.DeliveryDetailsViewRepository;
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
public class DeliveriesDetailsService {
    private DeliveryDetailsViewRepository deliveryDetailsViewRepository;
    @Autowired
    public DeliveriesDetailsService(DeliveryDetailsViewRepository deliveryDetailsViewRepository) {
        this.deliveryDetailsViewRepository = deliveryDetailsViewRepository;
    }

    public List<DeliveryDetailsView> findDeliveriesBySubscriptionId(String subscriptionId){
            return deliveryDetailsViewRepository.findBySubscriptionwiseDeliveryId_SubscriptionId(subscriptionId);
    }

    public List<DeliveryDetailsView> findDeliveriesContainingProduct(String productId){
        DeliveredProductDetail product= new DeliveredProductDetail(productId);
        List<DeliveredProductDetail> products= new ArrayList<>();
        products.add(product);
        return deliveryDetailsViewRepository.findByDeliveredProductDetailsIn(products);
    }
}
