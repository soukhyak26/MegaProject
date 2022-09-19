package com.affaince.accounting.trading;

import com.affaince.accounting.db.*;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.CreditLedgerEntry;
import com.affaince.accounting.ledger.accounts.DebitLedgerEntry;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.ledger.accounts.LedgerAccountEntry;
import com.affaince.accounting.stock.ClosingStockAccount;
import com.affaince.accounting.stock.OpeningStockAccount;
import com.affaince.accounting.trials.TrialBalance;
import com.affaince.accounting.trials.TrialBalanceEntry;
import org.joda.time.LocalDateTime;

import java.util.List;
import java.util.Objects;

public class DefaultTradingAccountPostingProcessor implements TradingAccountPostingProcessor {
    @Override
    public LedgerAccount postToTradingAccount(String merchantId, LocalDateTime startDate, LocalDateTime closureDate, TradingFrequency tradingFrequency){
        TrialBalance trialBalance= TrialBalanceDatabaseSimulator.searchLatestTrialBalance(merchantId);
        assert trialBalance != null;
        List<TrialBalanceEntry> creditEntries =trialBalance.getCreditEntries();
        List<TrialBalanceEntry> debitEntries = trialBalance.getDebitEntries();
        LedgerAccount tradingAccount = getActiveInstanceOfTradingAccount(merchantId,startDate, closureDate, tradingFrequency);
        for(TrialBalanceEntry trialBalanceEntry: creditEntries){
            tradingAccount = postTrialBalanceEntry(merchantId,tradingAccount,trialBalanceEntry,startDate,closureDate,tradingFrequency);
        }
        for(TrialBalanceEntry trialBalanceEntry: debitEntries){
            tradingAccount = postTrialBalanceEntry(merchantId,tradingAccount,trialBalanceEntry,startDate,closureDate,tradingFrequency);
        }
        //posting complete ,now set closure date to current date
        return balanceTradingAccount(merchantId,tradingAccount,startDate,closureDate);
    }
    private LedgerAccount postTrialBalanceEntry(String merchantId, LedgerAccount tradingAccount,TrialBalanceEntry trialBalanceEntry,LocalDateTime startDate, LocalDateTime closureDate, TradingFrequency tradingFrequency) {

        AccountIdentifier accountIdentifier = trialBalanceEntry.getAccountIdentifier();
        switch (accountIdentifier) {
            case BUSINESS_PURCHASE_ACCOUNT:
            case BUSINESS_SALES_RETURN_ACCOUNT:
            case DISTRIBUTION_SUPPLIER_ACCOUNT:
                tradingAccount.debit(new DebitLedgerEntry(closureDate, trialBalanceEntry.getAccountId(), trialBalanceEntry.getAccountIdentifier(),null,null, trialBalanceEntry.getBalanceAmount()));
                break;
            case BUSINESS_PURCHASE_RETURN_ACCOUNT:
            case BUSINESS_SALES_ACCOUNT:
                tradingAccount.credit(new CreditLedgerEntry(closureDate, trialBalanceEntry.getAccountId(), trialBalanceEntry.getAccountIdentifier(),null,null, trialBalanceEntry.getBalanceAmount()));
                break;
        }
        return tradingAccount;
    }

    public LedgerAccount balanceTradingAccount(String merchantId,LedgerAccount  tradingAccount,LocalDateTime startDate, LocalDateTime closureDate){
        List<LedgerAccountEntry> debitEntries = tradingAccount.getDebits();
        List<LedgerAccountEntry> creditEntries = tradingAccount.getCredits();

        double sumOfDebits = debitEntries.stream().mapToDouble(de->de.getAmount()).sum();
        double sumOfCredits = creditEntries.stream().mapToDouble(ce->ce.getAmount()).sum();
        if(sumOfDebits > sumOfCredits){
            double grossLossAmount =sumOfDebits - sumOfCredits;
            return postGrossValuesToTradingAccount(merchantId,tradingAccount,grossLossAmount,startDate,closureDate,false);
        }else if( sumOfCredits > sumOfDebits){
            double grossProfitAmount = sumOfCredits-sumOfDebits;
            return postGrossValuesToTradingAccount(merchantId,tradingAccount,grossProfitAmount,startDate,closureDate,true);
        }else{
            return tradingAccount;
        }
    }

    private LedgerAccount postGrossValuesToTradingAccount(String merchantId,LedgerAccount tradingAccount,double grossValue,LocalDateTime startDate,LocalDateTime closureDate,boolean isProfit){
        LedgerAccount profitAndLossAccount = AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(merchantId, "profitAndLoss", AccountIdentifier.PROFIT_AND_LOSS_ACCOUNT,startDate,closureDate);
        if(isProfit){
            tradingAccount.debit(new DebitLedgerEntry(closureDate, "grossProfit", AccountIdentifier.GROSS_PROFIT,null,null, grossValue));
            profitAndLossAccount.credit(new CreditLedgerEntry(closureDate,"grossProfit",AccountIdentifier.GROSS_PROFIT,null,null,grossValue));
        }else{
            tradingAccount.credit(new CreditLedgerEntry(closureDate, "grossLoss", AccountIdentifier.GROSS_LOSS,null,null, grossValue));
            profitAndLossAccount.debit(new DebitLedgerEntry(closureDate,"grossLoss", AccountIdentifier.GROSS_LOSS,null,null,grossValue));
        }
        return tradingAccount;
    }

    private LedgerAccount getActiveInstanceOfTradingAccount(String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod, TradingFrequency tradingFrequency) {
        LedgerAccount latestTradingAccount = AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(merchantId, "trading", AccountIdentifier.TRADING_ACCOUNT,startDateOfPeriod,closureDateOfPeriod);

        //Now update trading account with opening and closing stock
        boolean isOpeningStockPresent = Objects.requireNonNull(latestTradingAccount).getDebits().stream().anyMatch(dle -> dle.getPeerAccountNumber().equals("openingStock"));
        boolean isClosingStockPresent = false;
        for (LedgerAccountEntry dle : latestTradingAccount.getCredits()) {
            if (dle.getPeerAccountNumber().equals("closingStock")) {
                isClosingStockPresent = true;
                break;
            }
        }
        if (!isOpeningStockPresent) {
            OpeningStockAccount activeInstanceOfOpeningStockAccount = OpeningStockDatabaseSimulator.getLatestOpeningStockAccountsByAccountIdAndAccountIdentifier(merchantId, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT);
            if (null != activeInstanceOfOpeningStockAccount) {
                double openingStockEntry = activeInstanceOfOpeningStockAccount.getBalanceAmount();
                latestTradingAccount.debit(new DebitLedgerEntry(closureDateOfPeriod, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT,null,null, openingStockEntry));
            }
        }
        if (!isClosingStockPresent) {
            ClosingStockAccount activeInstanceOfClosingStockAccount = ClosingStockDatabaseSimulator.getLatestClosingStockAccountByAccountIdAndAccountIdentifier(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
            if(null != activeInstanceOfClosingStockAccount) {
                double closingStockEntry = activeInstanceOfClosingStockAccount.getBalanceAmount();
                latestTradingAccount.credit(new CreditLedgerEntry(closureDateOfPeriod, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT,null,null,closingStockEntry));
            }
        }
        return latestTradingAccount;
    }

}
