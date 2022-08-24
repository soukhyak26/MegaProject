package com.affaince.accounting.journal.events;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.transactions.SourceDocument;

//giver is bank account, receiver is rent account
public class PremiseRentPaymentEventProcessor extends AbstractAccountIdentificationRulesProcessor {
    public ParticipantAccount getDefaultGiverAccount(SourceDocument sourceDocument, double amountExchanged) {
        return new ParticipantAccount(null,null, AccountDatabaseSimulator.searchLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT).get(0).getAccountId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT,amountExchanged);
    }

    public ParticipantAccount getDefaultReceiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return new ParticipantAccount(null,null,AccountDatabaseSimulator.searchLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(),AccountIdentifier.OWNER_OF_PREMISE_RENTED_ACCOUNT).get(0).getAccountId(),AccountIdentifier.OWNER_OF_PREMISE_RENTED_ACCOUNT,amountExchanged);
    }

    public ParticipantAccount findHiddenGiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenReceiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }
}
