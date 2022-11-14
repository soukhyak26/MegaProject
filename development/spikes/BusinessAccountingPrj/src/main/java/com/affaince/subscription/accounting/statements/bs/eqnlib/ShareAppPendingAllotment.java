package com.affaince.subscription.accounting.statements.bs.eqnlib;

import com.affaince.subscription.accounting.statements.BalanceSheetEntity;

import java.util.ArrayList;
import java.util.List;

public class ShareAppPendingAllotment {
    private BalanceSheetEntity value;

    public ShareAppPendingAllotment(){
    }
    public void addToValue(BalanceSheetEntity balanceSheetEntity){
        this.value = balanceSheetEntity;
    }

    public BalanceSheetEntity getValue() {
        return value;
    }
    public List<BalanceSheetEntity> fetchAllEntries(){
        List<BalanceSheetEntity> allEntries = new ArrayList<>();
        allEntries.add(this.value);
        return allEntries;
    }

    @Override
    public String toString() {
        return "ShareAppPendingAllotment{" +
                "value=" + value +
                '}';
    }
}
