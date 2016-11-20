package com.affaince.subscription.payments.query.repository;

import com.affaince.subscription.payments.query.view.DeliveryCostView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by anayonkar on 21/8/16.
 */
public interface DeliveryCostViewRepository extends CrudRepository<DeliveryCostView, String> {
    DeliveryCostView findByDeliveryId(String deliveryId);
    List<DeliveryCostView> findBySubscriptionId(String subscriptionId);
    //TODO: introduce method to look for DeliveryCostView by subscription id AND delivery id
}
