package com.affaince.accounting.trading;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

public class CreditTradingAccountEntry extends TradingAccountEntry {

    public CreditTradingAccountEntry(LocalDateTime date, String peerAccountNumber, AccountIdentifier accountIdentifier, double amount) {
        super(date, peerAccountNumber, accountIdentifier, amount);
    }

    @Override
    public String toString() {
        return "CreditTradingAccountEntry{} " + super.toString();
    }
}
