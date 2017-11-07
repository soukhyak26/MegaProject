package com.affaince.subscription.subscriber.query.predictions;

import com.affaince.subscription.common.service.interpolate.InterpolatorChain;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.subscriber.query.repository.SubscriberPseudoActualsViewRepository;
import com.affaince.subscription.subscriber.query.repository.SubscribersForecastViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriberPseudoActualsView;
import com.affaince.subscription.subscriber.query.view.SubscribersForecastView;
import com.affaince.subscription.subscriber.vo.SubscriberForecastParameter;
import com.affaince.subscription.subscriber.web.exception.SubscriberForecastAlreadyExistsException;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by mandar on 10/28/2017.
 */
public class ManualSubscriberForecastHelper {
    private final SubscribersForecastViewRepository subscribersForecastViewRepository;
    private final SubscriberPseudoActualsViewRepository subscriberPseudoActualsViewRepository;
    private final InterpolatorChain interpolatorChain;
    private static final int INTERPOLATE_NEW_SUBSCRIPTIONS = 1;
    private static final int INTERPOLATE_TOTAL_SUBSCRIPTIONS = 2;

    @Autowired
    public ManualSubscriberForecastHelper(SubscribersForecastViewRepository subscribersForecastViewRepository,SubscriberPseudoActualsViewRepository subscriberPseudoActualsViewRepository,InterpolatorChain interpolatorChain) {
        this.subscribersForecastViewRepository = subscribersForecastViewRepository;
        this.subscriberPseudoActualsViewRepository=subscriberPseudoActualsViewRepository;
        this.interpolatorChain= interpolatorChain;
    }

    public void addManualForecast(SubscriberForecastParameter[] subscriberForecastParameters, LocalDate forecastDate) throws SubscriberForecastAlreadyExistsException {
            LocalDate firstStartDate = null;
            LocalDate lastEndDate = null;Sort endDateSort = new Sort(Sort.Direction.DESC, "endDate");

            long totalSubscribers = 0;
            for (SubscriberForecastParameter parameter : subscriberForecastParameters) {
                List<SubscribersForecastView> existingForecastViews = this.subscribersForecastViewRepository.findByEndDateBetween(parameter.getStartDate(), parameter.getEndDate());
                //forecast should not be newly added if it already exists in the view
                if (null != existingForecastViews && existingForecastViews.size() > 0) {
                    throw SubscriberForecastAlreadyExistsException.build(parameter.getStartDate(), parameter.getEndDate());
                }
                //find forecasts entered earlier to current forecast entry
                List<SubscribersForecastView> earlierForecastViews = this.subscribersForecastViewRepository.findByEndDateLessThan(parameter.getEndDate(), endDateSort);
                if (earlierForecastViews.isEmpty()) {
                    totalSubscribers = parameter.getNumberOfNewSubscribers() - parameter.getNumberOfChurnedSubscribers();
                } else {
                    totalSubscribers = earlierForecastViews.get(0).getTotalSubscribers() + parameter.getNumberOfNewSubscribers() - parameter.getNumberOfChurnedSubscribers();
                }
                SubscribersForecastView subscribersForecastView = new SubscribersForecastView(parameter.getStartDate(), parameter.getEndDate(), parameter.getNumberOfNewSubscribers(), parameter.getNumberOfChurnedSubscribers(), totalSubscribers,forecastDate);
                this.subscribersForecastViewRepository.save(subscribersForecastView);
                if (null == firstStartDate) {
                    firstStartDate = parameter.getStartDate();
                }
                lastEndDate = parameter.getEndDate();
            }
            derivePseudoActualsFromForecast(firstStartDate,forecastDate);
    }

    private double[] interpolateStepForecastFromForecast(int whomToInterpolate) {
        List<SubscribersForecastView> registeredForecastValues = subscribersForecastViewRepository.
                findByForecastContentStatusOrderBySubscriberVersionId_StartDateAsc
                        (ForecastContentStatus.ACTIVE);
        SubscribersForecastView firstForecastView = registeredForecastValues.get(0);
        LocalDate dateOfPlatformBeginning = firstForecastView.getSubscriberVersionId().getStartDate();
        double[] x = new double[registeredForecastValues.size()];     //day on which interpolated value has been taken
        double[] y = new double[registeredForecastValues.size()];     //interpolated value of total subscription
        int count = 0;
        for (SubscribersForecastView previousView : registeredForecastValues) {
            LocalDate endDate = previousView.getEndDate();
            int day = Days.daysBetween(dateOfPlatformBeginning, endDate).getDays(); //TODO- should we add/subtract 1 in the value?
            x[count] = day;
            if (whomToInterpolate == INTERPOLATE_TOTAL_SUBSCRIPTIONS) {
                y[count] = previousView.getTotalSubscribers();
            } else if (whomToInterpolate == INTERPOLATE_NEW_SUBSCRIPTIONS) {
                y[count] = previousView.getNewSubscribers();
            }
            count++;
        }
        double[] interpolatedSubscriptionsPerDay = interpolatorChain.interpolate(x, y);
        return interpolatedSubscriptionsPerDay;
    }

    private void derivePseudoActualsFromForecast(LocalDate firstStartDate,LocalDate forecastDate){
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
            SubscriberPseudoActualsView productPseudoActualsView = new SubscriberPseudoActualsView(firstStartDate.plusDays(i), Double.valueOf(interpolatedNewSubscriptionCount).longValue(), Double.valueOf(dailychurnedSubscriptionCount).longValue(), Double.valueOf(interpolatedTotalSubscriptionCount).longValue(),forecastDate);
            subscriberPseudoActualsViewRepository.save(productPseudoActualsView);
        }
    }

}
