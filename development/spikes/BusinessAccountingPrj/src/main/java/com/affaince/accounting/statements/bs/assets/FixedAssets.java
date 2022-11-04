package com.affaince.accounting.statements.bs.assets;

import com.affaince.accounting.statements.bs.BalanceSheetEntity;

public class FixedAssets {
    private BalanceSheetEntity tangibleAssets;
    private BalanceSheetEntity intangibleAssets;
    private BalanceSheetEntity capitalWIP;
    private BalanceSheetEntity intangibleUnderDevelopment;


    public FixedAssets(){
        this.tangibleAssets = new BalanceSheetEntity();
        this.intangibleAssets = new BalanceSheetEntity();
        this.capitalWIP = new BalanceSheetEntity();
        this.intangibleUnderDevelopment = new BalanceSheetEntity();
    }
    public void addToTangibleAssets(double value,String narration){
        this.tangibleAssets.addToValue(value);
        this.tangibleAssets.addToNarration(narration);
    }

    public void addToIntangibleAssets(double value,String narration){
        this.intangibleAssets.addToValue(value);
        this.intangibleAssets.addToNarration(narration);
    }
    public void addToCapitalWIP(double value,String narration){
        this.capitalWIP.addToValue(value);
        this.capitalWIP.addToNarration(narration);
    }

    public void addToIntangibleUnderDevelopment(double value,String narration){
        this.intangibleUnderDevelopment.addToValue(value);
        this.intangibleUnderDevelopment.addToNarration(narration);
    }

    public BalanceSheetEntity getTangibleAssets() {
        return tangibleAssets;
    }

    public BalanceSheetEntity getIntangibleAssets() {
        return intangibleAssets;
    }

    public BalanceSheetEntity getCapitalWIP() {
        return capitalWIP;
    }

    public BalanceSheetEntity getIntangibleUnderDevelopment() {
        return intangibleUnderDevelopment;
    }
}
