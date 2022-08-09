package com.affaince.accounting.journal.subsidiaries;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

public abstract class AbstractCashBookEntry {
    private String merchantId;
    private LocalDateTime date;
    private String peerAccountNumber;
    private AccountIdentifier accountIdentifier;
    private String journalFolio;
    private double amount;
    private String CashBookEntryAccountNumber;
    private CashBookEntryAccountType accountType;

    public AbstractCashBookEntry(String merchantId,LocalDateTime date, String peerAccountNumber, AccountIdentifier accountIdentifier, String journalFolio, double amount, String cashBookEntryAccountNumber, CashBookEntryAccountType accountType) {
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
}
