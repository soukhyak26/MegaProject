package com.affaince.accounting.endofperiod;

import com.affaince.accounting.balance.LedgerBalancingScheduler;
import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.db.ClosingStockDatabaseSimulator;
import com.affaince.accounting.db.OpeningStockDatabaseSimulator;
import com.affaince.accounting.db.TrialBalanceDatabaseSimulator;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.pnl.DefaultProfitAndLossAccountPostingProcessor;
import com.affaince.accounting.pnl.ProfitAndLossAccount;
import com.affaince.accounting.pnl.ProfitAndLossAccountPostingProcessor;
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

import java.util.List;


public class PeriodReconciliationProcessor {

    public void processStartOfPeriodOperations(String merchant, LocalDateTime startDate, LocalDateTime closureDate, TradingFrequency tradingFrequency) {
        AccountDatabaseSimulator.buildDatabase(startDate,closureDate);
        createOpeningStockAccount(merchant, startDate, closureDate, tradingFrequency);
        createClosingStockAccount(merchant, startDate, closureDate, tradingFrequency);
        createTradingAccountAsPerFrequency(merchant, startDate, closureDate, tradingFrequency);
        createProfitAndLossAccountAsPerFrequency(merchant, startDate, closureDate, tradingFrequency);
        LedgerBalancingScheduler.processOpening(merchant, startDate, closureDate);
    }


    public void processEndOfPeriodOperations(String merchant, LocalDateTime startDate, LocalDateTime closureDate, TradingFrequency tradingFrequency) {
            LedgerBalancingScheduler.processClosure(merchant, startDate, closureDate);
            TrialBalance trialBalance = processTrialBalance(merchant, startDate, closureDate);
            if (trialBalance.isTrialBalanceTallied())
            {
                TradingAccountPostingProcessor tradingAccountPostingProcessor = new DefaultTradingAccountPostingProcessor();
                tradingAccountPostingProcessor.postToTradingAccount(merchant, startDate, closureDate, tradingFrequency);
                ProfitAndLossAccountPostingProcessor profitAndLossAccountPostingProcessor = new DefaultProfitAndLossAccountPostingProcessor();
                profitAndLossAccountPostingProcessor.postToProfitAndLossAccount(merchant, startDate, closureDate, tradingFrequency);
            }else {
                throw new RuntimeException("Trial Balance is not tallied");
            }
        System.out.println("###########LEDGER################");
        printAccounts(merchant,startDate,closureDate);
        System.out.println("###########END - LEDGER################");

    }

    public TrialBalance processTrialBalance(String merchantId, LocalDateTime startDate, LocalDateTime closureDate) {
        TrialBalanceProcessor trialBalanceProcessor = new DefaultTrialBalanceProcessor2();
        TrialBalance trialBalance = trialBalanceProcessor.processTrialBalance(merchantId, startDate,closureDate);
        //System.out.println(" is trial balance tallied? :: " + trialBalance.isTrialBalanceTallied());
        TrialBalanceDatabaseSimulator.addTrialBalance(trialBalance);
        return trialBalance;
    }

    //opening stock implementation is correct.. it just copies value from closing stock.. thats it.
    private OpeningStockAccount createOpeningStockAccount(String merchantId, LocalDateTime startDate, LocalDateTime closureDate, TradingFrequency tradingFrequency) {
        ClosingStockAccount latestClosingStockAccount = ClosingStockDatabaseSimulator.getLatestClosingStockAccountByAccountIdAndAccountIdentifier(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
        OpeningStockAccount newInstance = null;
        switch (tradingFrequency) {
            case DAILY:
            case MONTHLY:
            case YEARLY:
                startDate = new LocalDateTime(startDate.getYear(), startDate.monthOfYear().get(), startDate.dayOfMonth().get(), 0, 0, 0);
                LocalDateTime endDate = new LocalDateTime(closureDate.getYear(), closureDate.monthOfYear().get(), closureDate.dayOfMonth().get(), 23, 59, 59);
                newInstance = new OpeningStockAccount(merchantId, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT, startDate, endDate);
                if (null != latestClosingStockAccount) {
                    newInstance.setBalanceAmount(latestClosingStockAccount.getBalanceAmount());
                }
                OpeningStockDatabaseSimulator.addAccount(newInstance);
                break;
        }
        return newInstance;
    }

    private ClosingStockAccount createClosingStockAccount(String merchantId, LocalDateTime startDate, LocalDateTime closureDate, TradingFrequency tradingFrequency) {
        ClosingStockAccount latestClosingStockAccount = ClosingStockDatabaseSimulator.getLatestClosingStockAccountByAccountIdAndAccountIdentifier(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
        double closingBalanceAmount = 0;
        if (null != latestClosingStockAccount) {
            closingBalanceAmount = latestClosingStockAccount.getBalanceAmount();
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

    private LedgerAccount createTradingAccountAsPerFrequency(String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod, TradingFrequency tradingFrequency) {
        TradingAccount newTradingAccount;
        switch (tradingFrequency) {
            case DAILY:
            case MONTHLY:
            case YEARLY:
                newTradingAccount = new TradingAccount(merchantId, "trading", AccountIdentifier.TRADING_ACCOUNT,
                        new LocalDateTime(startDateOfPeriod.getYear(), startDateOfPeriod.monthOfYear().get(), startDateOfPeriod.dayOfMonth().get(), 0, 0, 0),
                        new LocalDateTime(closureDateOfPeriod.getYear(), closureDateOfPeriod.monthOfYear().get(), closureDateOfPeriod.dayOfMonth().get(), 23, 59, 59)
                );
                AccountDatabaseSimulator.addAccount(newTradingAccount);
                return newTradingAccount;
        }
        return null;
    }


    private ProfitAndLossAccount createProfitAndLossAccountAsPerFrequency(String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod, TradingFrequency tradingFrequency) {
        ProfitAndLossAccount newProfitAndLossAccount;

        newProfitAndLossAccount = new ProfitAndLossAccount(merchantId, "profitAndLoss", AccountIdentifier.PROFIT_AND_LOSS_ACCOUNT,
                new LocalDateTime(startDateOfPeriod.getYear(), startDateOfPeriod.monthOfYear().get(), startDateOfPeriod.dayOfMonth().get(), 0, 0, 0),
                new LocalDateTime(closureDateOfPeriod.getYear(), closureDateOfPeriod.monthOfYear().get(), closureDateOfPeriod.dayOfMonth().get(), 23, 59, 59)
        );
        AccountDatabaseSimulator.addAccount(newProfitAndLossAccount);
        return newProfitAndLossAccount;
    }
    public void printAccounts(String merchantId,LocalDateTime startDate,LocalDateTime closureDate){
        List<LedgerAccount> allAccounts= AccountDatabaseSimulator.getAllActiveAccounts(merchantId,startDate,closureDate);
        for(LedgerAccount account : allAccounts){
            if( (null != account.getDebits() && account.getDebits().size()>0)  || (null !=account.getCredits() && account.getCredits().size()>0) ) {
                System.out.println(account);
            }
        }
    }
}


