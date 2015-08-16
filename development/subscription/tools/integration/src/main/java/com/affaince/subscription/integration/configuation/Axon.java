package com.affaince.subscription.integration.configuation;

import com.affaince.subscription.configuration.Default;
import com.affaince.subscription.integration.command.event.GenericEventPublisher;
import com.affaince.subscription.integration.command.event.SubscriptionableItemReceivedEvent;
import com.affaince.subscription.integration.command.event.SubscriptionableItemReceivedEvent;
import com.affaince.subscription.integration.command.listener.LogProcessListener;
import com.affaince.subscription.integration.command.listener.ReadEventListener;
import com.affaince.subscription.integration.command.mapper.SubscriptionableItemReceivedEventFieldSetMapper;
import com.affaince.subscription.integration.command.writer.Writer;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Route;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.axonframework.eventhandling.EventTemplate;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jms.annotation.EnableJms;

/**
 * Created by mandark on 19-07-2015.
 */
@Configuration
@EnableBatchProcessing
@EnableJms
public class Axon extends Default {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    CamelContext camelContext;
    public static final String OVERRIDEN_BY_EXPRESSION_VALUE = "overriden by expression value";

    /*
    @Bean
    Job fileReadJob() {
        return jobs.get("fileReadJob").listener(readEventListener()).start(step()).build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step").<SubscriptionableItemReceivedEvent, SubscriptionableItemReceivedEvent>chunk(1).reader(fileReader(OVERRIDEN_BY_EXPRESSION_VALUE)).processor(fileProcessor()).writer(fileWriter()).listener(logProcessListener()).faultTolerant().skipLimit(5).skip(Exception.class).build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<SubscriptionableItemReceivedEvent> fileReader(@Value("${directoryPath}") String directoryPath) {
        FlatFileItemReader<SubscriptionableItemReceivedEvent> reader = new FlatFileItemReader<SubscriptionableItemReceivedEvent>();
        reader.setLinesToSkip(1);//first line is title definition
        reader.setResource(new ClassPathResource(directoryPath));
        reader.setLineMapper(lineMapper());
        return reader;
    }
*/

/*
    @Bean
    public DefaultLineMapper<SubscriptionableItemReceivedEvent> lineMapper() {
        DefaultLineMapper<SubscriptionableItemReceivedEvent> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"BATCH_ID", "CATEGORY_ID", "CATEGORY_NAME", "SUBCATEGORY_ID", "SUBCATEGORY_NAME", "CURRENT_MRP", "CURRENT_PURCHASE", "PRICE_PER_UNIT", "CURRENT_STOCK", "PRODUCT_ID"
        });

        BeanWrapperFieldSetMapper<SubscriptionableItemReceivedEvent> fieldSetMapper = new BeanWrapperFieldSetMapper<SubscriptionableItemReceivedEvent>();
        fieldSetMapper.setTargetType(SubscriptionableItemReceivedEvent.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(subscriptionableItemReceivedEventFieldSetMapper());
        return lineMapper;
    }


    @Bean
    public SubscriptionableItemReceivedEventFieldSetMapper subscriptionableItemReceivedEventFieldSetMapper() {
        return new SubscriptionableItemReceivedEventFieldSetMapper();
    }
*/

/*
    */

    /**
     * configure the processor related stuff
     *//*

    @Bean
    public ItemProcessor<SubscriptionableItemReceivedEvent,SubscriptionableItemReceivedEvent> fileProcessor() {
        return new SubscriptionableItemReceivedEventProcessor();
    }

    @Bean
    public ItemWriter<SubscriptionableItemReceivedEvent> fileWriter() {
        return new Writer();
    }
    // end::readerwriterprocessor[]

    @Bean
    public ReadEventListener readEventListener() {
        return new ReadEventListener();
    }

    @Bean
    public LogProcessListener logProcessListener() {
        return new LogProcessListener();
    }
*/
    @Bean
    public GenericEventPublisher publisher(EventTemplate template) {
        return new GenericEventPublisher(template);
    }


    @Bean
    public RouteBuilder route() {
        return new RouteBuilder() {
            public void configure() {
/*
                from("file:D://abc?fileName=subscriptionableItemsFeed.in").unmarshal("bindyForIn").marshal().json(JsonLibrary.Jackson).to("activemq:eventbustopic");
*/

                from("file:D://abc").
                        unmarshal().bindy(BindyType.Csv, SubscriptionableItemReceivedEvent.class).
                        split().body().
                        to("bean:publisher");

            }
        };
    }

    @Bean
    CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext context) {
                System.out.println("@@@@@@@@@@@Hey Camel Started@@@@@@@@@@@");
            }
        };
    }

}
