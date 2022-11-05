package com.affaince.accounting.statements.bs;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.statements.BalanceSheetEntity;
import com.affaince.accounting.statements.bs.assets.Assets;
import com.affaince.accounting.statements.bs.eqnlib.EquityAndLiabilities;
import org.joda.time.LocalDate;

public class BalanceSheet {
    private String merchantId;
    private LocalDate startDate;
    private LocalDate endDate;
    private EquityAndLiabilities pastCycleEquitiesAndLiabilities;
    private EquityAndLiabilities currentCycleEquitiesAndLiabilities;
    private Assets pastCycleAssets;
    private Assets currentCycleAssets;

    public BalanceSheet(String merchantId, LocalDate startDate, LocalDate endDate) {
        this.merchantId = merchantId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addToCurrentCycleBalanceSheet(BalanceSheetEntity balanceSheetEntity){
        AccountIdentifier accountIdentifier = balanceSheetEntity.getAccountIdentifier();
        switch (accountIdentifier) {
            case FIXED_ASSETS_ACCOUNT:
            case CLOSING_STOCK_ACCOUNT:
            case BUSINESS_CASH_ACCOUNT:
            case BUSINESS_BANK_ACCOUNT:
                addToCurrentCycleAssets(balanceSheetEntity);
                break;
            case DISTRIBUTION_SUPPLIER_ACCOUNT:
            case SUPPLIER_OF_GOODS_ACCOUNT:
            case SERVICE_PROVIDER_ACCOUNT:
            case BUSINESS_CAPITAL_ACCOUNT:
                addToCurrentCycleEquitiesAndLiabilities(balanceSheetEntity);
                break;

        }
    }
    public void addToCurrentCycleAssets(BalanceSheetEntity balanceSheetEntity){
        AccountIdentifier accountIdentifier = balanceSheetEntity.getAccountIdentifier();
        switch (accountIdentifier) {
            case FIXED_ASSETS_ACCOUNT:
                currentCycleAssets.addToTangibleAssets(balanceSheetEntity);
                break;
            case CLOSING_STOCK_ACCOUNT:
                currentCycleAssets.addToInventories(balanceSheetEntity);
                break;
            case BUSINESS_CASH_ACCOUNT:
                currentCycleAssets.addToCashAndCashEquivalents(balanceSheetEntity);
                break;
            case BUSINESS_BANK_ACCOUNT:
                currentCycleAssets.addToCashAndCashEquivalents(balanceSheetEntity);
                break;
        }
    }
    public void addToCurrentCycleEquitiesAndLiabilities(BalanceSheetEntity balanceSheetEntity){
        AccountIdentifier accountIdentifier = balanceSheetEntity.getAccountIdentifier();
        switch (accountIdentifier) {
            case DISTRIBUTION_SUPPLIER_ACCOUNT:
            case SUPPLIER_OF_GOODS_ACCOUNT:
            case SERVICE_PROVIDER_ACCOUNT:
                currentCycleEquitiesAndLiabilities.addToTradePayables(balanceSheetEntity);
                break;
            case BUSINESS_CAPITAL_ACCOUNT:
            case NET_PROFIT:
            case NET_LOSS:
                currentCycleEquitiesAndLiabilities.addToReservesAndSurplus(balanceSheetEntity);
                break;
        }
    }
    public String getMerchantId() {
        return merchantId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public EquityAndLiabilities getPastCycleEquitiesAndLiabilities() {
        return pastCycleEquitiesAndLiabilities;
    }

    public EquityAndLiabilities getCurrentCycleEquitiesAndLiabilities() {
        return currentCycleEquitiesAndLiabilities;
    }

    public Assets getPastCycleAssets() {
        return pastCycleAssets;
    }

    public Assets getCurrentCycleAssets() {
        return currentCycleAssets;
    }
}
