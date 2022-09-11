package com.affaince.accounting.trading;

import com.affaince.accounting.db.*;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.stock.ClosingStockAccount;
import com.affaince.accounting.stock.OpeningStockAccount;
import com.affaince.accounting.trials.TrialBalance;
import com.affaince.accounting.trials.TrialBalanceEntry;
import org.joda.time.LocalDateTime;

import java.util.List;
import java.util.Objects;

public class DefaultTradingAccountPostingProcessor implements TradingAccountPostingProcessor {
    @Override
    public TradingAccount postToTradingAccount(String merchantId, LocalDateTime postingDate, TradingFrequency tradingFrequency){
        TrialBalance trialBalance= TrialBalanceDatabaseSimulator.searchLatestTrialBalance(merchantId);
        assert trialBalance != null;
        List<TrialBalanceEntry> creditEntries =trialBalance.getCreditEntries();
        List<TrialBalanceEntry> debitEntries = trialBalance.getDebitEntries();
        TradingAccount tradingAccount = null;
        for(TrialBalanceEntry trialBalanceEntry: creditEntries){
         tradingAccount = postTrialBalanceEntry(merchantId,trialBalanceEntry,postingDate,tradingFrequency);
        }
        for(TrialBalanceEntry trialBalanceEntry: debitEntries){
            tradingAccount = postTrialBalanceEntry(merchantId,trialBalanceEntry,postingDate,tradingFrequency);
        }
        //posting complete ,now set closure date to current date
        return tradingAccount;
    }
    private TradingAccount postTrialBalanceEntry(String merchantId, TrialBalanceEntry trialBalanceEntry, LocalDateTime postingDate, TradingFrequency tradingFrequency) {
        TradingAccount activeInstance = getActiveInstanceOfTradingAccount(merchantId, postingDate, tradingFrequency);
        AccountIdentifier accountIdentifier = trialBalanceEntry.getAccountIdentifier();
        switch (accountIdentifier) {
            case BUSINESS_PURCHASE_ACCOUNT:
            case BUSINESS_SALES_RETURN_ACCOUNT:
            case DISTRIBUTION_SUPPLIER_ACCOUNT:
                activeInstance.debit(new DebitTradingAccountEntry(postingDate, trialBalanceEntry.getAccountId(), trialBalanceEntry.getAccountIdentifier(), trialBalanceEntry.getBalanceAmount()));
                break;
            case BUSINESS_PURCHASE_RETURN_ACCOUNT:
            case BUSINESS_SALES_ACCOUNT:
                activeInstance.credit(new CreditTradingAccountEntry(postingDate, trialBalanceEntry.getAccountId(), trialBalanceEntry.getAccountIdentifier(), trialBalanceEntry.getBalanceAmount()));
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
