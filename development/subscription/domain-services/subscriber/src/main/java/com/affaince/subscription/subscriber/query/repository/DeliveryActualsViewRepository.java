package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.subscriber.query.view.DeliveryActualsView;
import com.affaince.subscription.subscriber.vo.DeliveryActualsVersionId;
import com.affaince.subscription.subscriber.vo.DeliveryForecastVersionId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 11/7/2017.
 */
public interface DeliveryActualsViewRepository extends CrudRepository<DeliveryActualsView,DeliveryActualsVersionId> {
    List<DeliveryActualsView> findByDeliveryActualsVersionId_WeightRangeMinAndDeliveryActualsVersionId_weightRangeMax(double weightRangeMin, double weightRangeMax);
    List<DeliveryActualsView> findByDeliveryActualsVersionId_WeightRangeMinAndDeliveryActualsVersionId_weightRangeMaxDeliveryActualsVersionId_DeliveryDateDesc(double weightRangeMin, double weightRangeMax);
}
