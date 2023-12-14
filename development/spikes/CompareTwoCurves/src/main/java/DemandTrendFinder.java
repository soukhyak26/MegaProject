import net.sourceforge.openforecast.*;

import java.io.*;
import java.util.*;

/**
 * Created by mandark on 29-04-2016.
 */
public class DemandTrendFinder {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        DemandTrendFinder finder = new DemandTrendFinder();
        Map<Integer,Long> forecastedData =finder.readFile("data1.csv");
        Map<Integer,Long> actualData =finder.readFile("data2.csv");
        System.out.println("Mean Absolute Error : " + finder.calculateMeanAbsoluteError(forecastedData,actualData));
        System.out.println("Mean Squared Error : " + finder.calculateMeanSquaredError(forecastedData,actualData));
        System.out.println("Root Mean Squared Error : " + finder.calculateRootMeanSquaredError(forecastedData,actualData));
        System.out.println("Percentage Error : " + finder.calculatePercentageError(forecastedData,actualData));
    }

    private Map<Integer,Long> readFile(String filePath){
        InputStream is = this.getClass().getResourceAsStream(filePath);
        InputStreamReader isr = new InputStreamReader(is);
        Map<Integer,Long> dayWiseData = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(isr);
            String currentLine = null;
            int i = 0;

            while ((currentLine = reader.readLine()) != null) {
                String[] dayAndValue = currentLine.split(",");
                int day = Integer.parseInt(dayAndValue[0]);
                long value=Long.parseLong(dayAndValue[1]);
                dayWiseData.put(day,value);
            }
            reader.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return dayWiseData;
    }

    private long calculateMeanAbsoluteError(Map<Integer,Long> forecastedData, Map<Integer,Long> actualData){
        if(forecastedData.size() != actualData.size()){
            throw new IllegalArgumentException("Collections must be of the same size");
        }
        long sum=0;

        for(int key : forecastedData.keySet()){
            long forecastValue = forecastedData.get(key);
            long actualValue = actualData.get(key);
            sum += Math.abs(actualValue - forecastValue);
        }
        return sum / actualData.values().size();
    }

    private long calculateMeanSquaredError(Map<Integer,Long> forecastedData, Map<Integer,Long> actualData){
        if(forecastedData.size() != actualData.size()){
            throw new IllegalArgumentException("Collections must be of the same size");
        }
        long sum=0;

        for(int key : forecastedData.keySet()){
            long forecastValue = forecastedData.get(key);
            long actualValue = actualData.get(key);
            sum += Math.pow(Math.abs(actualValue - forecastValue),2);
        }
        return sum / actualData.values().size();
    }

    private long calculateRootMeanSquaredError(Map<Integer,Long> forecastedData, Map<Integer,Long> actualData){
        if(forecastedData.size() != actualData.size()){
            throw new IllegalArgumentException("Collections must be of the same size");
        }
        long sum=0;

        for(int key : forecastedData.keySet()){
            long forecastValue = forecastedData.get(key);
            long actualValue = actualData.get(key);
            sum += Math.pow(Math.abs(actualValue - forecastValue),2);
        }
        return Double.valueOf(Math.sqrt(sum / actualData.values().size())).longValue();
    }

    private long calculatePercentageError(Map<Integer,Long> forecastedData, Map<Integer,Long> actualData){
        if(forecastedData.size() != actualData.size()){
            throw new IllegalArgumentException("Collections must be of the same size");
        }
        long sum=0;

        for(int key : forecastedData.keySet()){
            long forecastValue = forecastedData.get(key);
            long actualValue = actualData.get(key);
            sum += ((actualValue - forecastValue)/actualValue) * 100;
        }
        return Double.valueOf(Math.sqrt(sum / actualData.values().size())).longValue();
    }

}

