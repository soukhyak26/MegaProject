package com.affaince.subscription.forecast.product;

import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mandar on 19-11-2016.
 */
@Component
public class ProductRegistrationClient {
    @Value("${subscription.forecast.registerproducts.url}")
    private String registerProductsUrl;

    public void registerProduct(Map productAttributesMap){
        ClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());

        RestTemplate restTemplate = new RestTemplate(requestFactory);
/*
        Map<String, String> params = new HashMap<String, String>();
        params.put("productid", productId);
*/
        System.out.println("$$$$$$$$$$$$$$registerProductsUrl: " + registerProductsUrl);
        restTemplate.put(registerProductsUrl, null, productAttributesMap);

    }
}
