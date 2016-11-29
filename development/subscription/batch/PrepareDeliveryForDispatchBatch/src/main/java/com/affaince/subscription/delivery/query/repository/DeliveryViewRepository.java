package com.affaince.subscription.delivery.query.repository;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.delivery.query.view.DeliveryView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public interface DeliveryViewRepository extends CrudRepository<DeliveryView, String> {

    List<DeliveryView> findByStatusAndDeliveryDateBetween(DeliveryStatus deliveryStatus,
                                                          LocalDate startDate,
                                                          LocalDate endDate);
}
