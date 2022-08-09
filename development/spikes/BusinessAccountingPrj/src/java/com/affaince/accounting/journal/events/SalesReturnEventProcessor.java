package com.affaince.accounting.journal.events;

import com.affaince.accounting.db.PartyDatabaseSimulator;
import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.transactions.Party;
import com.affaince.accounting.transactions.SourceDocument;

//subscriber account is giver and sales return is default receiver
//debit sales return in case of cash or on credit transaction
//in case of credit transaction credit receiver subscriber,in case of cash transaction credit bank
public class SalesReturnEventProcessor extends AbstractAccountIdentificationRulesProcessor {

    public ParticipantAccount getDefaultGiverAccount(SourceDocument sourceDocument, double amountExchanged) {
        if(sourceDocument.getModeOfTransaction() == ModeOfTransaction.ON_CREDIT){
            Party giverParty = PartyDatabaseSimulator.searchByMerchantIdAndPartyId(sourceDocument.getMerchantId(),sourceDocument.getGiverParticipant().getPartyId());
            String giverAccountId = giverParty.getAccountId();
            return new ParticipantAccount(giverParty.getPartyId(),giverParty.getPartyType(),giverAccountId,giverParty.getPartyType().getAccountIdentifier(),sourceDocument.getGiverParticipant().getAmountExchanged());
        }else{
            return new ParticipantAccount(null,null,sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT,amountExchanged);
        }
    }

    public ParticipantAccount getDefaultReceiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return new ParticipantAccount(null,null,sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_SALES_RETURN_ACCOUNT,amountExchanged);
    }

    public ParticipantAccount findHiddenGiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenReceiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }
}
