package com.affaince.accounting.journal.events;

import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.transactions.SourceDocument;

import java.util.List;

public interface AccountingEventListener {
    List<ParticipantAccount> identifyParticipatingGiverAccounts(SourceDocument sourceDocument);
    List<ParticipantAccount> identifyParticipatingReceiverAccounts(SourceDocument sourceDocument);
    public void onEvent(SourceDocument sourceDocument);

}
