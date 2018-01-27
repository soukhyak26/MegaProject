package com.affiance.prediction.algos;

import com.affaince.subscription.common.vo.DataFrameVO;
import com.affiance.prediction.config.HistoryMaxSizeConstraints;
import com.affiance.prediction.config.HistoryMinSizeConstraints;
import com.cloudera.sparkts.DateTimeIndex;
import com.cloudera.sparkts.DayFrequency;
import com.cloudera.sparkts.api.java.DateTimeIndexFactory;
import com.cloudera.sparkts.api.java.JavaTimeSeriesRDD;
import com.cloudera.sparkts.api.java.JavaTimeSeriesRDDFactory;
import com.cloudera.sparkts.models.ARIMA;
import com.cloudera.sparkts.models.ARIMAModel;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import scala.Tuple2;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mandar on 19-06-2016.
 */

public class ARIMABasedDemandForecaster{
    private TimeSeriesBasedForecaster nextForecaster;
    @Autowired
    private HistoryMinSizeConstraints historyMinSizeConstraints;
    @Autowired
    private HistoryMaxSizeConstraints historyMaxSizeConstraints;
    @Autowired
    private SparkSession spark;

    public ARIMABasedDemandForecaster() {
    }

    private Dataset<Row> loadObservations(JavaSparkContext sparkContext, SQLContext sqlContext,
                                       List<DataFrameVO> dataFrames) {

        JavaRDD<DataFrameVO> dfRdd = sparkContext.parallelize(dataFrames);
        JavaRDD<Row> rowRdd = dfRdd.map((DataFrameVO dataFrameVO) -> {
            ZonedDateTime dt = ZonedDateTime.of(dataFrameVO.getDate().getYear(),
                    dataFrameVO.getDate().getMonthOfYear(), dataFrameVO.getDate().getDayOfMonth(), 0, 0, 0, 0,
                    ZoneId.systemDefault());
            String symbol = dataFrameVO.getToken();
            double price = dataFrameVO.getValue();
            return RowFactory.create(Timestamp.from(dt.toInstant()), symbol, price);
        });
        List<StructField> fields = new ArrayList();
        fields.add(DataTypes.createStructField("timestamp", DataTypes.TimestampType, true));
        fields.add(DataTypes.createStructField("token", DataTypes.StringType, true));
        fields.add(DataTypes.createStructField("value", DataTypes.DoubleType, true));
        StructType schema = DataTypes.createStructType(fields);
        return spark.createDataFrame(rowRdd, schema);
    }

    public void addNextForecaster(TimeSeriesBasedForecaster forecaster) {
        if (null == nextForecaster) {
            this.nextForecaster = forecaster;
        } else {
            this.nextForecaster.addNextForecaster(forecaster);
        }
    }

    public List<DataFrameVO> forecast(String dataIdentifier, List<DataFrameVO> dataFrames) {
        System.out.println("@@@IN ARIMA");
        ZoneId zone = ZoneId.systemDefault();
        System.out.println("@@@dataFrames date"+ dataFrames.get(0).getDate());

        ZonedDateTime startDateTime=ZonedDateTime.of(dataFrames.get(0).getDate().getYear(), dataFrames.get(0).getDate().getMonthOfYear(), dataFrames.get(0).getDate().getDayOfMonth(), 0, 0, 0, 0, zone);
        ZonedDateTime endDateTime= ZonedDateTime.of(dataFrames.get(dataFrames.size() - 1).getDate().getYear(), dataFrames.get(dataFrames.size() - 1).getDate().getMonthOfYear(), dataFrames.get(dataFrames.size() - 1).getDate().getDayOfMonth(), 0, 0, 0, 0, zone);
        if (dataFrames.size() > historyMinSizeConstraints.getArima()) {
            String token=dataFrames.get(0).getToken();
            JavaSparkContext javaSparkContext= JavaSparkContext.fromSparkContext(spark.sparkContext());
            SQLContext sqlContext = new SQLContext(javaSparkContext);
            Dataset<Row> tickerObs = loadObservations(javaSparkContext, sqlContext, dataFrames);
            // Create an daily DateTimeIndex over August and September 2015
            DateTimeIndex dtIndex = DateTimeIndexFactory.uniformFromInterval(startDateTime, endDateTime,new DayFrequency(1));

            // Align the ticker data on the DateTimeIndex to create a TimeSeriesRDD
            JavaTimeSeriesRDD tickerTsrdd = JavaTimeSeriesRDDFactory.timeSeriesRDDFromObservations(
                    dtIndex, tickerObs, "timestamp", "token", "value");

            // Cache it in memory
            tickerTsrdd.cache();

            // Count the number of series (number of symbols)
            System.out.println(tickerTsrdd.count());

            // Impute missing values using linear interpolation
            JavaTimeSeriesRDD<String> filled = tickerTsrdd.fill("linear");
            JavaTimeSeriesRDD<String> df = filled.mapSeries(vector -> {
                DenseVector newVec = new org.apache.spark.mllib.linalg.DenseVector(vector.toArray());
                Arrays.asList(ArrayUtils.toObject(newVec.toArray())).forEach(x ->{
                    x = (x == Double.NaN) ? 0.0 : x;
                });
                ARIMAModel arimaModel = ARIMA.fitModel(1, 0, 0, newVec, true, "css-cgd", null);
                Vector forecasted = arimaModel.forecast(newVec, dataFrames.size() / 2);
                Arrays.asList(ArrayUtils.toObject(forecasted.toArray())).forEach(x ->{
                    x = (x == Double.NaN) ? 0.0 : x;
                });

                return new org.apache.spark.mllib.linalg.DenseVector(forecasted.toArray());
            });
            df=df.slice(endDateTime.plusDays(1),endDateTime.plusDays(Math.abs(dataFrames.size()/2)));
            List<Tuple2<ZonedDateTime, Vector>> forecasts = df.toInstants().collect();
            List<DataFrameVO> forecastedSubscriptionCounts = new ArrayList<>();
            for(Tuple2<ZonedDateTime, Vector> sample: forecasts){
                ZonedDateTime dateTime=sample._1();
                System.out.println("ARIMA$$$$$ZonedDateTime:"+ dateTime.toLocalDate().toString());
                Vector vector=sample._2();
                for (int j = 0; j < vector.size(); j++) {
                    double forecastedValue = vector.apply(j);
                    System.out.println("ARIMA $$$$$forecast: " + forecastedValue);
                    DataFrameVO outputVO= new DataFrameVO(new LocalDate(dateTime.getYear(),dateTime.getMonthValue(),dateTime.getDayOfMonth()),token,forecastedValue,dataFrames.get(0).getAggregationType());
                    forecastedSubscriptionCounts.add(outputVO);
                }
            }
            sqlContext.clearCache();
            return forecastedSubscriptionCounts;

        } else {
            return null;
        }

    }

    public void setHistoryMinSizeConstraints(HistoryMinSizeConstraints historyMinSizeConstraints) {
        this.historyMinSizeConstraints = historyMinSizeConstraints;
    }

    public void setHistoryMaxSizeConstraints(HistoryMaxSizeConstraints historyMaxSizeConstraints) {
        this.historyMaxSizeConstraints = historyMaxSizeConstraints;
    }
}
