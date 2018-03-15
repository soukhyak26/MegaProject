package com.verifier.domains.payments.repository;

import com.affaince.subscription.common.vo.DeliveryId;
import com.verifier.domains.payments.view.DeliveryDetailsView;
import com.verifier.domains.payments.vo.DeliverableProductDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 5/28/2017.
 */
public interface DeliveryDetailsViewRepository extends CrudRepository<DeliveryDetailsView,DeliveryId> {
    public List<DeliveryDetailsView> findBySubscriptionwiseDeliveryId_SubscriptionId(String subscriptionId);
    public List<DeliveryDetailsView> findBydeliverableProductDetailsIn(List<DeliverableProductDetail> products);
}
