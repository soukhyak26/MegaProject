package com.verifier.domains.payments.repository;

import com.verifier.domains.payments.view.PaymentSchemesView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 5/20/2017.
 */
public interface PaymentsPaymentSchemesViewRepository extends CrudRepository<PaymentSchemesView, String> {
    public List<PaymentSchemesView> findAll();
    public List<PaymentSchemesView> findBySchemeEndDateBefore(LocalDate date);
}
