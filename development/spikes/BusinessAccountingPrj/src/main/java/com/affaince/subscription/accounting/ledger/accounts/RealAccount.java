package com.affaince.subscription.accounting.ledger.accounts;

import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

public class RealAccount extends LedgerAccount {
    public RealAccount(String merchantId, String accountId, AccountIdentifier accountIdentifier, LocalDateTime startDate, LocalDateTime closureDate) {
        super(merchantId,accountId,accountIdentifier,startDate,closureDate);
    }

    @Override
    public String toString() {
        return "\nRealAccount{} " + super.toString();
    }
}
