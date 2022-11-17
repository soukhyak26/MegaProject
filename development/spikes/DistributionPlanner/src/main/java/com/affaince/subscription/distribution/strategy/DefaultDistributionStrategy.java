package com.affaince.subscription.distribution.strategy;

import com.affaince.subscription.distribution.db.DefaultShippingProfileRepositoryMock;
import com.affaince.subscription.distribution.db.DeliveryForecastView;
import com.affaince.subscription.distribution.db.DeliveryForecastViewRepository;
import com.affaince.subscription.distribution.db.DeliveryForecastViewRepositoryMock;
import com.affaince.subscription.distribution.profiles.DefaultShippingProfile;
import com.affaince.subscription.distribution.sampler.DeliveriesDistributionPortfolio;
import com.affaince.subscription.distribution.sampler.DeliveriesDistributor;
import com.affaince.subscription.distribution.sampler.Period;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

public class DefaultDistributionStrategy implements DistributionStrategy {

    private DeliveryForecastViewRepository deliveryForecastViewRepository;
    private final DeliveriesDistributor deliveriesDistributor;

    //@Autowired
    public DefaultDistributionStrategy(DeliveryForecastViewRepository deliveryForecastViewRepository, DeliveriesDistributor deliveriesDistributor) {
        this.deliveryForecastViewRepository = deliveryForecastViewRepository;
        this.deliveriesDistributor = new DeliveriesDistributor();
    }

    public DefaultDistributionStrategy() {
        this.deliveriesDistributor = new DeliveriesDistributor();
    }

    @Override
    public double calculateTotalDistributionExpensePerPeriod(LocalDate startDate, LocalDate endDate) {
        return 0;
    }

    @Override
    public Map<Period, DeliveriesDistributionPortfolio> distributeDistributionExpenseAcrossProducts(String merchantId) {
        List<DeliveryForecastView> deliveryForecasts = retrieveActiveDeliveryForecasts();
        DefaultShippingProfile shippingProfile = retrieveDefaultShippingProfile(merchantId);
        Map<Period, DeliveriesDistributionPortfolio> periodWiseDeliveriesDistributionProfilesMap = deliveriesDistributor.distributeDeliveriesOfAPeriodAcrossZones(deliveryForecasts, shippingProfile);
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
