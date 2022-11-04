package com.affaince.accounting.statements.bs.eqnlib;

import com.affaince.accounting.statements.bs.BalanceSheetEntity;

public class ShareAppPendingAllotment {
    private BalanceSheetEntity value;

    public ShareAppPendingAllotment(){
        this.value = new BalanceSheetEntity();
    }
    public void addToValue(double value,String narration){
        this.value.addToValue(value);
        this.value.addToNarration(narration);
    }

    public BalanceSheetEntity getValue() {
        return value;
    }
}
