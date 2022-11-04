package com.affaince.accounting.statements.bs.assets;

import com.affaince.accounting.statements.bs.BalanceSheetEntity;

public class CurrentAssets {
    private BalanceSheetEntity currentInvestments;
    private BalanceSheetEntity inventories;
    private BalanceSheetEntity tradeReceivables;
    private BalanceSheetEntity cashAndCashEquivalents;
    private BalanceSheetEntity shortTermLoansAndAdvances;
    private BalanceSheetEntity otherCurrentAssets;


    public CurrentAssets(){
        this.currentInvestments = new BalanceSheetEntity();
        this.inventories = new BalanceSheetEntity();
        this.tradeReceivables = new BalanceSheetEntity();
        this.cashAndCashEquivalents = new BalanceSheetEntity();
        this.shortTermLoansAndAdvances = new BalanceSheetEntity();
        this.otherCurrentAssets = new BalanceSheetEntity();
    }
    public BalanceSheetEntity getCurrentInvestments() {
        return currentInvestments;
    }

    public void addToCurrentInvestments(double value,String narration) {
        this.currentInvestments.addToValue(value);
        this.currentInvestments.addToNarration(narration);
    }

    public BalanceSheetEntity getInventories() {
        return inventories;
    }

    public void addToInventories(double value,String narration) {
        this.inventories.addToValue(value);
        this.inventories.addToNarration(narration);
    }

    public BalanceSheetEntity getTradeReceivables() {
        return tradeReceivables;
    }

    public void addToTradeReceivables(double value,String narration) {
        this.tradeReceivables.addToValue(value);
        this.tradeReceivables.addToNarration(narration);
    }

    public BalanceSheetEntity getCashAndCashEquivalents() {
        return cashAndCashEquivalents;
    }

    public void addToCashAndCashEquivalents(double value,String narration) {
        this.cashAndCashEquivalents.addToValue(value);
        this.cashAndCashEquivalents.addToNarration(narration);
    }

    public BalanceSheetEntity getShortTermLoansAndAdvances() {
        return shortTermLoansAndAdvances;
    }

    public void addToShortTermLoansAndAdvances(double value,String narration) {
        this.shortTermLoansAndAdvances.addToValue(value);
        this.shortTermLoansAndAdvances.addToNarration(narration);
    }

    public BalanceSheetEntity getOtherCurrentAssets() {
        return otherCurrentAssets;
    }

    public void addToOtherCurrentAssets(double value,String narration) {
        this.otherCurrentAssets.addToValue(value);
        this.otherCurrentAssets.addToNarration(narration);
    }


}
