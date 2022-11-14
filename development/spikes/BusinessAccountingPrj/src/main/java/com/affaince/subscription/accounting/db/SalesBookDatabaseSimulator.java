package com.affaince.subscription.accounting.db;

import com.affaince.subscription.accounting.journal.subsidiaries.SubsidiaryBookEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class SalesBookDatabaseSimulator {
    private final List<SubsidiaryBookEntry> salesBookJournalEntries;
    @Autowired
    public SalesBookDatabaseSimulator(){
        salesBookJournalEntries = new ArrayList<>();
    }
    public void addJournalEntry(SubsidiaryBookEntry subsidiaryBookEntry){
        salesBookJournalEntries.add(subsidiaryBookEntry);
    }

    public void addJournalEntries(List<SubsidiaryBookEntry> newSubsidiaryBookEntries){
        salesBookJournalEntries.addAll(newSubsidiaryBookEntries);
    }
    public List<SubsidiaryBookEntry> getSalesBookJournalEntries() {
        return salesBookJournalEntries;
    }
}
