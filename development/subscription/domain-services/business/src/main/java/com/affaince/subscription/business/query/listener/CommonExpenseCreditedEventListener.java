package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.CommonExpenseCreditedEvent;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.business.query.view.BusinessAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 9/5/16.
 */
@Component
public class CommonExpenseCreditedEventListener {
    private final BusinessAccountViewRepository businessAccountViewRepository;

    @Autowired
    public CommonExpenseCreditedEventListener(BusinessAccountViewRepository businessAccountViewRepository) {
        this.businessAccountViewRepository = businessAccountViewRepository;
    }

    @EventHandler
    public void on(CommonExpenseCreditedEvent event) {
        BusinessAccountView businessAccountView = businessAccountViewRepository.findById(event.getBusinessAccountId());
        businessAccountView.getCommonExpensesAccount().credit(event.getAmountToCredit());
        businessAccountViewRepository.save(businessAccountView);
    }
}
