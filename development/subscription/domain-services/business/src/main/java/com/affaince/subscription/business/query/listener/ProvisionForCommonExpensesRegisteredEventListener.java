package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.ProvisionForCommonExpensesRegisteredEvent;
import com.affaince.subscription.business.query.repository.CommonExpenseAccountViewRepository;
import com.affaince.subscription.business.query.repository.CommonExpensesTransactionsViewRepository;
import com.affaince.subscription.business.query.repository.CommonOperatingExpenseConfigViewRepository;
import com.affaince.subscription.business.query.view.CommonExpenseAccountView;
import com.affaince.subscription.business.query.view.CommonExpensesTransactionsView;
import com.affaince.subscription.business.query.view.CommonOperatingExpenseConfigView;
import com.affaince.subscription.business.vo.OperatingExpenseVO;
import com.affaince.subscription.business.vo.TransactionReasonCode;
import com.affaince.subscription.business.vo.TransactionType;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 22-01-2017.
 */
@Component
public class ProvisionForCommonExpensesRegisteredEventListener {
    private CommonOperatingExpenseConfigViewRepository commonOperatingExpenseConfigViewRepository;
    private CommonExpenseAccountViewRepository commonExpenseAccountViewRepository;
    private CommonExpensesTransactionsViewRepository commonExpensesTransactionsViewRepository;

    @Autowired
    public ProvisionForCommonExpensesRegisteredEventListener(CommonOperatingExpenseConfigViewRepository commonOperatingExpenseConfigViewRepository,CommonExpenseAccountViewRepository commonExpenseAccountViewRepository,CommonExpensesTransactionsViewRepository commonExpensesTransactionsViewRepository) {
        this.commonOperatingExpenseConfigViewRepository=commonOperatingExpenseConfigViewRepository;
        this.commonExpenseAccountViewRepository = commonExpenseAccountViewRepository;
        this.commonExpensesTransactionsViewRepository=commonExpensesTransactionsViewRepository;
    }

    @EventHandler
    public void on(ProvisionForCommonExpensesRegisteredEvent event){
        for (OperatingExpenseVO expense : event.getExpenses()) {
            CommonOperatingExpenseConfigView commonOperatingExpenseConfigView= new CommonOperatingExpenseConfigView(expense.getExpenseHeader(),expense.getAmount(),expense.getPeriod(),expense.getSensitivityCharacteristic());
            commonOperatingExpenseConfigViewRepository.save(commonOperatingExpenseConfigView);
        }
        CommonExpenseAccountView commonExpenseAccountView= new CommonExpenseAccountView(event.getId(),event.getProvisionForCommonExpenses(),event.getProvisionForCommonExpenses(),event.getStartDate().toLocalDateTime(LocalTime.now()),event.getEndDate().toLocalDateTime(LocalTime.MIDNIGHT));
        commonExpenseAccountViewRepository.save(commonExpenseAccountView);
        CommonExpensesTransactionsView commonExpensesTransactionsView=new CommonExpensesTransactionsView(event.getStartDate(),event.getProvisionForCommonExpenses(), TransactionType.CREDIT, TransactionReasonCode.OPERATING_EXPENSE_PROVISION_REGISTERED);
        commonExpensesTransactionsViewRepository.save(commonExpensesTransactionsView);
    }
}
