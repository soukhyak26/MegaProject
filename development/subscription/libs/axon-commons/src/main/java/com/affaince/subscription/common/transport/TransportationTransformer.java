package com.affaince.subscription.common.transport;

import com.affaince.subscription.common.vo.DataFrameVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.client.HttpClients;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 8/31/2017.
 */
public abstract class TransportationTransformer {

    private String convertToJsonString(List<DataFrameVO> objectsToTransform) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(objectsToTransform);
    }

    private List<DataFrameVO> convertJsonToObjectsCollection(String jsonString) throws IOException {
        return (List<DataFrameVO>) new ObjectMapper().readValue(jsonString, DataFrameVO.class);
    }

    public abstract List<DataFrameVO> prepare(Object id) throws JsonProcessingException;

    public abstract void marshallSendAndReceive(Object id);

    public final List<DataFrameVO> marshallSendAndReceive(Object id,String url) {
        try {
            final List<DataFrameVO> dataFrames = prepare(id);
           // final String requestString= convertToJsonString(dataFrames);
            return sendForForecast(url,dataFrames);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    private List<DataFrameVO> sendForForecast(String url,List<DataFrameVO> dataFrames) {
        ClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        RestTemplate restTemplate = new RestTemplate(requestFactory);
/*
        Map<String, Object> params = new HashMap<>();
        params.put("requestString", requestString);
*/
        System.out.println("$$$$$$$$$$$$$$forecastUrl: " + url);
        HttpEntity<List<DataFrameVO>> request=new HttpEntity<>(dataFrames);
        ResponseEntity<List<DataFrameVO>> response=restTemplate.exchange(url, HttpMethod.POST,request,new ParameterizedTypeReference<List<DataFrameVO>>(){});
        return response.getBody();

        //restTemplate.put(url, null, params);

    }

}
