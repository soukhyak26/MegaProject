package com.affaince.accounting.ledger.accounts;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

public class RealAccount extends AbstractLedgerAccountStereoType{
    public RealAccount(String merchantId, String accountId, AccountIdentifier accountIdentifier, LocalDateTime startDate, LocalDateTime closureDate) {
        super(merchantId,accountId,accountIdentifier,startDate,closureDate);
    }

    @Override
    public String toString() {
        return "\nRealAccount{} " + super.toString();
    }
}
