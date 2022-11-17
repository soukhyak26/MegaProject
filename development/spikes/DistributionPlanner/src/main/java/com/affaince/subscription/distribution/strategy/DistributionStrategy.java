package com.affaince.subscription.distribution.strategy;

import com.affaince.subscription.distribution.sampler.DeliveriesDistributionPortfolio;
import com.affaince.subscription.distribution.sampler.Period;
import org.joda.time.LocalDate;

import java.util.Map;

public interface DistributionStrategy {
    Map<Period, DeliveriesDistributionPortfolio> distributeDistributionExpenseAcrossProducts(String merchantId);

    double calculateTotalDistributionExpensePerPeriod(LocalDate startDate, LocalDate endDate);

}
