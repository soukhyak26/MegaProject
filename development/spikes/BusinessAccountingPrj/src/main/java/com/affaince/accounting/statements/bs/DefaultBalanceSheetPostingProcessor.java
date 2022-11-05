package com.affaince.accounting.statements.bs;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.db.TrialBalanceDatabaseSimulator;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.CreditLedgerEntry;
import com.affaince.accounting.ledger.accounts.DebitLedgerEntry;
import com.affaince.accounting.reconcile.AccountingPeriod;
import com.affaince.accounting.trials.TrialBalance;
import com.affaince.accounting.trials.TrialBalanceEntry;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultBalanceSheetPostingProcessor implements BalanceSheetPostingProcessor{
    private final AccountDatabaseSimulator accountDatabaseSimulator;
    private final TrialBalanceDatabaseSimulator trialBalanceDatabaseSimulator;

    @Autowired
    public DefaultBalanceSheetPostingProcessor(AccountDatabaseSimulator accountDatabaseSimulator, TrialBalanceDatabaseSimulator trialBalanceDatabaseSimulator) {
        this.accountDatabaseSimulator = accountDatabaseSimulator;
        this.trialBalanceDatabaseSimulator = trialBalanceDatabaseSimulator;
    }

    @Override
    public BalanceSheet postToBalanceSheet(String merchantId, LocalDate startDateOfPeriod, LocalDate closureDateOfPeriod) {
        TrialBalance trialBalance= trialBalanceDatabaseSimulator.searchLatestTrialBalance(merchantId);
        assert trialBalance != null;
        List<TrialBalanceEntry> creditEntries =trialBalance.getCreditEntries();
        List<TrialBalanceEntry> debitEntries = trialBalance.getDebitEntries();
        BalanceSheet balanceSheet = new BalanceSheet(merchantId,startDateOfPeriod,closureDateOfPeriod);
        for(TrialBalanceEntry trialBalanceEntry: creditEntries){
            balanceSheet = postTrialBalanceEntry(merchantId,balanceSheet,trialBalanceEntry,startDateOfPeriod,closureDateOfPeriod);
        }
        for(TrialBalanceEntry trialBalanceEntry: debitEntries){
            balanceSheet = postTrialBalanceEntry(merchantId,balanceSheet,trialBalanceEntry,startDateOfPeriod,closureDateOfPeriod);
        }
        //posting complete ,now set closure date to current date
        //return balanceProfitAndLossAccount(merchantId,profitAndLossAccount,startDate,closureDate);
        return balanceSheet;
    }
    private BalanceSheet postTrialBalanceEntry(String merchantId, BalanceSheet balanceSheet, TrialBalanceEntry trialBalanceEntry, LocalDate startDate, LocalDate closureDate) {
        AccountIdentifier accountIdentifier = trialBalanceEntry.getAccountIdentifier();
        return balanceSheet;
    }



}
