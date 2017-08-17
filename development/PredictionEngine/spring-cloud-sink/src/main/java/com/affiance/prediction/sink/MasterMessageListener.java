package com.affiance.prediction.sink;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * Created by mandar on 8/14/2017.
 */
@EnableBinding(Sink.class)
public class MasterMessageListener {
    @StreamListener(Sink.INPUT)
    public void listen(String data){

    }
}
