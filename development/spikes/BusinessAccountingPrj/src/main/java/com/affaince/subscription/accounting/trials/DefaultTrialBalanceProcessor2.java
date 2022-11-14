package com.affaince.subscription.accounting.trials;

import com.affaince.subscription.accounting.db.AccountDatabaseSimulator;
import org.joda.time.LocalDateTime;

//@Component
@Deprecated
public class DefaultTrialBalanceProcessor2 implements TrialBalanceProcessor{
    private final AccountDatabaseSimulator accountDatabaseSimulator;
    //private final ClosingStockDatabaseSimulator closingStockDatabaseSimulator;

    //@Autowired
    public DefaultTrialBalanceProcessor2(AccountDatabaseSimulator accountDatabaseSimulator) {
        this.accountDatabaseSimulator = accountDatabaseSimulator;
    }

    @Override
    public TrialBalance processTrialBalance(String merchantId, LocalDateTime startDate, LocalDateTime closureDate) {
/*
        List<LedgerAccount> activeLedgerAccountsOfAMerchant = accountDatabaseSimulator.getAllActiveAccounts(merchantId,startDate,closureDate);
        TrialBalance trialBalance = new TrialBalance(merchantId,"1", closureDate.toLocalDate());
        for (LedgerAccount ledgerAccount : activeLedgerAccountsOfAMerchant){
            double debitBalanceBroughtDown=ledgerAccount.getDebits().stream().filter(db->(db.getAccountIdentifier() !=AccountIdentifier.TO_BALANCE_BROUGHT_DOWN && db.getAccountIdentifier() != AccountIdentifier.TO_BALANCE_CARRIED_DOWN)).mapToDouble(db->db.getAmount()).sum();
            double creditBalanceBroughtDown=ledgerAccount.getCredits().stream().filter(cr->(cr.getAccountIdentifier() != AccountIdentifier.BY_BALANCE_BROUGHT_DOWN && cr.getAccountIdentifier() != AccountIdentifier.BY_BALANCE_CARRIED_DOWN)).mapToDouble(cr->cr.getAmount()).sum();
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
*/
        return null;
    }
}
