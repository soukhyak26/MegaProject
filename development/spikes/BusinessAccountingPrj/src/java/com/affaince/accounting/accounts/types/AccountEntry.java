package com.affaince.accounting.accounts.types;

import org.joda.time.LocalDateTime;

public abstract class AccountEntry {
    private LocalDateTime date;
    private String particulars;
    private String journalFolio;
    private double amount;

    public AccountEntry(LocalDateTime date, String particulars, String journalFolio, double amount) {
        this.date = date;
        this.particulars = particulars;
        this.journalFolio = journalFolio;
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getParticulars() {
        return particulars;
    }

    public String getJournalFolio() {
        return journalFolio;
    }

    public double getAmount() {
        return amount;
    }
}
