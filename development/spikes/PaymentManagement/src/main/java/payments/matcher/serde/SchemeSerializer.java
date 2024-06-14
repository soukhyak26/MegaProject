package payments.matcher.serde;

import com.affaince.payments.scheme.Scheme;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SchemeSerializer {

    public String serialize(Scheme scheme){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enableDefaultTyping();
            return objectMapper.writeValueAsString(scheme);
        }catch(JsonProcessingException ex){
            ex.printStackTrace();
        }
        return null;
    }

}
