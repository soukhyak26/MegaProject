package com.affaince.subscription.subscriber.event.listener;

import com.affaince.subscription.subscriber.event.BenefitAddedEvent;
import com.affaince.subscription.subscriber.query.repository.BenefitViewRepository;
import com.affaince.subscription.subscriber.query.view.BenefitView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 25-10-2015.
 */
@Component
public class BenefitAddedEventListener {

    private final BenefitViewRepository repository;

    @Autowired
    public BenefitAddedEventListener(BenefitViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(BenefitAddedEvent event) {
        final BenefitView benefitView = new BenefitView(event.getBenefitId(), event.getBenefitEquation(),
                event.getBenefitEquationInJsonFormat(), event.getActivationStartTime(), event.getActivationEndTime());
        repository.save(benefitView);
    }
}
