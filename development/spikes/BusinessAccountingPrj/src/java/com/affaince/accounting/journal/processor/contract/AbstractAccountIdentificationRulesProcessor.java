package com.affaince.accounting.journal.processor.contract;

import com.affaince.accounting.accounts.types.LedgerAccount;
import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.db.PartyDatabaseSimulator;
import com.affaince.accounting.journal.entity.Participant;
import com.affaince.accounting.journal.entity.Party;
import com.affaince.accounting.journal.entity.SourceDocument;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.journal.qualifiers.PartyTypes;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAccountIdentificationRulesProcessor implements AccountIdentificationRulesProcessor {
    @Override
    public List<LedgerAccount> identifyParticipatingGiverAccounts(SourceDocument sourceDocument) {
        Participant giverParticipant = sourceDocument.getGiverParticipant();
        List<LedgerAccount> giverAccounts;
        if(null != giverParticipant.getPartyId() || giverParticipant.getPartyType() != PartyTypes.BUSINESS) {
            Party giverParty = PartyDatabaseSimulator.searchByPartyId(giverParticipant.getPartyId());
            String giverAccountId = giverParty.getAccountId();
            giverAccounts= AccountDatabaseSimulator.searchLedgerAccountsByAccountIdAndAccountIdentifier(giverAccountId,giverParty.getPartyType().getAccountIdentifier());
        }else{
            LedgerAccount giverAccount = getDefaultGiverAccount();
            giverAccounts = new ArrayList<>() ;
            giverAccounts.add(giverAccount);
        }

        double transactionAmount = sourceDocument.getTransactionAmount();
        double giverTransactionAmount = giverParticipant.getAmountExchanged();

        //typically the last year's residual amount will be rolled over into next year capital account
        //if the known giver has given 3000 but transaction amount is 5000
        //it means there are more than one givers
        if(transactionAmount > giverTransactionAmount){
            LedgerAccount hiddenGiverAccount = findHiddenGiverAccount();
            if( null != hiddenGiverAccount){
                giverAccounts.add(hiddenGiverAccount);
            }
        }
        //If giver has given 5000 but transaction amount is only 3000
        // this should be invalid scenario.. adn should cause exception
        // transaction amount has to be same or grater than giver as well as receiver.
        else if(transactionAmount < giverTransactionAmount){
        /* LedgerAccount hiddenReceiverAccount = findHiddenReceiverAccount();
            if( null != hiddenReceiverAccount){
                giverAccounts.add(hiddenGiverAccount);
            }*/
        }
        return giverAccounts;
    }

    @Override
    public List<LedgerAccount> identifyParticipatingReceiverAccounts(SourceDocument sourceDocument) {

        Participant receiverParticipant = sourceDocument.getReceiverParticipant();
        List<LedgerAccount> receiverAccounts;
        if(null != receiverParticipant.getPartyId() || receiverParticipant.getPartyType() != PartyTypes.BUSINESS) {
            Party receiverParty = PartyDatabaseSimulator.searchByPartyId(receiverParticipant.getPartyId());
            AccountIdentifier receiverAccountIdentifier = receiverParty.getPartyType().getAccountIdentifier();
            receiverAccounts = AccountDatabaseSimulator.searchLedgerAccountsByAccountIdAndAccountIdentifier(receiverParty.getAccountId(), receiverAccountIdentifier);
        }else{
            LedgerAccount receiverAccount = getDefaultReceiverAccount();
            receiverAccounts = new ArrayList<>() ;
            receiverAccounts.add(receiverAccount);
        }

        double receiverAmount = receiverParticipant.getAmountExchanged();


        double transactionAmount = sourceDocument.getTransactionAmount();
        //If transaction amount is more than receiver amount it means
        // additional amount is received by some hidden receiver.
        if(transactionAmount > receiverAmount){
            LedgerAccount hiddenReceiverAccount = findHiddenReceiverAccount();
            if( null != hiddenReceiverAccount){
                receiverAccounts.add(hiddenReceiverAccount);
            }
        }
        //in this case part of the amount invested is getting diverted to some other account
        //this should be invalid scenario and should cause exception
        // transaction amount has to be same or greater than giver as well as receiver.
        else if(transactionAmount < receiverAmount){
            LedgerAccount hiddenGiverAccount = findHiddenGiverAccount();
            if( null != hiddenGiverAccount){
                receiverAccounts.add(hiddenGiverAccount);
            }
        }
        return receiverAccounts;
    }

}
