package com.affaince.accounting.statements.bs;

import com.affaince.accounting.statements.bs.assets.Assets;
import com.affaince.accounting.statements.bs.eqnlib.EquityAndLiabilities;
import org.joda.time.LocalDate;

public class BalanceSheet {
    private String merchantId;
    private LocalDate startDate;
    private LocalDate endDate;
    private EquityAndLiabilities pastCycleEquitiesAndLiabilities;
    private EquityAndLiabilities currentCycleEquitiesAndLiabilities;
    private Assets pastCycleAssets;
    private Assets currentCycleAssets;

    public BalanceSheet(String merchantId, LocalDate startDate, LocalDate endDate) {
        this.merchantId = merchantId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
