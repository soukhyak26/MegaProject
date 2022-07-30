package com.affaince.accounting.journal.processor.events;

import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
//giver is bank account receiver is taxPaymentAccount
public class TaxPaymentEventProcessor extends AbstractAccountIdentificationRulesProcessor {
    public ParticipantAccount getDefaultGiverAccount(String merchantId,double amountExchanged) {
        return new ParticipantAccount(merchantId,AccountIdentifier.BUSINESS_BANK_ACCOUNT,amountExchanged);
    }

    public ParticipantAccount getDefaultReceiverAccount(String merchantId,double amountExchanged) {
        return new ParticipantAccount(merchantId,AccountIdentifier.TAX_ACCOUNT,amountExchanged);
    }

    public ParticipantAccount findHiddenGiverAccount(String merchantId,double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenReceiverAccount(String merchantId,double amountExchanged) {
        return null;
    }
}
