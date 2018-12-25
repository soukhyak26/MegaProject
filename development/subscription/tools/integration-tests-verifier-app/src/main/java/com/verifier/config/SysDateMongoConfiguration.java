package com.verifier.config;

import com.affaince.subscription.common.idconverter.LocalDateTimeToStringConverter;
import com.affaince.subscription.common.idconverter.LocalDateToStringConverter;
import com.affaince.subscription.common.idconverter.StringToLocalDateConverter;
import com.affaince.subscription.common.idconverter.StringToLocalDateTimeConverter;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.verifier.converters.IntegerToLocalDateConverter;
import com.verifier.converters.LocalDateReadingConverter;
import com.verifier.converters.LocalDateToIntegerConverter;
import com.verifier.converters.LocalDateWritingConverter;
import com.verifier.domains.sysdate.repository.SysDateTimeViewRepository;
import com.verifier.domains.sysdate.repository.SysDateViewRepository;
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
@EnableMongoRepositories(mongoTemplateRef ="sysDateMongoTemplate", basePackageClasses = {SysDateViewRepository.class,
SysDateTimeViewRepository.class})
public class SysDateMongoConfiguration {
    @Bean //(name="subscriberMongoTemplate")
    @Qualifier("sysDateMongoTemplate")
    public MongoTemplate sysDateMongoTemplate(@Qualifier("SysDateMongoDbFactory") MongoDbFactory factory,@Qualifier("SysDateMappingMongoConverter") MappingMongoConverter mappingMongoConverter) {
        System.out.println("###INside SysDateMongoDbFactory creation " + factory.getDb().getName());
        MongoTemplate mongoTemplate = new MongoTemplate(factory,mappingMongoConverter);
        MappingMongoConverter mmc = (MappingMongoConverter)mongoTemplate.getConverter();
        mmc.setCustomConversions(sysDateCustomConversions());
        mmc.afterPropertiesSet();
        return mongoTemplate;
    }

    @Bean //(name="SubscriberMongo")
    @Qualifier("SysDateMongo")
    public MongoClient mongo(@Qualifier("SysDateMongoClientURI") MongoClientURI mongoClientURI) throws UnknownHostException {
        System.out.println("###INside MOngoClient creation");
        return new MongoClient(mongoClientURI);
    }

    @Bean //(name="SubscriberMongoClientURI")
    @Qualifier("SysDateMongoClientURI")
    public MongoClientURI mongoClientURI(@Value("${view.db.sysdate.host}") String host, @Value("${view.db.sysdate.port}") int port,
                                         @Value("${view.db.sysdate.name}") String dbName,
                                         @Value("${affaince.db.username}") String username,
                                         @Value("${affaince.db.password}") String password) {
        return new MongoClientURI("mongodb://" /*+ username + ":" + password + "@" */
                + host
                + ":"
                + port
                + "/" +
                dbName);
    }

    @Bean //(name="SubscriberMongoDbFactory")
    @Qualifier("SysDateMongoDbFactory")
    public MongoDbFactory SysDateMongoDbFactory(@Value("${view.db.sysdate.host}") String host, @Value("${view.db.sysdate.port}") int port,
                                         @Value("${view.db.sysdate.name}") String dbName) throws UnknownHostException {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }
    @Bean //(name="SubscriberMappingMongoConverter")
    @Qualifier("SysDateMappingMongoConverter")
    public MappingMongoConverter SysDateMappingMongoConverter(@Qualifier("SysDateMongoDbFactory")MongoDbFactory mongoDbFactory,
                                                       @Qualifier("SysDateCustomConversions") CustomConversions customConversions) throws Exception {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(sysDateCustomConversions());
        converter.afterPropertiesSet();
        return converter;
    }

    @Bean //(name="SubscriberCustomConversions")
    @Qualifier("SysDateCustomConversions")
    public CustomConversions sysDateCustomConversions(){
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(new LocalDateToStringConverter());
        converters.add(new LocalDateTimeToStringConverter());
        converters.add(new StringToLocalDateConverter());
        converters.add(new StringToLocalDateTimeConverter());
        return new CustomConversions(converters);
    }

} 
