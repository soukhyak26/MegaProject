package com.affaince.accounting.statements.bs.eqnlib;

import com.affaince.accounting.statements.BalanceSheetEntity;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class EquityAndLiabilities {
    private String merchantId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ShareHoldersFunds shareHoldersFunds;
    private ShareAppPendingAllotment shareAppPendingAllotment;
    private NonCurrentLiabilities nonCurrentLiabilities;
    private CurrentLiabilities currentLiabilities;

    public EquityAndLiabilities(String merchantId, LocalDateTime startDate, LocalDateTime endDate){
        this.merchantId=merchantId;
        this.startDate=startDate;
        this.endDate=endDate;
        this.shareHoldersFunds = new ShareHoldersFunds();
        this.shareAppPendingAllotment = new ShareAppPendingAllotment();
        this.nonCurrentLiabilities = new NonCurrentLiabilities();
        this.currentLiabilities = new CurrentLiabilities();
    }

    public void addToShareCapital(BalanceSheetEntity balanceSheetEntity){
        this.shareHoldersFunds.addToShareCapital(balanceSheetEntity);
    }
    public void addToReservesAndSurplus(BalanceSheetEntity balanceSheetEntity){
        this. shareHoldersFunds.addToReservesAndSurplus(balanceSheetEntity);
    }
    public void addToMoneyReceivedAgainstShareWarrants(BalanceSheetEntity balanceSheetEntity){
        this.shareHoldersFunds.addToMoneyReceivedAgainstShareWarrants(balanceSheetEntity);
    }

    public void addToShareAppPendingAllotment(BalanceSheetEntity balanceSheetEntity){
        this.shareAppPendingAllotment.addToValue(balanceSheetEntity);
    }

    public void addToLongTermBorrowings(BalanceSheetEntity balanceSheetEntity){
        this.nonCurrentLiabilities.addToLongTermBorrowings(balanceSheetEntity);
    }
    public void addToDeferredTaxLiabilities(BalanceSheetEntity balanceSheetEntity){
        this.nonCurrentLiabilities.addToDeferredTaxLiabilities(balanceSheetEntity);
    }
    public void addToOtherLongTermLiabilities(BalanceSheetEntity balanceSheetEntity){
        this. nonCurrentLiabilities.addToOtherLongTermLiabilities(balanceSheetEntity);
    }

    public void addToLongTermProvisions(BalanceSheetEntity balanceSheetEntity){
        this.nonCurrentLiabilities.addToLongTermProvisions(balanceSheetEntity);
    }
    public void addToShortTermBorrowings(BalanceSheetEntity balanceSheetEntity){
        this.currentLiabilities.addToShortTermBorrowings(balanceSheetEntity);
    }
    public void addToTradePayables(BalanceSheetEntity balanceSheetEntity){
        this.currentLiabilities.addToTradePayables(balanceSheetEntity);
    }

    public void addToOtherCurrentLiabilities(BalanceSheetEntity balanceSheetEntity){
        this. currentLiabilities.addToOtherCurrentLiabilities(balanceSheetEntity);
    }
    public void addToShortTermProvisions(BalanceSheetEntity balanceSheetEntity){
        this.currentLiabilities.addToShortTermProvisions(balanceSheetEntity);
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

    public ShareHoldersFunds getShareHoldersFunds() {
        return shareHoldersFunds;
    }

    public ShareAppPendingAllotment getShareAppPendingAllotment() {
        return shareAppPendingAllotment;
    }

    public NonCurrentLiabilities getNonCurrentLiabilities() {
        return nonCurrentLiabilities;
    }

    public CurrentLiabilities getCurrentLiabilities() {
        return currentLiabilities;
    }

    public List<BalanceSheetEntity> fetchAllEntries(){
        List<BalanceSheetEntity> allEntries = new ArrayList<>();
        allEntries.addAll(this.shareHoldersFunds.fetchAllEntries());
        allEntries.addAll(this.shareAppPendingAllotment.fetchAllEntries());
        allEntries.addAll(this.nonCurrentLiabilities.fetchAllEntries());
        allEntries.addAll(this.currentLiabilities.fetchAllEntries());
        return allEntries;
    }

    @Override
    public String toString() {
        return "EquityAndLiabilities{" +
                "merchantId='" + merchantId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", shareHoldersFunds=" + shareHoldersFunds +
                ", shareAppPendingAllotment=" + shareAppPendingAllotment +
                ", nonCurrentLiabilities=" + nonCurrentLiabilities +
                ", currentLiabilities=" + currentLiabilities +
                '}';
    }
}
