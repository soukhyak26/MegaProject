package com.affaince.subscription.business.event.listener;

import com.affaince.subscription.business.event.BookingAmountCreditedEvent;
import com.affaince.subscription.business.query.repository.BookingAmountAccountViewRepository;
import com.affaince.subscription.business.query.view.BookingAmountAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 8/5/16.
 */
@Component
public class BookingAmountCreditedEventListener {
    private BookingAmountAccountViewRepository bookingAmountAccountViewRepository;
    @Autowired
    public BookingAmountCreditedEventListener(BookingAmountAccountViewRepository bookingAmountAccountViewRepository) {
        this.bookingAmountAccountViewRepository = bookingAmountAccountViewRepository;
    }

    @EventHandler
    public void on(BookingAmountCreditedEvent event) {
        BookingAmountAccountView bookingAmountAccountView = bookingAmountAccountViewRepository.findOne(event.getYear());
        bookingAmountAccountView.credit(event.getAmountToCredit());
        bookingAmountAccountViewRepository.save(bookingAmountAccountView);
    }
}
