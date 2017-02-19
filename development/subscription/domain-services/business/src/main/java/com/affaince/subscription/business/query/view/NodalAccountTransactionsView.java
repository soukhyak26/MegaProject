package com.affaince.subscription.business.query.view;

import com.affaince.subscription.business.vo.TransactionReasonCode;
import com.affaince.subscription.business.vo.TransactionType;
import org.joda.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 22-01-2017.
 */
@Document
public class NodalAccountTransactionsView extends AccountTransactionsView {
    public NodalAccountTransactionsView(LocalDate dateOfTransaction, double transactedAmount, TransactionType transactionType, TransactionReasonCode transactionReasonCode) {
        super(dateOfTransaction,transactedAmount,transactionType, transactionReasonCode);
    }

}
