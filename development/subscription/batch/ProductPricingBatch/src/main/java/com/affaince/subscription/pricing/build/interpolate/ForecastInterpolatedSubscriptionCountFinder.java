package com.affaince.subscription.pricing.build.interpolate;

import com.affaince.subscription.common.type.ProductForecastStatus;
import com.affaince.subscription.pricing.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.pricing.query.view.ProductForecastView;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by mandar on 16-08-2016.
 */
public class ForecastInterpolatedSubscriptionCountFinder {

    @Autowired
    private Interpolator interpolator;

    @Autowired
    private ProductForecastViewRepository productForecastViewRepository;

    public double[] getDailyInterpolatedTotalSubscriptionCounts(String productId) {
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        List<ProductForecastView> registeredForecastValues = productForecastViewRepository.
                findByProductVersionId_ProductIdAndProductForecastStatusOrderByProductVersionId_FromDateDesc
                        (productId, ProductForecastStatus.ACTIVE);
        ProductForecastView firstForecastView = registeredForecastValues.get(registeredForecastValues.size() - 1);
        LocalDate dateOfPlatformBeginning = firstForecastView.getProductVersionId().getFromDate();
        double[] x = new double[registeredForecastValues.size()];     //day on which interpolated value has been taken
        double[] y = new double[registeredForecastValues.size()];     //interpolated value of total subscription
        int count = 0;
        for (ProductForecastView previousView : registeredForecastValues) {
            LocalDate endDate = previousView.getEndDate();
            int day = Days.daysBetween(dateOfPlatformBeginning, endDate).getDays(); //TODO- should we add/subtract 1 in the value?
            x[count] = day;
            y[count] = previousView.getTotalNumberOfExistingSubscriptions();
            count++;
        }
        double[] interpolatedTotalSubscriptionsPerDay = interpolator.cubicSplineInterpolate(x, y);
/*
        LocalDate currentDate = LocalDate.now();
        int currentDay = Days.daysBetween(dateOfPlatformBeginning, currentDate).getDays();
*/
        return interpolatedTotalSubscriptionsPerDay;

    }

    public double getDailyInterpolatedTotalSubscriptionCountAsOnDate(String productId, LocalDate date) {
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        List<ProductForecastView> registeredForecastValues = productForecastViewRepository.
                findByProductVersionId_ProductIdAndProductForecastStatusOrderByProductVersionId_FromDateDesc
                        (productId, ProductForecastStatus.ACTIVE);
        ProductForecastView firstForecastView = registeredForecastValues.get(registeredForecastValues.size() - 1);
        LocalDate dateOfPlatformBeginning = firstForecastView.getProductVersionId().getFromDate();
        double[] x = new double[registeredForecastValues.size()];     //day on which interpolated value has been taken
        double[] y = new double[registeredForecastValues.size()];     //interpolated value of total subscription
        int count = 0;
        for (ProductForecastView previousView : registeredForecastValues) {
            LocalDate endDate = previousView.getEndDate();
            int day = Days.daysBetween(dateOfPlatformBeginning, endDate).getDays(); //TODO- should we add/subtract 1 in the value?
            x[count] = day;
            y[count] = previousView.getTotalNumberOfExistingSubscriptions();
        }
        double[] interpolatedTotalSubscriptionsPerDay = interpolator.cubicSplineInterpolate(x, y);
        int expectedDay = Days.daysBetween(dateOfPlatformBeginning, date).getDays();
        return interpolatedTotalSubscriptionsPerDay[expectedDay];
    }

}
