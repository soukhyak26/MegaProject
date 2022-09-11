package com.affaince.accounting.db;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.stock.ClosingStockAccount;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ClosingStockDatabaseSimulator {
    private static final List<ClosingStockAccount> allClosingStocks = new ArrayList<>();

    public static void addAccount(ClosingStockAccount openingStockAccount){
        allClosingStocks.add(openingStockAccount);
    }

    public static ClosingStockAccount getLatestClosingStockAccountByAccountIdAndAccountIdentifier(String merchantId, String accountId, AccountIdentifier accountIdentifier) {
        return allClosingStocks.stream().filter(account->account.getMerchantId().equals(merchantId) && account.getAccountIdentifier()==accountIdentifier).sorted(Comparator.comparing(ClosingStockAccount::getClosureDate).reversed()).findAny().orElse(null);
    }

    public static List<ClosingStockAccount> getLatestClosingStockAccountsByAccountIdentifier(String merchantId, AccountIdentifier accountIdentifier) {
        return allClosingStocks.stream().filter(account->account.getMerchantId().equals(merchantId) && account.getAccountIdentifier()==accountIdentifier).sorted(Comparator.comparing(ClosingStockAccount::getClosureDate)).collect(Collectors.toList());
    }

    public static List<ClosingStockAccount> getAllAccounts(String merchantId){
        return allClosingStocks.stream().filter(ac->ac.getMerchantId().equals(merchantId)).collect(Collectors.toList());
    }

    public static List<ClosingStockAccount> getAllActiveAccounts(String merchantId,LocalDateTime postingDate){
        return allClosingStocks.stream().filter(ac->ac.getMerchantId().equals(merchantId) && (ac.getClosureDate().isEqual(postingDate) || ac.getClosureDate().isAfter(postingDate))).collect(Collectors.toList());
    }

}
