package com.affaince.distribution.strategy;

import com.affaince.distribution.db.DeliveryForecastView;
import com.affaince.distribution.db.DeliveryForecastViewRepository;
import com.affaince.distribution.profiles.DefaultShippingProfile;
import com.affaince.distribution.sampler.DeliveriesDistributionProfile;
import com.affaince.distribution.sampler.DeliveriesDistributionSampler;
import com.affaince.distribution.sampler.Period;
import com.affaince.subscription.common.type.ForecastContentStatus;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class DefaultDistributionStrategy implements DistributionStrategy {

    private final DeliveryForecastViewRepository deliveryForecastViewRepository;
    private final DeliveriesDistributionSampler deliveriesDistributionSampler;

    @Autowired
    public DefaultDistributionStrategy(DeliveryForecastViewRepository deliveryForecastViewRepository, DeliveriesDistributionSampler deliveriesDistributionSampler) {
        this.deliveryForecastViewRepository = deliveryForecastViewRepository;
        this.deliveriesDistributionSampler = deliveriesDistributionSampler;
    }

    @Override
    public double calculateTotalDistributionExpensePerPeriod(LocalDate startDate, LocalDate endDate) {
        return 0;
    }

    @Override
    public void distributeDistributionExpenseAcrossProducts(String merchantId) {
        List<DeliveryForecastView> deliveryForecasts = retrieveActiveDeliveryForecasts();
        DefaultShippingProfile shippingProfile = retrieveDefaultShippingProfile(merchantId);
        Map<Period, DeliveriesDistributionProfile> periodWiseDeliveriesDistributionProfilesMap = deliveriesDistributionSampler.distributeDeliveriesOfAPeriod(deliveryForecasts, shippingProfile);
        for (Period period : periodWiseDeliveriesDistributionProfilesMap.keySet()) {
            periodWiseDeliveriesDistributionProfilesMap.get(period).computeTotalDistributionExpense(shippingProfile);
        }
    }


    private List<DeliveryForecastView> retrieveActiveDeliveryForecasts() {
        return deliveryForecastViewRepository.findByForecastContentStatusOrderByDeliveryForecastVersionId_DeliveryDateAsc(ForecastContentStatus.ACTIVE);
    }

    private DefaultShippingProfile retrieveDefaultShippingProfile(String merchantId) {
        return null;
    }

}
