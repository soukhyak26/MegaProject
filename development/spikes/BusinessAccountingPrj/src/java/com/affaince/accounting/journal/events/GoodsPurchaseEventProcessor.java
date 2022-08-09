package com.affaince.accounting.journal.events;

import com.affaince.accounting.db.PartyDatabaseSimulator;
import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.transactions.Party;
import com.affaince.accounting.transactions.SourceDocument;

//giver is supplier account and receiver(beneficiary ) is business purchase account
//in case of credit purchase -> purchase is debited and Supplier is credited
//in case of cash purchase ->purchase is debited and bank account is credited
public class GoodsPurchaseEventProcessor extends AbstractAccountIdentificationRulesProcessor {
    public ParticipantAccount getDefaultGiverAccount(SourceDocument sourceDocument, double amountExchanged) {
        if(sourceDocument.getModeOfTransaction()== ModeOfTransaction.ON_CREDIT){
            Party giverParty = PartyDatabaseSimulator.searchByMerchantIdAndPartyId(sourceDocument.getMerchantId(),sourceDocument.getGiverParticipant().getPartyId());
            String giverAccountId = giverParty.getAccountId();
            return new ParticipantAccount(giverParty.getPartyId(),giverParty.getPartyType(),giverAccountId,giverParty.getPartyType().getAccountIdentifier(),sourceDocument.getGiverParticipant().getAmountExchanged());
        }else {
            return new ParticipantAccount(null,null,sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT,amountExchanged);
        }
    }

    public ParticipantAccount getDefaultReceiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return new ParticipantAccount(null,null,sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_PURCHASE_ACCOUNT,amountExchanged);
    }

    public ParticipantAccount findHiddenGiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenReceiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }
}
