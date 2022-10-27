package com.affaince.distribution.strategy;

import org.joda.time.LocalDate;

public interface DistributionStrategy {
    void distributeDistributionExpenseAcrossProducts(String merchantId);

    double calculateTotalDistributionExpensePerPeriod(LocalDate startDate, LocalDate endDate);

}
