package com.affaince.subscription.accounting.trading;

import com.affaince.subscription.accounting.db.AccountDatabaseSimulator;
import com.affaince.subscription.accounting.db.ClosingStockDatabaseSimulator;
import com.affaince.subscription.accounting.db.OpeningStockDatabaseSimulator;
import com.affaince.subscription.accounting.db.TrialBalanceDatabaseSimulator;
import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.subscription.accounting.ledger.accounts.CreditLedgerEntry;
import com.affaince.subscription.accounting.ledger.accounts.DebitLedgerEntry;
import com.affaince.subscription.accounting.ledger.accounts.LedgerAccount;
import com.affaince.subscription.accounting.ledger.accounts.LedgerAccountEntry;
import com.affaince.subscription.accounting.reconcile.AccountingPeriod;
import com.affaince.subscription.accounting.stock.ClosingStockAccount;
import com.affaince.subscription.accounting.stock.OpeningStockAccount;
import com.affaince.subscription.accounting.trials.TrialBalance;
import com.affaince.subscription.accounting.trials.TrialBalanceEntry;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class DefaultTradingAccountPostingProcessor implements TradingAccountPostingProcessor {
    private final AccountDatabaseSimulator accountDatabaseSimulator;
    private final TrialBalanceDatabaseSimulator trialBalanceDatabaseSimulator;
    private final OpeningStockDatabaseSimulator openingStockDatabaseSimulator;
    private final ClosingStockDatabaseSimulator closingStockDatabaseSimulator;

    @Autowired
    public DefaultTradingAccountPostingProcessor(AccountDatabaseSimulator accountDatabaseSimulator, TrialBalanceDatabaseSimulator trialBalanceDatabaseSimulator, OpeningStockDatabaseSimulator openingStockDatabaseSimulator, ClosingStockDatabaseSimulator closingStockDatabaseSimulator) {
        this.accountDatabaseSimulator = accountDatabaseSimulator;
        this.trialBalanceDatabaseSimulator = trialBalanceDatabaseSimulator;
        this.openingStockDatabaseSimulator = openingStockDatabaseSimulator;
        this.closingStockDatabaseSimulator = closingStockDatabaseSimulator;
    }

    @Override
    public LedgerAccount postToTradingAccount(String merchantId, LocalDateTime startDate, LocalDateTime closureDate, AccountingPeriod accountingPeriod) {
        TrialBalance trialBalance = trialBalanceDatabaseSimulator.searchLatestTrialBalance(merchantId);
        assert trialBalance != null;
        List<TrialBalanceEntry> creditEntries = trialBalance.getCreditEntries();
        List<TrialBalanceEntry> debitEntries = trialBalance.getDebitEntries();
        LedgerAccount tradingAccount = getActiveInstanceOfTradingAccount(merchantId, startDate, closureDate, accountingPeriod);
        for (TrialBalanceEntry trialBalanceEntry : creditEntries) {
            tradingAccount = postTrialBalanceEntry(merchantId, tradingAccount, trialBalanceEntry, startDate, closureDate, accountingPeriod);
        }
        for (TrialBalanceEntry trialBalanceEntry : debitEntries) {
            tradingAccount = postTrialBalanceEntry(merchantId, tradingAccount, trialBalanceEntry, startDate, closureDate, accountingPeriod);
        }
        //posting complete ,now set closure date to current date
        return balanceTradingAccount(merchantId, tradingAccount, startDate, closureDate);
    }

    private LedgerAccount postTrialBalanceEntry(String merchantId, LedgerAccount tradingAccount, TrialBalanceEntry trialBalanceEntry, LocalDateTime startDate, LocalDateTime closureDate, AccountingPeriod accountingPeriod) {

        AccountIdentifier accountIdentifier = trialBalanceEntry.getAccountIdentifier();
        AccountIdentifier peerAccountIdentifier = trialBalanceEntry.getPeerAccountIdentifier();
        switch (accountIdentifier) {
            case BUSINESS_PURCHASE_ACCOUNT:
            case BUSINESS_SALES_RETURN_ACCOUNT:
                if( peerAccountIdentifier != AccountIdentifier.BY_BALANCE_CARRIED_DOWN && peerAccountIdentifier != AccountIdentifier.TO_BALANCE_CARRIED_DOWN){
                    tradingAccount.debit(new DebitLedgerEntry(closureDate, trialBalanceEntry.getAccountId(), trialBalanceEntry.getAccountIdentifier(), null, null, trialBalanceEntry.getBalanceAmount()));
                }
                break;
            case BUSINESS_PURCHASE_RETURN_ACCOUNT:
            case BUSINESS_SALES_ACCOUNT:
                if( peerAccountIdentifier != AccountIdentifier.BY_BALANCE_CARRIED_DOWN && peerAccountIdentifier != AccountIdentifier.TO_BALANCE_CARRIED_DOWN) {
                    tradingAccount.credit(new CreditLedgerEntry(closureDate, trialBalanceEntry.getAccountId(), trialBalanceEntry.getAccountIdentifier(), null, null, trialBalanceEntry.getBalanceAmount()));
                }
                break;
        }
        return tradingAccount;
    }

    public LedgerAccount balanceTradingAccount(String merchantId, LedgerAccount tradingAccount, LocalDateTime startDate, LocalDateTime closureDate) {
        List<LedgerAccountEntry> debitEntries = tradingAccount.getDebits();
        List<LedgerAccountEntry> creditEntries = tradingAccount.getCredits();

        double sumOfDebits = debitEntries.stream().mapToDouble(de -> de.getAmount()).sum();
        double sumOfCredits = creditEntries.stream().mapToDouble(ce -> ce.getAmount()).sum();
        if (sumOfDebits > sumOfCredits) {
            double grossLossAmount = sumOfDebits - sumOfCredits;
            return postGrossValuesToTradingAccount(merchantId, tradingAccount, grossLossAmount, startDate, closureDate, false);
        } else if (sumOfCredits > sumOfDebits) {
            double grossProfitAmount = sumOfCredits - sumOfDebits;
            return postGrossValuesToTradingAccount(merchantId, tradingAccount, grossProfitAmount, startDate, closureDate, true);
        } else {
            return tradingAccount;
        }
    }

    private LedgerAccount postGrossValuesToTradingAccount(String merchantId, LedgerAccount tradingAccount, double grossValue, LocalDateTime startDate, LocalDateTime closureDate, boolean isProfit) {
        LedgerAccount profitAndLossAccount = accountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(merchantId, "profitAndLoss", AccountIdentifier.PROFIT_AND_LOSS_ACCOUNT, startDate, closureDate);
        if (isProfit) {
            tradingAccount.debit(new DebitLedgerEntry(closureDate, "grossProfit", AccountIdentifier.GROSS_PROFIT, null, null, grossValue));
            profitAndLossAccount.credit(new CreditLedgerEntry(closureDate, "grossProfit", AccountIdentifier.GROSS_PROFIT, null, null, grossValue));
        } else {
            tradingAccount.credit(new CreditLedgerEntry(closureDate, "grossLoss", AccountIdentifier.GROSS_LOSS, null, null, grossValue));
            profitAndLossAccount.debit(new DebitLedgerEntry(closureDate, "grossLoss", AccountIdentifier.GROSS_LOSS, null, null, grossValue));
        }
        return tradingAccount;
    }

    private LedgerAccount getActiveInstanceOfTradingAccount(String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod, AccountingPeriod accountingPeriod) {
        LedgerAccount latestTradingAccount = accountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(merchantId, "trading", AccountIdentifier.TRADING_ACCOUNT, startDateOfPeriod, closureDateOfPeriod);

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
            OpeningStockAccount activeInstanceOfOpeningStockAccount = openingStockDatabaseSimulator.getLatestOpeningStockAccountsByAccountIdAndAccountIdentifier(merchantId, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT);
            if (null != activeInstanceOfOpeningStockAccount) {
                double openingStockEntry = activeInstanceOfOpeningStockAccount.getBalanceAmount();
                latestTradingAccount.debit(new DebitLedgerEntry(closureDateOfPeriod, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT, null, null, openingStockEntry));
            }
        }
        if (!isClosingStockPresent) {
            ClosingStockAccount activeInstanceOfClosingStockAccount = closingStockDatabaseSimulator.getLatestClosingStockAccountByAccountIdAndAccountIdentifier(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
            if (null != activeInstanceOfClosingStockAccount) {
                double closingStockEntry = activeInstanceOfClosingStockAccount.getBalanceAmount();
                latestTradingAccount.credit(new CreditLedgerEntry(closureDateOfPeriod, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT, null, null, closingStockEntry));
            }
        }
        return latestTradingAccount;
    }

}
