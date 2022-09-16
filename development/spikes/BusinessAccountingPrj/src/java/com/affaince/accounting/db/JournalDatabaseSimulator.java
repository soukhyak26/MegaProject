package com.affaince.accounting.db;

import com.affaince.accounting.journal.entity.JournalRecord;

import java.util.ArrayList;
import java.util.List;

public class JournalDatabaseSimulator {
    private static final List<JournalRecord> journalEntries = new ArrayList<>();

    public static void addJournalEntry(JournalRecord journalRecord){
        journalEntries.add(journalRecord);
    }

    public static void addJournalEntries(List<JournalRecord> newJournalEntries){
        journalEntries.addAll(newJournalEntries);
    }
    public static List<JournalRecord> getJournalEntries() {
        return journalEntries;
    }
}
