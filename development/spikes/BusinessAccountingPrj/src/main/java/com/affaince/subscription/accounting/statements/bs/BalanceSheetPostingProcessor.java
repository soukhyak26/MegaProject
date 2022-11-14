package com.affaince.subscription.accounting.statements.bs;

import org.joda.time.LocalDateTime;


public interface BalanceSheetPostingProcessor {
    BalanceSheet postToBalanceSheet(String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod);
}
