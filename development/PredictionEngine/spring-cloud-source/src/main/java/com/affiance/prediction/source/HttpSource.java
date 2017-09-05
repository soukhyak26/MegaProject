package com.affiance.prediction.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mandar on 8/14/2017.
 */
@RestController
@RequestMapping(value = "/forecast")
@EnableBinding(Source.class)
public class HttpSource {
    @Autowired
    private Source source;

    @RequestMapping(value="/src", method= RequestMethod.POST, consumes={"application/json"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handleRequest(@RequestBody String body, @RequestHeader(value="entity-type") Object entityType,@RequestHeader(value="entity-id") Object entityId){

        sendMessage(body,entityType,entityId);
    }
    private void sendMessage(Object body,Object entityType,Object entityId){
        Map<String,Object> headers= new HashMap<>();
        headers.put("entity-type",entityType);
        headers.put("entity-id",entityId);
        System.out.println("@@@@In HttSource");

        source.output().send(MessageBuilder.createMessage(body,new MessageHeaders(headers)));
    }
}
