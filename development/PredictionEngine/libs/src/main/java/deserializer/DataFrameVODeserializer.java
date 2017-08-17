package deserializer;

import com.affiance.prediction.vo.DataFrameVO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

/**
 * Created by mandar on 8/14/2017.
 */
public class DataFrameVODeserializer extends JsonDeserializer<DataFrameVO> {
    private static DateTimeFormatter formatter =
            DateTimeFormat.forPattern("dd-MM-yyyy");

    @Override
    public DataFrameVO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        LocalDate date = null;
        String token = null;
        double value = -1;
        if (currentToken == JsonToken.VALUE_STRING) {
            if (currentToken.name().equals("date")) {
                String dateTimeAsString = jsonParser.getText().trim();
                date = LocalDate.parse(dateTimeAsString, formatter);
            } else if (currentToken.name().equals("token")) {
                token = jsonParser.getText().trim();
            }
        } else if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            value = jsonParser.getDoubleValue();
        }
        return new DataFrameVO(date, token, value);
    }
}
