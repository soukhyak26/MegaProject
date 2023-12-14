package com.examples.config;

import org.apache.spark.sql.SparkSession;

/**
 * Created by mandar on 6/24/2017.
 */
public class SparkConfig {

    private String appName;

    private String masterUrl;


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
