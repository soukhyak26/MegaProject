package com.affaince.subscription.pricing.determine;

import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.common.type.WeightedProductDemandTrend;
import org.apache.avro.test.specialtypes.builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mandar on 05-07-2016.
 */
@Component
public class PricingClient {
    @Value("${subscription.pricing.findproducts.url}")
    private String findProductsUrl;
    @Value("${subscription.pricing.url}")
    private String pricingUrl;

    public void calculatePrice(WeightedProductDemandTrend trend) {
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<String> result = restTemplate.getForObject(findProductsUrl, ArrayList.class);
        Map<String, String> params = new HashMap<String, String>();
        params.put("productid", trend.getProductId());
        //params.put("productdemandtrend", "" + trend.getProductDemandTrend());
        //params.put("weight", "" + trend.getWeight());
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(pricingUrl);
        restTemplate.put(builder.buildAndExpand(params).toUri().toString(), params);
    }
}
