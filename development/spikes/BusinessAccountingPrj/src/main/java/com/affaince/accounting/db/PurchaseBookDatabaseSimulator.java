package com.affaince.accounting.db;

import com.affaince.accounting.journal.subsidiaries.SubsidiaryBookEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PurchaseBookDatabaseSimulator {

    private final List<SubsidiaryBookEntry> purchaseBookJournalEntries;

    @Autowired
    public PurchaseBookDatabaseSimulator(){
        this.purchaseBookJournalEntries = new ArrayList<>();
    }
    public void addJournalEntry(SubsidiaryBookEntry subsidiaryBookEntry){
        purchaseBookJournalEntries.add(subsidiaryBookEntry);
    }

    public void addJournalEntries(List<SubsidiaryBookEntry> newSubsidiaryBookEntries){
        purchaseBookJournalEntries.addAll(newSubsidiaryBookEntries);
    }
    public List<SubsidiaryBookEntry> getPurchaseBookJournalEntries() {
        return purchaseBookJournalEntries;
    }
}
