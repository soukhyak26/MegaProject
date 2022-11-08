package com.affaince.accounting.statements.bs;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.db.TrialBalanceDatabaseSimulator;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.ledger.accounts.LedgerAccountEntry;
import com.affaince.accounting.statements.BalanceSheetEntity;
import com.affaince.accounting.trials.TrialBalance;
import com.affaince.accounting.trials.TrialBalanceEntry;
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
        TrialBalance trialBalance= trialBalanceDatabaseSimulator.searchLatestTrialBalance(merchantId);
        assert trialBalance != null;
        List<TrialBalanceEntry> creditEntries =trialBalance.getCreditEntries();
        List<TrialBalanceEntry> debitEntries = trialBalance.getDebitEntries();
        BalanceSheet balanceSheet = new BalanceSheet(merchantId,startDateOfPeriod,closureDateOfPeriod);
        for(TrialBalanceEntry trialBalanceEntry: creditEntries){
            AccountIdentifier accountIdentifier = trialBalanceEntry.getAccountIdentifier();
            switch(accountIdentifier) {
                case FIXED_ASSETS_ACCOUNT:
                case CLOSING_STOCK_ACCOUNT:
                case DISTRIBUTION_SUPPLIER_ACCOUNT:
                case SUPPLIER_OF_GOODS_ACCOUNT:
                case SERVICE_PROVIDER_ACCOUNT:
                case BUSINESS_CAPITAL_ACCOUNT:
                    BalanceSheetEntity entity = new BalanceSheetEntity(trialBalanceEntry.getAccountId(),
                            trialBalanceEntry.getAccountIdentifier(),trialBalanceEntry.getBalanceAmount(),"");
                   balanceSheet.addToCurrentCycleBalanceSheet(entity);
                   break;
            }
        }
        for(TrialBalanceEntry trialBalanceEntry: debitEntries){
            AccountIdentifier accountIdentifier = trialBalanceEntry.getAccountIdentifier();
            switch(accountIdentifier) {
                case FIXED_ASSETS_ACCOUNT:
                case BUSINESS_CASH_ACCOUNT:
                case BUSINESS_BANK_ACCOUNT:
                case DISTRIBUTION_SUPPLIER_ACCOUNT:
                case SUPPLIER_OF_GOODS_ACCOUNT:
                case SERVICE_PROVIDER_ACCOUNT:
                    BalanceSheetEntity entity = new BalanceSheetEntity(trialBalanceEntry.getAccountId(),
                            trialBalanceEntry.getAccountIdentifier(),trialBalanceEntry.getBalanceAmount(),"");
                    balanceSheet.addToCurrentCycleBalanceSheet(entity);
                    break;
            }
        }
        LedgerAccount profitAndLossAccount = accountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(merchantId,"profitAndLoss", AccountIdentifier.PROFIT_AND_LOSS_ACCOUNT,startDateOfPeriod,closureDateOfPeriod);
        BalanceSheetEntity entityProfitAndLoss = new BalanceSheetEntity(profitAndLossAccount.getAccountId(),
                profitAndLossAccount.getAccountIdentifier(),profitAndLossAccount.getDebits().stream().mapToDouble(lae->lae.getAmount()).sum(),"");
        balanceSheet.addToCurrentCycleBalanceSheet(entityProfitAndLoss);

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
