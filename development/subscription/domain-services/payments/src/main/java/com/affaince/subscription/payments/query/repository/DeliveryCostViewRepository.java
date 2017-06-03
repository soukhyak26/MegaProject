package com.affaince.subscription.payments.query.repository;

import com.affaince.subscription.payments.query.view.DeliveryCostView;
import com.affaince.subscription.payments.vo.SubscriptionwiseDeliveryId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryCostViewRepository extends CrudRepository<DeliveryCostView, SubscriptionwiseDeliveryId> {
    List<DeliveryCostView> findBySubscriptionwiseDeliveryId_SubscriptionId(String subscriptionId);
    List<DeliveryCostView> findBySubscriptionwiseDeliveryId_DeliveryId(String deliveryId);
    //TODO: introduce method to look for DeliveryCostView by subscription id AND delivery id
}
