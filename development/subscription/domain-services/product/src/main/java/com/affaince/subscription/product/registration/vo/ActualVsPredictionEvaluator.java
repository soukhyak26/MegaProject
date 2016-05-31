package com.affaince.subscription.product.registration.vo;

/**
 * Created by mandar on 29-05-2016.
 */
public class ActualVsPredictionEvaluator {
    private double actualValue;
    private double predictedValue1;
    private double predictedValue2;
    private double predictedValue3;
    private double deviation1;
    private double deviation2;
    private double deviation3;

    public ActualVsPredictionEvaluator(double actualValue) {
        this.actualValue = actualValue;
    }

    public double getPredictedValue1() {
        return predictedValue1;
    }

    public void setPredictedValue1(double predictedValue1) {
        this.predictedValue1 = predictedValue1;
        this.deviation1=actualValue-predictedValue1;
    }

    public double getPredictedValue2() {
        return predictedValue2;
    }

    public void setPredictedValue2(double predictedValue2) {
        this.predictedValue2 = predictedValue2;
        this.deviation2=actualValue-predictedValue2;
    }

    public double getPredictedValue3() {
        return predictedValue3;
    }

    public void setPredicatedValue3(double predicatedValue3) {
        this.predictedValue3 = predicatedValue3;
        this.deviation3=actualValue-predicatedValue3;
    }

    public void addPredictedValue(double predictedValue){
        if(predictedValue1==0){
            predictedValue1=predictedValue;
        }else if(predictedValue2==0){
            predictedValue2=predictedValue;
        }else {
            predictedValue3=predictedValue;
        }
    }
    public double getPrecisePrediction(){
        double precisePrediction=0;
        if(deviation1<deviation2){
            if(deviation1<deviation3){
                precisePrediction=predictedValue1;
            }else{
                precisePrediction=predictedValue3;
            }
        }else{
            if(deviation2<deviation3){
                precisePrediction=predictedValue2;
            }else{
                precisePrediction=predictedValue3;
            }
        }
        return precisePrediction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActualVsPredictionEvaluator that = (ActualVsPredictionEvaluator) o;

        return Double.compare(that.actualValue, actualValue) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(actualValue);
        return (int) (temp ^ (temp >>> 32));
    }
}
