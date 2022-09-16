package com.affaince.accounting.journal.processor;

import com.affaince.accounting.journal.entity.JournalRecord;
import com.affaince.accounting.transactions.SourceDocument;

public interface JournalizingProcessor {
     public JournalRecord processJournalEntry(SourceDocument sourceDocument) throws Exception;
}

