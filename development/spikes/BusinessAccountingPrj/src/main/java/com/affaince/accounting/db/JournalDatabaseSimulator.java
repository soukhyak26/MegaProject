package com.affaince.accounting.db;

import com.affaince.accounting.journal.entity.JournalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JournalDatabaseSimulator {
    private List<JournalRecord> journalEntries ;
    @Autowired
    public JournalDatabaseSimulator(){
        journalEntries = new ArrayList<>();
    }
    public void addJournalEntry(JournalRecord journalRecord){
        journalEntries.add(journalRecord);
    }

    public void addJournalEntries(List<JournalRecord> newJournalEntries){
        journalEntries.addAll(newJournalEntries);
    }
    public List<JournalRecord> getJournalEntries() {
        return journalEntries;
    }
}
