package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.CommonOperatingExpenseAddedEvent;
import com.affaince.subscription.subscriber.query.repository.CommonOperatingExpenseViewRepository;
import com.affaince.subscription.subscriber.query.view.CommonOperatingExpenseView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
@Component
public class CommonOperatingExpenseAddedEventListener {

    private final CommonOperatingExpenseViewRepository operatingExpenseViewRepository;

    @Autowired
    public CommonOperatingExpenseAddedEventListener(CommonOperatingExpenseViewRepository operatingExpenseViewRepository) {
        this.operatingExpenseViewRepository = operatingExpenseViewRepository;
    }

    @EventHandler
    public void on(CommonOperatingExpenseAddedEvent event) {
        final CommonOperatingExpenseView commonOperatingExpenseView = new CommonOperatingExpenseView(
                event.getId(), event.getExpenseHeader(), event.getAmount(), event.getPeriod()
        );
        operatingExpenseViewRepository.save(commonOperatingExpenseView);
    }
}
