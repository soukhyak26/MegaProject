package com.affaince.accounting.db;

import com.affaince.accounting.journal.subsidiaries.CashBookEntry;

import java.util.ArrayList;
import java.util.List;

public class CashBookDatabaseSimulator {
    private static final List<CashBookEntry> cashBookEntries = new ArrayList<>();

    public static void addJournalEntry(CashBookEntry cashBookEntry){
        cashBookEntries.add(cashBookEntry);
    }

    public static void addJournalEntries(List<CashBookEntry> newCashBookEntries){
        cashBookEntries.addAll(newCashBookEntries);
    }
    public static List<CashBookEntry> getCashBookEntries() {
        return cashBookEntries;
    }
}
