package com.affaince.accounting.journal.processor;

import com.affaince.accounting.journal.entity.JournalEntry;
import com.affaince.accounting.transactions.SourceDocument;

public interface JournalizingProcessor {
     public JournalEntry processJournalEntry(SourceDocument sourceDocument) throws Exception;
}
