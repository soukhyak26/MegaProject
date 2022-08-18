package com.affaince.accounting.journal.events;

import com.affaince.accounting.journal.entity.Participant;
import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.transactions.SourceDocument;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAccountIdentificationRulesProcessor implements AccountIdentificationRulesProcessor {
    @Override
    public List<ParticipantAccount> identifyParticipatingGiverAccounts(SourceDocument sourceDocument) {
        final Participant giverParticipant = sourceDocument.getGiverParticipant();
        final List<ParticipantAccount> giverAccounts=new ArrayList<>() ;;
        final double transactionAmount = sourceDocument.getTransactionAmount();
        final double giverTransactionAmount = giverParticipant.getAmountExchanged();

        //typically the last year's residual amount will be rolled over into next year capital account
        //if the known giver has given 3000 but transaction amount is 5000
        //it means there are more than one givers
        if(transactionAmount > giverTransactionAmount){
            ParticipantAccount hiddenGiverAccount = findHiddenGiverAccount(sourceDocument,(transactionAmount-giverTransactionAmount));
            if( null != hiddenGiverAccount){
                giverAccounts.add(hiddenGiverAccount);
            }
        }
        //If giver has given 5000 but transaction amount is only 3000
        // this should be invalid scenario.. adn should cause exception
        // transaction amount has to be same or grater than giver as well as receiver.
        else if(transactionAmount < giverTransactionAmount){
            System.out.println("Not a valid scenario");
        }
        ParticipantAccount giverAccount = getDefaultGiverAccount(sourceDocument,giverParticipant.getAmountExchanged());
        giverAccounts.add(giverAccount);
        return giverAccounts;
    }

    @Override
    public List<ParticipantAccount> identifyParticipatingReceiverAccounts(SourceDocument sourceDocument) {
        Participant receiverParticipant = sourceDocument.getReceiverParticipant();
        double receiverAmount = receiverParticipant.getAmountExchanged();
        List<ParticipantAccount> receiverAccounts = new ArrayList<>() ;;
        double transactionAmount = sourceDocument.getTransactionAmount();
        //If transaction amount is more than receiver amount it means
        // additional amount is received by some hidden receiver.
        if(transactionAmount > receiverAmount){
            ParticipantAccount hiddenReceiverAccount = findHiddenReceiverAccount(sourceDocument,(transactionAmount-receiverAmount));
            if( null != hiddenReceiverAccount){
                receiverAccounts.add(hiddenReceiverAccount);
            }
        }
        //in this case part of the amount invested is getting diverted to some other account
        //this should be invalid scenario and should cause exception
        // transaction amount has to be same or greater than giver as well as receiver.
        else if(transactionAmount < receiverAmount){
            System.out.println("Not a valid scenario");
        }
        ParticipantAccount receiverAccount = getDefaultReceiverAccount(sourceDocument,receiverParticipant.getAmountExchanged());
        receiverAccounts.add(receiverAccount);
        return receiverAccounts;
    }

    public abstract ParticipantAccount getDefaultGiverAccount(SourceDocument sourceDocument,double amountExchanged);
    public abstract ParticipantAccount getDefaultReceiverAccount(SourceDocument sourceDocument,double amountExchanged);
    public abstract ParticipantAccount findHiddenGiverAccount(SourceDocument sourceDocument,double amountExchanged);
    public abstract ParticipantAccount findHiddenReceiverAccount(SourceDocument sourceDocument,double amountExchanged);


}
