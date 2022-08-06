package com.affaince.accounting.journal.processor;

import com.affaince.accounting.journal.entity.JournalEntry;
import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.events.AccountIdentificationRulesProcessor;
import com.affaince.accounting.journal.processor.factory.AccountIdentificationRulesProcessorFactory;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.journal.qualifiers.TransactionEvents;
import com.affaince.accounting.transactions.SourceDocument;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class SubsidiaryJournalizingProcessor implements JournalizingProcessor{
    @Override
    public JournalEntry processJournalEntry(SourceDocument sourceDocument) throws Exception {
        //if it is credit purchase or credit sale.. then add it to purchase book or sales book
        // what about purchase return .. ?
        //what about sales return . .?
        if(sourceDocument.getModeOfTransaction()== ModeOfTransaction.ON_CREDIT){
            AccountIdentificationRulesProcessor accountIdentificationRulesProcessor = AccountIdentificationRulesProcessorFactory.getAccountIdentificationRulesProcessor(sourceDocument);
            requireNonNull(accountIdentificationRulesProcessor);
            List<ParticipantAccount> giverAccounts = accountIdentificationRulesProcessor.identifyParticipatingGiverAccounts(sourceDocument);
            List<ParticipantAccount> receiverAccounts = accountIdentificationRulesProcessor.identifyParticipatingReceiverAccounts(sourceDocument);
            if( sourceDocument.getTransactionEvent()== TransactionEvents.GOODS_PURCHASE_BY_BUSINESS ){
                processEntryInPurchaseBook(sourceDocument,giverAccounts,receiverAccounts);
            }else if(sourceDocument.getTransactionEvent()== TransactionEvents.GOODS_DELIVERY_TO_SUBSCRIBER){
                processEntryInSalesBook(sourceDocument,giverAccounts,receiverAccounts);
            }else if(sourceDocument.getTransactionEvent() == TransactionEvents.PURCHASE_RETURN_BY_BUSINESS){
                processEntryInPurchaseReturnBook(sourceDocument,giverAccounts,receiverAccounts);
            }else if(sourceDocument.getTransactionEvent() == TransactionEvents.GOODS_RETURN_FROM_SUBSCRIBER){
                processEntryInSalesReturnBook(sourceDocument,giverAccounts,receiverAccounts);
            }


        }

        return null;
    }

    public void processEntryInPurchaseBook(SourceDocument sourceDocument,List<ParticipantAccount> giverAccounts,List<ParticipantAccount> receiverAccounts){
        //in case of credit purchase from supplier.. default giver is supplier
    }

    public void processEntryInSalesBook(SourceDocument sourceDocument,List<ParticipantAccount> giverAccounts,List<ParticipantAccount> receiverAccounts){

    }

    public void processEntryInPurchaseReturnBook(SourceDocument sourceDocument,List<ParticipantAccount> giverAccounts,List<ParticipantAccount> receiverAccounts){

    }
    public void processEntryInSalesReturnBook(SourceDocument sourceDocument,List<ParticipantAccount> giverAccounts,List<ParticipantAccount> receiverAccounts){

    }
}
