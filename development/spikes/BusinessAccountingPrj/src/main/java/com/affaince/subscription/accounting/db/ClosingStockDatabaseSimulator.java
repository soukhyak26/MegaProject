package com.affaince.subscription.accounting.db;

import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.subscription.accounting.stock.ClosingStockAccount;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class ClosingStockDatabaseSimulator {
    private List<ClosingStockAccount> allClosingStocks ;
    @Autowired
    public ClosingStockDatabaseSimulator(){
        this.allClosingStocks = new ArrayList<>();
    }

    public void addAccount(ClosingStockAccount openingStockAccount){
        allClosingStocks.add(openingStockAccount);
    }

    public ClosingStockAccount getLatestClosingStockAccountByAccountIdAndAccountIdentifier(String merchantId, String accountId, AccountIdentifier accountIdentifier) {
        return allClosingStocks.stream().filter(account->account.getMerchantId().equals(merchantId) && account.getAccountIdentifier()==accountIdentifier).sorted(Comparator.comparing(ClosingStockAccount::getClosureDate).reversed()).findAny().orElse(null);
    }

    public List<ClosingStockAccount> getLatestClosingStockAccountsByAccountIdentifier(String merchantId, AccountIdentifier accountIdentifier) {
        return allClosingStocks.stream().filter(account->account.getMerchantId().equals(merchantId) && account.getAccountIdentifier()==accountIdentifier).sorted(Comparator.comparing(ClosingStockAccount::getClosureDate)).collect(Collectors.toList());
    }

    public List<ClosingStockAccount> getAllAccounts(String merchantId){
        return allClosingStocks.stream().filter(ac->ac.getMerchantId().equals(merchantId)).collect(Collectors.toList());
    }

    public List<ClosingStockAccount> getAllActiveAccounts(String merchantId,LocalDateTime postingDate){
        return allClosingStocks.stream().filter(ac->ac.getMerchantId().equals(merchantId) && (ac.getClosureDate().isEqual(postingDate) || ac.getClosureDate().isAfter(postingDate))).collect(Collectors.toList());
    }

}
