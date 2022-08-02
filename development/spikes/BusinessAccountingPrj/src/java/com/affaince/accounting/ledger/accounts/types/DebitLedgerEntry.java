package com.affaince.accounting.ledger.accounts.types;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;


public class DebitLedgerEntry extends LedgerAccountEntry {


    public DebitLedgerEntry(LocalDateTime date, String peerAccountId, AccountIdentifier accountIdentifier, String journalFolio, double amount) {
        super(date, peerAccountId,accountIdentifier, journalFolio, amount);
    }

    @Override
    public String toString() {
        return "DebitLedgerEntry{} " + super.toString();
    }

}
