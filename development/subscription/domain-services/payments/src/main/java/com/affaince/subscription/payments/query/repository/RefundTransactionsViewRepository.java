package com.affaince.subscription.payments.query.repository;

import com.affaince.subscription.common.vo.DeliveryId;
import com.affaince.subscription.payments.query.view.DeliveryDetailsView;
import com.affaince.subscription.payments.query.view.RefundTransactionsView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 7/13/2017.
 */
public interface RefundTransactionsViewRepository extends CrudRepository<RefundTransactionsView,String> {
}
