package com.verifier.config;

import com.affaince.subscription.common.idconverter.LocalDateTimeToStringConverter;
import com.affaince.subscription.common.idconverter.LocalDateToStringConverter;
import com.affaince.subscription.common.idconverter.StringToLocalDateConverter;
import com.affaince.subscription.common.idconverter.StringToLocalDateTimeConverter;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.verifier.domains.payments.repository.PaymentsPaymentSchemesViewRepository;
import com.verifier.domains.payments.repository.PaymentsProductViewRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories(mongoTemplateRef="paymentsMongoTemplate",basePackageClasses = {com.verifier.domains.payments.repository.DeliveryCostAccountViewRepository.class,
com.verifier.domains.payments.repository.DeliveryDetailsViewRepository.class,
com.verifier.domains.payments.repository.PaymentAccountViewRepository.class,
        com.verifier.domains.payments.repository.PaymentInstallmentViewRepository.class,
        PaymentsPaymentSchemesViewRepository.class,
        com.verifier.domains.payments.repository.PaymentTransactionViewRepository.class,
        com.verifier.domains.payments.repository.ProductOfferPricesViewRepository.class,
        com.verifier.domains.payments.repository.ProductTaggedPricesViewRepository.class,
        PaymentsProductViewRepository.class,
        com.verifier.domains.payments.repository.RefundTransactionsViewRepository.class,
        com.verifier.domains.payments.repository.RefundViewRepository.class,
        com.verifier.domains.payments.repository.TotalReceivableCostAccountViewRepository.class,
        com.verifier.domains.payments.repository.TotalReceivedCostAccountViewRepository.class,
        com.verifier.domains.payments.repository.TotalSubscriptionCostAccountViewRepository.class
        })
public class PaymentsMongoConfiguration {
    @Bean
    @Qualifier("paymentsMongoTemplate")
    public MongoTemplate paymentsMongoTemplate(@Qualifier("paymentsMongoDbFactory") MongoDbFactory factory) {
        System.out.println("###INside Payments MongoTemplate creation " + factory.getDb().getName());
        MongoTemplate mongoTemplate = new MongoTemplate(factory);
        return mongoTemplate;
    }

    @Bean
    @Qualifier("PaymentsMongo")
    public MongoClient mongo(@Qualifier("PaymentsMongoClientURI") MongoClientURI mongoClientURI) throws UnknownHostException {
        System.out.println("###INside MOngoClient creation");
        return new MongoClient(mongoClientURI);
    }

    @Bean
    @Qualifier("PaymentsMongoClientURI")
    public MongoClientURI mongoClientURI(@Value("${view.db.payments.host}") String host, @Value("${view.db.payments.port}") int port,
                                         @Value("${view.db.payments.name}") String dbName,
                                         @Value("${affaince.db.username}") String username,
                                         @Value("${affaince.db.password}") String password) {
        return new MongoClientURI("mongodb://" /*+ username + ":" + password + "@" */
                + host
                + ":"
                + port
                + "/" +
                dbName);
    }

    @Bean
    @Qualifier("paymentsMongoDbFactory")
    public MongoDbFactory paymentsMongoDbFactory(@Value("${view.db.payments.host}") String host, @Value("${view.db.payments.port}") int port,
                                         @Value("${view.db.payments.name}") String dbName) throws UnknownHostException {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }

    @Bean
    @Qualifier("PaymentsCustomConversions")
    public CustomConversions paymentsCustomConversions(){
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(new LocalDateToStringConverter());
        converters.add(new LocalDateTimeToStringConverter());
        converters.add(new StringToLocalDateConverter());
        converters.add(new StringToLocalDateTimeConverter());
        return new CustomConversions(converters);
    }

    @Bean
    @Qualifier("PaymentsMappingMongoConverter")
    public MappingMongoConverter mappingMongoConverter(@Qualifier("PaymentsMongo")Mongo mongo,
                                                       @Qualifier("paymentsMongoDbFactory") MongoDbFactory mongoDbFactory,
                                                       @Qualifier("PaymentsCustomConversions") CustomConversions customConversions) {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(customConversions);
        return converter;
    }

} 
