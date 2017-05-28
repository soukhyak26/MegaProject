package com.affaince.subscription.payments.query.repository;

import com.affaince.subscription.payments.query.view.DeliveryDetailsView;
import com.affaince.subscription.payments.vo.SubscriptionwiseDeliveryId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 5/28/2017.
 */
public interface DeliveryDetailsViewRepository extends CrudRepository<DeliveryDetailsView,SubscriptionwiseDeliveryId> {
    public List<DeliveryDetailsView> findBySubscriptionwiseDeliveryId_SubscriptionId(String subscriptionId);
}
