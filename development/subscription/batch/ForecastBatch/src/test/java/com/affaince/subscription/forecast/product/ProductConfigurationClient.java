package com.affaince.subscription.forecast.product;

import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by mandar on 19-11-2016.
 */
public class ProductConfigurationClient {
    @Value("${subscription.forecast.configureproducts.url}")
    private String configureProductsUrl;

    public void configureProduct(Map productAttributesMap){
        ClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());

        RestTemplate restTemplate = new RestTemplate(requestFactory);
/*
        Map<String, String> params = new HashMap<String, String>();
        params.put("productid", productId);
*/
        System.out.println("$$$$$$$$$$$$$$configureProductsUrl: " + configureProductsUrl);
        restTemplate.put(configureProductsUrl, null, productAttributesMap);

    }

}
