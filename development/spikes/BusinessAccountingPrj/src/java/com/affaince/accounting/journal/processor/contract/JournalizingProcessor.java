package com.affaince.accounting.journal.processor.contract;

import com.affaince.accounting.journal.entity.JournalEntry;
import com.affaince.accounting.journal.entity.SourceDocument;

public interface JournalizingProcessor {
     public JournalEntry processJournalEntry(SourceDocument sourceDocument) throws Exception;
}
