package com.affiance.prediction.app;

import com.affiance.prediction.algos.ARIMABasedDemandForecaster;
import com.affiance.prediction.vo.DataFrameVO;
import com.affiance.prediction.vo.EntityHistoryPacket;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.annotation.Transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 8/15/2017.
 */
@EnableBinding(Processor.class)
@SpringBootApplication
@ComponentScan ("com.affiance")
@PropertySource({"classpath:Application.properties"})
@EnableAutoConfiguration
public class Application {
    @Autowired
    ARIMABasedDemandForecaster arimaBasedDemandForecaster;

    @Transformer(inputChannel = Processor.INPUT,
            outputChannel = Processor.OUTPUT)
    public String transform(String historicalRecords,Map<String,Object> headers) throws IOException {
        System.out.println("@@@@IN processor");
        ObjectMapper mapper=new ObjectMapper();
        mapper.registerModule(new JodaModule());
        EntityHistoryPacket entityHistoryPacket=mapper.readValue(historicalRecords,new TypeReference<EntityHistoryPacket>(){});
        Object id=entityHistoryPacket.getEntityId();
        System.out.println("@@@EntityID:" + id.toString());
        List<DataFrameVO> dataFrameVOs=entityHistoryPacket.getDataFrameVOs();
        System.out.println("@@@dataframes:" + dataFrameVOs);
        int forecastRecordSize= (Integer)entityHistoryPacket.getEntityMetadata().getNamedEntries().get("MIN_FORECAST_SIZE");
        List<DataFrameVO> forecastedRecords=forecast(id.toString(),dataFrameVOs,forecastRecordSize);
        entityHistoryPacket.setDataFrameVOs(forecastedRecords);
        return mapper.writeValueAsString(entityHistoryPacket);
    }

    private List<DataFrameVO> forecast(String id,List<DataFrameVO> historicalActualRecords,int forecastRecordSize){
        List<DataFrameVO> forecastedRecords=arimaBasedDemandForecaster.forecast(id,historicalActualRecords);
        //take a temporary collection just to get newly forecasted records and measure its size,if more or less than minimum forecastSize
        List<DataFrameVO> tempForecastRecords=new ArrayList<>(forecastedRecords);
        tempForecastRecords.removeAll(historicalActualRecords);
        if(tempForecastRecords.size()< forecastRecordSize){
            historicalActualRecords.addAll(tempForecastRecords);
            forecastedRecords=forecast(id,historicalActualRecords,forecastRecordSize);
        }
        return forecastedRecords;
    }
    public static void main(String[] args) {
        SpringApplication.run(
                Application.class, args);
    }
}