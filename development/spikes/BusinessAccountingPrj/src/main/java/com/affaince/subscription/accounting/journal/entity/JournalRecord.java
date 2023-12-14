package com.affaince.subscription.accounting.journal.entity;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class JournalRecord {
    private final String merchantId;
    private final String journalFolioNumber;
    private final String transactionReferenceNumber;
    private final LocalDateTime dateOfTransaction ;
    private final List<DebitJournalEntry> debits;
    private final List<CreditJournalEntry> credits;
    private final String narration;
    private String ledgerFolioNumber;


    public static class JournalEntryBuilder {
        private String merchantId;
        private String journalFolioNumber;
        private String transactionReferenceNumber;
        private LocalDateTime dateOfTransaction ;
        private final List<DebitJournalEntry> debits;
        private final List<CreditJournalEntry> credits;
        private String narration;


        public JournalEntryBuilder(){
            this.debits=new ArrayList<>();
            this.credits= new ArrayList<>();
        }
        public JournalEntryBuilder merchantId(String merchantId){
            requireNonNull(merchantId);
            this.merchantId=merchantId;
            return this;
        }
        public JournalEntryBuilder journalFolioNumber(String journalFolioNumber){
            requireNonNull(journalFolioNumber);
            this.journalFolioNumber=journalFolioNumber;
            return this;
        }
        public JournalEntryBuilder transactionReferenceNumber(String transactionReferenceNumber){
            requireNonNull(transactionReferenceNumber);
            this.transactionReferenceNumber=transactionReferenceNumber;
            return this;
        }

        public JournalEntryBuilder dateOfTransaction(LocalDateTime dateOfTransaction){
            requireNonNull(dateOfTransaction);
            this.dateOfTransaction=dateOfTransaction;
            return this;
        }
        public JournalEntryBuilder debit(DebitJournalEntry debitJournalEntry){
            requireNonNull(debitJournalEntry);
            this.debits.add(debitJournalEntry);
            return this;
        }

        public JournalEntryBuilder credit(CreditJournalEntry creditJournalEntry){
            requireNonNull(creditJournalEntry);
            this.credits.add(creditJournalEntry);
            return this;
        }
        public JournalEntryBuilder narration(String narration){
            requireNonNull(narration);
            this.narration=narration;
            return this;
        }

        public JournalRecord build() {
            return JournalRecord.create(this);
        }
    }


    public static JournalRecord.JournalEntryBuilder newBuilder(){
        return new JournalEntryBuilder();
    }

    private JournalRecord(JournalEntryBuilder journalEntryBuilder){
       this.merchantId= journalEntryBuilder.merchantId;
       this.journalFolioNumber=journalEntryBuilder.journalFolioNumber;
       this.transactionReferenceNumber=journalEntryBuilder.transactionReferenceNumber;
       this.dateOfTransaction=journalEntryBuilder.dateOfTransaction;
       this.debits = journalEntryBuilder.debits;
       this.credits=journalEntryBuilder.credits;
       this.narration=journalEntryBuilder.narration;
    }
    public static JournalRecord create(JournalEntryBuilder journalEntryBuilder){
        return new JournalRecord(journalEntryBuilder);
    }

    public String getLedgerFolioNumber() {
        return ledgerFolioNumber;
    }

    public void setLedgerFolioNumber(String ledgerFolioNumber) {
        this.ledgerFolioNumber = ledgerFolioNumber;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getJournalFolioNumber() {
        return journalFolioNumber;
    }

    public LocalDateTime getDateOfTransaction() {
        return dateOfTransaction;
    }

    public List<DebitJournalEntry> getDebits() {
        return debits;
    }

    public List<CreditJournalEntry> getCredits() {
        return credits;
    }

    public String getNarration() {
        return narration;
    }

    public String getTransactionReferenceNumber() {
        return transactionReferenceNumber;
    }


    @Override
    public String toString() {
        return "{" +
                "merchantId='" + merchantId + '\'' +
                ", journalFolioNumber='" + journalFolioNumber + '\'' +
                ", transactionReferenceNumber='" + transactionReferenceNumber + '\'' +
                ", dateOfTransaction=" + dateOfTransaction +
                ", debits=" + debits +
                ", credits=" + credits +
                ", narration='" + narration + '\'' +
                ", ledgerFolioNumber='" + ledgerFolioNumber + '\'' +
                '}';
    }
}
