package com.verifier.domains.payments.repository;

import com.verifier.domains.payments.view.PaymentSchemesView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 5/20/2017.
 */
public interface PaymentSchemesViewRepository extends CrudRepository<PaymentSchemesView, String> {
}
