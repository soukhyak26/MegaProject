package com.affaince.accounting.trading;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

public class DebitTradingAccountEntry extends TradingAccountEntry {

    public DebitTradingAccountEntry(LocalDateTime date, String peerAccountNumber, AccountIdentifier accountIdentifier, double amount) {
        super(date, peerAccountNumber, accountIdentifier, amount);
    }

    @Override
    public String toString() {
        return "DebitTradingAccountEntry{} " + super.toString();
    }
}
