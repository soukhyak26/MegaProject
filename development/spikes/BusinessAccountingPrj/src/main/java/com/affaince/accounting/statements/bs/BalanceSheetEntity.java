package com.affaince.accounting.statements.bs;

public class BalanceSheetEntity {
    private double value;
    private String narration;


    public double getValue() {
        return value;
    }

    public String getNarration() {
        return narration;
    }

    public void addToValue(double value){
      this.value += value;
    }

    public void addToNarration(String addedNarration){
        narration = narration + "$" + addedNarration;
    }
}
