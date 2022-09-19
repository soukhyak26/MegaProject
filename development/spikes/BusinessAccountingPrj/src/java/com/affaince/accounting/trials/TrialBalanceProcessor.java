package com.affaince.accounting.trials;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public interface TrialBalanceProcessor {
    TrialBalance processTrialBalance(String merchantId, LocalDateTime startDate, LocalDateTime closureDate);
}
