package com.affaince.subscription.pricing.forecast;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
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


    public void initiateForecast(String productId) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("productid", productId);
        restTemplate.put(forecastUrl, params);
    }

    public void initiatePseudoActual(String productId) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("productid", productId);
        restTemplate.put(pseudoActualUrl, params);
    }
}
