package com.affaince.subscription.accounting.statements.bs.eqnlib;

import com.affaince.subscription.accounting.statements.BalanceSheetEntity;

import java.util.ArrayList;
import java.util.List;

public class CurrentLiabilities {
    private List<BalanceSheetEntity> shortTermBorrowings;
    private List<BalanceSheetEntity> tradePayables;
    private List<BalanceSheetEntity> otherCurrentLiabilities;
    private List<BalanceSheetEntity> shortTermProvisions;

    public CurrentLiabilities(){
        this.shortTermBorrowings = new ArrayList<>();
        this.tradePayables=new ArrayList<>();
        this.otherCurrentLiabilities = new ArrayList<>();
        this.shortTermProvisions = new ArrayList<>();
    }
    public void addToShortTermBorrowings(BalanceSheetEntity balanceSheetEntity){
        this.shortTermBorrowings.add(balanceSheetEntity);
    }
    public void addToTradePayables(BalanceSheetEntity balanceSheetEntity){
        this.tradePayables.add(balanceSheetEntity);
    }

    public void addToOtherCurrentLiabilities(BalanceSheetEntity balanceSheetEntity){
        this. otherCurrentLiabilities.add(balanceSheetEntity);
    }
    public void addToShortTermProvisions(BalanceSheetEntity balanceSheetEntity){
        this.shortTermProvisions.add(balanceSheetEntity);
    }

    public List<BalanceSheetEntity> getShortTermBorrowings() {
        return shortTermBorrowings;
    }

    public List<BalanceSheetEntity> getTradePayables() {
        return tradePayables;
    }

    public List<BalanceSheetEntity> getOtherCurrentLiabilities() {
        return otherCurrentLiabilities;
    }

    public List<BalanceSheetEntity> getShortTermProvisions() {
        return shortTermProvisions;
    }
    public List<BalanceSheetEntity> fetchAllEntries(){
        List<BalanceSheetEntity> allEntries = new ArrayList<>();
        allEntries.addAll(this.shortTermBorrowings);
        allEntries.addAll(this.tradePayables);
        allEntries.addAll(this.otherCurrentLiabilities);
        allEntries.addAll(this.shortTermProvisions);
        return allEntries;
    }

    @Override
    public String toString() {
        return "CurrentLiabilities{" +
                "shortTermBorrowings=" + shortTermBorrowings +
                ", tradePayables=" + tradePayables +
                ", otherCurrentLiabilities=" + otherCurrentLiabilities +
                ", shortTermProvisions=" + shortTermProvisions +
                '}';
    }
}
