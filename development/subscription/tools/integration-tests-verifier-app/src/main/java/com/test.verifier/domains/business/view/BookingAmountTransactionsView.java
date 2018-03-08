package com.test.verifier.domains.business.view;

import com.test.verifier.domains.business.vo.TransactionReasonCode;
import com.test.verifier.domains.business.vo.TransactionType;
import org.joda.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 22-01-2017.
 */
@Document
public class BookingAmountTransactionsView extends AccountTransactionsView {
    private String contributorId;
    public BookingAmountTransactionsView(String contributorId, LocalDate dateOfTransaction, double transactedAmount, TransactionType transactionType, TransactionReasonCode transactionReasonCode) {
        super(dateOfTransaction,transactedAmount,transactionType, transactionReasonCode);
        this.contributorId=contributorId;
    }

    public String getContributorId() {
        return contributorId;
    }
}
