package com.affaince.accounting.journal.entity;

public class DebitJournalEntry {
    private String accountId;
    private double amount;

    public DebitJournalEntry(String accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }
}
