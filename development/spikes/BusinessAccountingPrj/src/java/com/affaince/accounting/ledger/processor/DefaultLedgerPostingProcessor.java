package com.affaince.accounting.ledger.processor;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.ids.DefaultIdGenerator;
import com.affaince.accounting.journal.entity.CreditJournalEntry;
import com.affaince.accounting.journal.entity.DebitJournalEntry;
import com.affaince.accounting.journal.entity.JournalRecord;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.CreditLedgerEntry;
import com.affaince.accounting.ledger.accounts.DebitLedgerEntry;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import org.joda.time.LocalDateTime;

import java.util.*;

public class DefaultLedgerPostingProcessor implements LedgerPostingProcessor{
    @Override
    public void postLedgerEntry(JournalRecord journalRecord) throws Exception{
        //identify the account which is to be debited.
        LocalDateTime dateOfTransaction = journalRecord.getDateOfTransaction();
        String journalFolioNumber = journalRecord.getJournalFolioNumber();
        boolean putMultipleCreditEntriesInDebitAccount=false;
        boolean putMultipleDebitEntriesInCreditAccount=false;

        List<DebitJournalEntry> debitJournalEntries = journalRecord.getDebits();
        List<CreditJournalEntry> creditJournalEntries = journalRecord.getCredits();
        String ledgerFolio = new DefaultIdGenerator().generator(journalRecord.getMerchantId() +
                "$" +
                journalFolioNumber +
                "$" +
                debitJournalEntries.get(0).getAccountId() +
                "$" +
                creditJournalEntries.get(0).getAccountId() +
                "$" +
                journalRecord.getDateOfTransaction());
        if(debitJournalEntries.size()>1){
            putMultipleDebitEntriesInCreditAccount=true;
        }
        if(creditJournalEntries.size()>1){
            putMultipleCreditEntriesInDebitAccount=true;
        }

        if(putMultipleCreditEntriesInDebitAccount && putMultipleDebitEntriesInCreditAccount){
            throw new Exception("Both credit and debit entries cannot be multiple in a single journal entry");
        }

        if(putMultipleDebitEntriesInCreditAccount) {
            //if multiple debit entries it means single credit entry
            CreditJournalEntry creditJournalEntry = creditJournalEntries.get(0);
            String creditAccountId = creditJournalEntry.getAccountId();
            AccountIdentifier creditAccountIdentifier=creditJournalEntry.getAccountIdentifier();
            LedgerAccount creditAccount = AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(journalRecord.getMerchantId(), creditAccountId, creditAccountIdentifier,journalRecord.getDateOfTransaction());
            for (DebitJournalEntry debitJournalEntry : debitJournalEntries) {
                String debitAccountId = debitJournalEntry.getAccountId();
                AccountIdentifier debitAccountIdentifier = debitJournalEntry.getAccountIdentifier();
                double debitAmount = debitJournalEntry.getAmount();
                creditAccount.credit(new CreditLedgerEntry(dateOfTransaction,debitAccountId,debitAccountIdentifier,journalFolioNumber,ledgerFolio,debitAmount));

                LedgerAccount debitAccount = AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(journalRecord.getMerchantId(), debitAccountId, debitAccountIdentifier,journalRecord.getDateOfTransaction());
                debitAccount.debit(new DebitLedgerEntry(dateOfTransaction,creditAccountId,creditAccountIdentifier,journalFolioNumber,ledgerFolio,debitAmount));
            }
        }else if(putMultipleCreditEntriesInDebitAccount){
            DebitJournalEntry debitJournalEntry= debitJournalEntries.get(0);
            String debitAccountId=debitJournalEntry.getAccountId();
            AccountIdentifier debitAccountIdentifier = debitJournalEntry.getAccountIdentifier();
            LedgerAccount debitAccount = AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(journalRecord.getMerchantId(), debitAccountId, debitAccountIdentifier,journalRecord.getDateOfTransaction());

            for (CreditJournalEntry creditJournalEntry : creditJournalEntries) {
                String creditAccountId = creditJournalEntry.getAccountId();
                AccountIdentifier creditAccountIdentifier = creditJournalEntry.getAccountIdentifier();
                double creditAmount = creditJournalEntry.getAmount();
                debitAccount.debit(new DebitLedgerEntry(dateOfTransaction,creditAccountId,creditAccountIdentifier,journalFolioNumber,ledgerFolio,creditAmount));

                LedgerAccount creditAccount = AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(journalRecord.getMerchantId(), creditAccountId, creditAccountIdentifier,journalRecord.getDateOfTransaction());
                creditAccount.credit(new CreditLedgerEntry(dateOfTransaction,debitAccountId,debitAccountIdentifier,journalFolioNumber,ledgerFolio,creditAmount));
            }
        }else {
            CreditJournalEntry creditJournalEntry = creditJournalEntries.get(0);
            String creditAccountId = creditJournalEntry.getAccountId();
            AccountIdentifier creditAccountIdentifier=creditJournalEntry.getAccountIdentifier();
            LedgerAccount creditAccount = AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(journalRecord.getMerchantId(), creditAccountId, creditAccountIdentifier,journalRecord.getDateOfTransaction());
            double creditAmount = creditJournalEntry.getAmount();

            DebitJournalEntry debitJournalEntry= debitJournalEntries.get(0);
            String debitAccountId=debitJournalEntry.getAccountId();
            AccountIdentifier debitAccountIdentifier = debitJournalEntry.getAccountIdentifier();
            LedgerAccount debitAccount = AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(journalRecord.getMerchantId(), debitAccountId, debitAccountIdentifier,journalRecord.getDateOfTransaction());
            double debitAmount = debitJournalEntry.getAmount();

            creditAccount.credit(new CreditLedgerEntry(dateOfTransaction,debitAccountId,debitAccountIdentifier,journalFolioNumber,ledgerFolio,creditAmount));
            debitAccount.debit(new DebitLedgerEntry(dateOfTransaction,creditAccountId,creditAccountIdentifier,journalFolioNumber,ledgerFolio,debitAmount));
        }
        journalRecord.setLedgerFolioNumber(ledgerFolio);
    }
}
