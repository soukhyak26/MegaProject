package com.verifier.domains.payments.repository;

import com.affaince.subscription.common.vo.CompositeDeliveryId;
import com.verifier.domains.payments.view.DeliveryCostAccountView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryCostAccountViewRepository extends CrudRepository<DeliveryCostAccountView, CompositeDeliveryId> {
    List<DeliveryCostAccountView> findByCompositeDeliveryId_SubscriptionIdOrderByDeliverySequenceAsc(String subscriptionId);
    List<DeliveryCostAccountView> findByCompositeDeliveryId_DeliveryId(String deliveryId);
    //TODO: introduce method to look for DeliveryCostAccountView by subscription id AND delivery id
}
