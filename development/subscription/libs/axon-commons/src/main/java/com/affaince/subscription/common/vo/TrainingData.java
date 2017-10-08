package com.affaince.subscription.common.vo;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandark on 21-02-2016.
 */
public class TrainingData {
    private int numberOfDataRecords;
    private int numberOfVariables;
    private List<TrainingRecord> trainingRecords;
    private RegressionResult regressionResult;

    public TrainingData() {
        trainingRecords = new ArrayList<TrainingRecord>();
    }

    public int getNumberOfDataRecords() {
        return this.numberOfDataRecords;
    }

    public void setNumberOfDataRecords(int numberOfDataRecords) {
        this.numberOfDataRecords = numberOfDataRecords;
    }

    public int getNumberOfVariables() {
        return this.numberOfVariables;
    }

    public void setNumberOfVariables(int numberOfVariables) {
        this.numberOfVariables = numberOfVariables;
    }

    public List<TrainingRecord> getTrainingRecords() {
        return this.trainingRecords;
    }

    public void setTrainingRecords(List<TrainingRecord> trainingRecords) {
        this.trainingRecords = trainingRecords;
    }

    public void addTrainingRecord(TrainingRecord record) {
        trainingRecords.add(record);
    }

    private double[] formDataToBeregressed() {
        final double[] dataToBeRegressed = new double[this.numberOfDataRecords * (this.numberOfVariables + 1)];
        int j = 0;
        for (int i = 0; i < this.numberOfDataRecords; i++) {
            j = i * (this.numberOfVariables + 1);
            TrainingRecord trainingRecord = trainingRecords.get(i);
            dataToBeRegressed[j] = trainingRecord.findOutputRecordElement().getTrainingRecordValue();
            for (TrainingRecordElement element : trainingRecord.getRecordElements()) {
                if (!element.isOutput()) {
                    dataToBeRegressed[j++] = element.getTrainingRecordValue();
                }
            }
        }
        return dataToBeRegressed;
    }

    public void trainData() {
        final double[] dataToBeRegressed = formDataToBeregressed();
        //this.regressionResult= MathsProcessingService.processMultipleLinearRegression(dataToBeRegressed, this.numberOfDataRecords, this.numberOfVariables);

    }

    public RegressionResult getRegressionResult() {
        return this.regressionResult;
    }
}
