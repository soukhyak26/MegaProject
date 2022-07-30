package com.affaince.accounting.journal.processor.events;

import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

//The giver should be distribution services provider (on credit) and receiver(beneficiary) should be business services availed account
public class DistributionServicesAvailedEventProcessor extends AbstractAccountIdentificationRulesProcessor {
    public ParticipantAccount getDefaultGiverAccount(String merchantId,double amountExchanged) {
        return null;
    }

    public ParticipantAccount getDefaultReceiverAccount(String merchantId,double amountExchanged) {
        return new ParticipantAccount(merchantId,AccountIdentifier.BUSINESS_SERVICES_AVAILED_ACCOUNT,amountExchanged);
    }

    public ParticipantAccount findHiddenGiverAccount(String merchantId,double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenReceiverAccount(String merchantId,double amountExchanged) {
        return null;
    }
}
