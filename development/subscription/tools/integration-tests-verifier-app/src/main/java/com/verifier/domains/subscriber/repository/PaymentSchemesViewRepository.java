package com.verifier.domains.subscriber.repository;

import com.verifier.domains.subscriber.view.PaymentSchemesView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 5/20/2017.
 */
public interface PaymentSchemesViewRepository extends CrudRepository<PaymentSchemesView, String> {
}
