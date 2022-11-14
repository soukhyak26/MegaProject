package com.affaince.subscription.accounting.balance;

import com.affaince.subscription.accounting.db.AccountDatabaseSimulator;
import com.affaince.subscription.accounting.ledger.accounts.LedgerAccount;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class LedgerBalancingScheduler {
    private AccountDatabaseSimulator accountDatabaseSimulator;
    private AccountBalancingProcessor accountBalancingProcessor;
    public LedgerBalancingScheduler(AccountDatabaseSimulator accountDatabaseSimulator,AccountBalancingProcessor accountBalancingProcessor){
        this.accountBalancingProcessor = accountBalancingProcessor;
        this.accountDatabaseSimulator=accountDatabaseSimulator;
    }
    public List<LedgerAccount> processOpening(String merchantId, LocalDateTime startDate, LocalDateTime closureDate){
        List<LedgerAccount> allActiveAccounts = accountDatabaseSimulator.getAllActiveAccounts(merchantId,startDate,closureDate);
        //AccountBalancingProcessor accountBalancingProcessor = new DefaultAccountBalancingProcessor();
        for(LedgerAccount ledgerAccount: allActiveAccounts) {
            accountBalancingProcessor.openAccount(ledgerAccount,startDate,closureDate);
        }
        return allActiveAccounts;
    }

    public List<LedgerAccount> processClosure(String merchantId, LocalDateTime startDate, LocalDateTime closureDate){
            List<LedgerAccount> allActiveAccounts = accountDatabaseSimulator.getAllActiveAccounts(merchantId,startDate,closureDate);
            //AccountBalancingProcessor accountBalancingProcessor = new DefaultAccountBalancingProcessor();
            for(LedgerAccount ledgerAccount: allActiveAccounts) {
                accountBalancingProcessor.closeAccount(ledgerAccount,startDate,closureDate);
            }
            return allActiveAccounts;
    }
}
