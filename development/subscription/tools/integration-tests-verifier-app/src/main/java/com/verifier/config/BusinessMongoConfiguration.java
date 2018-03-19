package com.verifier.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.verifier.domains.business.repository.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;
@Configuration
@EnableMongoRepositories(basePackageClasses = {BusinessBusinessAccountViewRepository.class,
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
})
public class BusinessMongoConfiguration {
    @Bean
    @Qualifier("BusinessMongoTemplate")
    public MongoTemplate businessMongoTemplate(@Qualifier("BusinessMongoDbFactory") MongoDbFactory factory) {
        System.out.println("###INside MOngoTemplate creation");
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
                                         @Value("${view.db.business.name}") String dbName,
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
    @Primary
    @Qualifier("BusinessMongoDbFactory")
    public MongoDbFactory businessMongoDbFactory(@Value("${view.db.business.host}") String host, @Value("${view.db.business.port}") int port,
                                         @Value("${view.db.business.name}") String dbName) throws UnknownHostException {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }
} 
