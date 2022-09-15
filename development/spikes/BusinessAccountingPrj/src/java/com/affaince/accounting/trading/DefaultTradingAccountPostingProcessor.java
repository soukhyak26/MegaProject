package com.affaince.accounting.trading;

import com.affaince.accounting.db.*;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.ledger.accounts.LedgerAccountEntry;
import com.affaince.accounting.stock.ClosingStockAccount;
import com.affaince.accounting.stock.OpeningStockAccount;
import com.affaince.accounting.trials.TrialBalance;
import com.affaince.accounting.trials.TrialBalanceEntry;
import org.joda.time.LocalDateTime;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultTradingAccountPostingProcessor implements TradingAccountPostingProcessor {
    @Override
    public TradingAccount postToTradingAccount(String merchantId,LocalDateTime startDate, LocalDateTime closureDate, TradingFrequency tradingFrequency){
        TrialBalance trialBalance= TrialBalanceDatabaseSimulator.searchLatestTrialBalance(merchantId);
        assert trialBalance != null;
        List<TrialBalanceEntry> creditEntries =trialBalance.getCreditEntries();
        List<TrialBalanceEntry> debitEntries = trialBalance.getDebitEntries();
        TradingAccount tradingAccount = getActiveInstanceOfTradingAccount(merchantId,startDate, closureDate, tradingFrequency);
        for(TrialBalanceEntry trialBalanceEntry: creditEntries){
            tradingAccount = postTrialBalanceEntry(merchantId,tradingAccount,trialBalanceEntry,startDate,closureDate,tradingFrequency);
        }
        for(TrialBalanceEntry trialBalanceEntry: debitEntries){
            tradingAccount = postTrialBalanceEntry(merchantId,tradingAccount,trialBalanceEntry,startDate,closureDate,tradingFrequency);
        }
        //posting complete ,now set closure date to current date
        return balanceTradingAccount(tradingAccount,closureDate);
    }
    private TradingAccount postTrialBalanceEntry(String merchantId, TradingAccount tradingAccount,TrialBalanceEntry trialBalanceEntry,LocalDateTime startDate, LocalDateTime closureDate, TradingFrequency tradingFrequency) {

        AccountIdentifier accountIdentifier = trialBalanceEntry.getAccountIdentifier();
        switch (accountIdentifier) {
            case BUSINESS_PURCHASE_ACCOUNT:
            case BUSINESS_SALES_RETURN_ACCOUNT:
            case DISTRIBUTION_SUPPLIER_ACCOUNT:
                tradingAccount.debit(new DebitTradingAccountEntry(closureDate, trialBalanceEntry.getAccountId(), trialBalanceEntry.getAccountIdentifier(), trialBalanceEntry.getBalanceAmount()));
                break;
            case BUSINESS_PURCHASE_RETURN_ACCOUNT:
            case BUSINESS_SALES_ACCOUNT:
                tradingAccount.credit(new CreditTradingAccountEntry(closureDate, trialBalanceEntry.getAccountId(), trialBalanceEntry.getAccountIdentifier(), trialBalanceEntry.getBalanceAmount()));
                break;
        }
        return tradingAccount;
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

    private TradingAccount getActiveInstanceOfTradingAccount(String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod, TradingFrequency tradingFrequency) {
        TradingAccount latestTradingAccount = TradingAccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(merchantId, "trading", AccountIdentifier.TRADING_ACCOUNT,startDateOfPeriod,closureDateOfPeriod);
        if (null == latestTradingAccount || (closureDateOfPeriod.isBefore(latestTradingAccount.getStartDate()) || startDateOfPeriod.isAfter(latestTradingAccount.getClosureDate()))) {
            latestTradingAccount = createTradingAccountAsPerFrequency(merchantId,startDateOfPeriod, closureDateOfPeriod, tradingFrequency);
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
                latestTradingAccount.debit(new DebitTradingAccountEntry(closureDateOfPeriod, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT, openingStockEntry));
            }
        }
        if (!isClosingStockPresent) {
            ClosingStockAccount activeInstanceOfClosingStockAccount = ClosingStockDatabaseSimulator.getLatestClosingStockAccountByAccountIdAndAccountIdentifier(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
            if(null != activeInstanceOfClosingStockAccount) {
                double closingStockEntry = activeInstanceOfClosingStockAccount.getBalanceAmount();
                latestTradingAccount.credit(new CreditTradingAccountEntry(closureDateOfPeriod, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT,closingStockEntry));
            }
        }
        return latestTradingAccount;
    }

    private TradingAccount createTradingAccountAsPerFrequency(String merchantId,LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod, TradingFrequency tradingFrequency) {
        TradingAccount newTradingAccount;
        switch (tradingFrequency) {
            case DAILY:
            case MONTHLY:
            case YEARLY:
                newTradingAccount = new TradingAccount(merchantId, "trading", AccountIdentifier.TRADING_ACCOUNT,
                        new LocalDateTime(startDateOfPeriod.getYear(), startDateOfPeriod.monthOfYear().get(), startDateOfPeriod.dayOfMonth().get(), 0, 0, 0),
                        new LocalDateTime(closureDateOfPeriod.getYear(), closureDateOfPeriod.monthOfYear().get(), closureDateOfPeriod.dayOfMonth().get(), 23, 59, 59)
                );
                TradingAccountDatabaseSimulator.addAccount(newTradingAccount);
                return newTradingAccount;
        }
        return null;
    }

}
