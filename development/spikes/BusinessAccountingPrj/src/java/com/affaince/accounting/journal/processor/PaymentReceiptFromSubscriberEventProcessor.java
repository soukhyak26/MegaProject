package com.affaince.accounting.journal.processor;

import com.affaince.accounting.accounts.types.LedgerAccount;
import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.journal.processor.contract.AbstractAccountIdentificationRulesProcessor;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
//giver is subscriber and receiver(beneficiary) is business bank account
public class PaymentReceiptFromSubscriberEventProcessor extends AbstractAccountIdentificationRulesProcessor {
    public LedgerAccount getDefaultGiverAccount() {
       return null;
    }

    public LedgerAccount getDefaultReceiverAccount() {
        return AccountDatabaseSimulator.searchLedgerAccountsByAccountIdentifier(AccountIdentifier.BUSINESS_BANK_ACCOUNT).get(0);
    }

    public LedgerAccount findHiddenGiverAccount() {
        return null;
    }

    public LedgerAccount findHiddenReceiverAccount() {
        return null;
    }
}
