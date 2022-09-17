package com.affaince.accounting.db;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.pnl.ProfitAndLossAccount;
import com.affaince.accounting.trading.TradingAccount;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProfitAndLossAccountDatabaseSimulator {
    private static final List<ProfitAndLossAccount> allPnLAccounts = new ArrayList<>();

    public static void addAccount(ProfitAndLossAccount ledgerAccount) {
        allPnLAccounts.add(ledgerAccount);
    }

    public static ProfitAndLossAccount searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(String merchantId, String accountId, AccountIdentifier accountIdentifier, LocalDateTime startDate, LocalDateTime closureDate) {
        return allPnLAccounts.stream().
                filter(account ->
                        account.getMerchantId().equals(merchantId) &&
                                account.getAccountIdentifier() == accountIdentifier &&
                                account.getAccountId().equals(accountId) &&
                                (account.getStartDate().isBefore(closureDate) && account.getClosureDate().isAfter(startDate))).findAny().orElse(null);
    }

    public static List<ProfitAndLossAccount> searchActiveLedgerAccountsByAccountIdentifier(String merchantId, AccountIdentifier accountIdentifier) {
        return allPnLAccounts.stream().filter(account -> account.getMerchantId().equals(merchantId) && account.getAccountIdentifier() == accountIdentifier && account.getClosureDate().equals(new LocalDateTime(9999, 12, 31, 23, 59, 59))).collect(Collectors.toList());
    }

    public static List<ProfitAndLossAccount> getAllAccounts(String merchantId) {
        return allPnLAccounts.stream().filter(ac -> ac.getMerchantId().equals(merchantId)).collect(Collectors.toList());
    }

    public static List<ProfitAndLossAccount> getAllActiveAccounts(String merchantId, LocalDateTime startDate, LocalDateTime closureDate) {
        return allPnLAccounts.stream().
                filter(account ->
                        account.getMerchantId().equals(merchantId) &&
                                (account.getStartDate().isBefore(closureDate) && account.getClosureDate().isAfter(startDate)))
                .collect(Collectors.toList());
    }

}
