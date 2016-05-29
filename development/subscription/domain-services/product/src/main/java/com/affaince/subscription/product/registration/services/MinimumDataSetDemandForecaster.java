package com.affaince.subscription.product.registration.services;

import com.affaince.subscription.product.registration.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.registration.query.view.ProductForecastMetricsView;
import net.sourceforge.openforecast.DataPoint;
import net.sourceforge.openforecast.DataSet;
import net.sourceforge.openforecast.ForecastingModel;
import net.sourceforge.openforecast.Observation;
import net.sourceforge.openforecast.models.DoubleExponentialSmoothingModel;
import org.joda.time.DateTimeComparator;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mandark on 01-05-2016.
 */
public class MinimumDataSetDemandForecaster implements ProductDemandForecaster {

    private ProductDemandForecaster productDemandForecaster;

    @Autowired
    public MinimumDataSetDemandForecaster() {
    }

    public void addNextForecaster(ProductDemandForecaster forecaster) {
        this.productDemandForecaster = forecaster;
    }

    public List<ProductForecastMetricsView> forecastDemandGrowthAndChurn(List<ProductActualMetricsView> productActualMetricsViewList) {
        if (productActualMetricsViewList.size() >= 3 && productActualMetricsViewList.size() <= 6) {
            Collections.sort(productActualMetricsViewList, DateTimeComparator.getDateOnlyInstance());
            DataSet observedData = new DataSet();
            LocalDate startDate = productActualMetricsViewList.get(0).getFromDate();

            int i = 0;
            for (ProductActualMetricsView productActualMetricsView : productActualMetricsViewList) {
                double totalSubscriptionCount = productActualMetricsView.getTotalNumberOfExistingCustomers();
                int duration = Days.daysBetween(startDate, productActualMetricsView.getFromDate()).getDays();
                DataPoint dp = new Observation(totalSubscriptionCount);
                dp.setIndependentValue("time", duration);
                i++;
            }
            observedData.setPeriodsPerYear(12);
            DataSet fcValues = new DataSet();

            for (int k = i + 1; k < i + i; k++) {
                DataPoint dp = new Observation(0.0);
                dp.setIndependentValue("t", k);
                fcValues.add(dp);
            }
            // Get forecast values
            //forecaster.init(observedData);
            //MovingAverageModel forecaster= new MovingAverageModel(3);
            ForecastingModel forecaster = new DoubleExponentialSmoothingModel(0.2, 0.3);
            observedData.setTimeVariable("t");
            forecaster.init(observedData);

            DataSet results = forecaster.forecast(fcValues);
            Iterator<DataPoint> it = results.iterator();
            List<ProductForecastMetricsView> newForecasts= new ArrayList<ProductForecastMetricsView>();
            while (it.hasNext()) {
                // Check that the results are within specified tolerance
                //  of the expected values
                DataPoint fc = (DataPoint) it.next();
                double newSubscriptionCount = fc.getDependentValue();
                double time = fc.getIndependentValue("t");
                //ProductForecastMetricsView forecastView= new ProductForecastMetricsView();
            }


        } else {
            if (null != productDemandForecaster) {
                return productDemandForecaster.forecastDemandGrowthAndChurn(productActualMetricsViewList);
            }
        }
        return null;
    }

}
