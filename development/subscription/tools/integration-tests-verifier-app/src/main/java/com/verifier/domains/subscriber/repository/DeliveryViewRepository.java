package com.verifier.domains.subscriber.repository;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.vo.DeliveryId;
import com.verifier.domains.subscriber.view.DeliveryView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public interface DeliveryViewRepository extends CrudRepository<DeliveryView, DeliveryId> {
    public List<DeliveryView> findAll();
    public List<DeliveryView> findByDeliveryId_SubscriberIdAndDeliveryId_SubscriptionIdAndDeliveryStatus(String subscriberId,String subscriptionId,DeliveryStatus deliveryStatus);
    public List<DeliveryView> findByDeliveryId_SubscriberIdAndDeliveryId_SubscriptionIdAndDeliveryStatusNot(String subscriberId,String subscriptionId,DeliveryStatus deliveryStatus);
    public List<DeliveryView> findByDeliveryId_SubscriberIdAndDeliveryId_SubscriptionIdAndDeliveryStatusOrderByDeliveryDateDesc(String subscriberId,String subscriptionId,DeliveryStatus deliveryStatus);
    public List<DeliveryView> findByDeliveryId_SubscriberIdAndDeliveryId_SubscriptionIdAndDeliveryDateAndDeliveryStatus(String subscriberId, String subscriptionId, LocalDate deliveryDate, DeliveryStatus deliveryStatus);
    public List<DeliveryView> findByDeliveryStatus(DeliveryStatus deliveryStatus);
}
