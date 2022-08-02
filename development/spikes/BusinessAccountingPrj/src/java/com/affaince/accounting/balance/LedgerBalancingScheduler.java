package com.affaince.accounting.balance;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.types.CreditLedgerEntry;
import com.affaince.accounting.ledger.accounts.types.DebitLedgerEntry;
import com.affaince.accounting.ledger.accounts.types.LedgerAccount;

import java.util.List;

public class LedgerBalancingScheduler {

    public void processLedgerBalancing(List<LedgerAccount> ledgerAccounts){
            AccountBalancingProcessor accountBalancingProcessor = new DefaultAccountBalancingProcessor();
            for(LedgerAccount ledgerAccount: ledgerAccounts) {
                LedgerAccount ledgerAccountCurrentVersion = accountBalancingProcessor.balanceAccount(ledgerAccount);
                LedgerAccount ledgerAccountCurrentVersionClone = (LedgerAccount) ledgerAccountCurrentVersion.clone();
                ledgerAccount.addPreviousVersion(ledgerAccountCurrentVersionClone);
                ledgerAccount.flushLedgerAccountEntries();

                DebitLedgerEntry closingDebitLedgerEntry = ledgerAccountCurrentVersion.getDebits().stream().filter(debitEntry->debitEntry.getAccountIdentifier()== AccountIdentifier.TO_BALANCE_CARRIED_DOWN).findFirst().get();
                DebitLedgerEntry closingDebitLedgerEntryClone = (DebitLedgerEntry)closingDebitLedgerEntry.clone();
                closingDebitLedgerEntryClone.setAccountIdentifier(AccountIdentifier.BY_BALANCE_BROUGHT_DOWN);

                CreditLedgerEntry closingCreditLedgerEntry = ledgerAccountCurrentVersion.getCredits().stream().filter(creditEntry->creditEntry.getAccountIdentifier()== AccountIdentifier.BY_BALANCE_CARRIED_DOWN).findFirst().get();
                CreditLedgerEntry closingCreditLedgerEntryClone= (CreditLedgerEntry) closingCreditLedgerEntry.clone();
                closingCreditLedgerEntryClone.setAccountIdentifier(AccountIdentifier.TO_BALANCE_BROUGHT_DOWN);

                ledgerAccount.debit(closingDebitLedgerEntryClone);
                ledgerAccount.credit(closingCreditLedgerEntryClone);
                //save ledgerAccount.
            }
    }
}
