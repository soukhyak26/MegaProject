package com.affaince.subscription.accounting.ledger.accounts;

import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

public class PersonalAccount extends LedgerAccount {
    public PersonalAccount(String merchantId, String accountId, AccountIdentifier accountIdentifier, LocalDateTime startDate, LocalDateTime closureDate) {
        super(merchantId,accountId,accountIdentifier,startDate,closureDate);
    }

    @Override
    public String toString() {
        return "\nPersonalAccount{} " + super.toString();
    }
}
