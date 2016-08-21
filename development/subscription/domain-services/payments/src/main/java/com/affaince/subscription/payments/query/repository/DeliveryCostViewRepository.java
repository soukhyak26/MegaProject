package com.affaince.subscription.payments.query.repository;

import com.affaince.subscription.payments.query.view.DeliveryCostView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by anayonkar on 21/8/16.
 */
public interface DeliveryCostViewRepository extends CrudRepository<DeliveryCostView, String> {
    DeliveryCostView findByDeliveryId(String id);
}
