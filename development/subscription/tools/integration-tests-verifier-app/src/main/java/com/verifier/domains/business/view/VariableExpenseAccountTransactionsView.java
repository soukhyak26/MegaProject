package com.verifier.domains.business.view;

import com.verifier.domains.business.vo.TransactionReasonCode;
import com.verifier.domains.business.vo.TransactionType;
import org.joda.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 22-01-2017.
 */
@Document
public class VariableExpenseAccountTransactionsView extends AccountTransactionsView{
    public VariableExpenseAccountTransactionsView(){super();}
    public VariableExpenseAccountTransactionsView(LocalDate dateOfTransaction, String contributorId, double transactedAmount, TransactionType transactionType, TransactionReasonCode transactionReasonCode) {
        super(dateOfTransaction,contributorId,transactedAmount,transactionType, transactionReasonCode);
    }
}
