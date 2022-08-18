package com.affaince.accounting.journal.processor;

import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.events.AccountIdentificationRulesProcessor;
import com.affaince.accounting.journal.processor.factory.AccountIdentificationRulesProcessorFactory;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.journal.qualifiers.TransactionEvents;
import com.affaince.accounting.journal.subsidiaries.SubsidiaryBookEntry;
import com.affaince.accounting.transactions.SourceDocument;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class SubsidiaryJournalizingProcessor {
    public List<SubsidiaryBookEntry> processJournalEntry(SourceDocument sourceDocument) throws Exception {
        //if it is credit purchase or credit sale.. then add it to purchase book or sales book
        // what about purchase return .. ?
        //what about sales return . .?
        if(sourceDocument.getModeOfTransaction()== ModeOfTransaction.ON_CREDIT){
            AccountIdentificationRulesProcessor accountIdentificationRulesProcessor = AccountIdentificationRulesProcessorFactory.getAccountIdentificationRulesProcessor(sourceDocument);
            requireNonNull(accountIdentificationRulesProcessor);
            List<ParticipantAccount> giverAccounts = accountIdentificationRulesProcessor.identifyParticipatingGiverAccounts(sourceDocument);
            List<ParticipantAccount> receiverAccounts = accountIdentificationRulesProcessor.identifyParticipatingReceiverAccounts(sourceDocument);
            if( sourceDocument.getTransactionEvent()== TransactionEvents.GOODS_PURCHASE_BY_BUSINESS ){
                return processEntryInPurchaseBook(sourceDocument,giverAccounts,receiverAccounts);
            }else if(sourceDocument.getTransactionEvent()== TransactionEvents.GOODS_DELIVERY_TO_SUBSCRIBER){
                return processEntryInSalesBook(sourceDocument,giverAccounts,receiverAccounts);
            }else if(sourceDocument.getTransactionEvent() == TransactionEvents.PURCHASE_RETURN_BY_BUSINESS){
                return processEntryInPurchaseReturnBook(sourceDocument,giverAccounts,receiverAccounts);
            }else if(sourceDocument.getTransactionEvent() == TransactionEvents.GOODS_RETURN_FROM_SUBSCRIBER){
                return processEntryInSalesReturnBook(sourceDocument,giverAccounts,receiverAccounts);
            }
        }
        return null;
    }

    public List<SubsidiaryBookEntry> processEntryInPurchaseBook(SourceDocument sourceDocument, List<ParticipantAccount> giverAccounts, List<ParticipantAccount> receiverAccounts){
        //in case of credit purchase from supplier.. default giver is supplier
        List<SubsidiaryBookEntry> purchaseBookEntries = new ArrayList<>();
        for(ParticipantAccount participantAccount: giverAccounts) {
            SubsidiaryBookEntry.SubsidiaryBookEntryBuilder subsidiaryBookEntryBuilder = SubsidiaryBookEntry.newBuilder();
            purchaseBookEntries.add(subsidiaryBookEntryBuilder
                    .merchantId(sourceDocument.getMerchantId())
                    .dateOfTransaction(sourceDocument.getDateOfTransaction())
                    .partyId(participantAccount.getPartyId())
                    .partyType(participantAccount.getPartyType())
                    .accountId(participantAccount.getAccountId())
                    .accountIdentifier(participantAccount.getAccountIdentifier())
                    .transactionEntityDetailList(sourceDocument.getTransactionEntityDetails())
                    .invoiceNumber("XX")
                    .totalPrice(sourceDocument.getTransactionAmount())
                    .remarks(sourceDocument.getDescription())
                    .build());
        }
        return purchaseBookEntries;
    }

    public List<SubsidiaryBookEntry> processEntryInSalesBook(SourceDocument sourceDocument, List<ParticipantAccount> giverAccounts, List<ParticipantAccount> receiverAccounts){
        List<SubsidiaryBookEntry> saleBookEntries = new ArrayList<>();
        for(ParticipantAccount participantAccount: receiverAccounts) {
            SubsidiaryBookEntry.SubsidiaryBookEntryBuilder salesBookEntryBuilder = SubsidiaryBookEntry.newBuilder();
            saleBookEntries.add(salesBookEntryBuilder
                    .merchantId(sourceDocument.getMerchantId())
                    .dateOfTransaction(sourceDocument.getDateOfTransaction())
                    .partyId(participantAccount.getPartyId())
                    .partyType(participantAccount.getPartyType())
                    .accountId(participantAccount.getAccountId())
                    .accountIdentifier(participantAccount.getAccountIdentifier())
                    .transactionEntityDetailList(sourceDocument.getTransactionEntityDetails())
                    .invoiceNumber("XX")
                    .totalPrice(sourceDocument.getTransactionAmount())
                    .remarks(sourceDocument.getDescription())
                    .build());
        }
        return saleBookEntries;

    }

    public List<SubsidiaryBookEntry> processEntryInPurchaseReturnBook(SourceDocument sourceDocument,List<ParticipantAccount> giverAccounts,List<ParticipantAccount> receiverAccounts){
        List<SubsidiaryBookEntry> purchaseReturnBookEntries = new ArrayList<>();
        for(ParticipantAccount participantAccount: giverAccounts) {
            SubsidiaryBookEntry.SubsidiaryBookEntryBuilder purchaseReturnBookEntryBuilder = SubsidiaryBookEntry.newBuilder();
            purchaseReturnBookEntries.add(purchaseReturnBookEntryBuilder
                    .merchantId(sourceDocument.getMerchantId())
                    .dateOfTransaction(sourceDocument.getDateOfTransaction())
                    .partyId(participantAccount.getPartyId())
                    .partyType(participantAccount.getPartyType())
                    .accountId(participantAccount.getAccountId())
                    .accountIdentifier(participantAccount.getAccountIdentifier())
                    .transactionEntityDetailList(sourceDocument.getTransactionEntityDetails())
                    .invoiceNumber("XX")
                    .totalPrice(sourceDocument.getTransactionAmount())
                    .remarks(sourceDocument.getDescription())
                    .build());
        }
        return purchaseReturnBookEntries;

    }
    public List<SubsidiaryBookEntry> processEntryInSalesReturnBook(SourceDocument sourceDocument,List<ParticipantAccount> giverAccounts,List<ParticipantAccount> receiverAccounts){
        List<SubsidiaryBookEntry> salesReturnBookEntries = new ArrayList<>();
        for(ParticipantAccount participantAccount: receiverAccounts) {
            SubsidiaryBookEntry.SubsidiaryBookEntryBuilder salesReturnBookEntryBuilder = SubsidiaryBookEntry.newBuilder();
            salesReturnBookEntries.add(salesReturnBookEntryBuilder
                    .merchantId(sourceDocument.getMerchantId())
                    .dateOfTransaction(sourceDocument.getDateOfTransaction())
                    .partyId(participantAccount.getPartyId())
                    .partyType(participantAccount.getPartyType())
                    .accountId(participantAccount.getAccountId())
                    .accountIdentifier(participantAccount.getAccountIdentifier())
                    .transactionEntityDetailList(sourceDocument.getTransactionEntityDetails())
                    .invoiceNumber("XX")
                    .totalPrice(sourceDocument.getTransactionAmount())
                    .remarks(sourceDocument.getDescription())
                    .build());
        }
        return salesReturnBookEntries;

    }
}
