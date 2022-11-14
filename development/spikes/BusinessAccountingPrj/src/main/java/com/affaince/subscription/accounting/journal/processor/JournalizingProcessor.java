package com.affaince.subscription.accounting.journal.processor;

import com.affaince.subscription.accounting.journal.entity.JournalRecord;
import com.affaince.subscription.accounting.journal.entity.ParticipantAccount;
import com.affaince.subscription.accounting.transactions.SourceDocument;

import java.util.List;

public interface JournalizingProcessor {
     public JournalRecord processJournalEntry(SourceDocument sourceDocument, List<ParticipantAccount> giverAccounts, List<ParticipantAccount> receiverAccounts) throws Exception;
}

