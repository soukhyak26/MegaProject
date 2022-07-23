package com.affaince.accounting.journal.processor;

import com.affaince.accounting.accounts.types.LedgerAccount;
import com.affaince.accounting.accounts.types.NominalAccount;
import com.affaince.accounting.accounts.types.PersonalAccount;
import com.affaince.accounting.accounts.types.RealAccount;
import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.journal.entity.*;
import com.affaince.accounting.journal.processor.contract.JournalizingProcessor;
import com.affaince.accounting.journal.qualifiers.AccountQualifiers;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.journal.qualifiers.TransactionEvents;
import com.affaince.accounting.journal.qualifiers.PartyTypes;

public class DefaultJournalizingProcessor implements JournalizingProcessor {

    @Override
    public void processJournalEntry(SourceDocument sourceDocument) {

    }


   public JournalEntry.JournalEntryBuilder processJournalEntryBuildingRules (Participant giverParty, LedgerAccount giverAccount, Participant receiverParty, LedgerAccount receiverAccount, JournalEntry.JournalEntryBuilder journalEntryBuilder) throws Exception{
        if(null != giverAccount) {
            if (giverAccount instanceof PersonalAccount) {
                //debit the receiver, credit the giver
                journalEntryBuilder.credit(new CreditJournalEntry(giverAccount.getAccountId(), giverParty.getAmountExchanged()));
            } else if (giverAccount instanceof RealAccount) {
                //debit what comes IN, Credit what goes OUT
                journalEntryBuilder.credit(new CreditJournalEntry(giverAccount.getAccountId(), giverParty.getAmountExchanged()));
            } else if (giverAccount instanceof NominalAccount) {
                //debit all expenses and losses, credit all incomes and gains
                journalEntryBuilder.debit(new DebitJournalEntry(giverAccount.getAccountId(), giverParty.getAmountExchanged()));
            } else {
                throw new Exception("Wrong account type");
            }
        }
        if( null != receiverAccount) {
            if (receiverAccount instanceof PersonalAccount) {
                //debit the receiver, credit the giver
                journalEntryBuilder.debit(new DebitJournalEntry(receiverAccount.getAccountId(), receiverParty.getAmountExchanged()));
            } else if (receiverAccount instanceof RealAccount) {
                //debit what comes IN, Credit what goes OUT
                journalEntryBuilder.debit(new DebitJournalEntry(receiverAccount.getAccountId(), receiverParty.getAmountExchanged()));
            } else if (receiverAccount instanceof NominalAccount) {
                //debit all expenses and losses, credit all incomes and gains
                journalEntryBuilder.credit(new CreditJournalEntry(receiverAccount.getAccountId(), receiverParty.getAmountExchanged()));
            }else{
                throw new Exception("Wrong account type");
            }
        }
       return null;
    }

    public JournalEntry.JournalEntryBuilder resolveParticipatingAccounts(SourceDocument sourceDocument, JournalEntry.JournalEntryBuilder journalEntryBuilder) {
        //nature of transaction tells expected accounts in the type of transaction
        //nature of transaction should also tell, in some of the transaction there can be discount or bad debt
        //discount as well as bad debt both are exceptional situations. So nature of transaction should support exceptionAccountQualifier
        TransactionEvents transactionEvents = sourceDocument.getNatureOfTransaction();

        //nature of transaction would tell which parties should participate
        PartyTypes expectedGiver = transactionEvents.getGiver();
        AccountQualifiers expectedGiverAccountQualifier = expectedGiver.getAccountQualifiers();
        PartyTypes expectedReceiver = transactionEvents.getReceiver();
        AccountQualifiers expectedReceiverAccountQualifier = expectedReceiver.getAccountQualifiers();

        // now find actual giver and receiver, one of them may be BUSINESS
        Participant actualGiverParty = sourceDocument.getGiverParticipant();
        Participant actualReceiverParty = sourceDocument.getReceiverParticipant();

        PartyTypes actualGiverPartyType = actualGiverParty.getPartyType();
        AccountQualifiers actualGiverAccountQualifier = actualGiverPartyType.getAccountQualifiers();

        PartyTypes actualReceiverPartyType = actualReceiverParty.getPartyType();
        AccountQualifiers actualReceiverAccountQualifier = actualReceiverPartyType.getAccountQualifiers();

        LedgerAccount giverAccount=null;
        LedgerAccount receiverAccount=null;

        //for now lets assume that one of the account qualifier is null
        if (actualGiverAccountQualifier != null && actualGiverAccountQualifier == expectedGiverAccountQualifier) {
            giverAccount = AccountDatabaseSimulator.searchLedgerAccount(actualGiverParty.getPartyId(), actualGiverAccountQualifier);

        }
        if (actualReceiverAccountQualifier != null && actualReceiverAccountQualifier == expectedReceiverAccountQualifier) {
            receiverAccount = AccountDatabaseSimulator.searchLedgerAccount(actualReceiverParty.getPartyId(), actualReceiverAccountQualifier);
        }
        try {
            journalEntryBuilder = processJournalEntryBuildingRules(actualGiverParty, giverAccount, actualReceiverParty, receiverAccount, journalEntryBuilder);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public DoubleEJournalEntry buildDoubleEJournalEntry(SourceDocument sourceDocument) {
        ModeOfTransaction modeOfTransaction = sourceDocument.getModeOfTransaction();
        double transactionAmount = sourceDocument.getTransactionAmount();
        JournalEntry.JournalEntryBuilder journalEntryBuilder = JournalEntry.newBuilder()
                .journalFolioNumber("1")
                .dateOfTransaction(sourceDocument.getDateOfTransaction())
                .narration(sourceDocument.getDescription()) ;

        TransactionEntityDetail transactionEntityDetail = sourceDocument.getTransactionEntityDetail();
        journalEntryBuilder = resolveParticipatingAccounts(sourceDocument, journalEntryBuilder);




    }
}
