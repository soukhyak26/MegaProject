import net.sourceforge.openforecast.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mandark on 29-04-2016.
 */
public class DemandTrendFinder2 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        DataSet observedData = new DataSet();
        File file = new File("D:\\prices.csv");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine = null;
        int i = 0;
        List<Double> prices= new ArrayList<Double>();
        while ((currentLine = reader.readLine()) != null) {
            String[] priceAndQuantity = currentLine.split(",");
            DataPoint dp = new Observation(Double.parseDouble(priceAndQuantity[2]));
            prices.add(Double.parseDouble(priceAndQuantity[0]));
            dp.setIndependentValue("p", Double.parseDouble(priceAndQuantity[0]));
            //timeLags.add(Double.parseDouble(priceAndQuantity[4]));
            //dp.setIndependentValue("t", Double.parseDouble(priceAndQuantity[4]));
            observedData.add(dp);
            i++;
        }
        reader.close();

        //double newLags[] = findTimeLagTimeSeries(timeLags.stream().mapToDouble(Double::doubleValue).toArray());
        double [] newPrices=findPriceTimeSeries(prices.stream().mapToDouble(Double::doubleValue).toArray());
        observedData.setPeriodsPerYear(12);

        ForecastingModel forecaster
                = Forecaster.getBestForecast(observedData);
        DataSet fcValues = new DataSet();

        for (int k = 0; k < 10; k++) {
            DataPoint dp = new Observation(0.0);
            dp.setIndependentValue("p", newPrices[k]);
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
            double price = fc.getIndependentValue("p");
            System.out.println("for p:" + price + "Result : " + fcValue);
        }
    }

    private static double[] findPriceTimeSeries(double[] prices) {
        DataSet observedData = new DataSet();
        int i=0;
        for (;i < prices.length; i++) {
            DataPoint dp = new Observation(prices[i]);
            dp.setIndependentValue("t", i+1);
            observedData.add(dp);
        }
        DataSet fcValues = new DataSet();
        ForecastingModel forecaster
                = Forecaster.getBestForecast(observedData);

        //for (double q = timeLags[timeLags.length-1]; q < timeLags[timeLags.length-1]+20; q++) {
        for(double q=i;q <i+20;q++){
            DataPoint dp = new Observation(0.0);
            dp.setIndependentValue("t", q);
            fcValues.add(dp);
        }

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

/*
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
        for(double q=i+(timeLags[timeLags.length-1]-timeLags[timeLags.length-2]);q <i+20;q++){
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
*/
}

