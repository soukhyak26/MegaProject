package com.verifier.domains.subscriber.repository;

import com.verifier.domains.subscriber.view.DeliveryActualsView;
import com.verifier.domains.subscriber.vo.DeliveryActualsVersionId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 11/7/2017.
 */
public interface DeliveryActualsViewRepository extends CrudRepository<DeliveryActualsView,DeliveryActualsVersionId> {
    List<DeliveryActualsView> findAll();
    List<DeliveryActualsView> findByDeliveryActualsVersionId_WeightRangeMinAndDeliveryActualsVersionId_WeightRangeMax(double weightRangeMin, double weightRangeMax);
    List<DeliveryActualsView> findByDeliveryActualsVersionId_WeightRangeMinAndDeliveryActualsVersionId_WeightRangeMaxOrderByDeliveryActualsVersionId_DeliveryDateDesc(double weightRangeMin, double weightRangeMax);
}
