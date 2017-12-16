package com.affaince.subscription.common.transport;

import com.affaince.subscription.common.vo.*;
import com.affaince.subscription.date.SysDate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.apache.http.impl.client.HttpClients;
import org.joda.time.Days;
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

    public abstract List<DataFrameVO> prepare(Object id, Map<String, Object> metadata) throws JsonProcessingException;

    public abstract void marshallSendAndReceive(Object id, Map<String, Object> metadata);

    public final void marshallSendAndReceive(Object id, Map<String, Object> metadata, String url) {
        try {
            final List<DataFrameVO> dataFrames = prepare(id, metadata);
            //TODO: HERE we need to ensure that forecast shall be provided minimum until end of the current year in case actuals data is less to predict forecast
            //TODO: typically we are having the rule of getting forecast values half of the actuals data size. Now we need to see that this half sized forecasts goes upto end of year
            //TODO: else add some forecasted values in the actuals data set and recalculate forecast so that it reaches end of the year
            // TODO: This will be required only until initial few months when actuals data is less.
            final LocalDate lastActualsHistoricalRecordEndDate = dataFrames.stream().map(u -> u.getEndDate()).max(LocalDate::compareTo).get();
            final LocalDate endOfYearDate = new LocalDate(lastActualsHistoricalRecordEndDate.getYear(), 12, 31);
            int minimumForecastSize = 0;
            //size of forecast records should be minimum until end of year in case last actual record is in first half of that year
            if (12 - lastActualsHistoricalRecordEndDate.getMonthOfYear() < 6) {
                minimumForecastSize = Days.daysBetween(lastActualsHistoricalRecordEndDate, endOfYearDate).getDays();
            } else {
                minimumForecastSize = 12;
            }
            Iterator<String> metadataKeys = metadata.keySet().iterator();
            EntityType entityType = null;
            EntityMetricType entityMetricType = null;
            while (metadataKeys.hasNext()) {
                String key = metadataKeys.next();
                switch (key) {
                    case "ENTITY_TYPE":
                        entityType = (EntityType) metadata.get(key);
                        break;
                    case "ENTITY_METRIC_TYPE":
                        entityMetricType = (EntityMetricType) metadata.get(key);
                        break;
                }
            }
            metadata.put("MIN_FORECAST_SIZE", minimumForecastSize);
            EntityMetadata entityMetadata = new EntityMetadata(metadata);
            EntityHistoryPacket entityHistoryPacket = new EntityHistoryPacket(id, entityType, dataFrames, SysDate.now(), entityMetadata);
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JodaModule());
            String requestString = mapper.writeValueAsString(entityHistoryPacket);
            System.out.println("@@@@requestString: " + requestString);

            // final String requestString= convertToJsonString(dataFrames);
            sendForForecast(url, requestString);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error in converting forecast payload to Json", e.getStackTrace());
        }
    }

    private void sendForForecast(String url, String requestString) {
        ClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        System.out.println("$$$$$$$$$$$$$$forecastUrl: " + url);
        HttpEntity<String> request = new HttpEntity<>(requestString);
        restTemplate.exchange(url, HttpMethod.POST, request, String.class);
    }

}
