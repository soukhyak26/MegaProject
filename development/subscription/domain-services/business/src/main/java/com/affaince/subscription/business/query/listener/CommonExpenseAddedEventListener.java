package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.CommonExpenseCreatedEvent;
import com.affaince.subscription.business.query.repository.CommonOperatingExpenseViewRepository;
import com.affaince.subscription.business.query.view.CommonOperatingExpenseView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
@Component
public class CommonExpenseAddedEventListener {

    private final CommonOperatingExpenseViewRepository operatingExpenseViewRepository;

    @Autowired
    public CommonExpenseAddedEventListener(CommonOperatingExpenseViewRepository operatingExpenseViewRepository) {
        this.operatingExpenseViewRepository = operatingExpenseViewRepository;
    }

    @EventHandler
    public void on(CommonExpenseCreatedEvent event) {
        final CommonOperatingExpenseView commonOperatingExpenseView = new CommonOperatingExpenseView(event.getCommonOperatingExpenseId(), event.getExpenseHeader(), event.getAmount(), event.getMonthOfYear());
        operatingExpenseViewRepository.save(commonOperatingExpenseView);
    }
}
