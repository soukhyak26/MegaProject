package com.affaince.subscription.business.event.listener;

import com.affaince.subscription.business.event.CommonExpenseDebitedEvent;
import com.affaince.subscription.business.query.repository.CommonExpenseAccountViewRepository;
import com.affaince.subscription.business.query.view.CommonExpenseAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 9/5/16.
 */
@Component
public class CommonExpenseDebitedEventListener {
    private final CommonExpenseAccountViewRepository commonExpenseAccountViewRepository;

    @Autowired
    public CommonExpenseDebitedEventListener(CommonExpenseAccountViewRepository commonExpenseAccountViewRepository) {
        this.commonExpenseAccountViewRepository = commonExpenseAccountViewRepository;
    }

    @EventHandler
    public void on(CommonExpenseDebitedEvent event) {
        CommonExpenseAccountView commonExpenseAccountView = commonExpenseAccountViewRepository.findOne(event.getYear());
        commonExpenseAccountView.debit(event.getAmountToDebit());
        commonExpenseAccountViewRepository.save(commonExpenseAccountView);
    }
}
