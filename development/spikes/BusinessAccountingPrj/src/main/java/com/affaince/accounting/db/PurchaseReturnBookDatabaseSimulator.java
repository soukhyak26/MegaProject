package com.affaince.accounting.db;

import com.affaince.accounting.journal.subsidiaries.SubsidiaryBookEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PurchaseReturnBookDatabaseSimulator {
    private final List<SubsidiaryBookEntry> purchaseReturnBookJournalEntries;
    @Autowired
    public PurchaseReturnBookDatabaseSimulator(){
        purchaseReturnBookJournalEntries = new ArrayList<>();
    }
    public void addJournalEntry(SubsidiaryBookEntry subsidiaryBookEntry){
        purchaseReturnBookJournalEntries.add(subsidiaryBookEntry);
    }

    public void addJournalEntries(List<SubsidiaryBookEntry> newSubsidiaryBookEntries){
        purchaseReturnBookJournalEntries.addAll(newSubsidiaryBookEntries);
    }
    public List<SubsidiaryBookEntry> getPurchaseReturnBookJournalEntries() {
        return purchaseReturnBookJournalEntries;
    }
}
