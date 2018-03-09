package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.CommonExpensesTransactionsView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 22-01-2017.
 */
public interface CommonExpensesTransactionsViewRepository extends CrudRepository<CommonExpensesTransactionsView, Long> {
    List<CommonExpensesTransactionsView> findByDateOfTransaction(LocalDate dateOfTransaction);
}
