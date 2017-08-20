package com.affiance.prediction.app;

import com.affiance.prediction.algos.ARIMABasedDemandForecaster;
import com.affiance.prediction.deserializer.DataFrameVODeserializer;
import com.affiance.prediction.vo.DataFrameVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.Transformer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 8/15/2017.
 */
@EnableBinding(Processor.class)
@SpringBootApplication
@ComponentScan ("com.affiance")
public class Application {
    @Autowired
    ARIMABasedDemandForecaster arimaBasedDemandForecaster;

    @Transformer(inputChannel = Processor.INPUT,
            outputChannel = Processor.OUTPUT)
    public Object transform(String historicalRecords,Map<String,Object> headers) throws IOException {
        System.out.println("@@@@IN processor");
        Object id=headers.get("entity-id");
        ObjectMapper mapper=new ObjectMapper();
        List<DataFrameVO> dataFrameVOs=mapper.readValue(historicalRecords,List.class);
        List<DataFrameVO> forecastedRecords=arimaBasedDemandForecaster.forecast(id.toString(),dataFrameVOs);
        String forecastedDataFrameVOString = mapper.writeValueAsString(forecastedRecords);
        return forecastedDataFrameVOString;
    }
    public static void main(String[] args) {
        SpringApplication.run(
                Application.class, args);
    }
}