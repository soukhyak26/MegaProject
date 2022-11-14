package com.affaince.subscription.accounting.journal.subsidiaries;

import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

public abstract class CashBookEntry {
    private String merchantId;
    private LocalDateTime date;
    private String peerAccountNumber;
    private AccountIdentifier accountIdentifier;
    private String journalFolio;
    private double amount;
    private String CashBookEntryAccountNumber;
    private CashBookEntryAccountType accountType;

    public CashBookEntry(String merchantId, LocalDateTime date, String peerAccountNumber, AccountIdentifier accountIdentifier, String journalFolio, double amount, String cashBookEntryAccountNumber, CashBookEntryAccountType accountType) {
        this.merchantId=merchantId;
        this.date = date;
        this.peerAccountNumber = peerAccountNumber;
        this.accountIdentifier = accountIdentifier;
        this.journalFolio = journalFolio;
        this.amount = amount;
        CashBookEntryAccountNumber = cashBookEntryAccountNumber;
        this.accountType = accountType;
    }

    public String getMerchantId() {
        return merchantId;
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

    public String getJournalFolio() {
        return journalFolio;
    }

    public double getAmount() {
        return amount;
    }

    public String getCashBookEntryAccountNumber() {
        return CashBookEntryAccountNumber;
    }

    public CashBookEntryAccountType getAccountType() {
        return accountType;
    }

    @Override
    public String toString() {
        return "{" +
                "merchantId='" + merchantId + '\'' +
                ", date=" + date +
                ", peerAccountNumber='" + peerAccountNumber + '\'' +
                ", accountIdentifier=" + accountIdentifier +
                ", journalFolio='" + journalFolio + '\'' +
                ", amount=" + amount +
                ", CashBookEntryAccountNumber='" + CashBookEntryAccountNumber + '\'' +
                ", accountType=" + accountType +
                '}';
    }
}
