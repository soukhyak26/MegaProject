package com.affaince.accounting.journal.entity;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public class ParticipantAccount {
    private String accountId;
    private AccountIdentifier accountIdentifier;
    private double amountExchanged;

    public ParticipantAccount(String accountId, AccountIdentifier accountIdentifier, double amountExchanged) {
        this.accountId = accountId;
        this.accountIdentifier = accountIdentifier;
        this.amountExchanged = amountExchanged;
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountIdentifier getAccountIdentifier() {
        return accountIdentifier;
    }

    public double getAmountExchanged() {
        return amountExchanged;
    }
}
