package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.ProfitAccountTransactionView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 03-02-2017.
 */
public interface BusinessProfitAccountTransactionsViewRepository extends CrudRepository<ProfitAccountTransactionView,Long>{
    List<ProfitAccountTransactionView> findByDateOfTransaction(LocalDate dateOfTransaction);
}
