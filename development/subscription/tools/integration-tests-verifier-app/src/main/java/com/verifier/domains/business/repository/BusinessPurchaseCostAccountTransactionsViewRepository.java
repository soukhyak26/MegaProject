package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.PurchaseCostAccountTransactionsView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 22-01-2017.
 */
public interface BusinessPurchaseCostAccountTransactionsViewRepository extends CrudRepository<PurchaseCostAccountTransactionsView, Long> {
    List<PurchaseCostAccountTransactionsView> findByDateOfTransaction(LocalDate dateOfTransaction);
}
