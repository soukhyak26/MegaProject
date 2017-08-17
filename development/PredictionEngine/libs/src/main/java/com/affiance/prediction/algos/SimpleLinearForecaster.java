package com.affiance.prediction.algos;

import com.affiance.prediction.config.HistoryMaxSizeConstraints;
import com.affiance.prediction.config.HistoryMinSizeConstraints;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 27-12-2016.
 */
public class SimpleLinearForecaster implements TimeSeriesBasedForecaster{
    private TimeSeriesBasedForecaster nextForecaster;
    @Autowired
    private HistoryMinSizeConstraints historyMinSizeConstraints;
    @Autowired
    private HistoryMaxSizeConstraints historyMaxSizeConstraints;

    public void addNextForecaster(TimeSeriesBasedForecaster forecaster) {
        if (null == nextForecaster) {
            this.nextForecaster = forecaster;
        } else {
            this.nextForecaster.addNextForecaster(forecaster);
        }
    }

    public List<Double> forecast(String dataIdentifier, List<Double> historicalDataList) {
        if (historicalDataList.size() > historyMinSizeConstraints.getSlf() && historicalDataList.size() <= historyMaxSizeConstraints.getSlf()) {
            List<Double> forecastValues= new ArrayList<>(12);
            if( historicalDataList.size()==1){
                double actualRecord= historicalDataList.get(0);
                for(int i=0;i<12;i++){
                    double nextForecastRecord=actualRecord*0.2;
                    forecastValues.add(nextForecastRecord);
                    actualRecord=nextForecastRecord;
                }
                return forecastValues;
            }
            double demandChange=0.0;
            for(int i=0;i<historicalDataList.size();i++){
                double firstHistoricalRecord=historicalDataList.get(i);
                if((i+1)< historicalDataList.size()) {
                    double secondHistoricalRecord = historicalDataList.get(i + 1);
                    demandChange += (secondHistoricalRecord - firstHistoricalRecord) / firstHistoricalRecord;
                }
            }
            double trend = demandChange/historicalDataList.size();
            double lastActualRecord= historicalDataList.get(historicalDataList.size()-1);
            for(int i=0;i<12;i++){
                double nextForecastRecord=lastActualRecord*trend;
                forecastValues.add(nextForecastRecord);
                lastActualRecord=nextForecastRecord;
            }
            return forecastValues;
        }else{
            //TODO : Handle NPE
            return nextForecaster == null ? null
                    : nextForecaster.forecast(dataIdentifier, historicalDataList);

        }
    }

    @Override
    public void setHistoryMinSizeConstraints(HistoryMinSizeConstraints historyMinSizeConstraints) {
     this.historyMinSizeConstraints=historyMinSizeConstraints;
    }

    @Override
    public void setHistoryMaxSizeConstraints(HistoryMaxSizeConstraints historyMaxSizeConstraints) {
        this.historyMaxSizeConstraints=historyMaxSizeConstraints;
    }


}
