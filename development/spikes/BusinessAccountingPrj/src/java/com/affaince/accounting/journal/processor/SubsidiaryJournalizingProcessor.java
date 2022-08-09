package com.affaince.accounting.journal.processor;

import com.affaince.accounting.journal.entity.JournalEntry;
import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.events.AccountIdentificationRulesProcessor;
import com.affaince.accounting.journal.processor.factory.AccountIdentificationRulesProcessorFactory;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.journal.qualifiers.TransactionEvents;
import com.affaince.accounting.journal.subsidiaries.PurchaseReturnBookEntry;
import com.affaince.accounting.journal.subsidiaries.SaleBookEntry;
import com.affaince.accounting.journal.subsidiaries.SalesReturnBookEntry;
import com.affaince.accounting.transactions.SourceDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<SaleBookEntry> processEntryInPurchaseBook(SourceDocument sourceDocument, List<ParticipantAccount> giverAccounts, List<ParticipantAccount> receiverAccounts){
        //in case of credit purchase from supplier.. default giver is supplier
        List<SaleBookEntry> purchaseBookEntries = new ArrayList<>();
        for(ParticipantAccount participantAccount: giverAccounts) {
            SaleBookEntry.SaleBookEntryBuilder purchaseBookEntryBuilder = SaleBookEntry.newBuilder();
            purchaseBookEntries.add(purchaseBookEntryBuilder
                    .merchantId(sourceDocument.getMerchantId())
                    .dateOfCreditPurchase(sourceDocument.getDateOfTransaction())
                    .partyId(participantAccount.getPartyId())
                    .partyType(participantAccount.getPartyType())
                    .accountId(participantAccount.getAccountId())
                    .accountIdentifier(participantAccount.getAccountIdentifier())
                    .transactionEntityDetailList(sourceDocument.getTransactionEntityDetails())
                    .inwardInvoiceNumber("XX")
                    .totalPrice(sourceDocument.getTransactionAmount())
                    .remarks(sourceDocument.getDescription())
                    .build());
        }
        return purchaseBookEntries;
    }

    public List<SaleBookEntry> processEntryInSalesBook(SourceDocument sourceDocument,List<ParticipantAccount> giverAccounts,List<ParticipantAccount> receiverAccounts){
        List<SaleBookEntry> saleBookEntries = new ArrayList<>();
        for(ParticipantAccount participantAccount: receiverAccounts) {
            SaleBookEntry.SaleBookEntryBuilder saleBookEntryBuilder = SaleBookEntry.newBuilder();
            saleBookEntries.add(saleBookEntryBuilder
                    .merchantId(sourceDocument.getMerchantId())
                    .dateOfCreditPurchase(sourceDocument.getDateOfTransaction())
                    .partyId(participantAccount.getPartyId())
                    .partyType(participantAccount.getPartyType())
                    .accountId(participantAccount.getAccountId())
                    .accountIdentifier(participantAccount.getAccountIdentifier())
                    .transactionEntityDetailList(sourceDocument.getTransactionEntityDetails())
                    .inwardInvoiceNumber("XX")
                    .totalPrice(sourceDocument.getTransactionAmount())
                    .remarks(sourceDocument.getDescription())
                    .build());
        }
        return saleBookEntries;

    }

    public List<PurchaseReturnBookEntry> processEntryInPurchaseReturnBook(SourceDocument sourceDocument,List<ParticipantAccount> giverAccounts,List<ParticipantAccount> receiverAccounts){
        List<PurchaseReturnBookEntry> purchaseReturnBookEntries = new ArrayList<>();
        for(ParticipantAccount participantAccount: giverAccounts) {
            PurchaseReturnBookEntry.PurchaseReturnBookEntryBuilder purchaseReturnBookEntryBuilder = PurchaseReturnBookEntry.newBuilder();
            purchaseReturnBookEntries.add(purchaseReturnBookEntryBuilder
                    .merchantId(sourceDocument.getMerchantId())
                    .dateOfCreditPurchaseReturn(sourceDocument.getDateOfTransaction())
                    .partyId(participantAccount.getPartyId())
                    .partyType(participantAccount.getPartyType())
                    .accountId(participantAccount.getAccountId())
                    .accountIdentifier(participantAccount.getAccountIdentifier())
                    .transactionEntityDetailList(sourceDocument.getTransactionEntityDetails())
                    .debitNoteNumber("XX")
                    .totalPrice(sourceDocument.getTransactionAmount())
                    .remarks(sourceDocument.getDescription())
                    .build());
        }
        return purchaseReturnBookEntries;

    }
    public List<SalesReturnBookEntry> processEntryInSalesReturnBook(SourceDocument sourceDocument,List<ParticipantAccount> giverAccounts,List<ParticipantAccount> receiverAccounts){
        List<SalesReturnBookEntry> salesReturnBookEntries = new ArrayList<>();
        for(ParticipantAccount participantAccount: receiverAccounts) {
            SalesReturnBookEntry.SalesReturnBookEntryBuilder salesReturnBookEntryBuilder = SalesReturnBookEntry.newBuilder();
            salesReturnBookEntries.add(salesReturnBookEntryBuilder
                    .merchantId(sourceDocument.getMerchantId())
                    .dateOfCreditSaleReturn(sourceDocument.getDateOfTransaction())
                    .partyId(participantAccount.getPartyId())
                    .partyType(participantAccount.getPartyType())
                    .accountId(participantAccount.getAccountId())
                    .accountIdentifier(participantAccount.getAccountIdentifier())
                    .transactionEntityDetailList(sourceDocument.getTransactionEntityDetails())
                    .creditNoteNumber("XX")
                    .totalPrice(sourceDocument.getTransactionAmount())
                    .remarks(sourceDocument.getDescription())
                    .build());
        }
        return salesReturnBookEntries;

    }
}
