package com.affaince.subscription.business.event.listener;

import com.affaince.subscription.business.event.BookingAmountDebitedEvent;
import com.affaince.subscription.business.query.repository.BookingAmountAccountViewRepository;
import com.affaince.subscription.business.query.repository.BookingAmountTransactionsViewRepository;
import com.affaince.subscription.business.query.view.BookingAmountAccountView;
import com.affaince.subscription.business.query.view.BookingAmountTransactionsView;
import com.affaince.subscription.business.vo.TransactionReasonCode;
import com.affaince.subscription.business.vo.TransactionType;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 8/5/16.
 */
@Component
public class BookingAmountDebitedEventListener {
    private BookingAmountAccountViewRepository bookingAmountAccountViewRepository;
    private BookingAmountTransactionsViewRepository bookingAmountTransactionsViewRepository;
    @Autowired
    public BookingAmountDebitedEventListener(BookingAmountAccountViewRepository bookingAmountAccountViewRepository,BookingAmountTransactionsViewRepository bookingAmountTransactionsViewRepository) {
        this.bookingAmountAccountViewRepository = bookingAmountAccountViewRepository;
        this.bookingAmountTransactionsViewRepository=bookingAmountTransactionsViewRepository;
    }

    @EventHandler
    public void on(BookingAmountDebitedEvent event) {
        BookingAmountAccountView bookingAmountAccountView = bookingAmountAccountViewRepository.findOne(event.getYear());
        bookingAmountAccountView.debit(event.getAmountToDebit());
        bookingAmountAccountViewRepository.save(bookingAmountAccountView);
        BookingAmountTransactionsView bookingAmountTransactionsView= new BookingAmountTransactionsView(event.getContributorId(),event.getDateOfTransaction(),event.getAmountToDebit(), TransactionType.DEBIT, TransactionReasonCode.ADVANCE_PAYMENT_ADJUSTED);
        bookingAmountTransactionsViewRepository.save(bookingAmountTransactionsView);
    }
}
