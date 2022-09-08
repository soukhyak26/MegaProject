package com.affaince.accounting.trading;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.db.TrialBalanceDatabaseSimulator;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.*;
import com.affaince.accounting.stock.ClosingStockAccount;
import com.affaince.accounting.stock.OpeningStockAccount;
import com.affaince.accounting.trials.TrialBalance;
import com.affaince.accounting.trials.TrialBalanceEntry;
import org.joda.time.LocalDateTime;

import java.util.List;
import java.util.Objects;

public class DefaultTradingAccountPostingProcessor implements TradingAccountPostingProcessor {
    @Override
    public LedgerAccount postToTradingAccount(String merchantId, LocalDateTime postingDate, TradingFrequency tradingFrequency){
        TrialBalance trialBalance= TrialBalanceDatabaseSimulator.searchLatestTrialBalance(merchantId);
        assert trialBalance != null;
        List<TrialBalanceEntry> creditEntries =trialBalance.getCreditEntries();
        List<TrialBalanceEntry> debitEntries = trialBalance.getDebitEntries();
        LedgerAccount tradingAccount = null;
        for(TrialBalanceEntry trialBalanceEntry: creditEntries){
         tradingAccount = postTrialBalanceEntry(merchantId,trialBalanceEntry,postingDate,tradingFrequency);
        }
        for(TrialBalanceEntry trialBalanceEntry: debitEntries){
            tradingAccount = postTrialBalanceEntry(merchantId,trialBalanceEntry,postingDate,tradingFrequency);
        }
        return tradingAccount;
    }
    private LedgerAccount postTrialBalanceEntry(String merchantId, TrialBalanceEntry trialBalanceEntry, LocalDateTime postingDate, TradingFrequency tradingFrequency) {
        LedgerAccount activeInstance = getActiveInstanceOfTradingAccount(merchantId, postingDate, tradingFrequency);
        AccountIdentifier accountIdentifier = trialBalanceEntry.getAccountIdentifier();
        switch (accountIdentifier) {
            case BUSINESS_PURCHASE_ACCOUNT:
            case BUSINESS_SALES_RETURN_ACCOUNT:
            case DISTRIBUTION_SUPPLIER_ACCOUNT:
                activeInstance.debit(new DebitLedgerEntry(postingDate, trialBalanceEntry.getAccountId(), trialBalanceEntry.getAccountIdentifier(), trialBalanceEntry.getLedgerFolio(), trialBalanceEntry.getBalanceAmount()));
                break;
            case BUSINESS_PURCHASE_RETURN_ACCOUNT:
            case BUSINESS_SALES_ACCOUNT:
                activeInstance.credit(new CreditLedgerEntry(postingDate, trialBalanceEntry.getAccountId(), trialBalanceEntry.getAccountIdentifier(), trialBalanceEntry.getLedgerFolio(), trialBalanceEntry.getBalanceAmount()));
                break;
        }
        return null;
    }

