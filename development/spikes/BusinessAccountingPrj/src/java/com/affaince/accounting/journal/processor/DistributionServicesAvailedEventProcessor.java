package com.affaince.accounting.journal.processor;

import com.affaince.accounting.accounts.types.LedgerAccount;
import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.journal.processor.contract.AbstractAccountIdentificationRulesProcessor;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

//The giver should be distribution services provider (on credit) and receiver(beneficiary) should be business services availed account
public class DistributionServicesAvailedEventProcessor extends AbstractAccountIdentificationRulesProcessor {
    public LedgerAccount getDefaultGiverAccount() {
        return null;
    }

    public LedgerAccount getDefaultReceiverAccount() {
        return AccountDatabaseSimulator.searchLedgerAccountsByAccountIdentifier(AccountIdentifier.BUSINESS_SERVICES_AVAILED_ACCOUNT).get(0);
    }

    public LedgerAccount findHiddenGiverAccount() {
        return null;
    }

    public LedgerAccount findHiddenReceiverAccount() {
        return null;
    }
}
