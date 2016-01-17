package com.affaince.notification.configuration;

import com.affaince.notification.events.PaymentProcessedEvent;
import com.affaince.notification.publisher.GenericMailEventPublisher;
import com.affaince.subscription.configuration.RabbitMQConfiguration;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anayonkar on 16/1/16.
 */

@Configuration
@EnableAutoConfiguration
public class Axon extends RabbitMQConfiguration {

    @Autowired
    CamelContext camelContext;


    @Bean
    public GenericMailEventPublisher GenericMailEventPublisher(){
        return new GenericMailEventPublisher();
    }

    @Bean
    public PaymentProcessedEvent PaymentProcessedEvent() {
        return new PaymentProcessedEvent();
    }

    /*@Autowired
    private PaymentProcessedEvent paymentProcessedEvent;*/

    @Bean
    public RouteBuilder routes() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                JacksonDataFormat df = new JacksonDataFormat(PaymentProcessedEvent.class);
                //from("${notification.event.feed.source}").
                //from("activemq:queue:Consumer.Notification.VirtualTopic.EventBus").
                from("rabbitmq://localhost/notification.exchange?queue=notification.queue&durable=true&declare=false").
                //from("rabbitmq://localhost/notification.exchange?routingKey=com.affaince.notification.events.PaymentProcessedEvent&durable=true&declare=false").
                        //unmarshal().json(JsonLibrary.Jackson)/
                        unmarshal(df).
                        //to("${notification.event.feed.destination}").
                                //to("bean:PaymentProcessedEvent"). //TODO: CHECK WHY THIS IS NOT WORKING
                                                                    //stack trace at end of file
                                                                    //Fixed after using JacksonDataFormat
                                //bean(paymentProcessedEvent.getClass()).
                        //to("${event.publisher}");
                                to("bean:GenericMailEventPublisher");
            }
        };
    }

    @Override
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.notification.events.PaymentProcessedEvent", PaymentProcessedEvent.class.getName());
        }};
    }
}

//stack trace:

/*
Stacktrace
        ---------------------------------------------------------------------------------------------------------------------------------------

        java.lang.IllegalStateException: No method invocation could be created, no matching method could be found on: com.affaince.notification.events.PaymentProcessedEvent@6d4cdd8e
        at org.apache.camel.component.bean.BeanProcessor.process(BeanProcessor.java:165)
        at org.apache.camel.util.AsyncProcessorHelper.process(AsyncProcessorHelper.java:109)
        at org.apache.camel.component.bean.BeanProcessor.process(BeanProcessor.java:68)
        at org.apache.camel.component.bean.BeanProducer.process(BeanProducer.java:38)
        at org.apache.camel.processor.SendProcessor.process(SendProcessor.java:129)
        at org.apache.camel.management.InstrumentationProcessor.process(InstrumentationProcessor.java:77)
        at org.apache.camel.processor.RedeliveryErrorHandler.process(RedeliveryErrorHandler.java:448)
        at org.apache.camel.processor.CamelInternalProcessor.process(CamelInternalProcessor.java:191)
        at org.apache.camel.processor.Pipeline.process(Pipeline.java:118)
        at org.apache.camel.processor.Pipeline.process(Pipeline.java:80)
        at org.apache.camel.processor.CamelInternalProcessor.process(CamelInternalProcessor.java:191)
        at org.apache.camel.util.AsyncProcessorHelper.process(AsyncProcessorHelper.java:109)
        at org.apache.camel.processor.DelegateAsyncProcessor.process(DelegateAsyncProcessor.java:87)
        at org.apache.camel.component.rabbitmq.RabbitMQConsumer$RabbitConsumer.handleDelivery(RabbitMQConsumer.java:204)
        at com.rabbitmq.client.impl.ConsumerDispatcher$5.run(ConsumerDispatcher.java:144)
        at com.rabbitmq.client.impl.ConsumerWorkService$WorkPoolRunnable.run(ConsumerWorkService.java:99)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
        at java.lang.Thread.run(Thread.java:745)

        2016-01-17 17:09:07.457  WARN 24097 --- [abbitMQConsumer] o.a.c.c.rabbitmq.RabbitMQConsumer        : Error processing exchange. Exchange[Message: {subscriberId=111, subscriptionId=007, paymentDate={era=1, dayOfYear=17, dayOfWeek=7, dayOfMonth=17, year=2016, chronology={zone={fixed=true, id=UTC}}, centuryOfEra=20, yearOfEra=2016, yearOfCentury=16, weekyear=2016, monthOfYear=1, weekOfWeekyear=2, values=[2016, 1, 17], fieldTypes=[{durationType={name=years}, rangeDurationType=null, name=year}, {durationType={name=months}, rangeDurationType={name=years}, name=monthOfYear}, {durationType={name=days}, rangeDurationType={name=months}, name=dayOfMonth}], fields=[{lenient=false, rangeDurationField=null, minimumValue=-292275054, maximumValue=292278993, leapDurationField={unitMillis=86400000, precise=true, name=days, type={name=days}, supported=true}, durationField={unitMillis=31556952000, precise=false, name=years, type={name=years}, supported=true}, name=year, type={durationType={name=years}, rangeDurationType=null, name=year}, supported=true}, {lenient=false, rangeDurationField={unitMillis=31556952000, precise=false, name=years, type={na... [Body clipped after 1000 chars, total length is 1822]]. Caused by: [java.lang.IllegalStateException - No method invocation could be created, no matching method could be found on: com.affaince.notification.events.PaymentProcessedEvent@6d4cdd8e]

        java.lang.IllegalStateException: No method invocation could be created, no matching method could be found on: com.affaince.notification.events.PaymentProcessedEvent@6d4cdd8e
        at org.apache.camel.component.bean.BeanProcessor.process(BeanProcessor.java:165)
        at org.apache.camel.util.AsyncProcessorHelper.process(AsyncProcessorHelper.java:109)
        at org.apache.camel.component.bean.BeanProcessor.process(BeanProcessor.java:68)
        at org.apache.camel.component.bean.BeanProducer.process(BeanProducer.java:38)
        at org.apache.camel.processor.SendProcessor.process(SendProcessor.java:129)
        at org.apache.camel.management.InstrumentationProcessor.process(InstrumentationProcessor.java:77)
        at org.apache.camel.processor.RedeliveryErrorHandler.process(RedeliveryErrorHandler.java:448)
        at org.apache.camel.processor.CamelInternalProcessor.process(CamelInternalProcessor.java:191)
        at org.apache.camel.processor.Pipeline.process(Pipeline.java:118)
        at org.apache.camel.processor.Pipeline.process(Pipeline.java:80)
        at org.apache.camel.processor.CamelInternalProcessor.process(CamelInternalProcessor.java:191)
        at org.apache.camel.util.AsyncProcessorHelper.process(AsyncProcessorHelper.java:109)
        at org.apache.camel.processor.DelegateAsyncProcessor.process(DelegateAsyncProcessor.java:87)
        at org.apache.camel.component.rabbitmq.RabbitMQConsumer$RabbitConsumer.handleDelivery(RabbitMQConsumer.java:204)
        at com.rabbitmq.client.impl.ConsumerDispatcher$5.run(ConsumerDispatcher.java:144)
        at com.rabbitmq.client.impl.ConsumerWorkService$WorkPoolRunnable.run(ConsumerWorkService.java:99)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
        at java.lang.Thread.run(Thread.java:745)

*/
