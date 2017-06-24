package com.affaince.subscription.configuration;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mandar on 6/24/2017.
 */
@Configuration
public class SparkConfig {
    @Bean
    SparkConf sparkConf(){
        SparkConf conf =new SparkConf().setAppName("Spark-based-ARIMA-Forecaster").setMaster("local");
        conf.set("spark.io.compression.codec", "org.apache.spark.io.LZ4CompressionCodec");
        conf.set("spark.driver.allowMultipleContexts", "true");
        return conf;
    }


    @Bean
    SparkContext sparkContext(SparkConf sparkConf){
        SparkContext context = new SparkContext(sparkConf);
        return context;
    }

/*
    @Bean
    SQLContext sqlContext(JavaSparkContext javaSparkContext){
        SQLContext sqlContext = new SQLContext(javaSparkContext);
        return sqlContext;
    }
*/

}
