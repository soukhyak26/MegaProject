package com.affaince.subscription.accounting.ledger.accounts;

import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;


public class DebitLedgerEntry extends LedgerAccountEntry {


    public DebitLedgerEntry(LocalDateTime date, String peerAccountId, AccountIdentifier accountIdentifier, String journalFolio, String ledgerFolio, double amount) {
        super(date, peerAccountId,accountIdentifier, journalFolio, ledgerFolio,amount);
    }

    @Override
    public String toString() {
        return "\nDebitLedgerEntry{} " + super.toString();
    }

}
