package com.affiance.prediction.config;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SparkSession;
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
    @Value("${master.uri:local}")
    private String masterUrl;


    @Bean
    SparkSession sparkSession() {
        return SparkSession
                .builder()
                .appName("Spark-based-ARIMA-Forecaster")
                .config("spark.master", "local")
                .config("spark.io.compression.codec", "org.apache.spark.io.LZ4CompressionCodec")
                .config("spark.sql.warehouse.dir", "file:///E:/apps/spark/warehouse/")
                .getOrCreate();
    }


}
