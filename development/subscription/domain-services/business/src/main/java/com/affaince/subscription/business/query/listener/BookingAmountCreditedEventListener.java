package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.BookingAmountCreditedEvent;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.business.query.view.BusinessAccountView;
import com.affaince.subscription.date.SysDate;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 8/5/16.
 */
@Component
public class BookingAmountCreditedEventListener {
    private final BusinessAccountViewRepository businessAccountViewRepository;

    @Autowired
    public BookingAmountCreditedEventListener(BusinessAccountViewRepository businessAccountViewRepository) {
        this.businessAccountViewRepository = businessAccountViewRepository;
    }

    @EventHandler
    public void on(BookingAmountCreditedEvent event) {
        BusinessAccountView businessAccountView = businessAccountViewRepository.findById(Integer.valueOf(SysDate.now().getYear()).toString());
        businessAccountView.getBookingAmountAccount().credit(event.getAmountToCredit());
        businessAccountViewRepository.save(businessAccountView);
    }
}
