package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.CommonExpenseCreatedEvent;
import com.affaince.subscription.business.query.repository.CommonOperatingExpenseViewRepository;
import com.affaince.subscription.business.query.view.CommonOperatingExpenseView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

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
            final YearMonth monthOfYear =event.getMonthOfYear();
            final double commonExpenseAmount=event.getAmount();
            final CommonOperatingExpenseView commonOperatingExpenseView = new CommonOperatingExpenseView(event.getCommonOperatingExpenseId(),event.getExpenseHeader(),commonExpenseAmount ,monthOfYear );
            operatingExpenseViewRepository.save(commonOperatingExpenseView);
    }
}
