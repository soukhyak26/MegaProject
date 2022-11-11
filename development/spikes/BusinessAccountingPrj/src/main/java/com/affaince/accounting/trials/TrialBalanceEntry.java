package com.affaince.accounting.trials;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public class TrialBalanceEntry {
    private String accountId;
    private AccountIdentifier accountIdentifier;
    private String peerAccountId;
    private AccountIdentifier peerAccountIdentifier;
    private String ledgerFolio;
    private double balanceAmount;
    private NatureOfBalance natureOfBalance;

    public TrialBalanceEntry(String accountId, AccountIdentifier accountIdentifier, String peerAccountId,AccountIdentifier peerAccountIdentifier,String ledgerFolio,double balanceAmount, NatureOfBalance natureOfBalance) {
        this.accountId = accountId;
        this.accountIdentifier = accountIdentifier;
        this.peerAccountId = peerAccountId;
        this. peerAccountIdentifier = peerAccountIdentifier;
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

    public String getPeerAccountId() {
        return peerAccountId;
    }

    public AccountIdentifier getPeerAccountIdentifier() {
        return peerAccountIdentifier;
    }

    @Override
    public String toString() {
        return "TrialBalanceEntry{" +
                "accountId='" + accountId + '\'' +
                ", accountIdentifier=" + accountIdentifier +
                ", peerAccountId='" + peerAccountId + '\'' +
                ", peerAccountIdentifier=" + peerAccountIdentifier +
                ", ledgerFolio='" + ledgerFolio + '\'' +
                ", balanceAmount=" + balanceAmount +
                ", natureOfBalance=" + natureOfBalance +
                '}';
    }
}
