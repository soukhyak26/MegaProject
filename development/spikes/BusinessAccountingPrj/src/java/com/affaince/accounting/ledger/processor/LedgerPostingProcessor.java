package com.affaince.accounting.ledger.processor;

import com.affaince.accounting.journal.entity.JournalEntry;

public interface LedgerPostingProcessor {
    void postLedgerEntry(JournalEntry journalEntry) throws Exception;
}
