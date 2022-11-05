package com.affaince.accounting.statements.bs.assets;

import com.affaince.accounting.statements.BalanceSheetEntity;

import java.util.ArrayList;
import java.util.List;

public class FixedAssets {
    private List<BalanceSheetEntity> tangibleAssets;
    private List<BalanceSheetEntity> intangibleAssets;
    private List<BalanceSheetEntity> capitalWIP;
    private List<BalanceSheetEntity> intangibleUnderDevelopment;


    public FixedAssets(){
        this.tangibleAssets = new ArrayList<>();
        this.intangibleAssets = new ArrayList<>();
        this.capitalWIP = new ArrayList<>();
        this.intangibleUnderDevelopment = new ArrayList<>();
    }
    public void addToTangibleAssets(BalanceSheetEntity balanceSheetEntity){
        this.tangibleAssets.add(balanceSheetEntity);
    }

    public void addToIntangibleAssets(BalanceSheetEntity balanceSheetEntity){
        this.intangibleAssets.add(balanceSheetEntity);
    }
    public void addToCapitalWIP(BalanceSheetEntity balanceSheetEntity){
        this.capitalWIP.add(balanceSheetEntity);
    }

    public void addToIntangibleUnderDevelopment(BalanceSheetEntity balanceSheetEntity){
        this.intangibleUnderDevelopment.add(balanceSheetEntity);
    }

    public List<BalanceSheetEntity> getTangibleAssets() {
        return tangibleAssets;
    }

    public List<BalanceSheetEntity> getIntangibleAssets() {
        return intangibleAssets;
    }

    public List<BalanceSheetEntity> getCapitalWIP() {
        return capitalWIP;
    }

    public List<BalanceSheetEntity> getIntangibleUnderDevelopment() {
        return intangibleUnderDevelopment;
    }
    public List<BalanceSheetEntity> fetchAllEntries(){
        List<BalanceSheetEntity> allEntries = new ArrayList<>();
        allEntries.addAll(this.tangibleAssets);
        allEntries.addAll(this.intangibleAssets);
        allEntries.addAll(this.capitalWIP);
        allEntries.addAll(this.intangibleUnderDevelopment);
        return allEntries;
    }
}
