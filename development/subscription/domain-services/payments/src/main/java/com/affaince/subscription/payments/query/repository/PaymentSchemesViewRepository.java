package com.affaince.subscription.payments.query.repository;

import com.affaince.subscription.payments.query.view.PaymentSchemesView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 5/20/2017.
 */
public interface PaymentSchemesViewRepository extends CrudRepository<PaymentSchemesView, String> {
}
