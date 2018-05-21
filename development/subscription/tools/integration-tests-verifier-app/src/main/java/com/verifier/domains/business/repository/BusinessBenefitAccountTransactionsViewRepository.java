package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.BenefitsAccountTransactionsView;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 22-01-2017.
 */
public interface BusinessBenefitAccountTransactionsViewRepository extends CrudRepository<BenefitsAccountTransactionsView, Long> {
    List<BenefitsAccountTransactionsView> findByTimeOfTransaction(LocalDateTime dateOfTransaction);
}
