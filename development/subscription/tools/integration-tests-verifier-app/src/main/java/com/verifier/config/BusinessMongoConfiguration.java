package com.verifier.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;
@Configuration
@EnableMongoRepositories(basePackageClasses = {com.verifier.domains.business.repository.BusinessAccountViewRepository.class,
        com.verifier.domains.business.repository.BenefitAccountTransactionsViewRepository.class,
        com.verifier.domains.business.repository.BenefitAccountViewRepository.class,
        com.verifier.domains.business.repository.BookingAmountAccountViewRepository.class,
        com.verifier.domains.business.repository.BookingAmountTransactionsViewRepository.class,
        com.verifier.domains.business.repository.BudgetChangeRecommendationViewRepository.class,
        com.verifier.domains.business.repository.BusinessAccountConfigurationViewRepository.class,
        com.verifier.domains.business.repository.CommonExpenseAccountViewRepository.class,
        com.verifier.domains.business.repository.CommonExpensesTransactionsViewRepository.class,
        com.verifier.domains.business.repository.CommonOperatingExpenseConfigViewRepository.class,
        com.verifier.domains.business.repository.DeliveryChargesRuleViewRepository.class,
        com.verifier.domains.business.repository.DeliveryForecastViewRepository.class,
        com.verifier.domains.business.repository.LossesAccountTransactionsViewRepository.class,
        com.verifier.domains.business.repository.LossesAccountViewRepository.class,
        com.verifier.domains.business.repository.NodalAccountTransactionsViewRepository.class,
        com.verifier.domains.business.repository.OthersAccountTransactionsViewRepository.class,
        com.verifier.domains.business.repository.NodalAccountViewRepository.class,
        com.verifier.domains.business.repository.OthersAccountViewRepository.class,
        com.verifier.domains.business.repository.ProductForecastViewRepository.class,
        com.verifier.domains.business.repository.ProductsStatisticsViewRepository.class,
        com.verifier.domains.business.repository.ProductViewRepository.class,
        com.verifier.domains.business.repository.ProfitAccountTransactionsViewRepository.class,
        com.verifier.domains.business.repository.ProfitAccountViewRepository.class,
        com.verifier.domains.business.repository.PurchaseCostAccountTransactionsViewRepository.class,
        com.verifier.domains.business.repository.PurchaseCostAccountViewRepository.class,
        com.verifier.domains.business.repository.ProfitAccountViewRepository.class,
        com.verifier.domains.business.repository.ProfitAccountTransactionsViewRepository.class,
        com.verifier.domains.business.repository.RefundAccountViewRepository.class,
        com.verifier.domains.business.repository.RevenueAccountTransactionsViewRepository.class,
        com.verifier.domains.business.repository.RevenueAccountViewRepository.class,
        com.verifier.domains.business.repository.SubscriptionForecastViewRepository.class,
        com.verifier.domains.business.repository.SubscriptionInfoViewRepository.class,
        com.verifier.domains.business.repository.SubscriptionReceivableAccountViewRepository.class,
        com.verifier.domains.business.repository.SubscriptionSpecificOSAccountTransactionsViewRepository.class,
        com.verifier.domains.business.repository.TaxesAccountTransactionsViewRepository.class,
        com.verifier.domains.business.repository.TaxesAccountViewRepository.class,
        com.verifier.domains.business.repository.VariableExpenseAccountTransactionsViewRepository.class,
        com.verifier.domains.business.repository.VariableExpenseAccountViewRepository.class,
})
public class BusinessMongoConfiguration {
    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory factory) {
        System.out.println("###INside MOngoTemplate creation");
        MongoTemplate mongoTemplate = new MongoTemplate(factory);
        return mongoTemplate;
    }

    @Bean
    public MongoClient mongo(MongoClientURI mongoClientURI) throws UnknownHostException {
        System.out.println("###INside MOngoClient creation");
        return new MongoClient(mongoClientURI);
    }

    @Bean
    public MongoClientURI mongoClientURI(@Value("${view.db.business.host}") String host, @Value("${view.db.business.port}") int port,
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
    public MongoDbFactory mongoDbFactory(@Value("${view.db.business.host}") String host, @Value("${view.db.business.port}") int port,
                                         @Value("${view.db.business.name}") String dbName) throws UnknownHostException {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }
} 
