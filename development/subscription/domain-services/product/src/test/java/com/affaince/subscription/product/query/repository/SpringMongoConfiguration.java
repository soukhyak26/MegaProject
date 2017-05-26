package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.idconverter.LocalDateReadingConverter;
import com.affaince.subscription.common.idconverter.LocalDateWritingConverter;
import com.affaince.subscription.common.idconverter.ProductVersionIdReaderConverter;
import com.affaince.subscription.common.idconverter.ProductVersionIdWriterConverter;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.CustomConversions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 02-07-2016.
 */
//@Configuration
//@EnableMongoRepositories
public class SpringMongoConfiguration {
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(new MongoClient("localhost"), "testProductRepo");
    }

    @Bean(name="customConversionsForProductVersionId")
    public CustomConversions customConversionsForProductVersionId() {
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(new ProductVersionIdReaderConverter());
        converters.add(new ProductVersionIdWriterConverter());
        return new CustomConversions(converters);
    }

    @Bean(name="customConversionsForEndDate")
    public CustomConversions customConversionsForEndDate() {
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(new LocalDateWritingConverter());
        converters.add(new LocalDateReadingConverter());
        return new CustomConversions(converters);
    }

}
