package com.affiance.prediction.transform;

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
