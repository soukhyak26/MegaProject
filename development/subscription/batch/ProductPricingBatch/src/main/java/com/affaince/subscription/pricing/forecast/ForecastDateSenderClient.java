package com.affaince.subscription.pricing.forecast;

import com.affaince.subscription.common.type.ProductDemandTrend;
import org.apache.http.impl.client.HttpClients;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mandar on 24-11-2016.
 */
public class ForecastDateSenderClient {
    @Value("${subscription.pricing.setnextforecastdate.url}")
    private String setNextForecastDateUrl;

    public void calculatePrice(String productId, LocalDateTime nextForecastDate) {
        ClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());

        RestTemplate restTemplate = new RestTemplate(requestFactory);
      //  ArrayList<String> result = restTemplate.getForObject(setNextForecastDateUrl, ArrayList.class);
        Map<String, Object> uriParams = new HashMap<>();
        uriParams.put("productid", productId);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(setNextForecastDateUrl);

        Map<String,Object> requestParams= new HashMap<>();
        requestParams.put("nextForecastDate",nextForecastDate);

        System.out.println("$$$$$$$$$$$$$$setNextForecastDateUrl: " + setNextForecastDateUrl);
        //restTemplate.put(builder.buildAndExpand(uriParams).toUri().toString(), null, productAttributesMap);
        restTemplate.put(builder.buildAndExpand(uriParams).toUri().toString(), requestParams, uriParams);
    }

}
