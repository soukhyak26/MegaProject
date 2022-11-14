package com.affaince.subscription.accounting.statements;

import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceSheetEntity entity = (BalanceSheetEntity) o;
        return Double.compare(entity.value, value) == 0 &&
                accountId.equals(entity.accountId) &&
                accountIdentifier == entity.accountIdentifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, accountIdentifier, value);
    }

    @Override
    public String toString() {
        return "BalanceSheetEntity{" +
                "accountId='" + accountId + '\'' +
                ", accountIdentifier=" + accountIdentifier +
                ", value=" + value +
                ", narration='" + narration + '\'' +
                '}';
    }
}
