package com.affaince.accounting.ledger.processor;

import com.affaince.accounting.journal.entity.JournalRecord;

public interface LedgerPostingProcessor {
    void postLedgerEntry(JournalRecord journalRecord) throws Exception;
}
