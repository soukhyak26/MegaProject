package com.affaince.subscription.benefits.event.listener;

import com.affaince.subscription.benefits.event.BenefitAddedEvent;
import com.affaince.subscription.benefits.query.repository.BenefitViewRepository;
import com.affaince.subscription.benefits.query.view.BenefitView;
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
