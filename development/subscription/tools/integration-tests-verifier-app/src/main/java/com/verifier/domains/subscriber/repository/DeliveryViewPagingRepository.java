package com.verifier.domains.subscriber.repository;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.vo.CompositeDeliveryId;
import com.verifier.domains.subscriber.view.DeliveryView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public interface DeliveryViewPagingRepository extends PagingAndSortingRepository<DeliveryView, CompositeDeliveryId> {
    public Page<DeliveryView> findByDeliveryStatus(DeliveryStatus deliveryStatus, Pageable pageable);
}