    private LedgerAccount getActiveInstanceOfTradingAccount(String merchantId, LocalDateTime postingDate, TradingFrequency tradingFrequency) {
        LedgerAccount latestTradingAccount = AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(merchantId, "trading", AccountIdentifier.TRADING_ACCOUNT);
        if (null == latestTradingAccount || (postingDate.isBefore(latestTradingAccount.getStartDate()) || postingDate.isAfter(latestTradingAccount.getClosureDate()))) {
            latestTradingAccount = createTradingAccountAsPerFrequency(merchantId, postingDate, tradingFrequency);
        }
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
            LedgerAccount activeInstanceOfOpeningStockAccount = obtainOpeningStockAccount(merchantId, postingDate, tradingFrequency);
            if (null != activeInstanceOfOpeningStockAccount) {
                LedgerAccountEntry openingStockEntry = activeInstanceOfOpeningStockAccount.getDebits().stream().findAny().orElse(null);
                if (null != openingStockEntry) {
                    latestTradingAccount.debit(new DebitLedgerEntry(postingDate, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT, "null", openingStockEntry.getAmount()));
                }
            }
        }
        if (!isClosingStockPresent) {
            LedgerAccount activeInstanceOfClosingStockAccount = obtainClosingStockAccount(merchantId, postingDate, tradingFrequency);
            if(null != activeInstanceOfClosingStockAccount) {
                LedgerAccountEntry closingStockEntry = activeInstanceOfClosingStockAccount.getCredits().stream().findAny().orElse(null);
                if (null != closingStockEntry) {
                    latestTradingAccount.credit(new CreditLedgerEntry(postingDate, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT, "null", closingStockEntry.getAmount()));
                }
            }
        }
        return latestTradingAccount;
    }

    private TradingAccount createTradingAccountAsPerFrequency(String merchantId, LocalDateTime postingDate, TradingFrequency tradingFrequency) {
        TradingAccount newTradingAccount;
        switch (tradingFrequency) {
            case DAILY:
                newTradingAccount = new TradingAccount(merchantId, "trading", AccountIdentifier.TRADING_ACCOUNT,
                        new LocalDateTime(postingDate.getYear(), postingDate.monthOfYear().get(), postingDate.dayOfMonth().get(), 0, 0, 0),
                        new LocalDateTime(postingDate.getYear(), postingDate.monthOfYear().get(), postingDate.dayOfMonth().get(), 23, 59, 59));
                AccountDatabaseSimulator.addAccount(newTradingAccount);
                return newTradingAccount;
            case MONTHLY:
                newTradingAccount = new TradingAccount(merchantId, "trading", AccountIdentifier.TRADING_ACCOUNT,
                        postingDate.monthOfYear().withMinimumValue(),
                        postingDate.monthOfYear().withMaximumValue());
                AccountDatabaseSimulator.addAccount(newTradingAccount);
                return newTradingAccount;
            case YEARLY:
                newTradingAccount = new TradingAccount(merchantId, "trading", AccountIdentifier.TRADING_ACCOUNT,
                        postingDate.year().withMinimumValue(),
                        postingDate.year().withMaximumValue());
                AccountDatabaseSimulator.addAccount(newTradingAccount);
                return newTradingAccount;
        }
        return null;
    }

    private LedgerAccount obtainOpeningStockAccount(String merchantId, LocalDateTime postingDate, TradingFrequency tradingFrequency) {
        LedgerAccount latestOpeningStockAccount = AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(merchantId, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT);
        LedgerAccount latestClosingStockAccount = AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
        if (null == latestOpeningStockAccount){
            LedgerAccount newInstance = null;
            switch (tradingFrequency) {
                case DAILY:
                    LocalDateTime startDate = new LocalDateTime(postingDate.getYear(), postingDate.monthOfYear().get(), postingDate.dayOfMonth().get(), 0, 0, 0);
                    LocalDateTime endDate = new LocalDateTime(postingDate.getYear(), postingDate.monthOfYear().get(), postingDate.dayOfMonth().get(), 23, 59, 59);
                    newInstance = new OpeningStockAccount(merchantId, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT, startDate, endDate);
                    if(null != latestClosingStockAccount) {
                        newInstance.debit(new DebitLedgerEntry(startDate, latestClosingStockAccount.getAccountId(), latestClosingStockAccount.getAccountIdentifier(), null, latestClosingStockAccount.getCredits().iterator().next().getAmount()));
                    }
                    AccountDatabaseSimulator.addAccount(newInstance);
                    break;
                case MONTHLY:
                    startDate = postingDate.monthOfYear().withMinimumValue();
                    endDate = postingDate.monthOfYear().withMaximumValue();
                    newInstance = new OpeningStockAccount(merchantId, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT, startDate, endDate);
                    if(null != latestClosingStockAccount) {
                        newInstance.debit(new DebitLedgerEntry(startDate, latestClosingStockAccount.getAccountId(), latestClosingStockAccount.getAccountIdentifier(), null, latestClosingStockAccount.getCredits().iterator().next().getAmount()));
                    }
                    AccountDatabaseSimulator.addAccount(newInstance);
                    break;
                case YEARLY:
                    startDate = postingDate.year().withMinimumValue();
                    endDate = postingDate.year().withMaximumValue();
                    newInstance = new OpeningStockAccount(merchantId, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT, startDate, endDate);
                    if(null != latestClosingStockAccount) {
                        newInstance.debit(new DebitLedgerEntry(startDate, latestClosingStockAccount.getAccountId(), latestClosingStockAccount.getAccountIdentifier(), null, latestClosingStockAccount.getCredits().iterator().next().getAmount()));
                    }
                    AccountDatabaseSimulator.addAccount(newInstance);
                    break;
            }
            return newInstance;
        } else if(postingDate.isBefore(latestOpeningStockAccount.getStartDate()) || postingDate.isAfter(latestOpeningStockAccount.getClosureDate())) {
            LedgerAccount newInstance = null;
            switch (tradingFrequency) {
                case DAILY:
                    LocalDateTime startDate = new LocalDateTime(postingDate.getYear(), postingDate.monthOfYear().get(), postingDate.dayOfMonth().get(), 0, 0, 0);
                    LocalDateTime endDate = new LocalDateTime(postingDate.getYear(), postingDate.monthOfYear().get(), postingDate.dayOfMonth().get(), 23, 59, 59);
                    newInstance = new OpeningStockAccount(merchantId, latestOpeningStockAccount.getAccountId(), AccountIdentifier.OPENING_STOCK_ACCOUNT, startDate, endDate);
                    if(null != latestClosingStockAccount) {
                        newInstance.debit(new DebitLedgerEntry(startDate, latestClosingStockAccount.getAccountId(), latestClosingStockAccount.getAccountIdentifier(), null, latestClosingStockAccount.getCredits().iterator().next().getAmount()));
                    }
                    AccountDatabaseSimulator.addAccount(newInstance);
                    break;
                case MONTHLY:
                    startDate = postingDate.monthOfYear().withMinimumValue();
                    endDate = postingDate.monthOfYear().withMaximumValue();
                    newInstance = new OpeningStockAccount(merchantId, latestOpeningStockAccount.getAccountId(), AccountIdentifier.OPENING_STOCK_ACCOUNT, startDate, endDate);
                    if(null != latestClosingStockAccount) {
                        newInstance.debit(new DebitLedgerEntry(startDate, latestClosingStockAccount.getAccountId(), latestClosingStockAccount.getAccountIdentifier(), null, latestClosingStockAccount.getCredits().iterator().next().getAmount()));
                    }
                    AccountDatabaseSimulator.addAccount(newInstance);
                    break;
                case YEARLY:
                    startDate = postingDate.year().withMinimumValue();
                    endDate = postingDate.year().withMaximumValue();
                    newInstance = new OpeningStockAccount(merchantId, latestOpeningStockAccount.getAccountId(), AccountIdentifier.OPENING_STOCK_ACCOUNT, startDate, endDate);
                    if(null != latestClosingStockAccount) {
                        newInstance.debit(new DebitLedgerEntry(startDate, latestClosingStockAccount.getAccountId(), latestClosingStockAccount.getAccountIdentifier(), null, latestClosingStockAccount.getCredits().iterator().next().getAmount()));
                    }
                    AccountDatabaseSimulator.addAccount(newInstance);
                    break;
            }
            return newInstance;
        } else {
            return latestOpeningStockAccount;
        }
    }

    private LedgerAccount obtainClosingStockAccount(String merchantId, LocalDateTime postingDate, TradingFrequency tradingFrequency) {
        LedgerAccount latestClosingStockAccount = AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
        if (null == latestClosingStockAccount || postingDate.isBefore(latestClosingStockAccount.getStartDate()) || postingDate.isAfter(latestClosingStockAccount.getClosureDate())) {
            LedgerAccount newInstance = null;
            switch (tradingFrequency) {
                case DAILY:
                    LocalDateTime startDate = new LocalDateTime(postingDate.getYear(), postingDate.monthOfYear().get(), postingDate.dayOfMonth().get(), 0, 0, 0);
                    LocalDateTime endDate = new LocalDateTime(postingDate.getYear(), postingDate.monthOfYear().get(), postingDate.dayOfMonth().get(), 23, 59, 59);
                    newInstance = new ClosingStockAccount(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT, startDate, endDate);
                    AccountDatabaseSimulator.addAccount(newInstance);
                    break;
                case MONTHLY:
                    startDate = postingDate.monthOfYear().withMinimumValue();
                    endDate = postingDate.monthOfYear().withMaximumValue();
                    newInstance = new ClosingStockAccount(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT, startDate, endDate);
                    AccountDatabaseSimulator.addAccount(newInstance);
                    break;
                case YEARLY:
                    startDate = postingDate.year().withMinimumValue();
                    endDate = postingDate.year().withMaximumValue();
                    newInstance = new OpeningStockAccount(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT, startDate, endDate);
                    AccountDatabaseSimulator.addAccount(newInstance);
                    break;
            }
            return newInstance;
        } else {
            return latestClosingStockAccount;
        }
    }
}
