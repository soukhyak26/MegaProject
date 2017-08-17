package com.affiance.prediction.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mandar on 8/14/2017.
 */
@Controller
@EnableBinding(Source.class)
public class HttpSource {
    @Autowired
    private Source source;

    @RequestMapping(value="/src/{entityId}", method= RequestMethod.POST, consumes={"application/json"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handleRequest(@RequestBody String body, @RequestHeader(value="entity-type") Object entityType,@RequestHeader(value="entity-id") Object entityId){

        sendMessage(body,entityType,entityId);
    }
    private void sendMessage(Object body,Object entityType,Object entityId){
        Map<String,Object> headers= new HashMap<>();
        headers.put("entity-type",entityType);
        headers.put("entity-id",entityId);
        source.output().send(MessageBuilder.createMessage(body,new MessageHeaders(headers)));
    }
}
