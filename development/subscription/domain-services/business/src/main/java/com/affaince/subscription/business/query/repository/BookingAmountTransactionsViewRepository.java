package com.affaince.subscription.business.query.repository;

import com.affaince.subscription.business.query.view.BookingAmountTransactionsView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 22-01-2017.
 */
public interface BookingAmountTransactionsViewRepository extends CrudRepository<BookingAmountTransactionsView, Long> {
}