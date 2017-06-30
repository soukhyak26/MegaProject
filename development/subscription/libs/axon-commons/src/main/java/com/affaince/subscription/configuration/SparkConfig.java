package com.affaince.subscription.configuration;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mandar on 6/24/2017.
 */
@Configuration
public class SparkConfig {
    @Value("${app.name:Spark-based-ARIMA-Forecaster}")
    private String appName;
/*
    @Value("${spark.home:}")
    private String sparkHome;
*/
    @Value("${master.uri:local}")
    private String masterUrl;

    @Bean
    SparkConf sparkConf(){
        SparkConf conf =new SparkConf().setAppName("Spark-based-ARIMA-Forecaster").setMaster(masterUrl);
        conf.set("spark.io.compression.codec", "org.apache.spark.io.LZ4CompressionCodec");
        //conf.setSparkHome(sparkHome);
        conf.set("spark.driver.allowMultipleContexts", "true");
        return conf;
    }


    @Bean
    SparkContext sparkContext(SparkConf sparkConf){
        return new SparkContext(sparkConf);
    }


    /*
    @Bean
    SQLContext sqlContext(JavaSparkContext javaSparkContext){
        SQLContext sqlContext = new SQLContext(javaSparkContext);
        return sqlContext;
    }
*/

}
