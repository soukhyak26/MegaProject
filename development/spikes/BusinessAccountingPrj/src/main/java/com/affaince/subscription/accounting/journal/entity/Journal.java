package com.affaince.subscription.accounting.journal.entity;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
public class Journal {
    private String merchantId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<JournalRecord> journalEntries ;


    public Journal(String merchantId,LocalDateTime startDate,LocalDateTime endDate){
        this.merchantId = merchantId;
        this.startDate = startDate;
        this.endDate = endDate;
        journalEntries = new ArrayList<>();
    }
    public void addJournalEntry(JournalRecord journalRecord){
        journalEntries.add(journalRecord);
    }

    public void addJournalEntries(List<JournalRecord> newJournalEntries){
        journalEntries.addAll(newJournalEntries);
    }
    public List<JournalRecord> getJournalEntries() {
        return journalEntries;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
