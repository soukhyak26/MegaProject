package com.affaince.accounting.journal.processor;

import com.affaince.accounting.accounts.types.LedgerAccount;
import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.journal.processor.contract.AbstractAccountIdentificationRulesProcessor;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

//The giver should be business bank acct and receiver(beneficiary) should be business services provider account
public class DistributionServicesPaymentEventProcessor extends AbstractAccountIdentificationRulesProcessor {
    public LedgerAccount getDefaultGiverAccount() {
        return AccountDatabaseSimulator.searchLedgerAccountsByAccountIdentifier(AccountIdentifier.BUSINESS_BANK_ACCOUNT).get(0);
    }

    public LedgerAccount getDefaultReceiverAccount() {
        return null;
    }

    public LedgerAccount findHiddenGiverAccount() {
        return null;
    }

    public LedgerAccount findHiddenReceiverAccount() {
        return null;
    }
}
