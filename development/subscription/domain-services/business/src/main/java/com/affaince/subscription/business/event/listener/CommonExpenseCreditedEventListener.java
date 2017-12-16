package com.affaince.subscription.business.event.listener;

import com.affaince.subscription.business.event.CommonExpenseCreditedEvent;
import com.affaince.subscription.business.query.repository.CommonExpenseAccountViewRepository;
import com.affaince.subscription.business.query.view.CommonExpenseAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 9/5/16.
 */
@Component
public class CommonExpenseCreditedEventListener {
    private final CommonExpenseAccountViewRepository commonExpenseAccountViewRepository;

    @Autowired
    public CommonExpenseCreditedEventListener(CommonExpenseAccountViewRepository commonExpenseAccountViewRepository) {
        this.commonExpenseAccountViewRepository = commonExpenseAccountViewRepository;
    }

    @EventHandler
    public void on(CommonExpenseCreditedEvent event) {
        CommonExpenseAccountView commonExpenseAccountView = commonExpenseAccountViewRepository.findOne(event.getYear());
        commonExpenseAccountView.credit(event.getAmountToCredit());
        commonExpenseAccountViewRepository.save(commonExpenseAccountView);
    }
}
