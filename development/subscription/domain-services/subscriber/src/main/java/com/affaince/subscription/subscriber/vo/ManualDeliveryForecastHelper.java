package com.affaince.subscription.subscriber.vo;

import com.affaince.subscription.common.service.interpolate.InterpolatorChain;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.subscriber.query.repository.DeliveryForecastViewRepository;
import com.affaince.subscription.subscriber.query.repository.DeliveryPseudoActualsViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryForecastView;
import com.affaince.subscription.subscriber.query.view.DeliveryPseudoActualsView;
import com.affaince.subscription.subscriber.web.exception.DeliveryForecastAlreadyExistsException;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by mandar on 10/28/2017.
 */
public class ManualDeliveryForecastHelper {
    private final DeliveryForecastViewRepository deliveryForecastViewRepository;
    private final DeliveryPseudoActualsViewRepository deliveryPseudoActualsViewRepository;
    private final InterpolatorChain interpolatorChain;
    private static final int INTERPOLATE_DELIVERY_COUNT = 1;

    @Autowired
    public ManualDeliveryForecastHelper(DeliveryForecastViewRepository deliveryForecastViewRepository, DeliveryPseudoActualsViewRepository deliveryPseudoActualsViewRepository, InterpolatorChain interpolatorChain) {
        this.deliveryForecastViewRepository = deliveryForecastViewRepository;
        this.deliveryPseudoActualsViewRepository=deliveryPseudoActualsViewRepository;
        this.interpolatorChain= interpolatorChain;
    }

    public void addManualForecast(DeliveryForecastParameter[] deliveryForecastParameters, LocalDate forecastDate) throws DeliveryForecastAlreadyExistsException {
            LocalDate firstStartDate = null;
            LocalDate lastEndDate = null;Sort endDateSort = new Sort(Sort.Direction.DESC, "endDate");

            long totaldeliverys = 0;
            final double valueRangeMin= deliveryForecastParameters[0].getValueRangeMin();
            final double valueRangeMax= deliveryForecastParameters[0].getValueRangeMax();
            for (DeliveryForecastParameter parameter : deliveryForecastParameters) {
                List<DeliveryForecastView> existingForecastViews = this.deliveryForecastViewRepository.findByEndDateBetween(parameter.getStartDate(), parameter.getEndDate());
                //forecast should not be newly added if it already exists in the view
                if (null != existingForecastViews && existingForecastViews.size() > 0) {
                    throw DeliveryForecastAlreadyExistsException.build(parameter.getStartDate(), parameter.getEndDate());
                }
                //find forecasts entered earlier to current forecast entry
                DeliveryForecastView deliveryForecastView = new DeliveryForecastView(parameter.getStartDate(), parameter.getEndDate(),forecastDate, parameter.getDeliveryCount(),parameter.getValueRangeMin(),parameter.getValueRangeMax(),ForecastContentStatus.ACTIVE);
                this.deliveryForecastViewRepository.save(deliveryForecastView);
                if (null == firstStartDate) {
                    firstStartDate = parameter.getStartDate();
                }
                lastEndDate = parameter.getEndDate();
            }
            derivePseudoActualsFromForecast(firstStartDate,forecastDate,valueRangeMin,valueRangeMax);
    }

    private double[] interpolateStepForecastFromForecast(int whomToInterpolate) {
        List<DeliveryForecastView> registeredForecastValues = deliveryForecastViewRepository.
                findByForecastContentStatusOrderBydeliveryVersionId_StartDateAsc
                        (ForecastContentStatus.ACTIVE);
        DeliveryForecastView firstForecastView = registeredForecastValues.get(0);
        LocalDate dateOfPlatformBeginning = firstForecastView.getDeliveryVersionId().getStartDate();
        double[] x = new double[registeredForecastValues.size()];     //day on which interpolated value has been taken
        double[] y = new double[registeredForecastValues.size()];     //interpolated value of total delivery
        int count = 0;
        for (DeliveryForecastView previousView : registeredForecastValues) {
            LocalDate endDate = previousView.getEndDate();
            int day = Days.daysBetween(dateOfPlatformBeginning, endDate).getDays(); //TODO- should we add/subtract 1 in the value?
            x[count] = day;
            if (whomToInterpolate == INTERPOLATE_DELIVERY_COUNT) {
                y[count] = previousView.getDeliveryCount();
            }
            count++;
        }
        double[] interpolateddeliverysPerDay = interpolatorChain.interpolate(x, y);
        return interpolateddeliverysPerDay;
    }

    private void derivePseudoActualsFromForecast(LocalDate firstStartDate,LocalDate forecastDate,double valueRangeMin, double valueRangeMax){
        //Now add PseudoActuals by interpolating manual forecastAdded
        double[] interpolatedPseudoActualsTotalDeliverys = interpolateStepForecastFromForecast(INTERPOLATE_DELIVERY_COUNT);
        double previousDayTotalDeliveryCount = 0;
        for (int i = 0; i < interpolatedPseudoActualsTotalDeliverys.length; i++) {
            double interpolatedTotaldeliveryCount = interpolatedPseudoActualsTotalDeliverys[i];
            previousDayTotalDeliveryCount=interpolatedTotaldeliveryCount;
            DeliveryPseudoActualsView deliveryPseudoActualsView = new DeliveryPseudoActualsView(firstStartDate.plusDays(i),forecastDate, Double.valueOf(interpolatedTotaldeliveryCount).longValue(),valueRangeMin, valueRangeMax,ForecastContentStatus.ACTIVE);
            deliveryPseudoActualsViewRepository.save(deliveryPseudoActualsView);
        }
    }
}
