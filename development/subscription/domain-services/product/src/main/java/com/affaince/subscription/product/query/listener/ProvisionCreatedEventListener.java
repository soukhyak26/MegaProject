package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.product.command.event.ProvisionCreatedEvent;
import com.affaince.subscription.product.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.product.query.view.BusinessAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 12-11-2016.
 */
@Component
public class ProvisionCreatedEventListener {

    private final BusinessAccountViewRepository businessAccountViewRepository;

    @Autowired
    public ProvisionCreatedEventListener(BusinessAccountViewRepository businessAccountViewRepository) {
        this.businessAccountViewRepository = businessAccountViewRepository;
    }

    @EventHandler
    public void on(ProvisionCreatedEvent event) {

        final Integer currentYear = event.getProvisionDate().getYear();
        LocalDateTime startDate = event.getProvisionDate();
        LocalDateTime endDate = new LocalDateTime(currentYear,12,31,12,59,59);

        businessAccountViewRepository.save(new BusinessAccountView(currentYear, startDate, endDate,
                event.getDefaultPercentFixedExpensePerUnitPrice(),
                event.getDefaultPercentVariableExpensePerUnitPrice()));
    }
}
