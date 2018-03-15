package com.verifier.domains.subscriber.repository;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.vo.DeliveryId;
import com.verifier.domains.subscriber.view.DeliveryView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public interface DeliveryViewRepository extends CrudRepository<DeliveryView, DeliveryId> {
    public List<DeliveryView> findByDeliveryStatus(DeliveryStatus deliveryStatus);
}
