package com.affaince.subscription.payments.query.repository;

import com.affaince.subscription.common.vo.DeliveryId;
import com.affaince.subscription.payments.query.view.DeliveryCostView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryCostViewRepository extends CrudRepository<DeliveryCostView, DeliveryId> {
    List<DeliveryCostView> findByDeliveryId_SubscriptionId(String subscriptionId);
    List<DeliveryCostView> findByDeliveryId_DeliveryId(String deliveryId);
    //TODO: introduce method to look for DeliveryCostView by subscription id AND delivery id
}
