package com.affaince.subscription.pricing.determine;

import com.affaince.subscription.common.type.ProductDemandTrend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mandar on 05-07-2016.
 */
public class PricingClient {
    @Value("${subscription.forecast.findproducts.url}")
    private static String findProductsUrl;
    @Value("${subscription.pricing.url}")
    private static String pricingUrl;

    public static void calculatePrice(String productId, ProductDemandTrend trend) {
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<String> result = restTemplate.getForObject(findProductsUrl, ArrayList.class);
        Map<String, String> params = new HashMap<String, String>();
        params.put("productid", productId);
        params.put("productdemandtrend", "" + trend.getTrendCode());
        restTemplate.put(pricingUrl, params);
    }
}
