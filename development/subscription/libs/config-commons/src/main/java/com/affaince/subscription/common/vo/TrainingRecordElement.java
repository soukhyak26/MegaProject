package com.affaince.subscription.common.vo;

/**
 * Created by mandark on 21-02-2016.
 */
public class TrainingRecordElement implements Comparable<TrainingRecordElement>{
    private int trainingRecordIndex;
    private String trainingRecordHeader;
    private double trainingRecordValue;
    private boolean isOutput=false;

    public TrainingRecordElement(int trainingRecordIndex, String trainingRecordHeader, double trainingRecordValue,boolean isOutput) {
        this.trainingRecordIndex = trainingRecordIndex;
        this.trainingRecordHeader = trainingRecordHeader;
        this.trainingRecordValue = trainingRecordValue;
        this.isOutput=isOutput;
    }

    public int getTrainingRecordIndex() {
        return this.trainingRecordIndex;
    }

    public String getTrainingRecordHeader() {
        return this.trainingRecordHeader;
    }

    public double getTrainingRecordValue() {
        return this.trainingRecordValue;
    }

    public boolean isOutput() {
        return this.isOutput;
    }


    @Override
    public int compareTo(TrainingRecordElement o) {
        return ((Integer)this.trainingRecordIndex).compareTo(o.getTrainingRecordIndex());
    }
}
