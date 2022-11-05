package com.affaince.accounting.statements.bs;

import org.joda.time.LocalDate;

public interface BalanceSheetPostingProcessor {
    BalanceSheet postToBalanceSheet(String merchantId, LocalDate startDateOfPeriod, LocalDate closureDateOfPeriod);
}
