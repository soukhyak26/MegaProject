package com.affaince.accounting.trials;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public class TrialBalanceEntry {
    private String accountId;
    private AccountIdentifier accountIdentifier;
    private String ledgerFolio;
    private double balanceAmount;
    private NatureOfBalance natureOfBalance;

    public TrialBalanceEntry(String accountId, AccountIdentifier accountIdentifier, String ledgerFolio,double balanceAmount, NatureOfBalance natureOfBalance) {
        this.accountId = accountId;
        this.accountIdentifier = accountIdentifier;
        this.ledgerFolio=ledgerFolio;
        this.balanceAmount = balanceAmount;
        this.natureOfBalance = natureOfBalance;
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountIdentifier getAccountIdentifier() {
        return accountIdentifier;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public NatureOfBalance getNatureOfBalance() {
        return natureOfBalance;
    }

    public String getLedgerFolio() {
        return ledgerFolio;
    }

    @Override
    public String toString() {
        return "{" +
                "accountId='" + accountId + '\'' +
                ", accountIdentifier=" + accountIdentifier +
                ", ledgerFolio='" + ledgerFolio + '\'' +
                ", balanceAmount=" + balanceAmount +
                ", natureOfBalance=" + natureOfBalance +
                '}';
    }
}
