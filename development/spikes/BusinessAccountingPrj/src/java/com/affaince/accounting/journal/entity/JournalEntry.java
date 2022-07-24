package com.affaince.accounting.journal.entity;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.requireNonNull;

public class JournalEntry {
    private String merchantId;
    private String journalFolioNumber;
    private LocalDateTime dateOfTransaction ;
    private List<DebitJournalEntry> debits;
    private List<CreditJournalEntry> credits;
    private String narration;
    private String ledgerFolioNumber;


    public static class JournalEntryBuilder {
        private String merchantId;
        String journalFolioNumber;
        LocalDateTime dateOfTransaction ;
        List<DebitJournalEntry> debits;
        List<CreditJournalEntry> credits;
        String narration;


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

        public JournalEntry build() {
            return JournalEntry.create(this);
        }
    }


    public static JournalEntry.JournalEntryBuilder newBuilder(){
        return new JournalEntryBuilder();
    }

    private JournalEntry(JournalEntryBuilder journalEntryBuilder){
       this.merchantId= journalEntryBuilder.merchantId;
       this.journalFolioNumber=journalEntryBuilder.journalFolioNumber;
       this.dateOfTransaction=journalEntryBuilder.dateOfTransaction;
       this.debits = journalEntryBuilder.debits;
       this.credits=journalEntryBuilder.credits;
       this.narration=journalEntryBuilder.narration;
    }
    public static JournalEntry create(JournalEntryBuilder journalEntryBuilder){
        return new JournalEntry(journalEntryBuilder);
    }

    public String getLedgerFolioNumber() {
        return ledgerFolioNumber;
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

    @Override
    public String toString() {
        return "JournalEntry{" +
                "merchantId='" + merchantId + '\'' +
                ", journalFolioNumber='" + journalFolioNumber + '\'' +
                ", dateOfTransaction=" + dateOfTransaction +
                ", debits=" + debits +
                ", credits=" + credits +
                ", narration='" + narration + '\'' +
                ", ledgerFolioNumber='" + ledgerFolioNumber + '\'' +
                '}';
    }
}
