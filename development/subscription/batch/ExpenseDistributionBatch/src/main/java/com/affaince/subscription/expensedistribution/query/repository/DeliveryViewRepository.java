package com.affaince.subscription.expensedistribution.query.repository;

import com.affaince.subscription.expensedistribution.query.view.DeliveryView;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rsavaliya on 20/3/16.
 */
public interface DeliveryViewRepository extends CrudRepository<DeliveryView, String> {
    @Query (value = "{'deliveryItems.deliveryItemId' : ?0'}")
    List<DeliveryView> findDeliveryViewsByDeliveryItemsDeliveryItemId (String deliveryItemId);
}
