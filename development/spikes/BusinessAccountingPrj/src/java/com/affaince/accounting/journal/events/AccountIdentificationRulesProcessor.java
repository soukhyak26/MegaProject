package com.affaince.accounting.journal.events;

import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.transactions.SourceDocument;

import java.util.List;

public interface AccountIdentificationRulesProcessor {
    List<ParticipantAccount> identifyParticipatingGiverAccounts(SourceDocument sourceDocument);
    List<ParticipantAccount> identifyParticipatingReceiverAccounts(SourceDocument sourceDocument);

}
