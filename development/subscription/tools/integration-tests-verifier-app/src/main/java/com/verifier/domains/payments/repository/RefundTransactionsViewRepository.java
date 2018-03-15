package com.verifier.domains.payments.repository;

import com.verifier.domains.payments.view.RefundTransactionsView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 7/13/2017.
 */
public interface RefundTransactionsViewRepository extends CrudRepository<RefundTransactionsView,String> {
}
