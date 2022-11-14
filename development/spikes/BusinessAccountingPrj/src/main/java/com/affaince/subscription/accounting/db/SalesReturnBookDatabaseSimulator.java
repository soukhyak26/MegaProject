package com.affaince.subscription.accounting.db;

import com.affaince.subscription.accounting.journal.subsidiaries.SubsidiaryBookEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class SalesReturnBookDatabaseSimulator {
    private final List<SubsidiaryBookEntry> salesReturnBookJournalEntries;
    @Autowired
    public SalesReturnBookDatabaseSimulator(){
        salesReturnBookJournalEntries = new ArrayList<>();
    }
    public void addJournalEntry(SubsidiaryBookEntry subsidiaryBookEntry){
        salesReturnBookJournalEntries.add(subsidiaryBookEntry);
    }

    public void addJournalEntries(List<SubsidiaryBookEntry> newSubsidiaryBookEntries){
        salesReturnBookJournalEntries.addAll(newSubsidiaryBookEntries);
    }
    public List<SubsidiaryBookEntry> getSalesReturnBookJournalEntries() {
        return salesReturnBookJournalEntries;
    }
}
