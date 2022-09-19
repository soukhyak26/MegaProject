package com.affaince.accounting.balance;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import org.joda.time.LocalDateTime;

import java.util.List;

public class LedgerBalancingScheduler {

    public static List<LedgerAccount> processOpening(String merchantId, LocalDateTime startDate, LocalDateTime closureDate){
        List<LedgerAccount> allActiveAccounts = AccountDatabaseSimulator.getAllActiveAccounts(merchantId,startDate,closureDate);
        AccountBalancingProcessor accountBalancingProcessor = new DefaultAccountBalancingProcessor();
        for(LedgerAccount ledgerAccount: allActiveAccounts) {
            accountBalancingProcessor.openAccount(ledgerAccount,startDate,closureDate);
        }
        return allActiveAccounts;
    }

    public static List<LedgerAccount> processClosure(String merchantId, LocalDateTime startDate, LocalDateTime closureDate){
            List<LedgerAccount> allActiveAccounts = AccountDatabaseSimulator.getAllActiveAccounts(merchantId,startDate,closureDate);
            AccountBalancingProcessor accountBalancingProcessor = new DefaultAccountBalancingProcessor();
            for(LedgerAccount ledgerAccount: allActiveAccounts) {
                accountBalancingProcessor.closeAccount(ledgerAccount,startDate,closureDate);
            }
            return allActiveAccounts;
    }
}
