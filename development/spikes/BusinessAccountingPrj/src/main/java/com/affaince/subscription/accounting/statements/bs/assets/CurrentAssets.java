package com.affaince.subscription.accounting.statements.bs.assets;

import com.affaince.subscription.accounting.statements.BalanceSheetEntity;

import java.util.ArrayList;
import java.util.List;

public class CurrentAssets {
    private List<BalanceSheetEntity> currentInvestments;
    private List<BalanceSheetEntity> inventories;
    private List<BalanceSheetEntity> tradeReceivables;
    private List<BalanceSheetEntity> cashAndCashEquivalents;
    private List<BalanceSheetEntity> shortTermLoansAndAdvances;
    private List<BalanceSheetEntity> otherCurrentAssets;


    public CurrentAssets(){
        this.currentInvestments = new ArrayList<>();
        this.inventories = new ArrayList<>();
        this.tradeReceivables = new ArrayList<>();
        this.cashAndCashEquivalents = new ArrayList<>();
        this.shortTermLoansAndAdvances = new ArrayList<>();
        this.otherCurrentAssets = new ArrayList<>();
    }
    public List<BalanceSheetEntity> getCurrentInvestments() {
        return currentInvestments;
    }

    public void addToCurrentInvestments(BalanceSheetEntity balanceSheetEntity) {
        this.currentInvestments.add(balanceSheetEntity);
    }

    public List<BalanceSheetEntity> getInventories() {
        return inventories;
    }

    public void addToInventories(BalanceSheetEntity balanceSheetEntity) {
        this.inventories.add(balanceSheetEntity);
    }

    public List<BalanceSheetEntity> getTradeReceivables() {
        return tradeReceivables;
    }

    public void addToTradeReceivables(BalanceSheetEntity balanceSheetEntity) {
        this.tradeReceivables.add(balanceSheetEntity);
    }

    public List<BalanceSheetEntity> getCashAndCashEquivalents() {
        return cashAndCashEquivalents;
    }

    public void addToCashAndCashEquivalents(BalanceSheetEntity balanceSheetEntity) {
        this.cashAndCashEquivalents.add(balanceSheetEntity);
    }

    public List<BalanceSheetEntity> getShortTermLoansAndAdvances() {
        return shortTermLoansAndAdvances;
    }

    public void addToShortTermLoansAndAdvances(BalanceSheetEntity balanceSheetEntity) {
        this.shortTermLoansAndAdvances.add(balanceSheetEntity);
    }

    public List<BalanceSheetEntity> getOtherCurrentAssets() {
        return otherCurrentAssets;
    }

    public void addToOtherCurrentAssets(BalanceSheetEntity balanceSheetEntity) {
        this.otherCurrentAssets.add(balanceSheetEntity);
    }

    public List<BalanceSheetEntity> fetchAllEntries(){
        List<BalanceSheetEntity> allEntries = new ArrayList<>();
        allEntries.addAll(this.currentInvestments);
        allEntries.addAll(this.inventories);
        allEntries.addAll(this.tradeReceivables);
        allEntries.addAll(this.cashAndCashEquivalents);
        allEntries.addAll(this.shortTermLoansAndAdvances);
        allEntries.addAll(this.otherCurrentAssets);
        return allEntries;
    }

    @Override
    public String toString() {
        return "CurrentAssets{" +
                "currentInvestments=" + currentInvestments +
                ", inventories=" + inventories +
                ", tradeReceivables=" + tradeReceivables +
                ", cashAndCashEquivalents=" + cashAndCashEquivalents +
                ", shortTermLoansAndAdvances=" + shortTermLoansAndAdvances +
                ", otherCurrentAssets=" + otherCurrentAssets +
                '}';
    }
}
