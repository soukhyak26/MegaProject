package com.affaince.subscription.business.distribution.strategy;

import com.affaince.subscription.business.distribution.db.DefaultShippingProfileRepositoryMock;
import com.affaince.subscription.business.distribution.db.DeliveryForecastView;
import com.affaince.subscription.business.distribution.db.DeliveryForecastViewRepository;
import com.affaince.subscription.business.distribution.db.DeliveryForecastViewRepositoryMock;
import com.affaince.subscription.business.distribution.profiles.DefaultShippingProfile;
import com.affaince.subscription.business.distribution.sampler.DeliveriesDistributionProfile;
import com.affaince.subscription.business.distribution.sampler.DeliveriesDistributionSampler;
import com.affaince.subscription.business.distribution.sampler.Period;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

public class DefaultDistributionStrategy implements DistributionStrategy {

    private DeliveryForecastViewRepository deliveryForecastViewRepository;
    private final DeliveriesDistributionSampler deliveriesDistributionSampler;

    //@Autowired
    public DefaultDistributionStrategy(DeliveryForecastViewRepository deliveryForecastViewRepository, DeliveriesDistributionSampler deliveriesDistributionSampler) {
        this.deliveryForecastViewRepository = deliveryForecastViewRepository;
        this.deliveriesDistributionSampler = new DeliveriesDistributionSampler();
    }

    public DefaultDistributionStrategy() {
        this.deliveriesDistributionSampler = new DeliveriesDistributionSampler();
    }

    @Override
    public double calculateTotalDistributionExpensePerPeriod(LocalDate startDate, LocalDate endDate) {
        return 0;
    }

    @Override
    public Map<Period, DeliveriesDistributionProfile> distributeDistributionExpenseAcrossProducts(String merchantId) {
        List<DeliveryForecastView> deliveryForecasts = retrieveActiveDeliveryForecasts();
        DefaultShippingProfile shippingProfile = retrieveDefaultShippingProfile(merchantId);
        Map<Period, DeliveriesDistributionProfile> periodWiseDeliveriesDistributionProfilesMap = deliveriesDistributionSampler.distributeDeliveriesOfAPeriodAcrossZones(deliveryForecasts, shippingProfile);
        for (Period period : periodWiseDeliveriesDistributionProfilesMap.keySet()) {
            periodWiseDeliveriesDistributionProfilesMap.get(period).computeTotalDistributionExpense(shippingProfile);
        }
        return periodWiseDeliveriesDistributionProfilesMap;
    }


    private List<DeliveryForecastView> retrieveActiveDeliveryForecasts() {
        //return deliveryForecastViewRepository.findByForecastContentStatusOrderByDeliveryForecastVersionId_DeliveryDateAsc(ForecastContentStatus.ACTIVE);
        return DeliveryForecastViewRepositoryMock.getDeliveryForecastViews();
    }

    private DefaultShippingProfile retrieveDefaultShippingProfile(String merchantId) {
        return DefaultShippingProfileRepositoryMock.getMerchantWiseShippingProfiles().get(0);
    }

}
