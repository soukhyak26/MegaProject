package com.affaince.accounting.reconcile;

import com.affaince.accounting.balance.LedgerBalancingScheduler;
import com.affaince.accounting.db.*;
import com.affaince.accounting.journal.entity.Journal;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.pnl.ProfitAndLossAccount;
import com.affaince.accounting.pnl.ProfitAndLossAccountPostingProcessor;
import com.affaince.accounting.stock.ClosingStockAccount;
import com.affaince.accounting.stock.OpeningStockAccount;
import com.affaince.accounting.trading.TradingAccount;
import com.affaince.accounting.trading.TradingAccountPostingProcessor;
import com.affaince.accounting.trials.TrialBalance;
import com.affaince.accounting.trials.TrialBalanceProcessor;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PeriodReconciliationProcessor {
    private final JournalDatabaseSimulator journalDatabaseSimulator;
    private final AccountDatabaseSimulator accountDatabaseSimulator;
    private final LedgerBalancingScheduler ledgerBalancingScheduler;
    private final TradingAccountPostingProcessor tradingAccountPostingProcessor;
    private final ProfitAndLossAccountPostingProcessor profitAndLossAccountPostingProcessor;
    private final TrialBalanceProcessor trialBalanceProcessor;
    private final TrialBalanceDatabaseSimulator trialBalanceDatabaseSimulator;
    private final OpeningStockDatabaseSimulator openingStockDatabaseSimulator;
    private final ClosingStockDatabaseSimulator closingStockDatabaseSimulator;
    @Autowired
    public PeriodReconciliationProcessor(JournalDatabaseSimulator journalDatabaseSimulator,
                                         AccountDatabaseSimulator accountDatabaseSimulator,
                                         LedgerBalancingScheduler ledgerBalancingScheduler,
                                         TrialBalanceProcessor trialBalanceProcessor,
                                         TrialBalanceDatabaseSimulator trialBalanceDatabaseSimulator,
                                         TradingAccountPostingProcessor tradingAccountPostingProcessor,
                                         ProfitAndLossAccountPostingProcessor profitAndLossAccountPostingProcessor,
                                         OpeningStockDatabaseSimulator openingStockDatabaseSimulator,
                                         ClosingStockDatabaseSimulator closingStockDatabaseSimulator){
        this.journalDatabaseSimulator = journalDatabaseSimulator;
        this.accountDatabaseSimulator=accountDatabaseSimulator;
        this.ledgerBalancingScheduler=ledgerBalancingScheduler;
        this.trialBalanceProcessor=trialBalanceProcessor;
        this.trialBalanceDatabaseSimulator = trialBalanceDatabaseSimulator;
        this.tradingAccountPostingProcessor=tradingAccountPostingProcessor;
        this.profitAndLossAccountPostingProcessor=profitAndLossAccountPostingProcessor;
        this.openingStockDatabaseSimulator = openingStockDatabaseSimulator;
        this.closingStockDatabaseSimulator = closingStockDatabaseSimulator;
    }
    public void processStartOfPeriodOperations(String merchant, LocalDateTime startDate, LocalDateTime closureDate, AccountingPeriod accountingPeriod) {
        Journal journal = new Journal(merchant,startDate,closureDate);
        journalDatabaseSimulator.addJournal(journal);
        accountDatabaseSimulator.buildDatabase(startDate,closureDate);
        createOpeningStockAccount(merchant, startDate, closureDate, accountingPeriod);
        createClosingStockAccount(merchant, startDate, closureDate, accountingPeriod);
        createTradingAccountAsPerFrequency(merchant, startDate, closureDate, accountingPeriod);
        createProfitAndLossAccountAsPerFrequency(merchant, startDate, closureDate, accountingPeriod);
        ledgerBalancingScheduler.processOpening(merchant, startDate, closureDate);
    }


    public void processEndOfPeriodOperations(String merchant, LocalDateTime startDate, LocalDateTime closureDate, AccountingPeriod accountingPeriod) {
            ledgerBalancingScheduler.processClosure(merchant, startDate, closureDate);
            TrialBalance trialBalance = processTrialBalance(merchant, startDate, closureDate);
            if (trialBalance.isTrialBalanceTallied())
            {
                LedgerAccount tradingAccount = tradingAccountPostingProcessor.postToTradingAccount(merchant, startDate, closureDate, accountingPeriod);
                System.out.println("###########Trading################");
                System.out.println(tradingAccount);
                System.out.println("###########END - Trading################");
                LedgerAccount profitAndLossAccount = profitAndLossAccountPostingProcessor.postToProfitAndLossAccount(merchant, startDate, closureDate, accountingPeriod);
                System.out.println("###########PnL################");
                System.out.println(profitAndLossAccount);
                System.out.println("###########END - PnL################");

            }else {
                throw new RuntimeException("Trial Balance is not tallied");
            }
/*
        System.out.println("###########LEDGER################");
        printAccounts(merchant,startDate,closureDate);
        System.out.println("###########END - LEDGER################");
*/

    }

    public TrialBalance processTrialBalance(String merchantId, LocalDateTime startDate, LocalDateTime closureDate) {
        //TrialBalanceProcessor trialBalanceProcessor = new DefaultTrialBalanceProcessor2();
        TrialBalance trialBalance = trialBalanceProcessor.processTrialBalance(merchantId, startDate,closureDate);
        System.out.println(" %%%%%%%%%%%%%%%%%Start - Trial Balance%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(trialBalance);
        System.out.println(" %%%%%%%%%%%%%%%%%End - Trial Balance%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        trialBalanceDatabaseSimulator.addTrialBalance(trialBalance);
        return trialBalance;
    }

    //opening stock implementation is correct.. it just copies value from closing stock.. thats it.
    private OpeningStockAccount createOpeningStockAccount(String merchantId, LocalDateTime startDate, LocalDateTime closureDate, AccountingPeriod accountingPeriod) {
        ClosingStockAccount latestClosingStockAccount = closingStockDatabaseSimulator.getLatestClosingStockAccountByAccountIdAndAccountIdentifier(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
        OpeningStockAccount newInstance = null;
        switch (accountingPeriod) {
            case DAILY:
            case MONTHLY:
            case YEARLY:
                startDate = new LocalDateTime(startDate.getYear(), startDate.monthOfYear().get(), startDate.dayOfMonth().get(), 0, 0, 0);
                LocalDateTime endDate = new LocalDateTime(closureDate.getYear(), closureDate.monthOfYear().get(), closureDate.dayOfMonth().get(), 23, 59, 59);
                newInstance = new OpeningStockAccount(merchantId, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT, startDate, endDate);
                if (null != latestClosingStockAccount) {
                    newInstance.setBalanceAmount(latestClosingStockAccount.getBalanceAmount());
                }
                openingStockDatabaseSimulator.addAccount(newInstance);
                break;
        }
        return newInstance;
    }

    private ClosingStockAccount createClosingStockAccount(String merchantId, LocalDateTime startDate, LocalDateTime closureDate, AccountingPeriod accountingPeriod) {
        ClosingStockAccount latestClosingStockAccount = closingStockDatabaseSimulator.getLatestClosingStockAccountByAccountIdAndAccountIdentifier(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
        double closingBalanceAmount = 0;
        if (null != latestClosingStockAccount) {
            closingBalanceAmount = latestClosingStockAccount.getBalanceAmount();
        }
        ClosingStockAccount newInstance = null;
        switch (accountingPeriod) {
            case DAILY:
            case MONTHLY:
            case YEARLY:
                startDate = new LocalDateTime(startDate.getYear(), startDate.monthOfYear().get(), startDate.dayOfMonth().get(), 0, 0, 0);
                LocalDateTime endDate = new LocalDateTime(closureDate.getYear(), closureDate.monthOfYear().get(), closureDate.dayOfMonth().get(), 23, 59, 59);
                newInstance = new ClosingStockAccount(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT, startDate, endDate);
                newInstance.setBalanceAmount(closingBalanceAmount);
                closingStockDatabaseSimulator.addAccount(newInstance);
                break;
        }
        return newInstance;
    }

    private LedgerAccount createTradingAccountAsPerFrequency(String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod, AccountingPeriod accountingPeriod) {
        TradingAccount newTradingAccount;
        switch (accountingPeriod) {
            case DAILY:
            case MONTHLY:
            case YEARLY:
                newTradingAccount = new TradingAccount(merchantId, "trading", AccountIdentifier.TRADING_ACCOUNT,
                        new LocalDateTime(startDateOfPeriod.getYear(), startDateOfPeriod.monthOfYear().get(), startDateOfPeriod.dayOfMonth().get(), 0, 0, 0),
                        new LocalDateTime(closureDateOfPeriod.getYear(), closureDateOfPeriod.monthOfYear().get(), closureDateOfPeriod.dayOfMonth().get(), 23, 59, 59)
                );
                accountDatabaseSimulator.addAccount(newTradingAccount);
                return newTradingAccount;
        }
        return null;
    }


    private ProfitAndLossAccount createProfitAndLossAccountAsPerFrequency(String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod, AccountingPeriod accountingPeriod) {
        ProfitAndLossAccount newProfitAndLossAccount = new ProfitAndLossAccount(merchantId, "profitAndLoss", AccountIdentifier.PROFIT_AND_LOSS_ACCOUNT,
                new LocalDateTime(startDateOfPeriod.getYear(), startDateOfPeriod.monthOfYear().get(), startDateOfPeriod.dayOfMonth().get(), 0, 0, 0),
                new LocalDateTime(closureDateOfPeriod.getYear(), closureDateOfPeriod.monthOfYear().get(), closureDateOfPeriod.dayOfMonth().get(), 23, 59, 59)
        );
        accountDatabaseSimulator.addAccount(newProfitAndLossAccount);
        return newProfitAndLossAccount;
    }
    public void printAccounts(String merchantId,LocalDateTime startDate,LocalDateTime closureDate){
        List<LedgerAccount> allAccounts= accountDatabaseSimulator.getAllActiveAccounts(merchantId,startDate,closureDate);
        for(LedgerAccount account : allAccounts){
            if( (null != account.getDebits() && account.getDebits().size()>0)  || (null !=account.getCredits() && account.getCredits().size()>0) ) {
                System.out.println(account);
            }
        }
    }
}


