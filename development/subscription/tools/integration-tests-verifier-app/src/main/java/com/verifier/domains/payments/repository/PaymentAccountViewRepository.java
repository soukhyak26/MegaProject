package com.verifier.domains.payments.repository;

import com.verifier.domains.payments.view.PaymentAccountView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 7/9/2017.
 */
public interface PaymentAccountViewRepository extends CrudRepository<PaymentAccountView,String> {
}
