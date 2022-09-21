package com.affaince.accounting.ledger.accounts;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;


public class CreditLedgerEntry extends LedgerAccountEntry {
    public CreditLedgerEntry(LocalDateTime date, String peerAccountId, AccountIdentifier accountIdentifier, String journalFolio,String ledgerFolio, double amount) {
        super(date, peerAccountId,accountIdentifier, journalFolio,ledgerFolio, amount);
    }

    @Override
    public String toString() {
        return "\nCreditLedgerEntry{} " + super.toString();
    }
}
