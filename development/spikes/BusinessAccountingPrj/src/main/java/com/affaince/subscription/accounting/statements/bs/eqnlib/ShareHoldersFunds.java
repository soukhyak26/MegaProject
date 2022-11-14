package com.affaince.subscription.accounting.statements.bs.eqnlib;

import com.affaince.subscription.accounting.statements.BalanceSheetEntity;

import java.util.ArrayList;
import java.util.List;

public class ShareHoldersFunds {
    private List<BalanceSheetEntity> shareCapital;
    private List<BalanceSheetEntity> reservesAndSurplus;
    private List<BalanceSheetEntity> moneyReceivedAgainstShareWarrants;

    public ShareHoldersFunds(){
        this.shareCapital = new ArrayList<>();
        this.reservesAndSurplus = new ArrayList<>();
        this.moneyReceivedAgainstShareWarrants = new ArrayList<>();
    }
    public void addToShareCapital(BalanceSheetEntity balanceSheetEntity){
        this.shareCapital.add(balanceSheetEntity);
    }
    public void addToReservesAndSurplus(BalanceSheetEntity balanceSheetEntity){
        this. reservesAndSurplus.add(balanceSheetEntity);
    }
    public void addToMoneyReceivedAgainstShareWarrants(BalanceSheetEntity balanceSheetEntity){
        this.moneyReceivedAgainstShareWarrants.add(balanceSheetEntity);
    }

    public List<BalanceSheetEntity> fetchAllEntries(){
        List<BalanceSheetEntity> allEntries = new ArrayList<>();
        allEntries.addAll(this.shareCapital);
        allEntries.addAll(this.reservesAndSurplus);
        allEntries.addAll(this.moneyReceivedAgainstShareWarrants);
        return allEntries;
    }

    @Override
    public String toString() {
        return "ShareHoldersFunds{" +
                "shareCapital=" + shareCapital +
                ", reservesAndSurplus=" + reservesAndSurplus +
                ", moneyReceivedAgainstShareWarrants=" + moneyReceivedAgainstShareWarrants +
                '}';
    }
}
