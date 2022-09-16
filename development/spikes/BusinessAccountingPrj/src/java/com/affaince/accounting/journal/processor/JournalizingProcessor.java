package com.affaince.accounting.journal.processor;

import com.affaince.accounting.journal.entity.JournalRecord;
import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.transactions.SourceDocument;

import java.util.List;

public interface JournalizingProcessor {
     public JournalRecord processJournalEntry(SourceDocument sourceDocument, List<ParticipantAccount> giverAccounts, List<ParticipantAccount> receiverAccounts) throws Exception;
}

