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

/**
 * Created by mandar on 8/14/2017.
 */
@Controller
@EnableBinding(Source.class)
public class HttpSource {
    @Autowired
    private Source source;

    @RequestMapping(value="/src/", method= RequestMethod.POST, consumes={"application/json"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handleRequest(@RequestBody String body, @RequestHeader(HttpHeaders.CONTENT_TYPE) Object contentType){
        sendMessage(body,contentType);
    }
    private void sendMessage(Object body,Object contentType){
        source.output().send(MessageBuilder.createMessage(body,new MessageHeaders(Collections.singletonMap(MessageHeaders.CONTENT_TYPE,contentType))));
    }
}
