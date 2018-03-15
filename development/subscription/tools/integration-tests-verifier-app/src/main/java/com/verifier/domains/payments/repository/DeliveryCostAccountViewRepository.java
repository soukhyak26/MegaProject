package com.verifier.domains.payments.repository;

import com.affaince.subscription.common.vo.DeliveryId;
import com.verifier.domains.payments.view.DeliveryCostAccountView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryCostAccountViewRepository extends CrudRepository<DeliveryCostAccountView, DeliveryId> {
    List<DeliveryCostAccountView> findByDeliveryId_SubscriptionId(String subscriptionId);
    List<DeliveryCostAccountView> findByDeliveryId_DeliveryId(String deliveryId);
    //TODO: introduce method to look for DeliveryCostAccountView by subscription id AND delivery id
}
