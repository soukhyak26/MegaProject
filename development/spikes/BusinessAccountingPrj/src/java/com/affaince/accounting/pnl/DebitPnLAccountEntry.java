package com.affaince.accounting.pnl;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

public class DebitPnLAccountEntry extends ProfitAndLossAccountEntry{
    public DebitPnLAccountEntry(LocalDateTime date, String peerAccountNumber, AccountIdentifier accountIdentifier, double amount) {
        super(date, peerAccountNumber, accountIdentifier, amount);
    }

    @Override
    public String toString() {
        return "DebitPnLAccountEntry{} " + super.toString();
    }
}
