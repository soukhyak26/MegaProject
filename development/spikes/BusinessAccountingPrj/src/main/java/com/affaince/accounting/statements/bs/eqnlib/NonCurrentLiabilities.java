package com.affaince.accounting.statements.bs.eqnlib;

import com.affaince.accounting.statements.bs.BalanceSheetEntity;

public class NonCurrentLiabilities {
    private BalanceSheetEntity longTermBorrowings;
    private BalanceSheetEntity deferredTaxLiabilities;
    private BalanceSheetEntity otherLongTermLiabilities;
    private BalanceSheetEntity longTermProvisions;

    public NonCurrentLiabilities(){
        this.longTermBorrowings = new BalanceSheetEntity();
        this.deferredTaxLiabilities = new BalanceSheetEntity();
        this.otherLongTermLiabilities = new BalanceSheetEntity();
        this.longTermProvisions = new BalanceSheetEntity();
    }
    public void addToLongTermBorrowings(double value,String narration){
        this.longTermBorrowings.addToValue(value);
        this.longTermBorrowings.addToNarration(narration);
    }
    public void addToDeferredTaxLiabilities(double value,String narration){
        this.deferredTaxLiabilities.addToValue(value);
        this.deferredTaxLiabilities.addToNarration(narration);
    }
    public void addToOtherLongTermLiabilities(double value,String narration){
        this.otherLongTermLiabilities.addToValue(value);
        this.otherLongTermLiabilities.addToNarration(narration);
    }

    public void addToLongTermProvisions(double value,String narration){
        this.longTermProvisions.addToValue(value);
        this.longTermProvisions.addToNarration(narration);
    }

}
