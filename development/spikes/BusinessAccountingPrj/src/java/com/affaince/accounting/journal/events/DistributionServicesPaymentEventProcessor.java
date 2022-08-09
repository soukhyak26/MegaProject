package com.affaince.accounting.journal.events;

import com.affaince.accounting.db.PartyDatabaseSimulator;
import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.transactions.Party;
import com.affaince.accounting.transactions.SourceDocument;

//The giver should be business bank acct and receiver(beneficiary) should be business services provider account
//this event is possible only when distribution services are availed on credit.. else it is taken care in dist. services availed event
public class DistributionServicesPaymentEventProcessor extends AbstractAccountIdentificationRulesProcessor {
    public ParticipantAccount getDefaultGiverAccount(SourceDocument sourceDocument, double amountExchanged) {
        return new ParticipantAccount(null,null,sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT,amountExchanged);
    }

    public ParticipantAccount getDefaultReceiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        Party receiverParty = PartyDatabaseSimulator.searchByMerchantIdAndPartyId(sourceDocument.getMerchantId(),sourceDocument.getReceiverParticipant().getPartyId());
        AccountIdentifier receiverAccountIdentifier = receiverParty.getPartyType().getAccountIdentifier();
        return new ParticipantAccount(receiverParty.getPartyId(), receiverParty.getPartyType(),receiverParty.getAccountId(), receiverAccountIdentifier,sourceDocument.getReceiverParticipant().getAmountExchanged());
    }

    public ParticipantAccount findHiddenGiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenReceiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }
}
