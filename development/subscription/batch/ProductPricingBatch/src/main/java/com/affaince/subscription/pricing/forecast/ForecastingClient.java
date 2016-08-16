package com.affaince.subscription.pricing.forecast;

import com.affaince.subscription.common.type.ProductForecastStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.pricing.forecast.interpolate.Interpolator;
import com.affaince.subscription.pricing.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.pricing.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.pricing.query.repository.ProductPseudoActualsViewRepository;
import com.affaince.subscription.pricing.query.view.ProductForecastView;
import com.affaince.subscription.pricing.query.view.ProductPseudoActualsView;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 05-07-2016.
 */
public class ForecastingClient {
    @Value("${subscription.forecast.findproducts.url}")
    private static String findProductsUrl;
    @Value("${subscription.forecast.predictforecast.url}")
    private static String forecastUrl;
    @Value("${subscription.forecast.predictpseudoactual.url}")
    private static String pseudoActualUrl;

    @Autowired
    private ProductPseudoActualsViewRepository productPseudoActualsViewRepository;

    @Autowired
    private ProductForecastViewRepository productForecastViewRepository;

    @Autowired
    private ProductConfigurationViewRepository productConfigurationViewRepository;
    @Autowired
    Interpolator interpolator;

    public void initiateForecast() {
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<String> result = restTemplate.getForObject(findProductsUrl, ArrayList.class);
        for (String productId : result) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("productid", productId);
            restTemplate.put(forecastUrl, params);
        }
    }

    public void initiatePseudoActual() {
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<String> result = restTemplate.getForObject(pseudoActualUrl, ArrayList.class);
        for (String productId : result) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("productid", productId);
            restTemplate.put(forecastUrl, params);
        }
    }

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

    public boolean triggerProductPricing (String productId, LocalDate currentDate) {
        final ProductVersionId productVersionId = new ProductVersionId(productId, currentDate);
        final ProductPseudoActualsView productPseudoActualsView = productPseudoActualsViewRepository.findOne(productVersionId);
        final double interpolatedTotalSubscriptionsOnDay = findInterpolatedTotalSubscriptionCountOnADay(productId, currentDate);
        final short changeThresholdForPriceChange =
                productConfigurationViewRepository.findOne(productId).getChangeThresholdForPriceChange();
        double difference = productPseudoActualsView.getTotalNumberOfExistingSubscriptions() - interpolatedTotalSubscriptionsOnDay;
        if (difference >= (interpolatedTotalSubscriptionsOnDay*10)/100 ||
                difference <= (-1)*((interpolatedTotalSubscriptionsOnDay*10)/100)) {
            return true;
        }
        return false;
    }
}
