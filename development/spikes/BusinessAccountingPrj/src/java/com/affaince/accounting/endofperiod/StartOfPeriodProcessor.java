package com.affaince.accounting.endofperiod;

import com.affaince.accounting.balance.LedgerBalancingScheduler;
import com.affaince.accounting.db.ClosingStockDatabaseSimulator;
import com.affaince.accounting.db.OpeningStockDatabaseSimulator;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.stock.ClosingStockAccount;
import com.affaince.accounting.stock.OpeningStockAccount;
import com.affaince.accounting.trading.TradingFrequency;
import org.joda.time.LocalDateTime;

public class StartOfPeriodProcessor {

    public void processStartOfPeriodOperations(String merchant, LocalDateTime closureDate, TradingFrequency tradingFrequency) {

        LedgerBalancingScheduler.processLedgerBalancing(merchant, closureDate);
        createOpeningStockAccount(merchant, closureDate, tradingFrequency);
        createClosingStockAccount(merchant, closureDate, tradingFrequency);
    }

    //opening stock implementation is correct.. it just copies value from closing stock.. thats it.
    private OpeningStockAccount createOpeningStockAccount(String merchantId, LocalDateTime  closureDate, TradingFrequency tradingFrequency) {
        //obtain current instance of opening account and close it
        OpeningStockAccount latestOpeningStockAccount = OpeningStockDatabaseSimulator.getLatestOpeningStockAccountsByAccountIdAndAccountIdentifier(merchantId, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT);
        ClosingStockAccount latestClosingStockAccount = ClosingStockDatabaseSimulator.getLatestClosingStockAccountByAccountIdAndAccountIdentifier(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
        if (null != latestOpeningStockAccount) {
            latestOpeningStockAccount.setClosureDate(closureDate);
        }
        OpeningStockAccount newInstance = null;
        switch (tradingFrequency) {
            case DAILY:
                LocalDateTime startDate = closureDate.plusDays(1);
                startDate = new LocalDateTime(startDate.getYear(), startDate.monthOfYear().get(), startDate.dayOfMonth().get(), 0, 0, 0);
                LocalDateTime endDate = new LocalDateTime(startDate.getYear(), startDate.monthOfYear().get(), startDate.dayOfMonth().get(), 23, 59, 59);
                newInstance = new OpeningStockAccount(merchantId, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT, startDate, endDate);
                if( null != latestClosingStockAccount) {
                    newInstance.setBalanceAmount(latestClosingStockAccount.getBalanceAmount());
                }
                OpeningStockDatabaseSimulator.addAccount(newInstance);
                break;
            case MONTHLY:
                startDate = closureDate.plusDays(1);
                startDate = new LocalDateTime(startDate.getYear(), startDate.monthOfYear().get(), startDate.dayOfMonth().get(), 0, 0, 0);
                endDate = startDate.plusMonths(1);
                endDate = new LocalDateTime(endDate.getYear(), endDate.monthOfYear().get(), endDate.dayOfMonth().get(), 23, 59, 59);
                newInstance = new OpeningStockAccount(merchantId, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT, startDate, endDate);
                if( null != latestClosingStockAccount) {
                    newInstance.setBalanceAmount(latestClosingStockAccount.getBalanceAmount());
                }
                OpeningStockDatabaseSimulator.addAccount(newInstance);
                break;
            case YEARLY:
                startDate = closureDate.plusDays(1);
                startDate = new LocalDateTime(startDate.getYear(), startDate.monthOfYear().get(), startDate.dayOfMonth().get(), 0, 0, 0);
                endDate = startDate.plusYears(1);
                endDate = new LocalDateTime(endDate.getYear(), endDate.monthOfYear().get(), endDate.dayOfMonth().get(), 23, 59, 59);
                newInstance = new OpeningStockAccount(merchantId, "openingStock", AccountIdentifier.OPENING_STOCK_ACCOUNT, startDate, endDate);
                if( null != latestClosingStockAccount) {
                    newInstance.setBalanceAmount(latestClosingStockAccount.getBalanceAmount());
                }
                OpeningStockDatabaseSimulator.addAccount(newInstance);
                break;
        }
        return newInstance;
    }

    private ClosingStockAccount createClosingStockAccount(String merchantId, LocalDateTime closureDate, TradingFrequency tradingFrequency) {
        ClosingStockAccount latestClosingStockAccount = ClosingStockDatabaseSimulator.getLatestClosingStockAccountByAccountIdAndAccountIdentifier(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT);
        double closingBalanceAmount=0;
        if (null != latestClosingStockAccount) {
            latestClosingStockAccount.setClosureDate(closureDate);
            closingBalanceAmount=latestClosingStockAccount.getBalanceAmount();
        }
        ClosingStockAccount newInstance = null;
        switch (tradingFrequency) {
            case DAILY:
                LocalDateTime startDate = closureDate.plusDays(1);
                startDate = new LocalDateTime(startDate.getYear(), startDate.monthOfYear().get(), startDate.dayOfMonth().get(), 0, 0, 0);
                LocalDateTime endDate = new LocalDateTime(startDate.getYear(), startDate.monthOfYear().get(), startDate.dayOfMonth().get(), 23, 59, 59);
                newInstance = new ClosingStockAccount(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT, startDate, endDate);
                newInstance.setBalanceAmount(closingBalanceAmount);
                ClosingStockDatabaseSimulator.addAccount(newInstance);
                break;
            case MONTHLY:
                startDate = closureDate.plusDays(1);
                startDate = new LocalDateTime(startDate.getYear(), startDate.monthOfYear().get(), startDate.dayOfMonth().get(), 0, 0, 0);
                endDate = startDate.plusMonths(1);
                endDate = new LocalDateTime(endDate.getYear(), endDate.monthOfYear().get(), endDate.dayOfMonth().get(), 23, 59, 59);
                newInstance = new ClosingStockAccount(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT, startDate, endDate);
                newInstance.setBalanceAmount(closingBalanceAmount);
                ClosingStockDatabaseSimulator.addAccount(newInstance);
                break;
            case YEARLY:
                startDate = closureDate.plusDays(1);
                startDate = new LocalDateTime(startDate.getYear(), startDate.monthOfYear().get(), startDate.dayOfMonth().get(), 0, 0, 0);
                endDate = startDate.plusYears(1);
                endDate = new LocalDateTime(endDate.getYear(), endDate.monthOfYear().get(), endDate.dayOfMonth().get(), 23, 59, 59);
                newInstance = new ClosingStockAccount(merchantId, "closingStock", AccountIdentifier.CLOSING_STOCK_ACCOUNT, startDate, endDate);
                newInstance.setBalanceAmount(closingBalanceAmount);
                ClosingStockDatabaseSimulator.addAccount(newInstance);
                break;
        }
        return newInstance;
    }

/*
    private double calculateBalanceAmount(String merchant){
        LedgerAccount purchaseAccount = AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdentifier(merchant, AccountIdentifier.BUSINESS_PURCHASE_ACCOUNT).get(0);
        LedgerAccount purchaseReturnAccount = AccountDatabaseSimulator.searchActiveLedgerAccountsByAccountIdentifier(merchant, AccountIdentifier.BUSINESS_PURCHASE_RETURN_ACCOUNT).get(0);

        LedgerAccountEntry  purchaseAccountDebitLedgerEntry= purchaseAccount.getDebits().stream().filter(debit -> debit.getPeerAccountNumber().equals("toBalanceBroughtDown") && debit.getAccountIdentifier() == AccountIdentifier.TO_BALANCE_BROUGHT_DOWN).findAny().orElse(null);
        LedgerAccountEntry purchaseAccountCreditLedgerEntry = purchaseAccount.getCredits().stream().filter(debit -> debit.getPeerAccountNumber().equals("byBalanceBroughtDown") && debit.getAccountIdentifier() == AccountIdentifier.BY_BALANCE_BROUGHT_DOWN).findAny().orElse(null);

        LedgerAccountEntry purchaseReturnAccountCreditLedgerEntry = purchaseReturnAccount.getCredits().stream().filter(credit -> credit.getPeerAccountNumber().equals("byBalanceBroughtDown") && credit.getAccountIdentifier() == AccountIdentifier.BY_BALANCE_BROUGHT_DOWN).findAny().orElse(null);
        double purchaseDebitAmount=0;
        double purchaseCreditAmount=0;
        double purchaseReturnCreditAmount=0;

        if(null != purchaseAccountCreditLedgerEntry){
            purchaseCreditAmount = purchaseAccountCreditLedgerEntry.getAmount();
        }
        if(null != purchaseAccountDebitLedgerEntry ){
            purchaseDebitAmount=purchaseAccountDebitLedgerEntry.getAmount();
        }
        if( null != purchaseReturnAccountCreditLedgerEntry){
            purchaseReturnCreditAmount = purchaseReturnAccountCreditLedgerEntry.getAmount();
        }
        return  purchaseDebitAmount- purchaseCreditAmount  - purchaseReturnCreditAmount ;
    }
*/


}


