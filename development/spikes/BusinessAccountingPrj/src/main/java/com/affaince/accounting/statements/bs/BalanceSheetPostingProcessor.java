package com.affaince.accounting.statements.bs;

import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;


public interface BalanceSheetPostingProcessor {
    BalanceSheet postToBalanceSheet(String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod);
}
