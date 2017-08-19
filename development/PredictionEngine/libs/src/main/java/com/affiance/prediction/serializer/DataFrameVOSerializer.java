package com.affiance.prediction.serializer;

import com.affiance.prediction.vo.DataFrameVO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

/**
 * Created by mandar on 8/14/2017.
 */
public class DataFrameVOSerializer extends JsonSerializer<DataFrameVO> {
    private static DateTimeFormatter formatter =
            DateTimeFormat.forPattern("dd-MM-yyyy");
    @Override
    public void serialize(DataFrameVO dataFrameVO, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        generator.writeStartObject();
        generator.writeFieldName("date");
        generator.writeString (formatter.print(dataFrameVO.getDate()));
        generator.writeFieldName("token");
        generator.writeString(dataFrameVO.getToken());
        generator.writeFieldName("value");
        generator.writeNumber(dataFrameVO.getValue());
        generator.writeEndObject();
    }

}
