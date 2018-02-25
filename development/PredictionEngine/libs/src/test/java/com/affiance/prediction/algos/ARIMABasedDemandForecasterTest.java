package com.affiance.prediction.algos;

import com.affaince.subscription.common.vo.*;
import com.affiance.prediction.config.DateTimeModule;
import com.affiance.prediction.config.Forecast;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.apache.http.impl.client.HttpClients;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by mandar on 19-06-2016.
 */
//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Forecast.class})
public class ARIMABasedDemandForecasterTest {
    private String forecastUrl="http://localhost:9000";

    @Test
    public void testPrecisePrediction() throws IOException {
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/demands2.tsv"))));
        long[][] readings = fileReader.lines().map(l -> l.trim().split("\t")).map(sa -> Stream.of(sa).mapToLong(Long::parseLong).toArray()).toArray(long[][]::new);
        LocalDate fromDate=new LocalDate(2016, 5, 1);
        List<DataFrameVO> actualsVOs= new ArrayList<>();
        for (int i = 0; i < readings.length; i++) {
            DataFrameVO actualsVO = new DataFrameVO(fromDate,"pro1", readings[i][0], AggregationType.INCREMENTAL);
            System.out.println("total subscription:" + readings[i][0]);
            actualsVOs.add(actualsVO);
            fromDate=fromDate.plusDays(1);
        }
        Map<String,Object> metadata =new HashMap<>();
        metadata.put("MIN_FORECAST_SIZE",Math.round(actualsVOs.size()/2));
        EntityMetadata entityMetadata= new EntityMetadata(metadata);
        EntityHistoryPacket entityHistoryPacket= new EntityHistoryPacket(1, EntityType.PRODUCT,actualsVOs, LocalDate.now(),entityMetadata);
        ObjectMapper mapper= new ObjectMapper();
        mapper.registerModule(new JodaModule());
       // mapper.registerModule(new DateTimeModule());
        String requestString = mapper.writeValueAsString(entityHistoryPacket);
        System.out.println("@@@@requestString: " + requestString);
        initiateForecast(requestString);
    }
    private void initiateForecast(String requestString) {
        ClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        Map<String, Object> params = new HashMap<>();
        params.put("request", requestString);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request=new HttpEntity<String>(requestString,headers);
        System.out.println("$$$$$$$$$$$$$$forecastUrl: " + forecastUrl);
        restTemplate.postForLocation(forecastUrl,request);
    }

    @After
    public void shutdown(){
    }

}
