package com.affaince.accounting.journal.processor.events;

import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
//Giver is sale account and receiver(beneficiary) is subscriber account
public class GoodsDeliveryToSubscriberEventProcessor extends AbstractAccountIdentificationRulesProcessor {
    public ParticipantAccount getDefaultGiverAccount(String merchantId,double amountExchanged) {
        return new ParticipantAccount(merchantId,AccountIdentifier.BUSINESS_SALES_ACCOUNT,amountExchanged);
    }

    public ParticipantAccount getDefaultReceiverAccount(String merchantId,double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenGiverAccount(String merchantId,double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenReceiverAccount(String merchantId,double amountExchanged) {
        return null;
    }
}
