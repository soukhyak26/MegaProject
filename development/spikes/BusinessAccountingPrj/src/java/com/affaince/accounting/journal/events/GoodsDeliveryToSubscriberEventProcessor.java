package com.affaince.accounting.journal.events;

import com.affaince.accounting.db.PartyDatabaseSimulator;
import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.transactions.Party;
import com.affaince.accounting.transactions.SourceDocument;

//Giver is sale account and receiver(beneficiary) is subscriber account
//in case of on credit mode ->sales is credited and subscriber is debited
//in case of on payment mode ->bank is debited and sales is credited
public class GoodsDeliveryToSubscriberEventProcessor extends AbstractAccountIdentificationRulesProcessor {
    public ParticipantAccount getDefaultGiverAccount(SourceDocument sourceDocument, double amountExchanged) {
        return new ParticipantAccount(null,null,sourceDocument.getMerchantId(), AccountIdentifier.BUSINESS_SALES_ACCOUNT, amountExchanged);
    }

    public ParticipantAccount getDefaultReceiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        if(sourceDocument.getModeOfTransaction() == ModeOfTransaction.BY_PAYMENT){
            return new ParticipantAccount(null,null,sourceDocument.getMerchantId(), AccountIdentifier.BUSINESS_BANK_ACCOUNT, amountExchanged);
        }else{
            Party receiverParty = PartyDatabaseSimulator.searchByMerchantIdAndPartyId(sourceDocument.getMerchantId(),sourceDocument.getReceiverParticipant().getPartyId());
            String giverAccountId = receiverParty.getAccountId();
            return new ParticipantAccount(receiverParty.getPartyId(), receiverParty.getPartyType(),giverAccountId,receiverParty.getPartyType().getAccountIdentifier(),sourceDocument.getReceiverParticipant().getAmountExchanged());
        }
    }

    public ParticipantAccount findHiddenGiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenReceiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }
}
