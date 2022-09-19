package com.affaince.accounting.journal.events;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.db.PartyDatabaseSimulator;
import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.transactions.Party;
import com.affaince.accounting.transactions.SourceDocument;

//The giver should be distribution services provider (on credit) and receiver(beneficiary) should be business services availed account
//distribution service can be availed with our without payment.
//here we have made assumption that it will always be on credit.
//in case dist. service is availed with payment in that case the payment may have been made ahead or simultaneously
//in case of on credit mode -> service provider is credited and service availed acct is debited
//at payment -> service provider is debited and bank is credited

//in case of on payment mode ->service provider should be debited and bank should be credited
//in such case no service provider payment should be triggered.
public class DistributionServicesAvailedEventProcessor extends AbstractAccountingEventListener {
    public ParticipantAccount getDefaultGiverAccount(SourceDocument sourceDocument) {
        double receivedAmount = sourceDocument.getReceiverParticipant().getAmountExchanged();
        if(sourceDocument.getModeOfTransaction()== ModeOfTransaction.ON_CREDIT){
            Party giverParty = PartyDatabaseSimulator.searchByMerchantIdAndPartyId(sourceDocument.getMerchantId(),sourceDocument.getGiverParticipant().getPartyId());
            return new ParticipantAccount(sourceDocument.getGiverParticipant().getPartyId(),sourceDocument.getGiverParticipant().getPartyType(), giverParty.getAccountId(), sourceDocument.getGiverParticipant().getPartyType().getAccountIdentifier() , receivedAmount);
        }else{
            return new ParticipantAccount(null,null, AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_BANK_ACCOUNT,sourceDocument.getDateOfTransaction()).get(0).getAccountId(), AccountIdentifier.BUSINESS_BANK_ACCOUNT, receivedAmount);
        }

    }

    public ParticipantAccount getDefaultReceiverAccount(SourceDocument sourceDocument) {
        double receivedAmount = sourceDocument.getReceiverParticipant().getAmountExchanged();
        if(sourceDocument.getModeOfTransaction()==ModeOfTransaction.ON_CREDIT) {
            return new ParticipantAccount(null,null,AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdentifier(sourceDocument.getMerchantId(),AccountIdentifier.BUSINESS_SERVICES_AVAILED_ACCOUNT,sourceDocument.getDateOfTransaction()).get(0).getAccountId(), AccountIdentifier.BUSINESS_SERVICES_AVAILED_ACCOUNT, receivedAmount);
        }else{
            Party giverParty = PartyDatabaseSimulator.searchByMerchantIdAndPartyId(sourceDocument.getMerchantId(),sourceDocument.getGiverParticipant().getPartyId());
            return new ParticipantAccount(giverParty.getPartyId(), giverParty.getPartyType(),giverParty.getAccountId(),sourceDocument.getGiverParticipant().getPartyType().getAccountIdentifier() , receivedAmount);
        }
    }

    public ParticipantAccount findHiddenGiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }

    public ParticipantAccount findHiddenReceiverAccount(SourceDocument sourceDocument,double amountExchanged) {
        return null;
    }

    public void onEvent(SourceDocument sourceDocument){
        super.onEvent(sourceDocument);
    }

}
