package com.affaince.accounting.pnl;

import com.affaince.accounting.db.*;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.trading.TradingAccount;
import com.affaince.accounting.trading.TradingAccountEntry;
import com.affaince.accounting.trading.TradingFrequency;
import com.affaince.accounting.trials.TrialBalance;
import com.affaince.accounting.trials.TrialBalanceEntry;
import org.joda.time.LocalDateTime;

import java.util.List;

public class DefaultProfitAndLossAccountPostingProcessor implements ProfitAndLossAccountPostingProcessor{
    @Override
    public ProfitAndLossAccount postToProfitAndLossAccount(String merchantId, LocalDateTime startDate, LocalDateTime closureDate, TradingFrequency tradingFrequency) {
        TrialBalance trialBalance= TrialBalanceDatabaseSimulator.searchLatestTrialBalance(merchantId);
        assert trialBalance != null;
        List<TrialBalanceEntry> creditEntries =trialBalance.getCreditEntries();
        List<TrialBalanceEntry> debitEntries = trialBalance.getDebitEntries();
        ProfitAndLossAccount profitAndLossAccount = getActiveInstanceOfProfitAndLossAccount(merchantId,startDate, closureDate, tradingFrequency);
        postLatestGrossValues(profitAndLossAccount,merchantId,startDate,closureDate);
        for(TrialBalanceEntry trialBalanceEntry: creditEntries){
            profitAndLossAccount = postTrialBalanceEntry(merchantId,profitAndLossAccount,trialBalanceEntry,startDate,closureDate,tradingFrequency);
        }
        for(TrialBalanceEntry trialBalanceEntry: debitEntries){
            profitAndLossAccount = postTrialBalanceEntry(merchantId,profitAndLossAccount,trialBalanceEntry,startDate,closureDate,tradingFrequency);
        }
        //posting complete ,now set closure date to current date
        return balanceProfitAndLossAccount(profitAndLossAccount,closureDate);
    }

    private void postLatestGrossValues(ProfitAndLossAccount profitAndLossAccount,String merchantId, LocalDateTime startDate, LocalDateTime closureDate){
        TradingAccount latestTradingAccount = TradingAccountDatabaseSimulator.searchActiveAccountsByAccountIdAndAccountIdentifier(merchantId, "trading", AccountIdentifier.TRADING_ACCOUNT,startDate,closureDate);
        TradingAccountEntry grossProfitEntry = latestTradingAccount.getDebits().stream().filter(de->de.getPeerAccountNumber().equals("grossProfit") && de.getAccountIdentifier()== AccountIdentifier.GROSS_PROFIT).findAny().orElse(null);
        TradingAccountEntry grossLossEntry = latestTradingAccount.getCredits().stream().filter(ce->ce.getPeerAccountNumber().equals("grossLoss") && ce.getAccountIdentifier()== AccountIdentifier.GROSS_LOSS).findAny().orElse(null);
        if( null != grossProfitEntry && null != grossLossEntry){
            throw new RuntimeException("Cannot have gross profit and gross loss with non zero values at the same time");
        }
        if(null != grossProfitEntry){
            profitAndLossAccount.credit(new CreditPnLAccountEntry(closureDate,grossProfitEntry.getPeerAccountNumber(),grossProfitEntry.getAccountIdentifier(),grossProfitEntry.getAmount()));
        }else if(null != grossLossEntry){
            profitAndLossAccount.debit(new DebitPnLAccountEntry(closureDate,grossLossEntry.getPeerAccountNumber(),grossLossEntry.getAccountIdentifier(),grossLossEntry.getAmount()));
        }else{
            //throw new RuntimeException("Cannot have both gross profit and gross loss NULL at the same time");
        }
    }
    private ProfitAndLossAccount postTrialBalanceEntry(String merchantId, ProfitAndLossAccount profitAndLossAccount, TrialBalanceEntry trialBalanceEntry, LocalDateTime startDate, LocalDateTime closureDate, TradingFrequency tradingFrequency) {
        AccountIdentifier accountIdentifier = trialBalanceEntry.getAccountIdentifier();
        switch (accountIdentifier) {
            case EMPLOYEE_SALARY_ACCOUNT:
            case OWNER_OF_PREMISE_RENTED_ACCOUNT:
            case TAX_ACCOUNT:
            case GROSS_LOSS:
            case BUSINESS_DISCOUNT_ALLOWED_ACCOUNT:
                profitAndLossAccount.debit(new DebitPnLAccountEntry(closureDate, trialBalanceEntry.getAccountId(), trialBalanceEntry.getAccountIdentifier(), trialBalanceEntry.getBalanceAmount()));
                break;
            case GROSS_PROFIT:
            case BUSINESS_DISCOUNT_RECEIVED_ACCOUNT:
                profitAndLossAccount.credit(new CreditPnLAccountEntry(closureDate, trialBalanceEntry.getAccountId(), trialBalanceEntry.getAccountIdentifier(), trialBalanceEntry.getBalanceAmount()));
                break;
        }
        return profitAndLossAccount;
    }

