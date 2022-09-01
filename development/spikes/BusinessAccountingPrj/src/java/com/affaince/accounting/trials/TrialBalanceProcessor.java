package com.affaince.accounting.trials;

import org.joda.time.LocalDate;

public interface TrialBalanceProcessor {
    TrialBalance processTrialBalance(String merchantId,LocalDate trialBalanceDate);
}
