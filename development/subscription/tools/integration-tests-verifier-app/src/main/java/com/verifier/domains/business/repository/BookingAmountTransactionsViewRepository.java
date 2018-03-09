package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.BookingAmountTransactionsView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 22-01-2017.
 */
public interface BookingAmountTransactionsViewRepository extends CrudRepository<BookingAmountTransactionsView, Long> {
    List<BookingAmountTransactionsView> findByDateOfTransaction(LocalDate dateOfTransaction);
}
