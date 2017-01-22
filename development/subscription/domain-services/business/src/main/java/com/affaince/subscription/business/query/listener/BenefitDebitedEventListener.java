package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.BenefitCreditedEvent;
import com.affaince.subscription.business.command.event.BenefitDebitedEvent;
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
public class BenefitDebitedEventListener {
    private final BenefitAccountViewRepository benefitAccountViewRepository;

    @Autowired
    public BenefitDebitedEventListener(BenefitAccountViewRepository benefitAccountViewRepository) {
        this.benefitAccountViewRepository = benefitAccountViewRepository;
    }

    @EventHandler
    public void on(BenefitDebitedEvent event) {
        BenefitAccountView benefitAccountView = benefitAccountViewRepository.findOne(event.getYear());
       // benefitAccountView.debit(event.getAmountToDebit());
        benefitAccountViewRepository.save(benefitAccountView);
    }
}
