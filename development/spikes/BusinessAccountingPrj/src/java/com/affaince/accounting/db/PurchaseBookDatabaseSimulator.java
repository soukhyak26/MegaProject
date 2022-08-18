package com.affaince.accounting.db;

import com.affaince.accounting.journal.subsidiaries.SubsidiaryBookEntry;

import java.util.ArrayList;
import java.util.List;

public class PurchaseBookDatabaseSimulator {
    private static final List<SubsidiaryBookEntry> purchaseBookJournalEntries = new ArrayList<>();

    public static void addJournalEntry(SubsidiaryBookEntry subsidiaryBookEntry){
        purchaseBookJournalEntries.add(subsidiaryBookEntry);
    }

    public static void addJournalEntries(List<SubsidiaryBookEntry> newSubsidiaryBookEntries){
        purchaseBookJournalEntries.addAll(newSubsidiaryBookEntries);
    }
    public static List<SubsidiaryBookEntry> getPurchaseBookJournalEntries() {
        return purchaseBookJournalEntries;
    }
}
