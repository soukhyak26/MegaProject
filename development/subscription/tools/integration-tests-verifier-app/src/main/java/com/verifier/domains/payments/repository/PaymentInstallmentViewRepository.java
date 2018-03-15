package com.verifier.domains.payments.repository;

import com.verifier.domains.payments.view.PaymentInstallmentView;
import org.springframework.data.repository.CrudRepository;

public interface PaymentInstallmentViewRepository extends CrudRepository <PaymentInstallmentView, String> {
}
