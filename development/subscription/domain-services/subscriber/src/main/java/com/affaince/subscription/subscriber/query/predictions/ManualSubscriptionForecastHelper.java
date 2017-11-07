package com.affaince.subscription.subscriber.query.predictions;

import com.affaince.subscription.common.service.interpolate.InterpolatorChain;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.subscriber.query.repository.SubscriptionForecastViewRepository;
import com.affaince.subscription.subscriber.query.repository.SubscriptionPseudoActualsViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriberPseudoActualsView;
import com.affaince.subscription.subscriber.query.view.SubscribersForecastView;
import com.affaince.subscription.subscriber.query.view.SubscriptionForecastView;
import com.affaince.subscription.subscriber.query.view.SubscriptionPseudoActualsView;
import com.affaince.subscription.subscriber.vo.SubscriptionForecastParameter;
import com.affaince.subscription.subscriber.web.exception.SubscriberForecastAlreadyExistsException;
import com.affaince.subscription.subscriber.web.exception.SubscriptionForecastAlreadyExistsException;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by mandar on 10/28/2017.
 */
public class ManualSubscriptionForecastHelper {
    private final SubscriptionForecastViewRepository subscriptionForecastViewRepository;
    private final SubscriptionPseudoActualsViewRepository subscriptionPseudoActualsViewRepository;
    private final InterpolatorChain interpolatorChain;
    private static final int INTERPOLATE_NEW_SUBSCRIPTIONS = 1;
    private static final int INTERPOLATE_TOTAL_SUBSCRIPTIONS = 2;

    @Autowired
    public ManualSubscriptionForecastHelper(SubscriptionForecastViewRepository subscriptionForecastViewRepository, SubscriptionPseudoActualsViewRepository subscriptionPseudoActualsViewRepository, InterpolatorChain interpolatorChain) {
        this.subscriptionForecastViewRepository = subscriptionForecastViewRepository;
        this.subscriptionPseudoActualsViewRepository=subscriptionPseudoActualsViewRepository;
        this.interpolatorChain= interpolatorChain;
    }

    public void addManualForecast(SubscriptionForecastParameter[] subscriptionForecastParameters, LocalDate forecastDate) throws SubscriptionForecastAlreadyExistsException {
            LocalDate firstStartDate = null;
            LocalDate lastEndDate = null;Sort endDateSort = new Sort(Sort.Direction.DESC, "endDate");

            long totalSubscriptions = 0;
            final double valueRangeMin= subscriptionForecastParameters[0].getValueRangeMin();
            final double valueRangeMax= subscriptionForecastParameters[0].getValueRangeMax();
            for (SubscriptionForecastParameter parameter : subscriptionForecastParameters) {
                List<SubscriptionForecastView> existingForecastViews = this.subscriptionForecastViewRepository.findByEndDateBetween(parameter.getStartDate(), parameter.getEndDate());
                //forecast should not be newly added if it already exists in the view
                if (null != existingForecastViews && existingForecastViews.size() > 0) {
                    throw SubscriptionForecastAlreadyExistsException.build(parameter.getStartDate(), parameter.getEndDate());
                }
                //find forecasts entered earlier to current forecast entry
                List<SubscriptionForecastView> earlierForecastViews = this.subscriptionForecastViewRepository.findByEndDateLessThan(parameter.getEndDate(), endDateSort);
                if (earlierForecastViews.isEmpty()) {
                    totalSubscriptions = parameter.getNumberOfNewSubscriptions() - parameter.getNumberOfChurnedSubscriptions();
                } else {
                    totalSubscriptions = earlierForecastViews.get(0).getTotalSubscriptions() + parameter.getNumberOfNewSubscriptions() - parameter.getNumberOfChurnedSubscriptions();
                }
                SubscriptionForecastView subscriptionForecastView = new SubscriptionForecastView(parameter.getStartDate(), parameter.getEndDate(), parameter.getNumberOfNewSubscriptions(), parameter.getNumberOfChurnedSubscriptions(), totalSubscriptions,forecastDate,parameter.getValueRangeMin(),parameter.getValueRangeMax());
                this.subscriptionForecastViewRepository.save(subscriptionForecastView);
                if (null == firstStartDate) {
                    firstStartDate = parameter.getStartDate();
                }
                lastEndDate = parameter.getEndDate();
            }
            derivePseudoActualsFromForecast(firstStartDate,forecastDate,valueRangeMin,valueRangeMax);
    }

