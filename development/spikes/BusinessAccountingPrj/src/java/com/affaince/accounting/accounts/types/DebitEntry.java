package com.affaince.accounting.accounts.types;

import org.joda.time.LocalDateTime;

public class DebitEntry extends AccountEntry{


    public DebitEntry(LocalDateTime date, String particulars, String journalFolio, double amount) {
        super(date, particulars, journalFolio, amount);
    }
}
