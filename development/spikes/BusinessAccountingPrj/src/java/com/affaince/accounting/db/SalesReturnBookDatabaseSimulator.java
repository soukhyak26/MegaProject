package com.affaince.accounting.db;

import com.affaince.accounting.journal.subsidiaries.SubsidiaryBookEntry;

import java.util.ArrayList;
import java.util.List;

public class SalesReturnBookDatabaseSimulator {
    private static final List<SubsidiaryBookEntry> salesReturnBookJournalEntries = new ArrayList<>();

    public static void addJournalEntry(SubsidiaryBookEntry subsidiaryBookEntry){
        salesReturnBookJournalEntries.add(subsidiaryBookEntry);
    }

    public static void addJournalEntries(List<SubsidiaryBookEntry> newSubsidiaryBookEntries){
        salesReturnBookJournalEntries.addAll(newSubsidiaryBookEntries);
    }
    public static List<SubsidiaryBookEntry> getSalesReturnBookJournalEntries() {
        return salesReturnBookJournalEntries;
    }
}
