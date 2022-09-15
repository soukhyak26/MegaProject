package com.affaince.accounting.db;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.trading.TradingAccount;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TradingAccountDatabaseSimulator {
    private static final List<TradingAccount> allTradingAccounts = new ArrayList<>();

    public static void addAccount(TradingAccount ledgerAccount) {
        allTradingAccounts.add(ledgerAccount);
    }

    public static TradingAccount searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(String merchantId, String accountId, AccountIdentifier accountIdentifier, LocalDateTime startDate, LocalDateTime closureDate) {
        return allTradingAccounts.stream().
                filter(account ->
                        account.getMerchantId().equals(merchantId) &&
                                account.getAccountIdentifier() == accountIdentifier &&
                                account.getAccountId().equals(accountId) &&
                                (account.getStartDate().isBefore(closureDate) && account.getClosureDate().isAfter(startDate))).findAny().orElse(null);
    }

    public static List<TradingAccount> searchActiveLedgerAccountsByAccountIdentifier(String merchantId, AccountIdentifier accountIdentifier) {
        return allTradingAccounts.stream().filter(account -> account.getMerchantId().equals(merchantId) && account.getAccountIdentifier() == accountIdentifier && account.getClosureDate().equals(new LocalDateTime(9999, 12, 31, 23, 59, 59))).collect(Collectors.toList());
    }

    public static List<TradingAccount> getAllAccounts(String merchantId) {
        return allTradingAccounts.stream().filter(ac -> ac.getMerchantId().equals(merchantId)).collect(Collectors.toList());
    }

    public static List<TradingAccount> getAllActiveAccounts(String merchantId, LocalDateTime startDate, LocalDateTime closureDate) {
        return allTradingAccounts.stream().
                filter(account ->
                        account.getMerchantId().equals(merchantId) &&
                                (account.getStartDate().isBefore(closureDate) && account.getClosureDate().isAfter(startDate)))
                .collect(Collectors.toList());
    }

}
