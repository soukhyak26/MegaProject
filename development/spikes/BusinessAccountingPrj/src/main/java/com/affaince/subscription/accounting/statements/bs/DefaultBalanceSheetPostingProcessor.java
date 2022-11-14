package com.affaince.subscription.accounting.statements.bs;

import com.affaince.subscription.accounting.db.AccountDatabaseSimulator;
import com.affaince.subscription.accounting.db.TrialBalanceDatabaseSimulator;
import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.subscription.accounting.ledger.accounts.LedgerAccount;
import com.affaince.subscription.accounting.ledger.accounts.LedgerAccountEntry;
import com.affaince.subscription.accounting.statements.BalanceSheetEntity;
import com.affaince.subscription.accounting.trials.TrialBalance;
import com.affaince.subscription.accounting.trials.TrialBalanceEntry;
import org.joda.time.LocalDateTime;
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
    public BalanceSheet postToBalanceSheet(String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod) {
        BalanceSheet balanceSheet = new BalanceSheet(merchantId,startDateOfPeriod,closureDateOfPeriod);
        balanceSheet = postTrialBalanceEntries(balanceSheet,merchantId, startDateOfPeriod, closureDateOfPeriod);
        balanceSheet = postProfitOrLoss(balanceSheet,merchantId, startDateOfPeriod, closureDateOfPeriod);
        balanceSheet = postClosingStock(balanceSheet,merchantId, startDateOfPeriod, closureDateOfPeriod);
        return balanceSheet;
    }
    private BalanceSheet postTrialBalanceEntries(BalanceSheet balanceSheet, String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod){
        TrialBalance trialBalance= trialBalanceDatabaseSimulator.searchLatestTrialBalance(merchantId);
        assert trialBalance != null;
        List<TrialBalanceEntry> creditEntries =trialBalance.getCreditEntries();
        List<TrialBalanceEntry> debitEntries = trialBalance.getDebitEntries();
        for(TrialBalanceEntry trialBalanceEntry: creditEntries){
            AccountIdentifier accountIdentifier = trialBalanceEntry.getAccountIdentifier();
            switch(accountIdentifier) {
                case FIXED_ASSETS_ACCOUNT:
                case BUSINESS_CASH_ACCOUNT:
                case BUSINESS_BANK_ACCOUNT:
                case SUBSCRIBER_ACCOUNT:
                    if(trialBalanceEntry.getPeerAccountIdentifier() == AccountIdentifier.BY_BALANCE_CARRIED_DOWN) {
                        BalanceSheetEntity entity = new BalanceSheetEntity(trialBalanceEntry.getAccountId(),
                                trialBalanceEntry.getAccountIdentifier(), trialBalanceEntry.getBalanceAmount(), "");
                        balanceSheet.addToCurrentCycleBalanceSheet(entity);
                    }
                    break;
                case CLOSING_STOCK_ACCOUNT:
                    BalanceSheetEntity entity = new BalanceSheetEntity(trialBalanceEntry.getAccountId(),
                            trialBalanceEntry.getAccountIdentifier(), trialBalanceEntry.getBalanceAmount(), "");
                    balanceSheet.addToCurrentCycleBalanceSheet(entity);
                    break;
            }
        }
        for(TrialBalanceEntry trialBalanceEntry: debitEntries){
            AccountIdentifier accountIdentifier = trialBalanceEntry.getAccountIdentifier();
            switch(accountIdentifier) {
                case BUSINESS_CAPITAL_ACCOUNT:
                case DISTRIBUTION_SUPPLIER_ACCOUNT:
                case SUPPLIER_OF_GOODS_ACCOUNT:
                case SERVICE_PROVIDER_ACCOUNT:
                    if(trialBalanceEntry.getPeerAccountIdentifier() == AccountIdentifier.TO_BALANCE_CARRIED_DOWN) {
                        BalanceSheetEntity entity = new BalanceSheetEntity(trialBalanceEntry.getAccountId(),
                                trialBalanceEntry.getAccountIdentifier(), trialBalanceEntry.getBalanceAmount(), "");
                        balanceSheet.addToCurrentCycleBalanceSheet(entity);
                    }
                    break;
            }
        }
        return balanceSheet;
    }

    private BalanceSheet postProfitOrLoss(BalanceSheet balanceSheet, String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod){
        LedgerAccount profitAndLossAccount = accountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(merchantId,"profitAndLoss", AccountIdentifier.PROFIT_AND_LOSS_ACCOUNT,startDateOfPeriod,closureDateOfPeriod);
        if(null != profitAndLossAccount.getDebits() && profitAndLossAccount.getDebits().size()>0 && profitAndLossAccount.getDebits().stream().anyMatch(pl->pl.getAccountIdentifier()==AccountIdentifier.NET_PROFIT)) {
            BalanceSheetEntity entityProfit = new BalanceSheetEntity(profitAndLossAccount.getAccountId(),
                    AccountIdentifier.NET_PROFIT,
                    profitAndLossAccount.getDebits().stream().filter(pldb->pldb.getAccountIdentifier()==AccountIdentifier.NET_PROFIT).findFirst().get().getAmount(),
                    "");
            balanceSheet.addToCurrentCycleBalanceSheet(entityProfit);
        }else if(null != profitAndLossAccount.getCredits() && profitAndLossAccount.getCredits().size()>0 && profitAndLossAccount.getCredits().stream().anyMatch(pl->pl.getAccountIdentifier()==AccountIdentifier.NET_LOSS)){
            BalanceSheetEntity entityProfit = new BalanceSheetEntity(profitAndLossAccount.getAccountId(),
                    AccountIdentifier.NET_LOSS,
                    (profitAndLossAccount.getCredits().stream().filter(lae -> (lae.getAccountIdentifier()==AccountIdentifier.NET_LOSS)).findFirst().get().getAmount())*-1, "");
            balanceSheet.addToCurrentCycleBalanceSheet(entityProfit);
        }
        return balanceSheet;
    }

    private BalanceSheet postClosingStock(BalanceSheet balanceSheet, String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod){
        LedgerAccount tradingAccount = accountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(merchantId,"trading", AccountIdentifier.TRADING_ACCOUNT,startDateOfPeriod,closureDateOfPeriod);
        LedgerAccountEntry ledgerAccountEntry = tradingAccount.getCredits().stream().filter(ta->ta.getAccountIdentifier() == AccountIdentifier.CLOSING_STOCK_ACCOUNT).findAny().orElse(null);
        if(null != ledgerAccountEntry && ledgerAccountEntry.getAmount()>0) {
            BalanceSheetEntity entityTradingAccount = new BalanceSheetEntity(ledgerAccountEntry.getPeerAccountNumber(),
                    ledgerAccountEntry.getAccountIdentifier(), ledgerAccountEntry.getAmount() , "");
            balanceSheet.addToCurrentCycleBalanceSheet(entityTradingAccount);
        }
        return balanceSheet;
    }


}
