package com.affaince.accounting.trading;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import org.joda.time.LocalDateTime;


public class TradingAccount extends LedgerAccount {

    public TradingAccount(String merchantId, String accountId, AccountIdentifier accountIdentifier, LocalDateTime startDate, LocalDateTime closureDate) {
        super(merchantId, accountId, accountIdentifier, startDate, closureDate);
    }

    @Override
    public String toString() {
        return "TradingAccount{} " + super.toString();
    }
}
