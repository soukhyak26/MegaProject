package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.BenefitCreditedEvent;
import com.affaince.subscription.business.query.repository.BenefitAccountViewRepository;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.business.query.view.BenefitAccountView;
import com.affaince.subscription.business.query.view.BusinessAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 8/5/16.
 */
@Component
public class BenefitCreditedEventListener {
    private final BenefitAccountViewRepository benefitAccountViewRepository;

    @Autowired
    public BenefitCreditedEventListener(BenefitAccountViewRepository benefitAccountViewRepository) {
        this.benefitAccountViewRepository = benefitAccountViewRepository;
    }

    @EventHandler
    public void on(BenefitCreditedEvent event) {
        BenefitAccountView benefitAccountView = benefitAccountViewRepository.findOne(event.getYear());
        benefitAccountView.credit(event.getAmountToCredit());
        benefitAccountViewRepository.save(benefitAccountView);
    }
}
