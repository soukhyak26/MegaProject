package com.affaince.accounting.ledger.accounts;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;


public abstract class LedgerAccountEntry implements Cloneable{
    private LocalDateTime date;
    private String peerAccountNumber;
    private AccountIdentifier accountIdentifier;
    private String journalFolio;
    private String ledgerFolio;
    private double amount;

    public LedgerAccountEntry(LocalDateTime date, String peerAccountNumber,AccountIdentifier accountIdentifier, String journalFolio,String ledgerFolio, double amount) {
        this.date = date;
        this.peerAccountNumber = peerAccountNumber;
        this.accountIdentifier=accountIdentifier;
        this.journalFolio = journalFolio;
        this.ledgerFolio = ledgerFolio;
        this.amount = amount;
    }

    public Object clone() {
        try {
            return super.clone();
        }catch (CloneNotSupportedException ex){
            ex.printStackTrace();
        }
        return null;
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
    public void setAccountIdentifier(AccountIdentifier accountIdentifier){
        this.accountIdentifier=accountIdentifier;
    }
    public String getJournalFolio() {
        return journalFolio;
    }

    public double getAmount() {
        return amount;
    }

    public String getLedgerFolio() {
        return ledgerFolio;
    }

    @Override
    public String toString() {
        return "LedgerAccountEntry{" +
                "date=" + date +
                ", peerAccountNumber='" + peerAccountNumber + '\'' +
                ", accountIdentifier=" + accountIdentifier +
                ", journalFolio='" + journalFolio + '\'' +
                ", ledgerFolio='" + ledgerFolio + '\'' +
                ", amount=" + amount +
                '}';
    }
}