package com.affaince.subscription.product.registration.services;

import com.affaince.subscription.product.registration.query.view.ProductActualsView;
import com.affaince.subscription.product.registration.query.view.ProductForecastView;
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

    public List<ProductForecastView> forecastDemandGrowthAndChurn(List<ProductActualsView> productActualsViewList) {
        if (productActualsViewList.size() >= 3 && productActualsViewList.size() <= 6) {
            Collections.sort(productActualsViewList, DateTimeComparator.getDateOnlyInstance());
            DataSet observedData = new DataSet();
            LocalDate startDate = productActualsViewList.get(0).getFromDate();

            int i = 0;
            for (ProductActualsView productActualsView : productActualsViewList) {
                double totalSubscriptionCount = productActualsView.getTotalNumberOfExistingCustomers();
                int duration = Days.daysBetween(startDate, productActualsView.getFromDate()).getDays();
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
            List<ProductForecastView> newForecasts= new ArrayList<ProductForecastView>();
            while (it.hasNext()) {
                // Check that the results are within specified tolerance
                //  of the expected values
                DataPoint fc = (DataPoint) it.next();
                double newSubscriptionCount = fc.getDependentValue();
                double time = fc.getIndependentValue("t");
                //ProductForecastView forecastView= new ProductForecastView();
            }


        } else {
            if (null != productDemandForecaster) {
                return productDemandForecaster.forecastDemandGrowthAndChurn(productActualsViewList);
            }
        }
        return null;
    }

}
