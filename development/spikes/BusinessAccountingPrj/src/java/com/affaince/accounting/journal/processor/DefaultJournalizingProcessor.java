package com.affaince.accounting.journal.processor;

import com.affaince.accounting.journal.entity.*;
import com.affaince.accounting.journal.processor.contract.AccountIdentificationRulesProcessor;
import com.affaince.accounting.journal.processor.contract.JournalizingProcessor;
import com.affaince.accounting.journal.processor.factory.AccountIdentificationRulesProcessorFactory;
import com.affaince.accounting.journal.qualifiers.AccountQualifiers;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;

import java.util.List;

public class DefaultJournalizingProcessor implements JournalizingProcessor {
    @Override
    public JournalEntry processJournalEntry(SourceDocument sourceDocument) throws Exception{
        ModeOfTransaction modeOfTransaction = sourceDocument.getModeOfTransaction();
        TransactionEntityDetail transactionEntityDetail = sourceDocument.getTransactionEntityDetail();
        AccountIdentificationRulesProcessor accountIdentificationRulesProcessor = AccountIdentificationRulesProcessorFactory.getAccountIdentificationRulesProcessor(sourceDocument);
        List<ParticipantAccount> giverAccounts = accountIdentificationRulesProcessor.identifyParticipatingGiverAccounts(sourceDocument);
        List<ParticipantAccount> receiverAccounts = accountIdentificationRulesProcessor.identifyParticipatingReceiverAccounts(sourceDocument);

        JournalEntry.JournalEntryBuilder journalEntryBuilder = JournalEntry.newBuilder()
                .merchantId(sourceDocument.getMerchantId())
                .journalFolioNumber("1")
                .dateOfTransaction(sourceDocument.getDateOfTransaction())
                .narration(sourceDocument.getDescription());
        for (ParticipantAccount giverAccount : giverAccounts) {
            if (giverAccount.getAccountIdentifier().getAccountQualifiers()== AccountQualifiers.PERSONAL_LEDGER_ACCOUNT) {
                //debit the receiver, credit the giver
                journalEntryBuilder.credit(new CreditJournalEntry(giverAccount.getAccountId(), giverAccount.getAmountExchanged()));
            } else if (giverAccount.getAccountIdentifier().getAccountQualifiers()== AccountQualifiers.REAL_LEDGER_ACCOUNT) {
                //debit what comes IN, Credit what goes OUT
                journalEntryBuilder.credit(new CreditJournalEntry(giverAccount.getAccountId(), giverAccount.getAmountExchanged()));
            } else if (giverAccount.getAccountIdentifier().getAccountQualifiers()==AccountQualifiers.NOMINAL_LEDGER_ACCOUNT) {
                //debit all expenses and losses, credit all incomes and gains
                journalEntryBuilder.debit(new DebitJournalEntry(giverAccount.getAccountId(), giverAccount.getAmountExchanged()));
            } else {
                throw new Exception("Wrong account type");
            }
        }
        for(ParticipantAccount receiverAccount: receiverAccounts) {
            if (receiverAccount.getAccountIdentifier().getAccountQualifiers() == AccountQualifiers.PERSONAL_LEDGER_ACCOUNT) {
                //debit the receiver, credit the giver
                journalEntryBuilder.debit(new DebitJournalEntry(receiverAccount.getAccountId(), receiverAccount.getAmountExchanged()));
            } else if (receiverAccount.getAccountIdentifier().getAccountQualifiers() == AccountQualifiers.REAL_LEDGER_ACCOUNT) {
                //debit what comes IN, Credit what goes OUT
                journalEntryBuilder.debit(new DebitJournalEntry(receiverAccount.getAccountId(), receiverAccount.getAmountExchanged()));
            } else if (receiverAccount.getAccountIdentifier().getAccountQualifiers() == AccountQualifiers.NOMINAL_LEDGER_ACCOUNT) {
                //debit all expenses and losses, credit all incomes and gains
                journalEntryBuilder.credit(new CreditJournalEntry(receiverAccount.getAccountId(), receiverAccount.getAmountExchanged()));
            } else {
                throw new Exception("Wrong account type");
            }
        }
        return journalEntryBuilder.build();
    }

}
