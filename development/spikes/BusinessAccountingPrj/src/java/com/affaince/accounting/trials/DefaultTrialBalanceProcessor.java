package com.affaince.accounting.trials;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.CreditLedgerEntry;
import com.affaince.accounting.ledger.accounts.DebitLedgerEntry;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import org.joda.time.LocalDate;

import java.util.List;

public class DefaultTrialBalanceProcessor implements TrialBalanceProcessor{
    @Override
    public TrialBalance processTrialBalance(String merchantId, LocalDate trialBalanceDate) {
        List<LedgerAccount> closedLedgerAccountsOfAMerchant = AccountDatabaseSimulator.getAllLatestClosedAccounts(merchantId);
        TrialBalance trialBalance = new TrialBalance("1", trialBalanceDate);
        for (LedgerAccount ledgerAccount : closedLedgerAccountsOfAMerchant){
            CreditLedgerEntry creditLedgerEntry = ledgerAccount.getCredits().stream().filter(acc->acc.getAccountIdentifier()== AccountIdentifier.BY_BALANCE_CARRIED_DOWN).findAny().orElse(null);
            double creditBalanceBroughtDown=0;
            if(null != creditLedgerEntry){
                creditBalanceBroughtDown += creditLedgerEntry.getAmount();
            }
            DebitLedgerEntry debitLedgerEntry =ledgerAccount.getDebits().stream().filter(acc->acc.getAccountIdentifier()== AccountIdentifier.TO_BALANCE_CARRIED_DOWN).findAny().orElse(null);
            double debitBalanceBroughtDown=0;
            if(null != debitLedgerEntry){
                debitBalanceBroughtDown += debitLedgerEntry.getAmount();
            }
            if(creditBalanceBroughtDown>0 && debitBalanceBroughtDown>0){
                throw new RuntimeException("credit balance brought down and debit balance brought down both cannot be present at same time");
            }
            switch (ledgerAccount.getAccountIdentifier().getAccountQualifiers()){
                case PERSONAL_LEDGER_ACCOUNT:
                case REAL_LEDGER_ACCOUNT:
                case NOMINAL_LEDGER_ACCOUNT:
                    if(creditBalanceBroughtDown >0) {
                        trialBalance.addToCreditEntries(new CreditTrialBalanceEntry(ledgerAccount.getAccountId(), ledgerAccount.getAccountIdentifier(), null, creditBalanceBroughtDown, NatureOfBalance.CUSTOMERS));
                    }else if(debitBalanceBroughtDown>0){
                        trialBalance.addToDebitEntries(new DebitTrialBalanceEntry(ledgerAccount.getAccountId(), ledgerAccount.getAccountIdentifier(), null, debitBalanceBroughtDown, NatureOfBalance.CUSTOMERS));
                    }
                    break;
            }
        }
        return trialBalance;
    }
}
