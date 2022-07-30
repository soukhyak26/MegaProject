package com.affaince.accounting.journal.processor.events;

import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.transactions.SourceDocument;

import java.util.List;

public interface AccountIdentificationRulesProcessor {
    ParticipantAccount getDefaultGiverAccount(String merchantId,double amountExchanged);
    ParticipantAccount getDefaultReceiverAccount(String merchantId,double amountExchanged);
    List<ParticipantAccount> identifyParticipatingGiverAccounts(SourceDocument sourceDocument);
    List<ParticipantAccount> identifyParticipatingReceiverAccounts(SourceDocument sourceDocument);
    ParticipantAccount findHiddenGiverAccount(String merchantId,double amountExchanged);
    ParticipantAccount findHiddenReceiverAccount(String merchantId,double amountExchanged);

}
