package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.common.vo.DeliveryId;
import com.affaince.subscription.subscriber.query.view.DeliveryView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public interface DeliveryViewRepository extends CrudRepository<DeliveryView, DeliveryId> {
}
