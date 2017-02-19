package com.affaince.subscription.business.query.view;

import com.affaince.subscription.business.vo.TransactionReasonCode;
import com.affaince.subscription.business.vo.TransactionType;
import org.joda.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 03-02-2017.
 */
@Document
public class ProfitAccountTransactionView extends AccountTransactionsView {
    private String productId;
    public ProfitAccountTransactionView(LocalDate dateOfTransaction, double transactedAmount, TransactionType transactionType, TransactionReasonCode transactionReasonCode,String productId) {
        super(dateOfTransaction,transactedAmount,transactionType, transactionReasonCode);
        this.productId=productId;
    }

}
