package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.OthersAccountTransactionsView;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 22-01-2017.
 */
public interface BusinessOthersAccountTransactionsViewRepository extends CrudRepository<OthersAccountTransactionsView, Long> {
    public List<OthersAccountTransactionsView> findByTimeOfTransaction(LocalDateTime transactionDate);
}
