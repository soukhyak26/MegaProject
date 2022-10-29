package com.affaince.subscription.business.distribution.strategy;

import com.affaince.subscription.business.distribution.sampler.DeliveriesDistributionProfile;
import com.affaince.subscription.business.distribution.sampler.Period;
import org.joda.time.LocalDate;

import java.util.Map;

public interface DistributionStrategy {
    Map<Period, DeliveriesDistributionProfile> distributeDistributionExpenseAcrossProducts(String merchantId);

    double calculateTotalDistributionExpensePerPeriod(LocalDate startDate, LocalDate endDate);

}
