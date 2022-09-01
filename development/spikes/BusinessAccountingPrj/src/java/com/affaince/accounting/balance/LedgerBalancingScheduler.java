package com.affaince.accounting.balance;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class LedgerBalancingScheduler {

    public static List<LedgerAccount> processLedgerBalancing(String merchantId){
            List<LedgerAccount> allActiveAccounts = AccountDatabaseSimulator.getAllActiveAccounts(merchantId);
            AccountBalancingProcessor accountBalancingProcessor = new DefaultAccountBalancingProcessor();
            List<LedgerAccount> ledgerAccountsActiveVersions = new ArrayList<>();
            LocalDateTime closureDate = LocalDateTime.now();
            for(LedgerAccount ledgerAccount: allActiveAccounts) {
                ledgerAccountsActiveVersions.add(accountBalancingProcessor.balanceAccount(ledgerAccount,closureDate));
            }
            return ledgerAccountsActiveVersions;
    }
}
