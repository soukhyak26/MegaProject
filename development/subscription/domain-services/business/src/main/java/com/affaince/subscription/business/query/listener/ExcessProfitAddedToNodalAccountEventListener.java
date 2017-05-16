package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.domain.NodalAccount;
import com.affaince.subscription.business.command.event.ExcessProfitAddedToNodalAccountEvent;
import com.affaince.subscription.business.query.repository.NodalAccountTransactionsViewRepository;
import com.affaince.subscription.business.query.repository.NodalAccountViewRepository;
import com.affaince.subscription.business.query.view.CommonExpensesTransactionsView;
import com.affaince.subscription.business.query.view.NodalAccountTransactionsView;
import com.affaince.subscription.business.query.view.NodalAccountView;
import com.affaince.subscription.business.vo.TransactionReasonCode;
import com.affaince.subscription.business.vo.TransactionType;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 03-02-2017.
 */
@Component
public class ExcessProfitAddedToNodalAccountEventListener {
    private NodalAccountViewRepository nodalAccountViewRepository;
    private NodalAccountTransactionsViewRepository nodalAccountTransactionsViewRepository;

    @Autowired
    public ExcessProfitAddedToNodalAccountEventListener(NodalAccountViewRepository nodalAccountViewRepository, NodalAccountTransactionsViewRepository nodalAccountTransactionsViewRepository) {
        this.nodalAccountViewRepository = nodalAccountViewRepository;
        this.nodalAccountTransactionsViewRepository = nodalAccountTransactionsViewRepository;
    }

    @EventHandler
    public void on(ExcessProfitAddedToNodalAccountEvent event) {
        NodalAccountView nodalAccountView = nodalAccountViewRepository.findOne(event.getBusinessAccountId());
        nodalAccountView.credit(event.getExcessProfit());
        nodalAccountViewRepository.save(nodalAccountView);

        NodalAccountTransactionsView nodalAccountTransactionsView=new NodalAccountTransactionsView(event.getDateOfTransaction(),event.getExcessProfit(), TransactionType.CREDIT, TransactionReasonCode.EXCESS_PROFIT_ADDED_BY_PRODUCT);
        nodalAccountTransactionsViewRepository.save(nodalAccountTransactionsView);
    }

}
