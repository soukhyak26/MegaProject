package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.product.registration.command.event.CommonExpenseTypeAddedEvent;
import com.affaince.subscription.product.registration.query.repository.CommonOperatingExpenseViewRepository;
import com.affaince.subscription.product.registration.query.view.CommonOperatingExpenseView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
@Component
public class CommonExpenseTypeAddedEventListener {

    private final CommonOperatingExpenseViewRepository operatingExpenseViewRepository;

    @Autowired
    public CommonExpenseTypeAddedEventListener(CommonOperatingExpenseViewRepository operatingExpenseViewRepository) {
        this.operatingExpenseViewRepository = operatingExpenseViewRepository;
    }

    @EventHandler
    public void on(CommonExpenseTypeAddedEvent event) {
        final CommonOperatingExpenseView commonOperatingExpenseView = new CommonOperatingExpenseView(event.getCommonOperatingExpenseId(), event.getExpenseHeader(), event.getAmount(), event.getPeriod(), event.getMonthOfYear());
        operatingExpenseViewRepository.save(commonOperatingExpenseView);
    }
}
