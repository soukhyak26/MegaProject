package com.affaince.subscription.accounting.statements.bs.assets;


import com.affaince.subscription.accounting.statements.BalanceSheetEntity;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class Assets {
    private String merchantId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private NonCurrentAssets nonCurrentAssets;
    private CurrentAssets currentAssets;

    public Assets(String merchantId, LocalDateTime startDate, LocalDateTime endDate){
        this.merchantId = merchantId;
        this.startDate=startDate;
        this.endDate = endDate;
        this.nonCurrentAssets = new NonCurrentAssets();
        this.currentAssets = new CurrentAssets();
    }

    public void addToNonCurrentInvestments(BalanceSheetEntity balanceSheetEntity){
        this.nonCurrentAssets.addToNonCurrentInvestments(balanceSheetEntity);
    }

    public void addToDeferredTaxAssets(BalanceSheetEntity balanceSheetEntity){
        this.nonCurrentAssets.addToDeferredTaxAssets(balanceSheetEntity);
    }
    public void addToLongTermLoansAndAdvances(BalanceSheetEntity balanceSheetEntity){
        this.nonCurrentAssets.addToLongTermLoansAndAdvances(balanceSheetEntity);
    }

    public void addToOtherNonCurrentAssets(BalanceSheetEntity balanceSheetEntity){
        this.nonCurrentAssets.addToOtherNonCurrentAssets(balanceSheetEntity);
    }

    public void addToTangibleAssets(BalanceSheetEntity balanceSheetEntity){
        this.nonCurrentAssets.addToTangibleAssets(balanceSheetEntity);
    }

    public void addToIntangibleAssets(BalanceSheetEntity balanceSheetEntity){
        this.nonCurrentAssets.addToIntangibleAssets(balanceSheetEntity);
    }
    public void addToCapitalWIP(BalanceSheetEntity balanceSheetEntity){
        this.nonCurrentAssets.addToCapitalWIP(balanceSheetEntity);
    }

    public void addToIntangibleUnderDevelopment(BalanceSheetEntity balanceSheetEntity){
        this.nonCurrentAssets.addToIntangibleUnderDevelopment(balanceSheetEntity);
    }

    public void addToCurrentInvestments(BalanceSheetEntity balanceSheetEntity) {
        this.currentAssets.addToCurrentInvestments(balanceSheetEntity);
    }
    public void addToInventories(BalanceSheetEntity balanceSheetEntity) {
        this.currentAssets.addToInventories(balanceSheetEntity);
    }
    public void addToTradeReceivables(BalanceSheetEntity balanceSheetEntity) {
        this.currentAssets.addToTradeReceivables(balanceSheetEntity);
    }
    public void addToCashAndCashEquivalents(BalanceSheetEntity balanceSheetEntity) {
        this.currentAssets.addToCashAndCashEquivalents(balanceSheetEntity);
    }
    public void addToShortTermLoansAndAdvances(BalanceSheetEntity balanceSheetEntity) {
        this.currentAssets.addToShortTermLoansAndAdvances(balanceSheetEntity);
    }
    public void addToOtherCurrentAssets(BalanceSheetEntity balanceSheetEntity) {
        this.currentAssets.addToOtherCurrentAssets(balanceSheetEntity);
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

    public NonCurrentAssets getNonCurrentAssets() {
        return nonCurrentAssets;
    }

    public CurrentAssets getCurrentAssets() {
        return currentAssets;
    }

    public List<BalanceSheetEntity> fetchAllEntries(){
        List<BalanceSheetEntity> allEntries = new ArrayList<>();
        allEntries.addAll(this.nonCurrentAssets.fetchAllEntries());
        allEntries.addAll(this.currentAssets.fetchAllEntries());
        return allEntries;
    }

    @Override
    public String toString() {
        return "Assets{" +
                "merchantId='" + merchantId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", nonCurrentAssets=" + nonCurrentAssets +
                ", currentAssets=" + currentAssets +
                '}';
    }
}