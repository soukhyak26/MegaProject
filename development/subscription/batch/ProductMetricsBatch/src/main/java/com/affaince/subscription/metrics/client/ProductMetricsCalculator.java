package com.affaince.subscription.metrics.client;

import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mandar on 2/25/2017.
 */
public class ProductMetricsCalculator {

    @Value("${subscription.metrics.findproducts.url}")
    private String findProductsUrl;

    @Value("${subscription.metrics.revenueprofit.url}")
    private String revenueProfitUrl;

    public void calculateProfitAndRevenue(String productId) {
        ClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        Map<String, String> uriParams = new HashMap<String, String>();
        uriParams.put("productId", productId);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(revenueProfitUrl);
        restTemplate.put(revenueProfitUrl, null, uriParams);
    }

}
