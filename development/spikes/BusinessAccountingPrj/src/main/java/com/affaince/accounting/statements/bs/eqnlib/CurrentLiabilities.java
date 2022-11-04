package com.affaince.accounting.statements.bs.eqnlib;

import com.affaince.accounting.statements.bs.BalanceSheetEntity;

public class CurrentLiabilities {
    private BalanceSheetEntity shortTermBorrowings;
    private BalanceSheetEntity tradePayables;
    private BalanceSheetEntity otherCurrentLiabilities;
    private BalanceSheetEntity shortTermProvisions;

    public CurrentLiabilities(){
        this.shortTermBorrowings = new BalanceSheetEntity();
        this.tradePayables=new BalanceSheetEntity();
        this.otherCurrentLiabilities = new BalanceSheetEntity();
        this.shortTermProvisions = new BalanceSheetEntity();
    }
    public void addToShortTermBorrowings(double value,String narration){
        this.shortTermBorrowings.addToValue(value);
        this.shortTermBorrowings.addToNarration(narration);
    }
    public void addToTradePayables( double value,String narration){
        this.tradePayables.addToValue(value);
        this.tradePayables.addToNarration(narration);
    }

    public void addToOtherCurrentLiabilities(double value,String narration){
        this. otherCurrentLiabilities.addToValue(value);
        this.otherCurrentLiabilities.addToNarration(narration);
    }
    public void addToShortTermProvisions(double value,String narration){
        this.shortTermProvisions.addToValue(value);
        this.shortTermProvisions.addToNarration(narration);
    }

    public BalanceSheetEntity getShortTermBorrowings() {
        return shortTermBorrowings;
    }

    public BalanceSheetEntity getTradePayables() {
        return tradePayables;
    }

    public BalanceSheetEntity getOtherCurrentLiabilities() {
        return otherCurrentLiabilities;
    }

    public BalanceSheetEntity getShortTermProvisions() {
        return shortTermProvisions;
    }
}
