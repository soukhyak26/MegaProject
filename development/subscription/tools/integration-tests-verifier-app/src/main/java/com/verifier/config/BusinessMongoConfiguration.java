package com.verifier.config;

import com.affaince.subscription.common.idconverter.LocalDateTimeToStringConverter;
import com.affaince.subscription.common.idconverter.LocalDateToStringConverter;
import com.affaince.subscription.common.idconverter.StringToLocalDateConverter;
import com.affaince.subscription.common.idconverter.StringToLocalDateTimeConverter;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.verifier.domains.business.repository.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableMongoRepositories(mongoTemplateRef="businessMongoTemplate",  basePackageClasses = {BusinessBusinessAccountViewRepository.class,
        BusinessBenefitAccountTransactionsViewRepository.class,
        BusinessBenefitAccountViewRepository.class,
        BusinessBookingAmountAccountViewRepository.class,
        BusinessBookingAmountTransactionsViewRepository.class,
        BusinessBudgetChangeRecommendationViewRepository.class,
        BusinessBusinessAccountConfigurationViewRepository.class,
        BusinessCommonExpenseAccountViewRepository.class,
        BusinessCommonExpensesTransactionsViewRepository.class,
        BusinessCommonOperatingExpenseConfigViewRepository.class,
        BusinessDeliveryChargesRuleViewRepository.class,
        BusinessDeliveryForecastViewRepository.class,
        BusinessLossesAccountTransactionsViewRepository.class,
        BusinessLossesAccountViewRepository.class,
        BusinessNodalAccountTransactionsViewRepository.class,
        BusinessOthersAccountTransactionsViewRepository.class,
        BusinessNodalAccountViewRepository.class,
        BusinessOthersAccountViewRepository.class,
        BusinessProductForecastViewRepository.class,
        BusinessProductsStatisticsViewRepository.class,
        BusinessProductViewRepository.class,
        BusinessProfitAccountTransactionsViewRepository.class,
        BusinessProfitAccountViewRepository.class,
        BusinessPurchaseCostAccountTransactionsViewRepository.class,
        BusinessPurchaseCostAccountViewRepository.class,
        BusinessProfitAccountViewRepository.class,
        BusinessProfitAccountTransactionsViewRepository.class,
        BusinessRefundAccountViewRepository.class,
        BusinessRevenueAccountTransactionsViewRepository.class,
        BusinessRevenueAccountViewRepository.class,
        BusinessSubscriptionForecastViewRepository.class,
        BusinessSubscriptionInfoViewRepository.class,
        BusinessSubscriptionReceivableAccountViewRepository.class,
        BusinessSubscriptionSpecificOSAccountTransactionsViewRepository.class,
        BusinessTaxesAccountTransactionsViewRepository.class,
        BusinessTaxesAccountViewRepository.class,
        BusinessVariableExpenseAccountTransactionsViewRepository.class,
        BusinessVariableExpenseAccountViewRepository.class,
        BusinessBenefitsProvisionCalendarViewRepository.class,
        BusinessCommonExpensesProvisionCalendarViewRepository.class,
        BusinessPurchaseCostProvisionCalendarViewRepository.class,
        BusinessTaxesProvisionCalendarViewRepository.class,
        BusinessVariableExpensesProvisionCalendarViewRepository.class,
        BusinessOthersProvisionCalendarViewRepository.class,
        BusinessDeliveryChargesRuleViewRepository.class
})
public class BusinessMongoConfiguration {
    @Bean
    @Qualifier("businessMongoTemplate")
    @Primary
    public MongoTemplate businessMongoTemplate(@Qualifier("businessMongoDbFactory") MongoDbFactory factory) {
        System.out.println("###INside BusinessMongoTemplate creation " + factory.getDb().getName());
        MongoTemplate businessMongoTemplate = new MongoTemplate(factory);
        return businessMongoTemplate;
    }

    @Bean
    @Qualifier("BusinessMongo")
    public MongoClient businessMongo(@Qualifier("BusinessMongoClientURI") MongoClientURI mongoClientURI) throws UnknownHostException {
        System.out.println("###INside MOngoClient creation");
        return new MongoClient(mongoClientURI);
    }

    @Bean
    @Qualifier("BusinessMongoClientURI")
    public MongoClientURI businessMongoClientURI(@Value("${view.db.business.host}") String host, @Value("${view.db.business.port}") int port,
                                         @Value("${view.db.business.name}") String dbName) {
        return new MongoClientURI("mongodb://" /*+ username + ":" + password + "@" */
                + host
                + ":"
                + port
                + "/" +
                dbName);
    }

    @Bean
    @Primary
    @Qualifier("businessMongoDbFactory")
    public MongoDbFactory businessMongoDbFactory(@Value("${view.db.business.host}") String host, @Value("${view.db.business.port}") int port,
                                         @Value("${view.db.business.name}") String dbName) throws UnknownHostException {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }

    @Bean
    @Qualifier("BusinessCustomConversions")
    public CustomConversions businessCustomConversions(){
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(new LocalDateToStringConverter());
        converters.add(new LocalDateTimeToStringConverter());
        converters.add(new StringToLocalDateConverter());
        converters.add(new StringToLocalDateTimeConverter());
        return new CustomConversions(converters);
    }

    @Bean
    @Qualifier("BusinessMappingMongoConverter")
    public MappingMongoConverter mappingMongoConverter(@Qualifier("BusinessMongo")Mongo mongo,
                                                       @Qualifier("businessMongoDbFactory") MongoDbFactory mongoDbFactory,
                                                       @Qualifier("BusinessCustomConversions") CustomConversions customConversions) {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(customConversions);
        return converter;
    }
} 
