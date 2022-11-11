package com.affaince.accounting.statements.bs;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.statements.BalanceSheetEntity;
import com.affaince.accounting.statements.bs.assets.Assets;
import com.affaince.accounting.statements.bs.eqnlib.EquityAndLiabilities;
import org.joda.time.LocalDateTime;

public class BalanceSheet {
    private String merchantId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private EquityAndLiabilities currentCycleEquitiesAndLiabilities;
    private Assets currentCycleAssets;

    public BalanceSheet(String merchantId, LocalDateTime startDate, LocalDateTime endDate) {
        this.merchantId = merchantId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentCycleAssets = new Assets(merchantId,startDate,endDate);
        this.currentCycleEquitiesAndLiabilities = new EquityAndLiabilities(merchantId,startDate,endDate);
    }

    public void addToCurrentCycleBalanceSheet(BalanceSheetEntity balanceSheetEntity){
        AccountIdentifier accountIdentifier = balanceSheetEntity.getAccountIdentifier();
        switch (accountIdentifier) {
            case FIXED_ASSETS_ACCOUNT:
            case CLOSING_STOCK_ACCOUNT:
            case BUSINESS_CASH_ACCOUNT:
            case BUSINESS_BANK_ACCOUNT:
            case SUBSCRIBER_ACCOUNT:
                addToCurrentCycleAssets(balanceSheetEntity);
                break;
            case DISTRIBUTION_SUPPLIER_ACCOUNT:
            case SUPPLIER_OF_GOODS_ACCOUNT:
            case SERVICE_PROVIDER_ACCOUNT:
            case BUSINESS_CAPITAL_ACCOUNT:
            case NET_PROFIT:
            case NET_LOSS:
                addToCurrentCycleEquitiesAndLiabilities(balanceSheetEntity);
                break;
        }
    }
    private void addToCurrentCycleAssets(BalanceSheetEntity balanceSheetEntity){
        AccountIdentifier accountIdentifier = balanceSheetEntity.getAccountIdentifier();
        switch (accountIdentifier) {
            case FIXED_ASSETS_ACCOUNT:
                currentCycleAssets.addToTangibleAssets(balanceSheetEntity);
                break;
            case CLOSING_STOCK_ACCOUNT:
                currentCycleAssets.addToInventories(balanceSheetEntity);
                break;
            case BUSINESS_CASH_ACCOUNT:
            case BUSINESS_BANK_ACCOUNT:
                currentCycleAssets.addToCashAndCashEquivalents(balanceSheetEntity);
                break;
            case SUBSCRIBER_ACCOUNT:
                currentCycleAssets.addToTradeReceivables(balanceSheetEntity);
                break;
        }
    }
    private void addToCurrentCycleEquitiesAndLiabilities(BalanceSheetEntity balanceSheetEntity){
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }


    public EquityAndLiabilities getCurrentCycleEquitiesAndLiabilities() {
        return currentCycleEquitiesAndLiabilities;
    }


    public Assets getCurrentCycleAssets() {
        return currentCycleAssets;
    }

    @Override
    public String toString() {
        return "BalanceSheet{" +
                "merchantId='" + merchantId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", currentCycleEquitiesAndLiabilities=" + currentCycleEquitiesAndLiabilities +
                ", currentCycleAssets=" + currentCycleAssets +
                '}';
    }
}
