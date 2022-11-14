package com.affaince.subscription.accounting.db;

import com.affaince.subscription.accounting.journal.subsidiaries.CashBookEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CashBookDatabaseSimulator {
    private List<CashBookEntry> cashBookEntries;
    @Autowired
    public CashBookDatabaseSimulator(){
        cashBookEntries = new ArrayList<>();
    }
    public void addJournalEntry(CashBookEntry cashBookEntry){
        cashBookEntries.add(cashBookEntry);
    }

    public void addJournalEntries(List<CashBookEntry> newCashBookEntries){
        cashBookEntries.addAll(newCashBookEntries);
    }
    public List<CashBookEntry> getCashBookEntries() {
        return cashBookEntries;
    }
}
