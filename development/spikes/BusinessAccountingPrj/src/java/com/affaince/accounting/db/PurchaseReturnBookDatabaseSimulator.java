package com.affaince.accounting.db;

import com.affaince.accounting.journal.subsidiaries.SubsidiaryBookEntry;

import java.util.ArrayList;
import java.util.List;

public class PurchaseReturnBookDatabaseSimulator {
    private static final List<SubsidiaryBookEntry> purchaseReturnBookJournalEntries = new ArrayList<>();

    public static void addJournalEntry(SubsidiaryBookEntry subsidiaryBookEntry){
        purchaseReturnBookJournalEntries.add(subsidiaryBookEntry);
    }

    public static void addJournalEntries(List<SubsidiaryBookEntry> newSubsidiaryBookEntries){
        purchaseReturnBookJournalEntries.addAll(newSubsidiaryBookEntries);
    }
    public static List<SubsidiaryBookEntry> getPurchaseReturnBookJournalEntries() {
        return purchaseReturnBookJournalEntries;
    }
}
