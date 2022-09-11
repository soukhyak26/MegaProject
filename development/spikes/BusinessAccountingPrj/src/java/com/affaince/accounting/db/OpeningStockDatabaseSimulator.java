package com.affaince.accounting.db;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.stock.OpeningStockAccount;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OpeningStockDatabaseSimulator {
    private static final List<OpeningStockAccount> allOpeningStocks = new ArrayList<>();

    public static void addAccount(OpeningStockAccount openingStockAccount){
        allOpeningStocks.add(openingStockAccount);
    }

    public static OpeningStockAccount getLatestOpeningStockAccountsByAccountIdAndAccountIdentifier(String merchantId, String accountId, AccountIdentifier accountIdentifier) {
        return allOpeningStocks.stream().filter(account->account.getMerchantId().equals(merchantId) && account.getAccountIdentifier()==accountIdentifier && account.getAccountId().equals(accountId)).sorted(Comparator.comparing(OpeningStockAccount::getClosureDate).reversed()).findAny().orElse(null);
    }

    public static List<OpeningStockAccount> getLatestOpeningStockAccountsByAccountIdentifier(String merchantId, AccountIdentifier accountIdentifier) {
        return allOpeningStocks.stream().filter(account->account.getMerchantId().equals(merchantId) && account.getAccountIdentifier()==accountIdentifier).sorted(Comparator.comparing(OpeningStockAccount::getClosureDate).reversed()).collect(Collectors.toList());
    }

    public static List<OpeningStockAccount> getAllAccounts(String merchantId){
        return allOpeningStocks.stream().filter(ac->ac.getMerchantId().equals(merchantId)).collect(Collectors.toList());
    }

}
