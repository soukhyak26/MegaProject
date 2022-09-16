package com.affaince.accounting.endofperiod;

import com.affaince.accounting.balance.LedgerBalancingScheduler;
import com.affaince.accounting.db.ClosingStockDatabaseSimulator;
import com.affaince.accounting.db.OpeningStockDatabaseSimulator;
import com.affaince.accounting.db.TrialBalanceDatabaseSimulator;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.stock.ClosingStockAccount;
import com.affaince.accounting.stock.OpeningStockAccount;
import com.affaince.accounting.trading.DefaultTradingAccountPostingProcessor;
import com.affaince.accounting.trading.TradingAccount;
import com.affaince.accounting.trading.TradingAccountPostingProcessor;
import com.affaince.accounting.trading.TradingFrequency;
import com.affaince.accounting.trials.DefaultTrialBalanceProcessor2;
import com.affaince.accounting.trials.TrialBalance;
import com.affaince.accounting.trials.TrialBalanceProcessor;
import org.joda.time.LocalDateTime;

public class PeriodReconciliationProcessor {

    public void processStartOfPeriodOperations(String merchant, LocalDateTime startDate,LocalDateTime closureDate, TradingFrequency tradingFrequency) {
        createOpeningStockAccount(merchant, startDate,closureDate, tradingFrequency);
        createClosingStockAccount(merchant, startDate,closureDate, tradingFrequency);
    }

    public void processEndOfPeriodOperations(String merchant, LocalDateTime startDate,LocalDateTime closureDate, TradingFrequency tradingFrequency){
        LedgerBalancingScheduler.processLedgerBalancing(merchant, closureDate);
        TrialBalanceProcessor trialBalanceProcessor = new DefaultTrialBalanceProcessor2();
        TrialBalance trialBalance= trialBalanceProcessor.processTrialBalance(merchant,closureDate.toLocalDate());
        System.out.println(" is trial balance tallied? :: " + trialBalance.isTrialBalanceTallied());
        TrialBalanceDatabaseSimulator.addTrialBalance(trialBalance);


/*
        System.out.println("trial Balance :::############");
        System.out.println(trialBalance);
        System.out.println("trial balance :: ############");
*/

        TradingAccountPostingProcessor tradingAccountPostingProcessor = new DefaultTradingAccountPostingProcessor();
        TradingAccount tradingAccount = tradingAccountPostingProcessor.postToTradingAccount(merchant, startDate, closureDate, tradingFrequency);
//        System.out.println("Trading Account{}} " + tradingAccount);


    }
    //opening stock implementation is correct.. it just copies value from closing stock.. thats it.
    private OpeningStockAccount createOpeningStockAccount(String merchantId,LocalDateTime startDate, LocalDateTime  closureDate, TradingFrequency tradingFrequency) {
        ClosingStockAccount latestClosingStockAccount = ClosingStockDatabaseSimulator.getLatestClosingStockAccountByAccountIdAndAccountIdentifier(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
        OpeningStockAccount newInstance = null;
        switch (tradingFrequency) {
            case DAILY:
            case MONTHLY:
            case YEARLY:
                //LocalDateTime startDate = closureDate.plusMinutes(1);
                startDate = new LocalDateTime(startDate.getYear(), startDate.monthOfYear().get(), startDate.dayOfMonth().get(), 0, 0, 0);
                LocalDateTime endDate = new LocalDateTime(closureDate.getYear(), closureDate.monthOfYear().get(), closureDate.dayOfMonth().get(), 23, 59, 59);
                newInstance = new OpeningStockAccount(merchantId, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT, startDate, endDate);
                if( null != latestClosingStockAccount) {
                    newInstance.setBalanceAmount(latestClosingStockAccount.getBalanceAmount());
                }
                OpeningStockDatabaseSimulator.addAccount(newInstance);
                break;
        }
        return newInstance;
    }

    private ClosingStockAccount createClosingStockAccount(String merchantId,LocalDateTime startDate, LocalDateTime closureDate, TradingFrequency tradingFrequency) {
        ClosingStockAccount latestClosingStockAccount = ClosingStockDatabaseSimulator.getLatestClosingStockAccountByAccountIdAndAccountIdentifier(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
        double closingBalanceAmount=0;
        if (null != latestClosingStockAccount) {
            closingBalanceAmount=latestClosingStockAccount.getBalanceAmount();
        }
        ClosingStockAccount newInstance = null;
        switch (tradingFrequency) {
            case DAILY:
            case MONTHLY:
            case YEARLY:
                startDate = new LocalDateTime(startDate.getYear(), startDate.monthOfYear().get(), startDate.dayOfMonth().get(), 0, 0, 0);
                LocalDateTime endDate = new LocalDateTime(closureDate.getYear(), closureDate.monthOfYear().get(), closureDate.dayOfMonth().get(), 23, 59, 59);
                newInstance = new ClosingStockAccount(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT, startDate, endDate);
                newInstance.setBalanceAmount(closingBalanceAmount);
                ClosingStockDatabaseSimulator.addAccount(newInstance);
                break;
        }
        return newInstance;
    }
}


