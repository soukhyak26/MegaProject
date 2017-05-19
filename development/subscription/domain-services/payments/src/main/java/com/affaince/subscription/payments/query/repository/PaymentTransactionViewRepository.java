package com.affaince.subscription.payments.query.repository;

import com.affaince.subscription.payments.query.view.PaymentTransactionView;
import com.affaince.subscription.payments.vo.PaymentTransactionType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 5/18/2017.
 */
public interface PaymentTransactionViewRepository  extends CrudRepository<PaymentTransactionView, String> {
    List<PaymentTransactionView> findBySusbcriptionId(String subscriptionId);
}
