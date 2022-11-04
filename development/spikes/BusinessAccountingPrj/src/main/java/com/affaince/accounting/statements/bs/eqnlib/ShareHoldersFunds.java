package com.affaince.accounting.statements.bs.eqnlib;

import com.affaince.accounting.statements.bs.BalanceSheetEntity;

public class ShareHoldersFunds {
    private BalanceSheetEntity shareCapital;
    private BalanceSheetEntity reservesAndSurplus;
    private BalanceSheetEntity moneyReceivedAgainstShareWarrants;

    public ShareHoldersFunds(){
        this.shareCapital = new BalanceSheetEntity();
        this.reservesAndSurplus = new BalanceSheetEntity();
        this.moneyReceivedAgainstShareWarrants = new BalanceSheetEntity();
    }
    public void addToShareCapital(double value,String narration){
        this.shareCapital.addToValue(value);
        this.shareCapital.addToNarration(narration);
    }
    public void addToReservesAndSurplus(double value,String narration){
        this. reservesAndSurplus.addToValue(value);
        this.reservesAndSurplus.addToNarration(narration);
    }
    public void addToMoneyReceivedAgainstShareWarrants(double value,String narration){
        this.moneyReceivedAgainstShareWarrants.addToValue(value);
        this.moneyReceivedAgainstShareWarrants.addToNarration(narration);
    }
}
