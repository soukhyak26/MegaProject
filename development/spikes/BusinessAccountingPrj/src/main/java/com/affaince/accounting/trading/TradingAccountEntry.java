package com.affaince.accounting.trading;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

public class TradingAccountEntry {
    private LocalDateTime date;
    private String peerAccountNumber;
    private AccountIdentifier accountIdentifier;
    private double amount;

    public TradingAccountEntry(LocalDateTime date, String peerAccountNumber, AccountIdentifier accountIdentifier, double amount) {
        this.date = date;
        this.peerAccountNumber = peerAccountNumber;
        this.accountIdentifier = accountIdentifier;
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getPeerAccountNumber() {
        return peerAccountNumber;
    }

    public AccountIdentifier getAccountIdentifier() {
        return accountIdentifier;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "{" +
                "date=" + date +
                ", peerAccountNumber='" + peerAccountNumber + '\'' +
                ", accountIdentifier=" + accountIdentifier +
                ", amount=" + amount +
                '}';
    }
}
