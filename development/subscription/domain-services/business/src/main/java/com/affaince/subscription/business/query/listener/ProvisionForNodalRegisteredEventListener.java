package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.ProvisionForNodalRegisteredEvent;
import com.affaince.subscription.business.query.repository.NodalAccountTransactionsViewRepository;
import com.affaince.subscription.business.query.repository.NodalAccountViewRepository;
import com.affaince.subscription.business.query.view.NodalAccountTransactionsView;
import com.affaince.subscription.business.query.view.NodalAccountView;
import com.affaince.subscription.business.vo.TransactionReasonCode;
import com.affaince.subscription.business.vo.TransactionType;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 22-01-2017.
 */
public class ProvisionForNodalRegisteredEventListener {
    private NodalAccountViewRepository nodalAccountViewRepository;
    private NodalAccountTransactionsViewRepository nodalAccountTransactionsViewRepository;

    @Autowired
    public ProvisionForNodalRegisteredEventListener(NodalAccountViewRepository nodalAccountViewRepository, NodalAccountTransactionsViewRepository nodalAccountTransactionsViewRepository) {
        this.nodalAccountViewRepository = nodalAccountViewRepository;
        this.nodalAccountTransactionsViewRepository = nodalAccountTransactionsViewRepository;
    }

    @EventHandler
    public void on (ProvisionForNodalRegisteredEvent event){
        NodalAccountView nodalAccountView= new NodalAccountView(event.getId(),event.getProvisionForNodal(),event.getProvisionForNodal(),event.getStartDate().toLocalDateTime(LocalTime.now()),event.getEndDate().toLocalDateTime(LocalTime.MIDNIGHT));
        nodalAccountViewRepository.save(nodalAccountView);
        NodalAccountTransactionsView nodalAccountTransactionsView= new NodalAccountTransactionsView(event.getStartDate(),event.getProvisionForNodal(), TransactionType.CREDIT, TransactionReasonCode.NODAL_PROVISION_REGISTERED);
        nodalAccountTransactionsViewRepository.save(nodalAccountTransactionsView);
    }
}
