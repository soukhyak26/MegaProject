package com.affaince.accounting.accounts.types;

import org.joda.time.LocalDateTime;

public class CreditEntry extends AccountEntry {
    public CreditEntry(LocalDateTime date, String particulars, String journalFolio, double amount) {
        super(date, particulars, journalFolio, amount);
    }
}
