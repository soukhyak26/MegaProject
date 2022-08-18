package com.affaince.accounting.db;

import com.affaince.accounting.journal.subsidiaries.SubsidiaryBookEntry;

import java.util.ArrayList;
import java.util.List;

public class SalesBookDatabaseSimulator {
    private static final List<SubsidiaryBookEntry> salesBookJournalEntries = new ArrayList<>();

    public static void addJournalEntry(SubsidiaryBookEntry subsidiaryBookEntry){
        salesBookJournalEntries.add(subsidiaryBookEntry);
    }

    public static void addJournalEntries(List<SubsidiaryBookEntry> newSubsidiaryBookEntries){
        salesBookJournalEntries.addAll(newSubsidiaryBookEntries);
    }
    public static List<SubsidiaryBookEntry> getSalesBookJournalEntries() {
        return salesBookJournalEntries;
    }
}
