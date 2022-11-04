package com.affaince.accounting.statements.bs.assets;

import com.affaince.accounting.statements.bs.BalanceSheetEntity;

public class NonCurrentAssets {
    private FixedAssets fixedAssets;
    private BalanceSheetEntity nonCurrentInvestments;
    private BalanceSheetEntity deferredTaxAssets;
    private BalanceSheetEntity longTermLoansAndAdvances;
    private BalanceSheetEntity otherNonCurrentAssets;

    public NonCurrentAssets(){
        this.fixedAssets = new FixedAssets();
        this.nonCurrentInvestments = new BalanceSheetEntity();
        this.deferredTaxAssets = new BalanceSheetEntity();
        this.longTermLoansAndAdvances = new BalanceSheetEntity();
        this.otherNonCurrentAssets = new BalanceSheetEntity();
    }
    public void addToNonCurrentInvestments(double value,String narration){
        this.nonCurrentInvestments.addToValue(value);
        this.nonCurrentInvestments.addToNarration(narration);
    }

    public void addToDeferredTaxAssets(double value,String narration){
        this.deferredTaxAssets.addToValue(value);
        this.deferredTaxAssets.addToNarration(narration);
    }
    public void addToLongTermLoansAndAdvances(double value,String narration){
        this.longTermLoansAndAdvances.addToValue(value);
        this.longTermLoansAndAdvances.addToNarration(narration);
    }

    public void addToOtherNonCurrentAssets(double value,String narration){
        this.otherNonCurrentAssets.addToValue(value);
        this.otherNonCurrentAssets.addToNarration(narration);
    }

    public void addToTangibleAssets(double value,String narration){
        this.fixedAssets.addToTangibleAssets(value,narration);
    }

    public void addToIntangibleAssets(double value,String narration){
        this.fixedAssets.addToIntangibleAssets(value,narration);
    }
    public void addToCapitalWIP(double value,String narration){
        this.fixedAssets.addToCapitalWIP(value,narration);
    }

    public void addToIntangibleUnderDevelopment(double value,String narration){
        this.fixedAssets.addToIntangibleUnderDevelopment(value,narration);
    }

    public FixedAssets getFixedAssets() {
        return fixedAssets;
    }

    public BalanceSheetEntity getNonCurrentInvestments() {
        return nonCurrentInvestments;
    }

    public BalanceSheetEntity getDeferredTaxAssets() {
        return deferredTaxAssets;
    }

    public BalanceSheetEntity getLongTermLoansAndAdvances() {
        return longTermLoansAndAdvances;
    }

    public BalanceSheetEntity getOtherNonCurrentAssets() {
        return otherNonCurrentAssets;
    }
}
