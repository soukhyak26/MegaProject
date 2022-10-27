package com.affaince.distribution.strategy;

import com.affaince.distribution.sampler.DeliveriesDistributionProfile;
import com.affaince.distribution.sampler.Period;
import org.joda.time.LocalDate;

import java.util.Map;

public interface DistributionStrategy {
    Map<Period, DeliveriesDistributionProfile> distributeDistributionExpenseAcrossProducts(String merchantId);

    double calculateTotalDistributionExpensePerPeriod(LocalDate startDate, LocalDate endDate);

}
