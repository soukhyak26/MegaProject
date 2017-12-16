package com.affaince.subscription.common.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mandark on 21-02-2016.
 */
public class TrainingRecord {
    private int trainingRecordIndex;
    private List<TrainingRecordElement> recordElements;


    public TrainingRecord(int trainingRecordIndex) {
        this.trainingRecordIndex = trainingRecordIndex;
        this.recordElements= new ArrayList<TrainingRecordElement>();
    }

    public void addRecorElement(TrainingRecordElement element){
        recordElements.add(element);
    }

    public int getTrainingRecordIndex() {
        return this.trainingRecordIndex;
    }

    public List<TrainingRecordElement> getRecordElements() {
        return this.recordElements;
    }

    public List<TrainingRecordElement> getRecordElementsByAscendingIndex() {
         Collections.sort(recordElements);
        return recordElements;
    }
    public TrainingRecordElement findOutputRecordElement(){
        for(TrainingRecordElement element: recordElements){
            if(element.isOutput()){
                return element;
            }
        }
        return null;
    }
}
