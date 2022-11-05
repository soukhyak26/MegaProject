package com.affaince.accounting.pnl;

import com.affaince.accounting.db.*;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.CreditLedgerEntry;
import com.affaince.accounting.ledger.accounts.DebitLedgerEntry;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.ledger.accounts.LedgerAccountEntry;
import com.affaince.accounting.reconcile.AccountingPeriod;
import com.affaince.accounting.trials.TrialBalance;
import com.affaince.accounting.trials.TrialBalanceEntry;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DefaultProfitAndLossAccountPostingProcessor implements ProfitAndLossAccountPostingProcessor{
    private final AccountDatabaseSimulator accountDatabaseSimulator;
    private final TrialBalanceDatabaseSimulator trialBalanceDatabaseSimulator;

    @Autowired
    public DefaultProfitAndLossAccountPostingProcessor(AccountDatabaseSimulator accountDatabaseSimulator, TrialBalanceDatabaseSimulator trialBalanceDatabaseSimulator) {
        this.accountDatabaseSimulator = accountDatabaseSimulator;
        this.trialBalanceDatabaseSimulator = trialBalanceDatabaseSimulator;
    }

    @Override
    public LedgerAccount postToProfitAndLossAccount(String merchantId, LocalDateTime startDate, LocalDateTime closureDate, AccountingPeriod accountingPeriod) {
        TrialBalance trialBalance= trialBalanceDatabaseSimulator.searchLatestTrialBalance(merchantId);
        assert trialBalance != null;
        List<TrialBalanceEntry> creditEntries =trialBalance.getCreditEntries();
        List<TrialBalanceEntry> debitEntries = trialBalance.getDebitEntries();
        LedgerAccount profitAndLossAccount = accountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(merchantId, "profitAndLoss", AccountIdentifier.PROFIT_AND_LOSS_ACCOUNT,startDate,closureDate);
        for(TrialBalanceEntry trialBalanceEntry: creditEntries){
            profitAndLossAccount = postTrialBalanceEntry(merchantId,profitAndLossAccount,trialBalanceEntry,startDate,closureDate, accountingPeriod);
        }
        for(TrialBalanceEntry trialBalanceEntry: debitEntries){
            profitAndLossAccount = postTrialBalanceEntry(merchantId,profitAndLossAccount,trialBalanceEntry,startDate,closureDate, accountingPeriod);
        }
        //posting complete ,now set closure date to current date
        return balanceProfitAndLossAccount(merchantId,profitAndLossAccount,startDate,closureDate);
    }

    private LedgerAccount postTrialBalanceEntry(String merchantId, LedgerAccount profitAndLossAccount, TrialBalanceEntry trialBalanceEntry, LocalDateTime startDate, LocalDateTime closureDate, AccountingPeriod accountingPeriod) {
        AccountIdentifier accountIdentifier = trialBalanceEntry.getAccountIdentifier();
        switch (accountIdentifier) {
            case EMPLOYEE_SALARY_ACCOUNT:
            case OWNER_OF_PREMISE_RENTED_ACCOUNT:
            case TAX_ACCOUNT:
            case GROSS_LOSS:
            case BUSINESS_DISCOUNT_ALLOWED_ACCOUNT:
            case SUBSCRIBER_REWARDS_ACCOUNT:
                profitAndLossAccount.debit(new DebitLedgerEntry(closureDate, trialBalanceEntry.getAccountId(), trialBalanceEntry.getAccountIdentifier(),null,null, trialBalanceEntry.getBalanceAmount()));
                break;
            case GROSS_PROFIT:
            case BUSINESS_DISCOUNT_RECEIVED_ACCOUNT:
                profitAndLossAccount.credit(new CreditLedgerEntry(closureDate, trialBalanceEntry.getAccountId(), trialBalanceEntry.getAccountIdentifier(),null,null, trialBalanceEntry.getBalanceAmount()));
                break;
        }
        return profitAndLossAccount;
    }

    public LedgerAccount balanceProfitAndLossAccount(String merchantId,LedgerAccount profitAndLossAccount,LocalDateTime startDate, LocalDateTime closureDate){
        List<LedgerAccountEntry> debitEntries = profitAndLossAccount.getDebits();
        List<LedgerAccountEntry> creditEntries = profitAndLossAccount.getCredits();

        double sumOfDebits = debitEntries.stream().mapToDouble(de->de.getAmount()).sum();
        double sumOfCredits = creditEntries.stream().mapToDouble(ce->ce.getAmount()).sum();
        if(sumOfDebits > sumOfCredits){
            double netLossAmount =sumOfDebits - sumOfCredits;
            return postNetValuesToProfitAndLossAccount(merchantId,profitAndLossAccount,netLossAmount,startDate,closureDate,false);
        }else if( sumOfCredits > sumOfDebits){
            double netProfitAmount = sumOfCredits-sumOfDebits;
            return postNetValuesToProfitAndLossAccount(merchantId,profitAndLossAccount,netProfitAmount,startDate,closureDate,true);
        }else{
            return profitAndLossAccount;
        }
    }

    private LedgerAccount postNetValuesToProfitAndLossAccount(String merchantId,LedgerAccount profitAndLossAccount, double netValue,LocalDateTime startDate, LocalDateTime closureDate, boolean isProfit){
        LedgerAccount capitalAccount = accountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(merchantId,"capital", AccountIdentifier.BUSINESS_CAPITAL_ACCOUNT,startDate,closureDate);
        if(isProfit){
            profitAndLossAccount.debit(new DebitLedgerEntry(closureDate, "netProfit", AccountIdentifier.NET_PROFIT, null,null,netValue));
            capitalAccount.credit(new CreditLedgerEntry(closureDate,"netProfit",AccountIdentifier.NET_PROFIT,null,null,netValue));
        }else{
            profitAndLossAccount.credit(new CreditLedgerEntry(closureDate, "netLoss", AccountIdentifier.NET_LOSS,null,null, netValue));
            capitalAccount.debit(new DebitLedgerEntry(closureDate,"netLoss",AccountIdentifier.NET_LOSS,null,null,netValue));
        }
        return profitAndLossAccount;
    }
}
