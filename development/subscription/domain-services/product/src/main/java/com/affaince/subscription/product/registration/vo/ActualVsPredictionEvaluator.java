        package com.affaince.subscription.product.registration.vo;

        import java.util.TreeMap;

        /**
         * Created by mandar on 29-05-2016.
         */
        public class ActualVsPredictionEvaluator {
            private final String uniqueKey;
            private final double actualValue;
            private TreeMap<Double, Double> predictionsVsDeviations;

            public ActualVsPredictionEvaluator(String uniqueKey, double actualValue) {
                this.uniqueKey = uniqueKey;
                this.actualValue = actualValue;
                predictionsVsDeviations = new TreeMap<>();
            }

            public void addPrediction(double predictedValue) {
                final double deviation = this.actualValue - predictedValue;
                predictionsVsDeviations.put(deviation, predictedValue);
            }

            public double findPrecisePrediction() {
                return predictionsVsDeviations.firstEntry().getValue();
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                ActualVsPredictionEvaluator that = (ActualVsPredictionEvaluator) o;

                if (Double.compare(that.actualValue, actualValue) != 0) return false;
                return uniqueKey.equals(that.uniqueKey);

            }

            @Override
            public int hashCode() {
                int result;
                long temp;
                result = uniqueKey.hashCode();
                temp = Double.doubleToLongBits(actualValue);
                result = 31 * result + (int) (temp ^ (temp >>> 32));
                return result;
            }

            public String getUniqueKey() {
                return uniqueKey;
            }

            public double getActualValue() {
                return actualValue;
            }

            public TreeMap<Double, Double> getPredictionsVsDeviations() {
                return predictionsVsDeviations;
            }
        }
