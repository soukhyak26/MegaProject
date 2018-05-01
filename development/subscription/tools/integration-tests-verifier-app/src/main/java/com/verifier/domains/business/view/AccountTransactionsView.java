package com.verifier.domains.business.view;

import com.verifier.domains.business.vo.TransactionReasonCode;
import com.verifier.domains.business.vo.TransactionType;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.data.annotation.Id;

/**
 * Created by mandar on 22-01-2017.
 */

public abstract class AccountTransactionsView {
    @Id
    private long transactionId;
    private LocalDateTime dateOfTransaction;
    private double transactedAmount;
    private TransactionType transactionType;
    private TransactionReasonCode transactionReasonCode;

    public AccountTransactionsView() {
    }

    public AccountTransactionsView(LocalDate dateOfTransaction, double transactedAmount, TransactionType transactionType, TransactionReasonCode transactionReasonCode) {
        final LocalDateTime currentDateTime=dateOfTransaction.toLocalDateTime(LocalTime.now());
        //TODO- NEED TO check if this is going to be duplicate
        this.transactionId = currentDateTime.toDateTime().getMillis();
        this.dateOfTransaction = currentDateTime;
        this.transactedAmount = transactedAmount;
        this.transactionType = transactionType;
        this.transactionReasonCode = transactionReasonCode;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getDateOfTransaction() {
        return dateOfTransaction;
    }

    public double getTransactedAmount() {
        return transactedAmount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public TransactionReasonCode getTransactionReasonCode() {
        return transactionReasonCode;
    }
}
