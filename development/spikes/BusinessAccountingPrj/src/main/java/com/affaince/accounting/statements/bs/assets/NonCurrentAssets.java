package com.affaince.accounting.statements.bs.assets;

import com.affaince.accounting.statements.BalanceSheetEntity;

import java.util.ArrayList;
import java.util.List;

public class NonCurrentAssets {
    private FixedAssets fixedAssets;
    private List<BalanceSheetEntity> nonCurrentInvestments;
    private List<BalanceSheetEntity> deferredTaxAssets;
    private List<BalanceSheetEntity> longTermLoansAndAdvances;
    private List<BalanceSheetEntity> otherNonCurrentAssets;

    public NonCurrentAssets(){
        this.fixedAssets = new FixedAssets();
        this.nonCurrentInvestments = new ArrayList<>();
        this.deferredTaxAssets = new ArrayList<>();
        this.longTermLoansAndAdvances = new ArrayList<>();
        this.otherNonCurrentAssets = new ArrayList<>();
    }
    public void addToNonCurrentInvestments(BalanceSheetEntity balanceSheetEntity){
        this.nonCurrentInvestments.add(balanceSheetEntity);
    }

    public void addToDeferredTaxAssets(BalanceSheetEntity balanceSheetEntity){
        this.deferredTaxAssets.add(balanceSheetEntity);
    }
    public void addToLongTermLoansAndAdvances(BalanceSheetEntity balanceSheetEntity){
        this.longTermLoansAndAdvances.add(balanceSheetEntity);
    }

    public void addToOtherNonCurrentAssets(BalanceSheetEntity balanceSheetEntity){
        this.otherNonCurrentAssets.add(balanceSheetEntity);
    }

    public void addToTangibleAssets(BalanceSheetEntity balanceSheetEntity){
        this.fixedAssets.addToTangibleAssets(balanceSheetEntity);
    }

    public void addToIntangibleAssets(BalanceSheetEntity balanceSheetEntity){
        this.fixedAssets.addToIntangibleAssets(balanceSheetEntity);
    }
    public void addToCapitalWIP(BalanceSheetEntity balanceSheetEntity){
        this.fixedAssets.addToCapitalWIP(balanceSheetEntity);
    }

    public void addToIntangibleUnderDevelopment(BalanceSheetEntity balanceSheetEntity){
        this.fixedAssets.addToIntangibleUnderDevelopment(balanceSheetEntity);
    }

    public FixedAssets getFixedAssets() {
        return fixedAssets;
    }

    public List<BalanceSheetEntity> getNonCurrentInvestments() {
        return nonCurrentInvestments;
    }

    public List<BalanceSheetEntity> getDeferredTaxAssets() {
        return deferredTaxAssets;
    }

    public List<BalanceSheetEntity> getLongTermLoansAndAdvances() {
        return longTermLoansAndAdvances;
    }

    public List<BalanceSheetEntity> getOtherNonCurrentAssets() {
        return otherNonCurrentAssets;
    }
    public List<BalanceSheetEntity> fetchAllEntries(){
        List<BalanceSheetEntity> allEntries = new ArrayList<>();
        allEntries.addAll(this.fixedAssets.fetchAllEntries());
        allEntries.addAll(this.nonCurrentInvestments);
        allEntries.addAll(this.deferredTaxAssets);
        allEntries.addAll(this.longTermLoansAndAdvances);
        allEntries.addAll(this.otherNonCurrentAssets);
        return allEntries;
    }

    @Override
    public String toString() {
        return "NonCurrentAssets{" +
                "fixedAssets=" + fixedAssets +
                ", nonCurrentInvestments=" + nonCurrentInvestments +
                ", deferredTaxAssets=" + deferredTaxAssets +
                ", longTermLoansAndAdvances=" + longTermLoansAndAdvances +
                ", otherNonCurrentAssets=" + otherNonCurrentAssets +
                '}';
    }
}
