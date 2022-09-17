package com.affaince.accounting.pnl;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

public class CreditPnLAccountEntry extends ProfitAndLossAccountEntry{
    public CreditPnLAccountEntry(LocalDateTime date, String peerAccountNumber, AccountIdentifier accountIdentifier, double amount) {
        super(date, peerAccountNumber, accountIdentifier, amount);
    }

    @Override
    public String toString() {
        return "CreditPnLAccountEntry{} " + super.toString();
    }
}