    private double[] interpolateStepForecastFromForecast(int whomToInterpolate) {
        List<SubscriptionForecastView> registeredForecastValues = subscriptionForecastViewRepository.
                findByForecastContentStatusOrderBySubscriptionVersionId_StartDateAsc
                        (ForecastContentStatus.ACTIVE);
        SubscriptionForecastView firstForecastView = registeredForecastValues.get(0);
        LocalDate dateOfPlatformBeginning = firstForecastView.getSubscriptionVersionId().getStartDate();
        double[] x = new double[registeredForecastValues.size()];     //day on which interpolated value has been taken
        double[] y = new double[registeredForecastValues.size()];     //interpolated value of total subscription
        int count = 0;
        for (SubscriptionForecastView previousView : registeredForecastValues) {
            LocalDate endDate = previousView.getEndDate();
            int day = Days.daysBetween(dateOfPlatformBeginning, endDate).getDays(); //TODO- should we add/subtract 1 in the value?
            x[count] = day;
            if (whomToInterpolate == INTERPOLATE_TOTAL_SUBSCRIPTIONS) {
                y[count] = previousView.getTotalSubscriptions();
            } else if (whomToInterpolate == INTERPOLATE_NEW_SUBSCRIPTIONS) {
                y[count] = previousView.getNewSubscriptions();
            }
            count++;
        }
        double[] interpolatedSubscriptionsPerDay = interpolatorChain.interpolate(x, y);
        return interpolatedSubscriptionsPerDay;
    }

    private void derivePseudoActualsFromForecast(LocalDate firstStartDate,LocalDate forecastDate,double valueRangeMin, double valueRangeMax){
        //Now add PseudoActuals by interpolating manual forecastAdded
        double[] interpolatedPseudoActualsTotalSubscriptions = interpolateStepForecastFromForecast(INTERPOLATE_TOTAL_SUBSCRIPTIONS);
        double[] interpolatedPseudoActualsNewSubscriptions = interpolateStepForecastFromForecast(INTERPOLATE_NEW_SUBSCRIPTIONS);
        double previousDayTotalSubcriptionCount = 0;
        double dailychurnedSubscriptionCount = 0;
        for (int i = 0; i < interpolatedPseudoActualsTotalSubscriptions.length; i++) {
            double interpolatedTotalSubscriptionCount = interpolatedPseudoActualsTotalSubscriptions[i];
            double interpolatedNewSubscriptionCount = interpolatedPseudoActualsNewSubscriptions[i];
            if (i == 0) {
                dailychurnedSubscriptionCount = interpolatedTotalSubscriptionCount - interpolatedNewSubscriptionCount;
            } else {
                dailychurnedSubscriptionCount = previousDayTotalSubcriptionCount + interpolatedNewSubscriptionCount - interpolatedTotalSubscriptionCount;
            }
            previousDayTotalSubcriptionCount=interpolatedTotalSubscriptionCount;
            SubscriptionPseudoActualsView subscriptionPseudoActualsView = new SubscriptionPseudoActualsView(firstStartDate.plusDays(i), Double.valueOf(interpolatedNewSubscriptionCount).longValue(), Double.valueOf(dailychurnedSubscriptionCount).longValue(), Double.valueOf(interpolatedTotalSubscriptionCount).longValue(),forecastDate,valueRangeMin, valueRangeMax);
            subscriptionPseudoActualsViewRepository.save(subscriptionPseudoActualsView);
        }
    }

}
