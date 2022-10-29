package com.affaince.subscription.business.distribution.categories;

import com.affaince.subscription.business.distribution.db.DeliveryForecastView;
import com.affaince.subscription.business.distribution.sampler.Period;
import com.affaince.subscription.business.distribution.sampler.WeightIndicator;

import java.util.List;
import java.util.Map;

public class PeriodWiseAndWeightWiseDeliveriesDistribution {
    private final Period period;
    private final Map<WeightIndicator, List<DeliveryForecastView>> periodWiseWeightWiseDeliveriesMap;

    public PeriodWiseAndWeightWiseDeliveriesDistribution(Period period, Map<WeightIndicator, List<DeliveryForecastView>> periodWiseWeightWiseDeliveriesMap) {
        this.period = period;
        this.periodWiseWeightWiseDeliveriesMap = periodWiseWeightWiseDeliveriesMap;
    }

    public Period getPeriod() {
        return period;
    }

    public Map<WeightIndicator, List<DeliveryForecastView>> getPeriodWiseWeightWiseDeliveriesMap() {
        return periodWiseWeightWiseDeliveriesMap;
    }
}
