package com.affaince.subscription.accounting.journal.events;

import com.affaince.subscription.accounting.journal.entity.ParticipantAccount;
import com.affaince.subscription.accounting.transactions.SourceDocument;

import java.util.List;

public interface AccountingEventListener {
    List<ParticipantAccount> identifyParticipatingGiverAccounts(SourceDocument sourceDocument);
    List<ParticipantAccount> identifyParticipatingReceiverAccounts(SourceDocument sourceDocument);
    public void onEvent(SourceDocument sourceDocument);

}
