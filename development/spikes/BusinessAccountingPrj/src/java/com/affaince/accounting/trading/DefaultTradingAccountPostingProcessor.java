package com.affaince.accounting.trading;

import com.affaince.accounting.db.*;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.ledger.accounts.LedgerAccountEntry;
import com.affaince.accounting.stock.ClosingStockAccount;
import com.affaince.accounting.stock.OpeningStockAccount;
import org.joda.time.LocalDateTime;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultTradingAccountPostingProcessor implements TradingAccountPostingProcessor {
    @Override
    public TradingAccount postToTradingAccount(String merchantId, LocalDateTime postingDate, TradingFrequency tradingFrequency){
        List<LedgerAccount> closedLedgerAccountsOfAMerchant = AccountDatabaseSimulator.getAllLatestClosedAccounts(merchantId);
        TradingAccount tradingAccount = null;
        for(LedgerAccount ledgerAccount : closedLedgerAccountsOfAMerchant){
            List<LedgerAccountEntry> creditEntries = ledgerAccount.getCredits().stream().filter(la->la.getAccountIdentifier() != AccountIdentifier.BY_BALANCE_BROUGHT_DOWN ||la.getAccountIdentifier() != AccountIdentifier.BY_BALANCE_CARRIED_DOWN).collect(Collectors.toList());
            for(LedgerAccountEntry ledgerAccountEntry: creditEntries){
                tradingAccount = postLedgerAccountEntry(merchantId,ledgerAccountEntry,postingDate,tradingFrequency);
            }
            List<LedgerAccountEntry> debitEntries = ledgerAccount.getDebits().stream().filter(la->la.getAccountIdentifier() != AccountIdentifier.TO_BALANCE_BROUGHT_DOWN ||la.getAccountIdentifier() != AccountIdentifier.TO_BALANCE_CARRIED_DOWN).collect(Collectors.toList());
            for(LedgerAccountEntry ledgerAccountEntry: debitEntries){
                tradingAccount = postLedgerAccountEntry(merchantId,ledgerAccountEntry,postingDate,tradingFrequency);
            }
        }
        return balanceTradingAccount(tradingAccount,postingDate);
    }

    public TradingAccount balanceTradingAccount(TradingAccount tradingAccount,LocalDateTime postingDate){
        List<TradingAccountEntry> debitEntries = tradingAccount.getDebits();
        List<TradingAccountEntry> creditEntries = tradingAccount.getCredits();

        double sumOfDebits = debitEntries.stream().mapToDouble(de->de.getAmount()).sum();
        double sumOfCredits = creditEntries.stream().mapToDouble(ce->ce.getAmount()).sum();
        if(sumOfDebits > sumOfCredits){
            double grossLossAmount =sumOfDebits - sumOfCredits;
            return postGrossValuesToTradingAccount(tradingAccount,grossLossAmount,postingDate,false);
        }else if( sumOfCredits > sumOfDebits){
            double grossProfitAmount = sumOfCredits-sumOfDebits;
            return postGrossValuesToTradingAccount(tradingAccount,grossProfitAmount,postingDate,true);
        }else{
            return tradingAccount;
        }
    }

    private TradingAccount postGrossValuesToTradingAccount(TradingAccount tradingAccount,double grossValue,LocalDateTime postingDate,boolean isProfit){
        if(isProfit){
            tradingAccount.debit(new DebitTradingAccountEntry(postingDate, "grossProfit", AccountIdentifier.GROSS_PROFIT, grossValue));
        }else{
            tradingAccount.credit(new CreditTradingAccountEntry(postingDate, "grossLoss", AccountIdentifier.GROSS_LOSS, grossValue));
        }
        return tradingAccount;
    }

    private TradingAccount postLedgerAccountEntry(String merchantId, LedgerAccountEntry ledgerAccountEntry, LocalDateTime postingDate, TradingFrequency tradingFrequency) {
        TradingAccount activeInstance = getActiveInstanceOfTradingAccount(merchantId, postingDate, tradingFrequency);
        AccountIdentifier accountIdentifier = ledgerAccountEntry.getAccountIdentifier();
        switch (accountIdentifier) {
            case BUSINESS_PURCHASE_ACCOUNT:
            case BUSINESS_SALES_RETURN_ACCOUNT:
            case DISTRIBUTION_SUPPLIER_ACCOUNT:
                activeInstance.debit(new DebitTradingAccountEntry(postingDate, ledgerAccountEntry.getPeerAccountNumber(), ledgerAccountEntry.getAccountIdentifier(), ledgerAccountEntry.getAmount()));
                break;
            case BUSINESS_PURCHASE_RETURN_ACCOUNT:
            case BUSINESS_SALES_ACCOUNT:
                activeInstance.credit(new CreditTradingAccountEntry(postingDate, ledgerAccountEntry.getPeerAccountNumber(), ledgerAccountEntry.getAccountIdentifier(), ledgerAccountEntry.getAmount()));
                break;
        }
        return activeInstance;
    }

    private TradingAccount getActiveInstanceOfTradingAccount(String merchantId, LocalDateTime postingDate, TradingFrequency tradingFrequency) {
        TradingAccount latestTradingAccount = TradingAccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(merchantId, "trading", AccountIdentifier.TRADING_ACCOUNT);
        if (null == latestTradingAccount || (postingDate.isBefore(latestTradingAccount.getStartDate()) || postingDate.isAfter(latestTradingAccount.getClosureDate()))) {
            latestTradingAccount = createTradingAccountAsPerFrequency(merchantId, postingDate, tradingFrequency);
        }
        //Now update trading account with opening and closing stock
        boolean isOpeningStockPresent = Objects.requireNonNull(latestTradingAccount).getDebits().stream().anyMatch(dle -> dle.getPeerAccountNumber().equals("openingStock"));
        boolean isClosingStockPresent = false;
        for (TradingAccountEntry dle : latestTradingAccount.getCredits()) {
            if (dle.getPeerAccountNumber().equals("closingStock")) {
                isClosingStockPresent = true;
                break;
            }
        }
        if (!isOpeningStockPresent) {
            OpeningStockAccount activeInstanceOfOpeningStockAccount = OpeningStockDatabaseSimulator.getLatestOpeningStockAccountsByAccountIdAndAccountIdentifier(merchantId, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT);
            if (null != activeInstanceOfOpeningStockAccount) {
                double openingStockEntry = activeInstanceOfOpeningStockAccount.getBalanceAmount();
                latestTradingAccount.debit(new DebitTradingAccountEntry(postingDate, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT, openingStockEntry));
            }
        }
        if (!isClosingStockPresent) {
            ClosingStockAccount activeInstanceOfClosingStockAccount = ClosingStockDatabaseSimulator.getLatestClosingStockAccountByAccountIdAndAccountIdentifier(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
            if(null != activeInstanceOfClosingStockAccount) {
                double closingStockEntry = activeInstanceOfClosingStockAccount.getBalanceAmount();
                latestTradingAccount.credit(new CreditTradingAccountEntry(postingDate, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT,closingStockEntry));
            }
        }
        return latestTradingAccount;
    }

    private TradingAccount createTradingAccountAsPerFrequency(String merchantId, LocalDateTime postingDate, TradingFrequency tradingFrequency) {
        TradingAccount newTradingAccount;
        switch (tradingFrequency) {
            case DAILY:
                newTradingAccount = new TradingAccount(merchantId, "trading", AccountIdentifier.TRADING_ACCOUNT,
                        new LocalDateTime(postingDate.getYear(), postingDate.monthOfYear().get(), postingDate.dayOfMonth().get(), 0, 0, 0),
                        new LocalDateTime(9999, 12, 31, 23, 59, 59));
                TradingAccountDatabaseSimulator.addAccount(newTradingAccount);
                return newTradingAccount;
            case MONTHLY:
                newTradingAccount = new TradingAccount(merchantId, "trading", AccountIdentifier.TRADING_ACCOUNT,
                        postingDate.monthOfYear().withMinimumValue(),
                        new LocalDateTime(9999, 12, 31, 23, 59, 59));
                TradingAccountDatabaseSimulator.addAccount(newTradingAccount);
                return newTradingAccount;
            case YEARLY:
                newTradingAccount = new TradingAccount(merchantId, "trading", AccountIdentifier.TRADING_ACCOUNT,
                        postingDate.year().withMinimumValue(),
                        new LocalDateTime(9999, 12, 31, 23, 59, 59));
                TradingAccountDatabaseSimulator.addAccount(newTradingAccount);
                return newTradingAccount;
        }
        return null;
    }

}
