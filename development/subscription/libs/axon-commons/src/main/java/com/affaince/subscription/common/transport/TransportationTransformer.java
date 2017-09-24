package com.affaince.subscription.common.transport;

import com.affaince.subscription.common.vo.*;
import com.affaince.subscription.date.SysDate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.apache.http.impl.client.HttpClients;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 8/31/2017.
 */
public abstract class TransportationTransformer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransportationTransformer.class);
    private String convertToJsonString(List<DataFrameVO> objectsToTransform) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(objectsToTransform);
    }

    private List<DataFrameVO> convertJsonToObjectsCollection(String jsonString) throws IOException {
        return (List<DataFrameVO>) new ObjectMapper().readValue(jsonString, DataFrameVO.class);
    }

    public abstract List<DataFrameVO> prepare(Object id,Map<String,Object> metadata) throws JsonProcessingException;

    public abstract void marshallSendAndReceive(Object id,Map<String,Object> metadata);

    public final List<DataFrameVO> marshallSendAndReceive(Object id, Map<String,Object> metadata, String url) {
        try {
            final List<DataFrameVO> dataFrames = prepare(id,metadata);
            Iterator<String> metadataKeys=metadata.keySet().iterator();
            EntityType entityType=null;
            EntityMetricType entityMetricType=null;
            while(metadataKeys.hasNext()){
                String key=metadataKeys.next();
                switch (key){
                    case "ENTITY_TYPE" :
                        entityType=(EntityType)metadata.get(key);
                        break;
                    case "ENTITY_METRIC_TYPE" :
                        entityMetricType=(EntityMetricType)metadata.get(key);
                        break;
                }
            }
            EntityMetadata entityMetadata= new EntityMetadata(metadata);
            EntityHistoryPacket entityHistoryPacket= new EntityHistoryPacket(id, entityType,dataFrames, SysDate.now(),entityMetadata);
            ObjectMapper mapper= new ObjectMapper();
            mapper.registerModule(new JodaModule());
            String requestString = mapper.writeValueAsString(entityHistoryPacket);
            System.out.println("@@@@requestString: " + requestString);

            // final String requestString= convertToJsonString(dataFrames);
            return sendForForecast(url,dataFrames);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error in converting forecast payload to Json", e.getStackTrace());
        }
        return null;
    }


    private List<DataFrameVO> sendForForecast(String url,List<DataFrameVO> dataFrames) {
        ClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        System.out.println("$$$$$$$$$$$$$$forecastUrl: " + url);
        HttpEntity<List<DataFrameVO>> request=new HttpEntity<>(dataFrames);
        ResponseEntity<List<DataFrameVO>> response=restTemplate.exchange(url, HttpMethod.POST,request,new ParameterizedTypeReference<List<DataFrameVO>>(){});
        return response.getBody();

        //restTemplate.put(url, null, params);

    }

}
