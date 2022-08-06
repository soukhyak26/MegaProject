package com.affaince.accounting.journal.processor;

import com.affaince.accounting.journal.entity.*;
import com.affaince.accounting.journal.events.AccountIdentificationRulesProcessor;
import com.affaince.accounting.journal.processor.factory.AccountIdentificationRulesProcessorFactory;
import com.affaince.accounting.journal.qualifiers.AccountQualifiers;
import com.affaince.accounting.transactions.SourceDocument;

import static java.util.Objects.requireNonNull;

import java.util.List;

public class DefaultJournalizingProcessor implements JournalizingProcessor {
    @Override
    public JournalEntry processJournalEntry(SourceDocument sourceDocument) throws Exception{
        AccountIdentificationRulesProcessor accountIdentificationRulesProcessor = AccountIdentificationRulesProcessorFactory.getAccountIdentificationRulesProcessor(sourceDocument);
        requireNonNull(accountIdentificationRulesProcessor);
        List<ParticipantAccount> giverAccounts = accountIdentificationRulesProcessor.identifyParticipatingGiverAccounts(sourceDocument);
        List<ParticipantAccount> receiverAccounts = accountIdentificationRulesProcessor.identifyParticipatingReceiverAccounts(sourceDocument);

        JournalEntry.JournalEntryBuilder journalEntryBuilder = JournalEntry.newBuilder()
                .merchantId(sourceDocument.getMerchantId())
                .journalFolioNumber("1")
                .transactionReferenceNumber(sourceDocument.getTransactionReferenceNumber())
                .dateOfTransaction(sourceDocument.getDateOfTransaction())
                .narration(sourceDocument.getDescription());
        for (ParticipantAccount giverAccount : giverAccounts) {
            if (giverAccount.getAccountIdentifier().getAccountQualifiers()== AccountQualifiers.PERSONAL_LEDGER_ACCOUNT) {
                //debit the receiver, credit the giver
                journalEntryBuilder = journalEntryBuilder.credit(new CreditJournalEntry(giverAccount.getAccountId(), giverAccount.getAccountIdentifier(), giverAccount.getAmountExchanged()));
            } else if (giverAccount.getAccountIdentifier().getAccountQualifiers()== AccountQualifiers.REAL_LEDGER_ACCOUNT) {
                //debit what comes IN, Credit what goes OUT
                journalEntryBuilder=journalEntryBuilder.credit(new CreditJournalEntry(giverAccount.getAccountId(), giverAccount.getAccountIdentifier(),giverAccount.getAmountExchanged()));
            } else if (giverAccount.getAccountIdentifier().getAccountQualifiers()==AccountQualifiers.NOMINAL_LEDGER_ACCOUNT) {
                //debit all expenses and losses, credit all incomes and gains
                if(!giverAccount.getAccountIdentifier().isGain()) {
                    journalEntryBuilder = journalEntryBuilder.debit(new DebitJournalEntry(giverAccount.getAccountId(), giverAccount.getAccountIdentifier(), giverAccount.getAmountExchanged()));
                }else{
                    journalEntryBuilder=journalEntryBuilder.credit(new CreditJournalEntry(giverAccount.getAccountId(), giverAccount.getAccountIdentifier(),giverAccount.getAmountExchanged()));
                }
            } else {
                throw new Exception("Wrong account type");
            }
        }
        for(ParticipantAccount receiverAccount: receiverAccounts) {
            if (receiverAccount.getAccountIdentifier().getAccountQualifiers() == AccountQualifiers.PERSONAL_LEDGER_ACCOUNT) {
                //debit the receiver, credit the giver
                journalEntryBuilder=journalEntryBuilder.debit(new DebitJournalEntry(receiverAccount.getAccountId(),receiverAccount.getAccountIdentifier(), receiverAccount.getAmountExchanged()));
            } else if (receiverAccount.getAccountIdentifier().getAccountQualifiers() == AccountQualifiers.REAL_LEDGER_ACCOUNT) {
                //debit what comes IN, Credit what goes OUT
                journalEntryBuilder=journalEntryBuilder.debit(new DebitJournalEntry(receiverAccount.getAccountId(), receiverAccount.getAccountIdentifier(),receiverAccount.getAmountExchanged()));
            } else if (receiverAccount.getAccountIdentifier().getAccountQualifiers() == AccountQualifiers.NOMINAL_LEDGER_ACCOUNT) {
                //debit all expenses and losses, credit all incomes and gains
                if(!receiverAccount.getAccountIdentifier().isGain()){
                    journalEntryBuilder=journalEntryBuilder.debit(new DebitJournalEntry(receiverAccount.getAccountId(), receiverAccount.getAccountIdentifier(),receiverAccount.getAmountExchanged()));
                }else{
                    journalEntryBuilder=journalEntryBuilder.credit(new CreditJournalEntry(receiverAccount.getAccountId(), receiverAccount.getAccountIdentifier(),receiverAccount.getAmountExchanged()));
                }

            } else {
                throw new Exception("Wrong account type");
            }
        }
        return journalEntryBuilder.build();
    }

}
