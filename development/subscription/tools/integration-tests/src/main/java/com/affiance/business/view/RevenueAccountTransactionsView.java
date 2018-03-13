package com.affiance.business.view;

import com.affiance.business.vo.TransactionReasonCode;
import com.affiance.business.vo.TransactionType;
import org.joda.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 22-01-2017.
 */
@Document
public class RevenueAccountTransactionsView extends AccountTransactionsView {
    public RevenueAccountTransactionsView(LocalDate dateOfTransaction, double transactedAmount, TransactionType transactionType, TransactionReasonCode transactionReasonCode) {
        super(dateOfTransaction,transactedAmount,transactionType, transactionReasonCode);
    }

}
