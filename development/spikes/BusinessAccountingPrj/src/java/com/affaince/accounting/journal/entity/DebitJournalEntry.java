package com.affaince.accounting.journal.entity;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public class DebitJournalEntry {
    private String accountId;
    private AccountIdentifier accountIdentifier;
    private double amount;

    public DebitJournalEntry(String accountId,AccountIdentifier accountIdentifier, double amount) {
        this.accountId = accountId;
        this.accountIdentifier=accountIdentifier;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountIdentifier getAccountIdentifier() {
        return accountIdentifier;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "\nDebitJournalEntry{" +
                "accountId='" + accountId + '\'' +
                ", accountIdentifier=" + accountIdentifier +
                ", amount=" + amount +
                '}';
    }
}
