package com.affaince.accounting.journal.entity;

public class CreditJournalEntry {
    private String accountId;
    private double amount;

    public CreditJournalEntry(String accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "CreditJournalEntry{" +
                "accountId='" + accountId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
