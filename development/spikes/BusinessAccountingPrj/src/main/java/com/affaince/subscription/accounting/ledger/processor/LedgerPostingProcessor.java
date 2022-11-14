package com.affaince.subscription.accounting.ledger.processor;

import com.affaince.subscription.accounting.journal.entity.JournalRecord;

public interface LedgerPostingProcessor {
    void postLedgerEntry(JournalRecord journalRecord) throws Exception;
}
