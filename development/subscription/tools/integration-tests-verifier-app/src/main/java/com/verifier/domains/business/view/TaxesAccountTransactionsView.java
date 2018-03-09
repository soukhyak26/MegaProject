package com.verifier.domains.business.view;

import com.verifier.domains.business.vo.TransactionReasonCode;
import com.verifier.domains.business.vo.TransactionType;
import org.joda.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 22-01-2017.
 */
@Document
public class TaxesAccountTransactionsView extends AccountTransactionsView {
    public TaxesAccountTransactionsView(LocalDate dateOfTransaction, double transactedAmount, TransactionType transactionType, TransactionReasonCode transactionReasonCode) {
        super(dateOfTransaction,transactedAmount,transactionType, transactionReasonCode);
    }

}