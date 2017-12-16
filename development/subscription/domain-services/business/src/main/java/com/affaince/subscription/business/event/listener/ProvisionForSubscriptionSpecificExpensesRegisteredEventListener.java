package com.affaince.subscription.business.event.listener;

import com.affaince.subscription.business.event.ProvisionForSubscriptionSpecificExpensesRegisteredEvent;
import com.affaince.subscription.business.query.repository.VariableExpenseAccountTransactionsViewRepository;
import com.affaince.subscription.business.query.repository.VariableExpenseAccountViewRepository;
import com.affaince.subscription.business.query.view.VariableExpenseAccountTransactionsView;
import com.affaince.subscription.business.query.view.VariableExpenseAccountView;
import com.affaince.subscription.business.vo.TransactionReasonCode;
import com.affaince.subscription.business.vo.TransactionType;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 22-01-2017.
 */
public class ProvisionForSubscriptionSpecificExpensesRegisteredEventListener {
    private VariableExpenseAccountViewRepository variableExpenseAccountViewRepository;
    private VariableExpenseAccountTransactionsViewRepository variableExpenseAccountTransactionsViewRepository;

    @Autowired
    public ProvisionForSubscriptionSpecificExpensesRegisteredEventListener(VariableExpenseAccountViewRepository variableExpenseAccountViewRepository, VariableExpenseAccountTransactionsViewRepository variableExpenseAccountTransactionsViewRepository) {
        this.variableExpenseAccountViewRepository = variableExpenseAccountViewRepository;
        this.variableExpenseAccountTransactionsViewRepository = variableExpenseAccountTransactionsViewRepository;
    }

    @EventHandler
    public void on(ProvisionForSubscriptionSpecificExpensesRegisteredEvent event){
        VariableExpenseAccountView variableExpenseAccountView= new VariableExpenseAccountView(event.getId(),event.getProvisionForSubscriptionSpecificExpenses(),event.getProvisionForSubscriptionSpecificExpenses(),event.getStartDate().toLocalDateTime(LocalTime.now()),event.getEndDate().toLocalDateTime(LocalTime.MIDNIGHT));
        variableExpenseAccountViewRepository.save(variableExpenseAccountView);
        VariableExpenseAccountTransactionsView variableExpenseAccountTransactionsView= new VariableExpenseAccountTransactionsView(event.getStartDate(),event.getProvisionForSubscriptionSpecificExpenses(), TransactionType.CREDIT, TransactionReasonCode.OPERATING_EXPENSE_PROVISION_REGISTERED);
        variableExpenseAccountTransactionsViewRepository.save(variableExpenseAccountTransactionsView);
    }
}
