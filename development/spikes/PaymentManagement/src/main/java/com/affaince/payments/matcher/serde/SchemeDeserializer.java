package com.affaince.payments.matcher.serde;

import com.affaince.payments.scheme.Scheme;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SchemeDeserializer {

    public Scheme deserialize(String schemeString){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enableDefaultTyping();
            return  mapper.readValue(schemeString, Scheme.class);
        }catch (JsonParseException ex){
            ex.printStackTrace();
        }catch(JsonMappingException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
