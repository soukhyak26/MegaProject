package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.ProvisionForNodalRegisteredEvent;
import com.affaince.subscription.business.command.event.ProvisionForOtherCostRegisteredEvent;
import com.affaince.subscription.business.query.repository.OthersAccountTransactionsViewRepository;
import com.affaince.subscription.business.query.repository.OthersAccountViewRepository;
import com.affaince.subscription.business.query.view.OthersAccountTransactionsView;
import com.affaince.subscription.business.query.view.OthersAccountView;
import com.affaince.subscription.business.vo.TransactionReasonCode;
import com.affaince.subscription.business.vo.TransactionType;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 22-01-2017.
 */
public class ProvisionForOtherCostRegisteredEventListener {
    private OthersAccountViewRepository othersAccountViewRepository;
    private OthersAccountTransactionsViewRepository othersAccountTransactionsViewRepository;

    @Autowired
    public ProvisionForOtherCostRegisteredEventListener(OthersAccountViewRepository othersAccountViewRepository, OthersAccountTransactionsViewRepository othersAccountTransactionsViewRepository) {
        this.othersAccountViewRepository = othersAccountViewRepository;
        this.othersAccountTransactionsViewRepository = othersAccountTransactionsViewRepository;
    }

    @EventHandler
    public void On(ProvisionForOtherCostRegisteredEvent event){
        OthersAccountView othersAccountView= new OthersAccountView(event.getId(),event.getProvisionForOtherCost(),event.getProvisionForOtherCost(),event.getStartDate().toLocalDateTime(LocalTime.now()),event.getEndDate().toLocalDateTime(LocalTime.MIDNIGHT));
        othersAccountViewRepository.save(othersAccountView);
        OthersAccountTransactionsView othersAccountTransactionsView= new OthersAccountTransactionsView(event.getStartDate(),event.getProvisionForOtherCost(), TransactionType.CREDIT, TransactionReasonCode.OTHERS_PROVISION_REGISTERED);
        othersAccountTransactionsViewRepository.save(othersAccountTransactionsView);
    }
}
