package com.affaince.accounting.ledger.processor;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.journal.entity.CreditJournalEntry;
import com.affaince.accounting.journal.entity.DebitJournalEntry;
import com.affaince.accounting.journal.entity.JournalEntry;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.types.CreditLedgerEntry;
import com.affaince.accounting.ledger.accounts.types.DebitLedgerEntry;
import com.affaince.accounting.ledger.accounts.types.LedgerAccount;
import org.joda.time.LocalDateTime;

import java.util.*;

public class DefaultLedgerPostingProcessor implements LedgerPostingProcessor{
    @Override
    public void postLedgerEntry(JournalEntry journalEntry) throws Exception{
        //identify the account which is to be debited.
        LocalDateTime dateOfTransaction = journalEntry.getDateOfTransaction();
        String journalFolioNumber = journalEntry.getJournalFolioNumber();
        boolean putMultipleCreditEntriesInDebitAccount=false;
        boolean putMultipleDebitEntriesInCreditAccount=false;

        List<DebitJournalEntry> debitJournalEntries = journalEntry.getDebits();
        List<CreditJournalEntry> creditJournalEntries = journalEntry.getCredits();
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
            LedgerAccount creditAccount = AccountDatabaseSimulator.searchLedgerAccountsByAccountIdAndAccountIdentifier(journalEntry.getMerchantId(), creditAccountId, creditAccountIdentifier);
            for (DebitJournalEntry debitJournalEntry : debitJournalEntries) {
                String debitAccountId = debitJournalEntry.getAccountId();
                AccountIdentifier debitAccountIdentifier = debitJournalEntry.getAccountIdentifier();
                double debitAmount = debitJournalEntry.getAmount();
                creditAccount.credit(new CreditLedgerEntry(dateOfTransaction,debitAccountId,debitAccountIdentifier,journalFolioNumber,debitAmount));

                LedgerAccount debitAccount = AccountDatabaseSimulator.searchLedgerAccountsByAccountIdAndAccountIdentifier(journalEntry.getMerchantId(), debitAccountId, debitAccountIdentifier);
                debitAccount.debit(new DebitLedgerEntry(dateOfTransaction,creditAccountId,creditAccountIdentifier,journalFolioNumber,debitAmount));

            }
        }else if(putMultipleCreditEntriesInDebitAccount){
            DebitJournalEntry debitJournalEntry= debitJournalEntries.get(0);
            String debitAccountId=debitJournalEntry.getAccountId();
            AccountIdentifier debitAccountIdentifier = debitJournalEntry.getAccountIdentifier();
            LedgerAccount debitAccount = AccountDatabaseSimulator.searchLedgerAccountsByAccountIdAndAccountIdentifier(journalEntry.getMerchantId(), debitAccountId, debitAccountIdentifier);

            for (CreditJournalEntry creditJournalEntry : creditJournalEntries) {
                String creditAccountId = creditJournalEntry.getAccountId();
                AccountIdentifier creditAccountIdentifier = creditJournalEntry.getAccountIdentifier();
                double creditAmount = creditJournalEntry.getAmount();
                debitAccount.debit(new DebitLedgerEntry(dateOfTransaction,creditAccountId,creditAccountIdentifier,journalFolioNumber,creditAmount));

                LedgerAccount creditAccount = AccountDatabaseSimulator.searchLedgerAccountsByAccountIdAndAccountIdentifier(journalEntry.getMerchantId(), creditAccountId, creditAccountIdentifier);
                creditAccount.credit(new CreditLedgerEntry(dateOfTransaction,debitAccountId,debitAccountIdentifier,journalFolioNumber,creditAmount));
            }
        }else {
            CreditJournalEntry creditJournalEntry = creditJournalEntries.get(0);
            String creditAccountId = creditJournalEntry.getAccountId();
            AccountIdentifier creditAccountIdentifier=creditJournalEntry.getAccountIdentifier();
            LedgerAccount creditAccount = AccountDatabaseSimulator.searchLedgerAccountsByAccountIdAndAccountIdentifier(journalEntry.getMerchantId(), creditAccountId, creditAccountIdentifier);
            double creditAmount = creditJournalEntry.getAmount();

            DebitJournalEntry debitJournalEntry= debitJournalEntries.get(0);
            String debitAccountId=debitJournalEntry.getAccountId();
            AccountIdentifier debitAccountIdentifier = debitJournalEntry.getAccountIdentifier();
            LedgerAccount debitAccount = AccountDatabaseSimulator.searchLedgerAccountsByAccountIdAndAccountIdentifier(journalEntry.getMerchantId(), debitAccountId, debitAccountIdentifier);
            double debitAmount = debitJournalEntry.getAmount();

            creditAccount.credit(new CreditLedgerEntry(dateOfTransaction,debitAccountId,debitAccountIdentifier,journalFolioNumber,creditAmount));
            debitAccount.debit(new DebitLedgerEntry(dateOfTransaction,creditAccountId,creditAccountIdentifier,journalFolioNumber,debitAmount));
        }

    }
}
