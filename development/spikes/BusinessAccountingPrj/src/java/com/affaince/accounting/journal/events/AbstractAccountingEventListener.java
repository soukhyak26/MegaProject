package com.affaince.accounting.journal.events;

import com.affaince.accounting.db.*;
import com.affaince.accounting.journal.entity.JournalRecord;
import com.affaince.accounting.journal.entity.Participant;
import com.affaince.accounting.journal.entity.ParticipantAccount;
import com.affaince.accounting.journal.processor.CashBookJournalizingProcessor;
import com.affaince.accounting.journal.processor.DefaultJournalizingProcessor;
import com.affaince.accounting.journal.processor.JournalizingProcessor;
import com.affaince.accounting.journal.processor.SubsidiaryJournalizingProcessor;
import com.affaince.accounting.journal.qualifiers.AccountingEvent;
import com.affaince.accounting.journal.subsidiaries.CashBookEntry;
import com.affaince.accounting.journal.subsidiaries.SubsidiaryBookEntry;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.ledger.processor.DefaultLedgerPostingProcessor;
import com.affaince.accounting.ledger.processor.LedgerPostingProcessor;
import com.affaince.accounting.transactions.SourceDocument;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAccountingEventListener implements AccountingEventListener {
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
            System.out.println("Not a valid scenario. " + sourceDocument.getAccountingEvent());
            System.out.println("giver: " + giverParticipant.getPartyId() + ": " + giverParticipant.getPartyType());
        }
        ParticipantAccount giverAccount = getDefaultGiverAccount(sourceDocument);
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
            System.out.println("Not a valid scenario. " + sourceDocument.getAccountingEvent());
            System.out.println("receiver: " + receiverParticipant.getPartyId() + ": " + receiverParticipant.getPartyType());
        }
        ParticipantAccount receiverAccount = getDefaultReceiverAccount(sourceDocument);
        receiverAccounts.add(receiverAccount);
        return receiverAccounts;
    }

    public void onEvent(SourceDocument sourceDocument){
        processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }
    private void processJournalLedgerAndSubsidiaryBooks(SourceDocument sourceDocument){
        JournalizingProcessor journalizingProcessor = new DefaultJournalizingProcessor();
        List<ParticipantAccount> giverAccounts = this.identifyParticipatingGiverAccounts(sourceDocument);
        List<ParticipantAccount> receiverAccounts = this.identifyParticipatingReceiverAccounts(sourceDocument);
        try {
            JournalRecord journalRecord = journalizingProcessor.processJournalEntry(sourceDocument,giverAccounts,receiverAccounts);

            System.out.println("###########Journal################");
            printJournal();
            System.out.println("###########Journal################");

            LedgerPostingProcessor ledgerPostingProcessor= new DefaultLedgerPostingProcessor();
            ledgerPostingProcessor.postLedgerEntry(journalRecord);
            JournalDatabaseSimulator.addJournalEntry(journalRecord);

            System.out.println("###########LEDGER################");
            printAccounts("merchant1");
            System.out.println("###########END - LEDGER################");

            SubsidiaryJournalizingProcessor subsidiaryJournalizingProcessor = new SubsidiaryJournalizingProcessor();
            List<SubsidiaryBookEntry> subsidiaryBookEntries =  subsidiaryJournalizingProcessor.processJournalEntry(sourceDocument);
            if(null != subsidiaryBookEntries && sourceDocument.getAccountingEvent()== AccountingEvent.GOODS_PURCHASE_BY_BUSINESS ){
                PurchaseBookDatabaseSimulator.addJournalEntries(subsidiaryBookEntries);
            }else if(null != subsidiaryBookEntries && sourceDocument.getAccountingEvent()== AccountingEvent.GOODS_DELIVERY_TO_SUBSCRIBER){
                SalesBookDatabaseSimulator.addJournalEntries(subsidiaryBookEntries);
            }else if(null != subsidiaryBookEntries && sourceDocument.getAccountingEvent() == AccountingEvent.PURCHASE_RETURN_BY_BUSINESS){
                PurchaseReturnBookDatabaseSimulator.addJournalEntries(subsidiaryBookEntries);
            } else if(null != subsidiaryBookEntries && sourceDocument.getAccountingEvent() == AccountingEvent.GOODS_RETURN_FROM_SUBSCRIBER){
                SalesReturnBookDatabaseSimulator.addJournalEntries(subsidiaryBookEntries);
            }

            CashBookJournalizingProcessor cashBookJournalizingProcessor = new CashBookJournalizingProcessor();
            List<CashBookEntry> cashBookEntries = cashBookJournalizingProcessor.processCashBookEntry(journalRecord.getJournalFolioNumber(),sourceDocument);
            if(null != cashBookEntries && !cashBookEntries.isEmpty()){
                CashBookDatabaseSimulator.addJournalEntries(cashBookEntries);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void printAccounts(String merchantId){
        List<LedgerAccount> allAccounts= AccountDatabaseSimulator.getAllAccounts(merchantId);
        for(LedgerAccount account : allAccounts){
            if( (null != account.getDebits() && account.getDebits().size()>0)  || (null !=account.getCredits() && account.getCredits().size()>0) ) {
                System.out.println(account);
            }
        }
    }
    public void printJournal(){
        List<JournalRecord> journalEntries = JournalDatabaseSimulator.getJournalEntries();
        for(JournalRecord journalRecord : journalEntries ){
            System.out.println(journalRecord);
        }
    }
    public void printPurchaseBook(){
        List<SubsidiaryBookEntry> journalEntries = PurchaseBookDatabaseSimulator.getPurchaseBookJournalEntries();
        for(SubsidiaryBookEntry journalEntry: journalEntries ){
            System.out.println(journalEntry);
        }
    }
    public void printPurchaseReturnBook(){
        List<SubsidiaryBookEntry> journalEntries = PurchaseReturnBookDatabaseSimulator.getPurchaseReturnBookJournalEntries();
        for(SubsidiaryBookEntry journalEntry: journalEntries ){
            System.out.println(journalEntry);
        }
    }
    public void printSalesBook(){
        List<SubsidiaryBookEntry> journalEntries = SalesBookDatabaseSimulator.getSalesBookJournalEntries();
        for(SubsidiaryBookEntry journalEntry: journalEntries ){
            System.out.println(journalEntry);
        }
    }
    public void printSalesReturnBook(){
        List<SubsidiaryBookEntry> journalEntries = SalesReturnBookDatabaseSimulator.getSalesReturnBookJournalEntries();
        for(SubsidiaryBookEntry journalEntry: journalEntries ){
            System.out.println(journalEntry);
        }
    }
    public void printCashBook(){
        List<CashBookEntry> journalEntries = CashBookDatabaseSimulator.getCashBookEntries();
        for(CashBookEntry journalEntry: journalEntries ){
            System.out.println(journalEntry);
        }
    }

    public abstract ParticipantAccount getDefaultGiverAccount(SourceDocument sourceDocument);
    public abstract ParticipantAccount getDefaultReceiverAccount(SourceDocument sourceDocument);
    public abstract ParticipantAccount findHiddenGiverAccount(SourceDocument sourceDocument,double amountExchanged);
    public abstract ParticipantAccount findHiddenReceiverAccount(SourceDocument sourceDocument,double amountExchanged);


}
