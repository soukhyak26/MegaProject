package com.affaince.accounting.trials;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import org.joda.time.LocalDateTime;

import java.util.List;

public class DefaultTrialBalanceProcessor2 implements TrialBalanceProcessor{
    @Override
    public TrialBalance processTrialBalance(String merchantId, LocalDateTime startDate, LocalDateTime closureDate) {
        List<LedgerAccount> activeLedgerAccountsOfAMerchant = AccountDatabaseSimulator.getAllActiveAccounts(merchantId,startDate,closureDate);
        TrialBalance trialBalance = new TrialBalance(merchantId,"1", closureDate.toLocalDate());
        for (LedgerAccount ledgerAccount : activeLedgerAccountsOfAMerchant){
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
