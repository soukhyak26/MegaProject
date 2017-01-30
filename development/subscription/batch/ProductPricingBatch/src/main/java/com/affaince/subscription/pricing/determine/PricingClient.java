package com.affaince.subscription.pricing.determine;

import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.common.type.WeightedProductDemandTrend;
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
    private String findProductsUrl;
    @Value("${subscription.pricing.url}")
    private String pricingUrl;

    public void calculatePrice(String productId, WeightedProductDemandTrend trend) {
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<String> result = restTemplate.getForObject(findProductsUrl, ArrayList.class);
        Map<String, String> params = new HashMap<String, String>();
        params.put("productid", productId);
        params.put("productdemandtrend", "" + trend.getProductDemandTrend());
        params.put("weight", "" + trend.getWeight());
        restTemplate.put(pricingUrl, params);
    }
}
