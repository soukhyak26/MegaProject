package com.affaince.subscription.business.event.listener;

import com.affaince.subscription.business.event.ProvisionForLossesRegisteredEvent;
import com.affaince.subscription.business.query.repository.LossesAccountTransactionsViewRepository;
import com.affaince.subscription.business.query.repository.LossesAccountViewRepository;
import com.affaince.subscription.business.query.view.LossesAccountTransactionsView;
import com.affaince.subscription.business.query.view.LossesAccountView;
import com.affaince.subscription.business.vo.TransactionReasonCode;
import com.affaince.subscription.business.vo.TransactionType;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 22-01-2017.
 */
public class ProvisionForLossesRegisteredEventListener {
    private LossesAccountViewRepository lossesAccountViewRepository;
    private LossesAccountTransactionsViewRepository lossesAccountTransactionsViewRepository;

    @Autowired
    public ProvisionForLossesRegisteredEventListener(LossesAccountViewRepository lossesAccountViewRepository, LossesAccountTransactionsViewRepository lossesAccountTransactionsViewRepository) {
        this.lossesAccountViewRepository = lossesAccountViewRepository;
        this.lossesAccountTransactionsViewRepository = lossesAccountTransactionsViewRepository;
    }

    @EventHandler
    public void on(ProvisionForLossesRegisteredEvent event){
        LossesAccountView lossesAccountView= new LossesAccountView(event.getId(),event.getProvisionForLosses(),event.getProvisionForLosses(),event.getStartDate().toLocalDateTime(LocalTime.now()),event.getEndDate().toLocalDateTime(LocalTime.MIDNIGHT));
        lossesAccountViewRepository.save(lossesAccountView);
        LossesAccountTransactionsView lossesAccountTransactionsView= new LossesAccountTransactionsView(event.getStartDate(),event.getProvisionForLosses(), TransactionType.CREDIT, TransactionReasonCode.LOSS_PROVISION_REGISTERED);
        lossesAccountTransactionsViewRepository.save(lossesAccountTransactionsView);
    }
}
