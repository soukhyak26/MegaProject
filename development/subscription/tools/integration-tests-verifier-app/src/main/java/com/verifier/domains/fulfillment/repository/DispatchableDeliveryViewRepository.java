package com.verifier.domains.fulfillment.repository;

import com.verifier.domains.fulfillment.view.DispatchableDeliveryView;
import com.verifier.domains.fulfillment.vo.DispatchableDeliveryId;
import org.springframework.data.repository.CrudRepository;

public interface DispatchableDeliveryViewRepository extends CrudRepository<DispatchableDeliveryView,DispatchableDeliveryId> {
}