    public ProfitAndLossAccount balanceProfitAndLossAccount(ProfitAndLossAccount profitAndLossAccount, LocalDateTime postingDate){
        List<ProfitAndLossAccountEntry> debitEntries = profitAndLossAccount.getDebits();
        List<ProfitAndLossAccountEntry> creditEntries = profitAndLossAccount.getCredits();

        double sumOfDebits = debitEntries.stream().mapToDouble(de->de.getAmount()).sum();
        double sumOfCredits = creditEntries.stream().mapToDouble(ce->ce.getAmount()).sum();
        if(sumOfDebits > sumOfCredits){
            double netLossAmount =sumOfDebits - sumOfCredits;
            return postNetValuesToProfitAndLossAccount(profitAndLossAccount,netLossAmount,postingDate,false);
        }else if( sumOfCredits > sumOfDebits){
            double netProfitAmount = sumOfCredits-sumOfDebits;
            return postNetValuesToProfitAndLossAccount(profitAndLossAccount,netProfitAmount,postingDate,true);
        }else{
            return profitAndLossAccount;
        }
    }

    private ProfitAndLossAccount postNetValuesToProfitAndLossAccount(ProfitAndLossAccount profitAndLossAccount, double netValue, LocalDateTime postingDate, boolean isProfit){
        if(isProfit){
            profitAndLossAccount.debit(new DebitPnLAccountEntry(postingDate, "netProfit", AccountIdentifier.NET_PROFIT, netValue));
        }else{
            profitAndLossAccount.credit(new CreditPnLAccountEntry(postingDate, "netLoss", AccountIdentifier.NET_LOSS, netValue));
        }
        return profitAndLossAccount;
    }

    private ProfitAndLossAccount getActiveInstanceOfProfitAndLossAccount(String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod, TradingFrequency tradingFrequency) {
        ProfitAndLossAccount latestProfitAndLossAccount = ProfitAndLossAccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(merchantId, "profitAndLoss", AccountIdentifier.TRADING_ACCOUNT,startDateOfPeriod,closureDateOfPeriod);
        if (null == latestProfitAndLossAccount || (closureDateOfPeriod.isBefore(latestProfitAndLossAccount.getStartDate()) || startDateOfPeriod.isAfter(latestProfitAndLossAccount.getClosureDate()))) {
            latestProfitAndLossAccount = createProfitAndLossAccountAsPerFrequency(merchantId,startDateOfPeriod, closureDateOfPeriod, tradingFrequency);
        }
        return latestProfitAndLossAccount;
    }

    private ProfitAndLossAccount createProfitAndLossAccountAsPerFrequency(String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod, TradingFrequency tradingFrequency) {
        ProfitAndLossAccount newProfitAndLossAccount;
        switch (tradingFrequency) {
            case DAILY:
            case MONTHLY:
            case YEARLY:
                newProfitAndLossAccount = new ProfitAndLossAccount(merchantId, "profitAndLoss", AccountIdentifier.PROFITANDLOSS_ACCOUNT,
                        new LocalDateTime(startDateOfPeriod.getYear(), startDateOfPeriod.monthOfYear().get(), startDateOfPeriod.dayOfMonth().get(), 0, 0, 0),
                        new LocalDateTime(closureDateOfPeriod.getYear(), closureDateOfPeriod.monthOfYear().get(), closureDateOfPeriod.dayOfMonth().get(), 23, 59, 59)
                );
                ProfitAndLossAccountDatabaseSimulator.addAccount(newProfitAndLossAccount );
                return newProfitAndLossAccount;
        }
        return null;
    }

}
