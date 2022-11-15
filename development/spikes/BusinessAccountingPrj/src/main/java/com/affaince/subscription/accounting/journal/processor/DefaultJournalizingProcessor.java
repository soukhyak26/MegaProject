package com.affaince.subscription.accounting.journal.processor;

import com.affaince.subscription.accounting.ids.IdGenerator;
import com.affaince.subscription.accounting.journal.qualifiers.AccountQualifiers;
import com.affaince.subscription.accounting.transactions.SourceDocument;
import com.affaince.subscription.accounting.journal.entity.CreditJournalEntry;
import com.affaince.subscription.accounting.journal.entity.DebitJournalEntry;
import com.affaince.subscription.accounting.journal.entity.JournalRecord;
import com.affaince.subscription.accounting.journal.entity.ParticipantAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
@Component
public class DefaultJournalizingProcessor implements JournalizingProcessor {
    private final IdGenerator idGenerator;
    @Autowired
    public DefaultJournalizingProcessor(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public JournalRecord processJournalEntry(SourceDocument sourceDocument, List<ParticipantAccount> giverAccounts, List<ParticipantAccount> receiverAccounts) throws Exception{
        String journalFolioNumber = idGenerator.generator(sourceDocument.getMerchantId() +
                "$" +
                sourceDocument.getDateOfTransaction().toLocalDate() +
/*
                "$" +
                sourceDocument.getGiverParticipant() +
                "$" +
                sourceDocument.getReceiverParticipant() +
*/
                "$" +
                sourceDocument.getAccountingEvent()
        );
        JournalRecord.JournalEntryBuilder journalEntryBuilder = JournalRecord.newBuilder()
                .merchantId(sourceDocument.getMerchantId())
                .journalFolioNumber(journalFolioNumber)
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
