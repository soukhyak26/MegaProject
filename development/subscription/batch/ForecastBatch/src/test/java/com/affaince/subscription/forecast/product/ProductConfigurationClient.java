package com.affaince.subscription.forecast.product;

import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mandar on 19-11-2016.
 */
@Component
public class ProductConfigurationClient {
    @Value("${subscription.forecast.configureproducts.url}")
    private String configureProductsUrl;

    public void configureProduct(String productId,Map productAttributesMap){
        ClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());

        RestTemplate restTemplate = new RestTemplate(requestFactory);
/*
        Map<String, String> params = new HashMap<String, String>();
        params.put("productid", productId);
*/
        Map<String, String> uriParams = new HashMap<String, String>();
        uriParams.put("productId", productId);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(configureProductsUrl);

        System.out.println("$$$$$$$$$$$$$$configureProductsUrl: " + configureProductsUrl);
        //restTemplate.put(builder.buildAndExpand(uriParams).toUri().toString(), null, productAttributesMap);
        restTemplate.put(builder.buildAndExpand(uriParams).toUri().toString(), productAttributesMap, uriParams);

    }

}
