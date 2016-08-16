package com.affaince.subscription.pricing.forecast.interpolate;

import com.affaince.subscription.common.type.ProductForecastStatus;
import com.affaince.subscription.pricing.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.pricing.query.view.ProductForecastView;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by mandar on 16-08-2016.
 */
public class ForecastInterpolatedSubscriptionCountFinder {

    @Autowired
    Interpolator interpolator;

    @Autowired
    private ProductForecastViewRepository productForecastViewRepository;

    public double findInterpolatedTotalSubscriptionCountOnADay(String productId, LocalDate currentDate) {
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        List<ProductForecastView> previousValues = productForecastViewRepository.
                findByProductVersionId_ProductIdAndProductForecastStatusOrderByProductVersionId_FromDateDesc
                        (productId, ProductForecastStatus.CORRECTED);
        ProductForecastView firstForecastView = previousValues.get(previousValues.size() - 1);
        LocalDate dateOfPlatformBeginning = firstForecastView.getProductVersionId().getFromDate();
        double[] x = new double[previousValues.size()];     //day on which interpolated vslue has been taken
        double[] y = new double[previousValues.size()];     //interpolated value of toal subscription
        int count = 0;
        for (ProductForecastView previousView : previousValues) {
            LocalDate endDate = previousView.getEndDate();
            int day = Days.daysBetween(dateOfPlatformBeginning, endDate).getDays(); //should we add/subtract 1 in the value?
            x[count] = day;
            y[count] = previousView.getTotalNumberOfExistingSubscriptions();
        }
        double[] interpolatedTotalSubscriptionsPerDay = interpolator.cubicSplineInterpolate(x, y);
        int currentDay = Days.daysBetween(dateOfPlatformBeginning, currentDate).getDays();
        return interpolatedTotalSubscriptionsPerDay[currentDay];

    }

}
