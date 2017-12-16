package com.affaince.subscription.common.factory;

import com.affaince.subscription.common.vo.TrainingData;
import com.affaince.subscription.common.vo.TrainingRecord;
import com.affaince.subscription.common.vo.TrainingRecordElement;

import java.util.List;
import java.util.Map;

/**
 * Created by mandark on 21-02-2016.
 */
public class TrainingDataBuilder {
    public static TrainingData buildTrainingData(List<Map<String, Double>> historicalData) {
        TrainingData trainingData = new TrainingData();
        int i = 0;
        for (Map<String, Double> record : historicalData) {
            TrainingRecord trainingRecord = new TrainingRecord(i++);
            int j = 0;
            for (String recordElementHeader : record.keySet()) {

                TrainingRecordElement recordElement = new TrainingRecordElement(j++, recordElementHeader, record.get(recordElementHeader), (j == 0) ? true : false);
                trainingRecord.addRecorElement(recordElement);
            }
            trainingData.addTrainingRecord(trainingRecord);
            i++;
        }
        return trainingData;
    }
}
