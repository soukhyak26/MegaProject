package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.ProfitCreditedEvent;
import com.affaince.subscription.business.query.repository.ProfitAccountTransactionsViewRepository;
import com.affaince.subscription.business.query.repository.ProfitAccountViewRepository;
import com.affaince.subscription.business.query.view.ProfitAccountTransactionView;
import com.affaince.subscription.business.query.view.ProfitAccountView;
import com.affaince.subscription.business.vo.TransactionReasonCode;
import com.affaince.subscription.business.vo.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 03-02-2017.
 */
@Component
public class ProfitCreditedEventListener {
    private ProfitAccountViewRepository profitAccountViewRepository;
    private ProfitAccountTransactionsViewRepository profitAccountTransactionsViewRepository;

    @Autowired
    public ProfitCreditedEventListener(ProfitAccountViewRepository profitAccountViewRepository, ProfitAccountTransactionsViewRepository profitAccountTransactionsViewRepository) {
        this.profitAccountViewRepository = profitAccountViewRepository;
        this.profitAccountTransactionsViewRepository = profitAccountTransactionsViewRepository;
    }
    public void on(ProfitCreditedEvent event){
        ProfitAccountView profitAccountView= profitAccountViewRepository.findOne(event.getBusinessAccountId());
        profitAccountView.credit(event.getAmountToBeCredited());
        ProfitAccountTransactionView profitAccountTransactionView= new ProfitAccountTransactionView(event.getDateOfTransaction(),event.getAmountToBeCredited(), TransactionType.CREDIT, TransactionReasonCode.PROFIT_ADDED_BY_PRODUCT,event.getProductId());
        profitAccountTransactionsViewRepository.save(profitAccountTransactionView);
    }
}
