package com.affaince.accounting.journal.processor.events;

import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
//subscriber account is giver and sales return is default receiver
public class SalesReturnEventProcessor extends AbstractAccountIdentificationRulesProcessor {
    public ParticipantAccount getDefaultGiverAccount(String merchantId,double amountExchanged) {
        return null;
    }

    public ParticipantAccount getDefaultReceiverAccount(String merchantId,double amountExchanged) {
        return new ParticipantAccount(merchantId,AccountIdentifier.BUSINESS_SALES_RETURN_ACCOUNT,amountExchanged);
    }

    public ParticipantAccount findHiddenGiverAccount(String merchantId,double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenReceiverAccount(String merchantId,double amountExchanged) {
        return null;
    }
}
