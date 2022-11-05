package com.affaince.accounting.statements;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public class BalanceSheetEntity {
    private String accountId;
    private AccountIdentifier accountIdentifier;
    private double value;
    private String narration;

    public BalanceSheetEntity(String accountId, AccountIdentifier accountIdentifier, double value, String narration) {
        this.accountId = accountId;
        this.accountIdentifier = accountIdentifier;
        this.value = value;
        this.narration = narration;
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountIdentifier getAccountIdentifier() {
        return accountIdentifier;
    }

    public double getValue() {
        return value;
    }

    public String getNarration() {
        return narration;
    }

    public void addToValue(double value){
      this.value += value;
    }

    public void addToNarration(String addedNarration){
        narration = narration + "$" + addedNarration;
    }
}
