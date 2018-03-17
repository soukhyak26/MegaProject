package com.verifier.domains.payments.repository;

import com.verifier.domains.payments.view.PaymentTransactionView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 5/18/2017.
 */
public interface PaymentTransactionViewRepository  extends CrudRepository<PaymentTransactionView, String> {
    List<PaymentTransactionView> findBySubscriptionId(String subscriptionId);

}
