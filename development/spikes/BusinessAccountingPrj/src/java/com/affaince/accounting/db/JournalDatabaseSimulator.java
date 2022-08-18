package com.affaince.accounting.db;

import com.affaince.accounting.journal.entity.JournalEntry;

import java.util.ArrayList;
import java.util.List;

public class JournalDatabaseSimulator {
    private static final List<JournalEntry> journalEntries = new ArrayList<>();

    public static void addJournalEntry(JournalEntry journalEntry){
        journalEntries.add(journalEntry);
    }

    public static void addJournalEntries(List<JournalEntry> newJournalEntries){
        journalEntries.addAll(newJournalEntries);
    }
    public static List<JournalEntry> getJournalEntries() {
        return journalEntries;
    }
}
