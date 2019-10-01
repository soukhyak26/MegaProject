package com.verifier.config;

import com.affaince.subscription.common.idconverter.LocalDateTimeToStringConverter;
import com.affaince.subscription.common.idconverter.LocalDateToStringConverter;
import com.affaince.subscription.common.idconverter.StringToLocalDateConverter;
import com.affaince.subscription.common.idconverter.StringToLocalDateTimeConverter;
import com.mongodb.*;
import com.verifier.domains.payments.repository.*;
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
@EnableMongoRepositories(mongoTemplateRef = "paymentsMongoTemplate", basePackageClasses = {DeliveryCostAccountViewRepository.class,
        DeliveryDetailsViewRepository.class,
        PaymentAccountViewRepository.class,
        PaymentInstallmentViewRepository.class,
        PaymentsPaymentSchemesViewRepository.class,
        PaymentTransactionViewRepository.class,
        ProductOfferPricesViewRepository.class,
        ProductTaggedPricesViewRepository.class,
        PaymentsProductViewRepository.class,
        RefundTransactionsViewRepository.class,
        RefundViewRepository.class,
        TotalReceivableCostAccountViewRepository.class,
        TotalReceivedCostAccountViewRepository.class,
        TotalSubscriptionCostAccountViewRepository.class
})
public class PaymentsMongoConfiguration {
    @Bean //(name = "paymentsMongoTemplate")
    @Qualifier("paymentsMongoTemplate")
    public MongoTemplate paymentsMongoTemplate(@Qualifier("paymentsMongoDbFactory") MongoDbFactory factory, @Qualifier("PaymentsMappingMongoConverter") MappingMongoConverter mappingMongoConverter) {
        System.out.println("###INside Payments MongoTemplate creation " + factory.getDb().getName());
        MongoTemplate mongoTemplate = new MongoTemplate(factory, mappingMongoConverter);
        return mongoTemplate;
    }

    @Bean //(name = "PaymentsMongo")
    @Qualifier("PaymentsMongo")
    public MongoClient mongo(@Qualifier("PaymentsMongoClientURI") MongoClientURI mongoClientURI) throws UnknownHostException {
        System.out.println("###INside MOngoClient creation");
        return new MongoClient(mongoClientURI);
    }

    @Bean //(name = "PaymentsMongoClientURI")
    @Qualifier("PaymentsMongoClientURI")
    public MongoClientURI mongoClientURI(@Value("${view.db.payments.host}") String host, @Value("${view.db.payments.port}") int port,
                                         @Value("${view.db.payments.name}") String dbName,
                                         @Value("${affaince.db.username}") String username,
                                         @Value("${affaince.db.password}") String password) {
        final MongoClientOptions options = MongoClientOptions.builder()
                .threadsAllowedToBlockForConnectionMultiplier(2)
                .connectionsPerHost(10)
                .connectTimeout(15000)
                .maxWaitTime(15000)
                .socketTimeout(15000)
                .heartbeatConnectTimeout(5000)
                .minHeartbeatFrequency(1000)
                .build();

        return new MongoClientURI("mongodb://" /*+ username + ":" + password + "@" */
                + host
                + ":"
                + port
                + "/" +
                dbName,MongoClientOptions.builder(options));
    }

    @Bean //(name = "paymentsMongoDbFactory")
    @Qualifier("paymentsMongoDbFactory")
    public MongoDbFactory paymentsMongoDbFactory(@Value("${view.db.payments.host}") String host, @Value("${view.db.payments.port}") int port,
                                                 @Value("${view.db.payments.name}") String dbName) throws UnknownHostException {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }

    @Bean //(name = "PaymentsCustomConversions")
    @Qualifier("PaymentsCustomConversions")
    public CustomConversions paymentsCustomConversions() {
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        return new CustomConversions(converters);
    }

    @Bean //(name = "PaymentsMappingMongoConverter")
    @Qualifier("PaymentsMappingMongoConverter")
    public MappingMongoConverter paymentsMappingMongoConverter(@Qualifier("paymentsMongoDbFactory") MongoDbFactory mongoDbFactory,
                                                       @Qualifier("PaymentsCustomConversions") CustomConversions customConversions) {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(customConversions);
        converter.afterPropertiesSet();
        return converter;
    }

} 
