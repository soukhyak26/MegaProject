package com.affaince.accounting.journal.processor.contract;

import com.affaince.accounting.accounts.types.LedgerAccount;
import com.affaince.accounting.journal.entity.SourceDocument;

import java.util.List;

public interface AccountIdentificationRulesProcessor {
    LedgerAccount getDefaultGiverAccount();
    LedgerAccount getDefaultReceiverAccount();
    List<LedgerAccount> identifyParticipatingGiverAccounts(SourceDocument sourceDocument);
    List<LedgerAccount> identifyParticipatingReceiverAccounts(SourceDocument sourceDocument);
    public LedgerAccount findHiddenGiverAccount();
    public LedgerAccount findHiddenReceiverAccount();
}
