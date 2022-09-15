package com.affaince.accounting.trials;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.ledger.accounts.LedgerAccountEntry;
import org.joda.time.LocalDate;

import java.util.List;

public class DefaultTrialBalanceProcessor2 implements TrialBalanceProcessor{
    @Override
    public TrialBalance processTrialBalance(String merchantId, LocalDate trialBalanceDate) {
        List<LedgerAccount> closedLedgerAccountsOfAMerchant = AccountDatabaseSimulator.getAllLatestClosedAccounts(merchantId);
        TrialBalance trialBalance = new TrialBalance(merchantId,"1", trialBalanceDate);
        for (LedgerAccount ledgerAccount : closedLedgerAccountsOfAMerchant){
            double creditBalanceBroughtDown=ledgerAccount.getDebits().stream().filter(db->(db.getAccountIdentifier() !=AccountIdentifier.TO_BALANCE_BROUGHT_DOWN && db.getAccountIdentifier() != AccountIdentifier.TO_BALANCE_CARRIED_DOWN)).mapToDouble(db->db.getAmount()).sum();
            double debitBalanceBroughtDown=ledgerAccount.getCredits().stream().filter(cr->(cr.getAccountIdentifier() != AccountIdentifier.BY_BALANCE_BROUGHT_DOWN && cr.getAccountIdentifier() != AccountIdentifier.BY_BALANCE_CARRIED_DOWN)).mapToDouble(cr->cr.getAmount()).sum();

            switch (ledgerAccount.getAccountIdentifier().getAccountQualifiers()){
                case PERSONAL_LEDGER_ACCOUNT:
                case REAL_LEDGER_ACCOUNT:
                case NOMINAL_LEDGER_ACCOUNT:
                    if(creditBalanceBroughtDown >0) {
                        trialBalance.addToCreditEntries(new CreditTrialBalanceEntry(ledgerAccount.getAccountId(), ledgerAccount.getAccountIdentifier(), null, creditBalanceBroughtDown, NatureOfBalance.CUSTOMERS));
                    }
                    if(debitBalanceBroughtDown>0){
                        trialBalance.addToDebitEntries(new DebitTrialBalanceEntry(ledgerAccount.getAccountId(), ledgerAccount.getAccountIdentifier(), null, debitBalanceBroughtDown, NatureOfBalance.CUSTOMERS));
                    }
                    break;
            }
        }
        return trialBalance;
    }
}
