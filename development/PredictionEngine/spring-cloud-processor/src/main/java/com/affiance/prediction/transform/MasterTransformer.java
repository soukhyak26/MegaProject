package com.affiance.prediction.transform;

import com.affiance.prediction.algos.ARIMABasedDemandForecaster;
import com.affiance.prediction.vo.DataFrameVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.affiance.prediction.deserializer.DataFrameVODeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 8/17/2017.
 */
public class MasterTransformer {
/*
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


*/
}
