package com.affaince.accounting.statements.bs.eqnlib;

import org.joda.time.LocalDate;

public class EquityAndLiabilities {
    private String merchantId;
    private LocalDate startDate;
    private LocalDate endDate;
    private ShareHoldersFunds shareHoldersFunds;
    private ShareAppPendingAllotment shareAppPendingAllotment;
    private NonCurrentLiabilities nonCurrentLiabilities;
    private CurrentLiabilities currentLiabilities;

    public EquityAndLiabilities(String merchantId,LocalDate startDate,LocalDate endDate){
        this.merchantId=merchantId;
        this.startDate=startDate;
        this.endDate=endDate;
        this.shareHoldersFunds = new ShareHoldersFunds();
        this.shareAppPendingAllotment = new ShareAppPendingAllotment();
        this.nonCurrentLiabilities = new NonCurrentLiabilities();
        this.currentLiabilities = new CurrentLiabilities();
    }

    public void addToShareCapital(double value,String narration){
        this.shareHoldersFunds.addToShareCapital(value,narration);
    }
    public void addToReservesAndSurplus(double value,String narration){
        this. shareHoldersFunds.addToReservesAndSurplus(value,narration);
    }
    public void addToMoneyReceivedAgainstShareWarrants(double value,String narration){
        this.shareHoldersFunds.addToMoneyReceivedAgainstShareWarrants(value,narration);
    }

    public void addToValue(double value,String narration){
        this.shareAppPendingAllotment.addToValue(value,narration);
    }

    public void addToLongTermBorrowings(double value,String narration){
        this.nonCurrentLiabilities.addToLongTermBorrowings(value,narration);
    }
    public void addToDeferredTaxLiabilities(double value,String narration){
        this.nonCurrentLiabilities.addToDeferredTaxLiabilities(value,narration);
    }
    public void addToOtherLongTermLiabilities(double value,String narration){
        this. nonCurrentLiabilities.addToOtherLongTermLiabilities(value,narration);
    }

    public void addToLongTermProvisions(double value,String narration){
        this.nonCurrentLiabilities.addToLongTermProvisions(value,narration);
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
}
