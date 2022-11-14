package com.affaince.subscription.accounting.db;

import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.subscription.accounting.stock.OpeningStockAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OpeningStockDatabaseSimulator {
    private List<OpeningStockAccount> allOpeningStocks = new ArrayList<>();
    @Autowired
    public OpeningStockDatabaseSimulator(){
        allOpeningStocks = new ArrayList<>();
    }
    public void addAccount(OpeningStockAccount openingStockAccount){
        allOpeningStocks.add(openingStockAccount);
    }

    public OpeningStockAccount getLatestOpeningStockAccountsByAccountIdAndAccountIdentifier(String merchantId, String accountId, AccountIdentifier accountIdentifier) {
        return allOpeningStocks.stream().filter(account->account.getMerchantId().equals(merchantId) && account.getAccountIdentifier()==accountIdentifier && account.getAccountId().equals(accountId)).sorted(Comparator.comparing(OpeningStockAccount::getClosureDate).reversed()).findAny().orElse(null);
    }

    public List<OpeningStockAccount> getLatestOpeningStockAccountsByAccountIdentifier(String merchantId, AccountIdentifier accountIdentifier) {
        return allOpeningStocks.stream().filter(account->account.getMerchantId().equals(merchantId) && account.getAccountIdentifier()==accountIdentifier).sorted(Comparator.comparing(OpeningStockAccount::getClosureDate).reversed()).collect(Collectors.toList());
    }

    public List<OpeningStockAccount> getAllAccounts(String merchantId){
        return allOpeningStocks.stream().filter(ac->ac.getMerchantId().equals(merchantId)).collect(Collectors.toList());
    }

}
