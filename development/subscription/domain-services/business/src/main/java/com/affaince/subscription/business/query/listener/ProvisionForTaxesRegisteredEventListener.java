package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.ProvisionForTaxesRegisteredEvent;
import com.affaince.subscription.business.query.repository.TaxesAccountTransactionsViewRepository;
import com.affaince.subscription.business.query.repository.TaxesAccountViewRepository;
import com.affaince.subscription.business.query.view.TaxesAccountTransactionsView;
import com.affaince.subscription.business.query.view.TaxesAccountView;
import com.affaince.subscription.business.vo.TransactionReasonCode;
import com.affaince.subscription.business.vo.TransactionType;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 22-01-2017.
 */
public class ProvisionForTaxesRegisteredEventListener {
    private TaxesAccountViewRepository taxesAccountViewRepository;
    private TaxesAccountTransactionsViewRepository taxesAccountTransactionsViewRepository;
    @Autowired
    public ProvisionForTaxesRegisteredEventListener(TaxesAccountViewRepository taxesAccountViewRepository, TaxesAccountTransactionsViewRepository taxesAccountTransactionsViewRepository) {
        this.taxesAccountViewRepository = taxesAccountViewRepository;
        this.taxesAccountTransactionsViewRepository = taxesAccountTransactionsViewRepository;
    }

    @EventHandler
    public void on (ProvisionForTaxesRegisteredEvent event){
        TaxesAccountView taxesAccountView= new TaxesAccountView(event.getId(),event.getProvisionForTaxes(),event.getProvisionForTaxes(),event.getStartDate().toLocalDateTime(LocalTime.now()),event.getEndDate().toLocalDateTime(LocalTime.MIDNIGHT));
        taxesAccountViewRepository.save(taxesAccountView);
        TaxesAccountTransactionsView taxesAccountTransactionsView= new TaxesAccountTransactionsView(event.getStartDate(),event.getProvisionForTaxes(), TransactionType.CREDIT, TransactionReasonCode.TAXES_PROVISION_REGISTERED);
        taxesAccountTransactionsViewRepository.save(taxesAccountTransactionsView);
    }
}
