package com.affaince.accounting.journal.subsidiaries;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

public class DebitCashBookEntry extends CashBookEntry {

    public DebitCashBookEntry(String merchantId,LocalDateTime date, String peerAccountNumber, AccountIdentifier accountIdentifier, String journalFolio, double amount, String cashBookEntryAccountNumber, CashBookEntryAccountType accountType) {
        super(merchantId,date, peerAccountNumber, accountIdentifier, journalFolio, amount, cashBookEntryAccountNumber, accountType);
    }

    @Override
    public String toString() {
        return "DebitCashBookEntry{} " + super.toString();
    }
}
