package com.affaince.accounting.statements.bs.assets;


import org.joda.time.LocalDate;

public class Assets {
    private String merchantId;
    private LocalDate startDate;
    private LocalDate endDate;
    private NonCurrentAssets nonCurrentAssets;
    private CurrentAssets currentAssets;

    public Assets(String merchantId,LocalDate startDate,LocalDate endDate){
        this.merchantId = merchantId;
        this.startDate=startDate;
        this.endDate = endDate;
        this.nonCurrentAssets = new NonCurrentAssets();
        this.currentAssets = new CurrentAssets();
    }

    public void addToNonCurrentInvestments(double value,String narration){
        this.nonCurrentAssets.addToNonCurrentInvestments(value,narration);
    }

    public void addToDeferredTaxAssets(double value,String narration){
        this.nonCurrentAssets.addToDeferredTaxAssets(value,narration);
    }
    public void addToLongTermLoansAndAdvances(double value,String narration){
        this.nonCurrentAssets.addToLongTermLoansAndAdvances(value,narration);
    }

    public void addToOtherNonCurrentAssets(double value,String narration){
        this.nonCurrentAssets.addToOtherNonCurrentAssets(value,narration);
    }

    public void addToTangibleAssets(double value,String narration){
        this.nonCurrentAssets.addToTangibleAssets(value,narration);
    }

    public void addToIntangibleAssets(double value,String narration){
        this.nonCurrentAssets.addToIntangibleAssets(value,narration);
    }
    public void addToCapitalWIP(double value,String narration){
        this.nonCurrentAssets.addToCapitalWIP(value,narration);
    }

    public void addToIntangibleUnderDevelopment(double value,String narration){
        this.nonCurrentAssets.addToIntangibleUnderDevelopment(value,narration);
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

    public NonCurrentAssets getNonCurrentAssets() {
        return nonCurrentAssets;
    }

    public CurrentAssets getCurrentAssets() {
        return currentAssets;
    }
}
