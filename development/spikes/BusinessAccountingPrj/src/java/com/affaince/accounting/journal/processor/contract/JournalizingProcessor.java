package com.affaince.accounting.journal.processor.contract;

import com.affaince.accounting.journal.entity.SourceDocument;

public interface JournalizingProcessor {
     public void processJournalEntry(SourceDocument sourceDocument) ;
}
