package com.affaince.accounting.statements.bs.eqnlib;

import com.affaince.accounting.statements.BalanceSheetEntity;

import java.util.ArrayList;
import java.util.List;

public class NonCurrentLiabilities {
    private List<BalanceSheetEntity> longTermBorrowings;
    private List<BalanceSheetEntity> deferredTaxLiabilities;
    private List<BalanceSheetEntity> otherLongTermLiabilities;
    private List<BalanceSheetEntity> longTermProvisions;

    public NonCurrentLiabilities(){
        this.longTermBorrowings = new ArrayList<>();
        this.deferredTaxLiabilities = new ArrayList<>();
        this.otherLongTermLiabilities = new ArrayList<>();
        this.longTermProvisions = new ArrayList<>();
    }
    public void addToLongTermBorrowings(BalanceSheetEntity balanceSheetEntity){
        this.longTermBorrowings.add(balanceSheetEntity);
    }
    public void addToDeferredTaxLiabilities(BalanceSheetEntity balanceSheetEntity){
        this.deferredTaxLiabilities.add(balanceSheetEntity);
    }
    public void addToOtherLongTermLiabilities(BalanceSheetEntity balanceSheetEntity){
        this.otherLongTermLiabilities.add(balanceSheetEntity);
    }

    public void addToLongTermProvisions(BalanceSheetEntity balanceSheetEntity){
        this.longTermProvisions.add(balanceSheetEntity);
    }


    public List<BalanceSheetEntity> fetchAllEntries(){
        List<BalanceSheetEntity> allEntries = new ArrayList<>();
        allEntries.addAll(this.longTermBorrowings);
        allEntries.addAll(this.deferredTaxLiabilities);
        allEntries.addAll(this.otherLongTermLiabilities);
        allEntries.addAll(this.longTermProvisions);
        return allEntries;
    }

    @Override
    public String toString() {
        return "NonCurrentLiabilities{" +
                "longTermBorrowings=" + longTermBorrowings +
                ", deferredTaxLiabilities=" + deferredTaxLiabilities +
                ", otherLongTermLiabilities=" + otherLongTermLiabilities +
                ", longTermProvisions=" + longTermProvisions +
                '}';
    }
}
