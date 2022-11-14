package com.affaince.subscription.accounting.ledger.accounts;

import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

//debit all expenses and losses and credit all income and gains.
public class NominalAccount extends LedgerAccount {
    public NominalAccount(String merchantId, String accountId, AccountIdentifier accountIdentifier, LocalDateTime startDate, LocalDateTime closureDate) {
        super(merchantId, accountId, accountIdentifier,startDate,closureDate);
    }

    @Override
    public String toString() {
        return "\nNominalAccount{} " + super.toString();
    }
}
