import net.sourceforge.openforecast.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mandark on 29-04-2016.
 */
public class DemandTrendFinder {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        DataSet observedData = new DataSet();
        DataPoint dp;
        File file = new File("D:\\prices.csv");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine = null;
        int i = 0;
        List<Double> timeLags= new ArrayList<Double>();
        while ((currentLine = reader.readLine()) != null) {
            String[] priceAndQuantity = currentLine.split(",");
            dp = new Observation(Double.parseDouble(priceAndQuantity[2]));
            dp.setIndependentValue("p", Double.parseDouble(priceAndQuantity[0]));
            timeLags.add(Double.parseDouble(priceAndQuantity[4]));
            dp.setIndependentValue("t", Double.parseDouble(priceAndQuantity[4]));
            //dp.setIndependentValue("t",i+1);

            observedData.add(dp);
            i++;
        }
        reader.close();

        double newLags[] = findTimeLagTimeSeries(timeLags.stream().mapToDouble(Double::doubleValue).toArray());
        observedData.setPeriodsPerYear(12);

        ForecastingModel forecaster
                = Forecaster.getBestForecast(observedData);
        DataSet fcValues = new DataSet();

        for (int k = 0; k < 10; k++) {
            dp = new Observation(0.0);
            dp.setIndependentValue("t",newLags[k]);
            fcValues.add(dp);
        }
        // Get forecast values
        //forecaster.init(observedData);
        DataSet results = forecaster.forecast(fcValues);

        Iterator<DataPoint> it = results.iterator();
        while (it.hasNext()) {
            // Check that the results are within specified tolerance
            //  of the expected values
            DataPoint fc = (DataPoint) it.next();
            double fcValue = fc.getDependentValue();
            double time = fc.getIndependentValue("t");
            System.out.println("for t:" + time + "Result : " + fcValue);
        }
    }

    private static double[] findTimeLagTimeSeries(double[] timeLags) {
        DataSet observedData = new DataSet();
        DataPoint dp;
        int i=0;
        for (;i < timeLags.length; i++) {
            dp = new Observation(timeLags[i]);
            dp.setIndependentValue("t", i+1);
            observedData.add(dp);
        }
        DataSet fcValues = new DataSet();

        //for (double q = timeLags[timeLags.length-1]; q < timeLags[timeLags.length-1]+20; q++) {
        for(double q=i+1;q <i+20;q++){
            dp = new Observation(0.0);
            dp.setIndependentValue("t", q);
            fcValues.add(dp);
        }

        ForecastingModel forecaster
                = Forecaster.getBestForecast(observedData);
        DataSet results = forecaster.forecast(fcValues);
        Iterator<DataPoint> it = results.iterator();
        double[] futureLags= new double[results.size()];
        int k=0;
        while (it.hasNext()) {
            // Check that the results are within specified tolerance
            //  of the expected values
            DataPoint fc = (DataPoint) it.next();
            double fcValue = fc.getDependentValue();
            futureLags[k]=fcValue;
            double time = fc.getIndependentValue("t");
            System.out.println("timelags for t:" + time + "Result : " + fcValue);
            k++;
        }
        System.out.println("**************************************");
        return futureLags;
    }
}

