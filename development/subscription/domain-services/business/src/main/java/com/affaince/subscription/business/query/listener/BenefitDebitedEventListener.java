package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.BenefitCreditedEvent;
import com.affaince.subscription.business.command.event.BenefitDebitedEvent;
import com.affaince.subscription.business.query.repository.BenefitAccountTransactionsViewRepository;
import com.affaince.subscription.business.query.repository.BenefitAccountViewRepository;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.business.query.view.BenefitAccountView;
import com.affaince.subscription.business.query.view.BenefitsAccountTransactionsView;
import com.affaince.subscription.business.query.view.BusinessAccountView;
import com.affaince.subscription.business.vo.TransactionReasonCode;
import com.affaince.subscription.business.vo.TransactionType;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BenefitDebitedEventListener {
    private final BenefitAccountViewRepository benefitAccountViewRepository;
    private final BenefitAccountTransactionsViewRepository benefitAccountTransactionsViewRepository;

    @Autowired
    public BenefitDebitedEventListener(BenefitAccountViewRepository benefitAccountViewRepository,BenefitAccountTransactionsViewRepository benefitAccountTransactionsViewRepository) {
        this.benefitAccountViewRepository = benefitAccountViewRepository;
        this.benefitAccountTransactionsViewRepository=benefitAccountTransactionsViewRepository;
    }

    @EventHandler
    public void on(BenefitDebitedEvent event) {
        BenefitAccountView benefitAccountView = benefitAccountViewRepository.findOne(event.getYear());
       // benefitAccountView.debit(event.getAmountToDebit());
        benefitAccountViewRepository.save(benefitAccountView);

        BenefitsAccountTransactionsView benefitsAccountTransactionsView= new BenefitsAccountTransactionsView(event.getContributorId(),event.getDateOfTransaction(),event.getAmountToDebit(), TransactionType.DEBIT, TransactionReasonCode.BENEFITS_PROVISION_CORRECTED);
        benefitAccountTransactionsViewRepository.save(benefitsAccountTransactionsView);
    }
}
