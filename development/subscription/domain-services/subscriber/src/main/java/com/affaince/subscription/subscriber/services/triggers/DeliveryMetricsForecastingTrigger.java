package com.affaince.subscription.subscriber.services.triggers;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.subscriber.query.repository.DeliveryActualsViewRepository;
import com.affaince.subscription.subscriber.query.repository.DeliveryPseudoActualsViewRepository;
import com.affaince.subscription.subscriber.query.repository.DeliveryViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryActualsView;
import com.affaince.subscription.subscriber.query.view.DeliveryPseudoActualsView;
import com.affaince.subscription.subscriber.query.view.DeliveryView;
import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.*;

/**
 * Created by mandar on 11/4/2017.
 */
public class DeliveryMetricsForecastingTrigger {
    @Autowired
    DeliveryActualsViewRepository deliveryActualsViewRepository;
    @Autowired
    DeliveryPseudoActualsViewRepository deliveryPseudoActualsViewRepository;

    public boolean shouldTriggerDeliveryMetricsForecast(double minWeight,double maxWeight) {
        final List<DeliveryActualsView> deliveryActualsViewsInGivenWeightRange = deliveryActualsViewRepository.findByDeliveryActualsVersionId_WeightRangeMinAndDeliveryActualsVersionId_weightRangeMaxDeliveryActualsVersionId_DeliveryDateDesc(minWeight,maxWeight);
        final Map<YearMonth,Long> monthwiseActualsDeliveryCounts=aggregateMonthwiseActualsDeliveryCount(deliveryActualsViewsInGivenWeightRange);
        List<DeliveryPseudoActualsView> deliveryPseudoActualsViews = deliveryPseudoActualsViewRepository.findByForecastContentStatusAndDeliveryForecastVersionId_WeightRangeMinAndDeliveryForecastVersionId_weightRangeMaxDeliveryForecastVersionId_DeliveryDateDesc(ForecastContentStatus.ACTIVE,minWeight,maxWeight);
        final Map<YearMonth,Long> monthwisePseudoActualsDeliveryCounts=aggregateMonthwisePseudoActualsDeliveryCount(deliveryPseudoActualsViews);
        long totalActualsMonthlyDeliveryCount=monthwiseActualsDeliveryCounts.get(0).longValue();
        long totalPseudoActualMonthlyDeliveryCount = monthwisePseudoActualsDeliveryCounts.get(0).longValue();

        final double changeOfActualDemandAgainstForecast = (totalActualsMonthlyDeliveryCount - totalPseudoActualMonthlyDeliveryCount) / totalPseudoActualMonthlyDeliveryCount;
        final double changeThresholdForPriceChange = 0.1;

        if (totalActualsMonthlyDeliveryCount > totalPseudoActualMonthlyDeliveryCount && Math.abs(changeOfActualDemandAgainstForecast) > changeThresholdForPriceChange) {
            return true;
        }else {
            return false;
        }
    }

    private Map<YearMonth,Long> aggregateMonthwiseActualsDeliveryCount(List<DeliveryActualsView> deliveryActualsViewsInGivenWeightRange){
        Map<YearMonth,Long> monthwiseDeliveryCount=new TreeMap<>(new Comparator<YearMonth>() {
            public int compare(YearMonth date1, YearMonth date2) {
                return date2.compareTo(date1);
            }
        });
        for( DeliveryActualsView delivery: deliveryActualsViewsInGivenWeightRange){
            YearMonth month = new YearMonth(delivery.getDeliveryActualsVersionId().getDeliveryDate().getMonthOfYear(),delivery.getDeliveryActualsVersionId().getDeliveryDate().getYear());
            if(null==monthwiseDeliveryCount.get(month)){
                monthwiseDeliveryCount.put(month,delivery.getDeliveryCount());
            }else{
                monthwiseDeliveryCount.put(month,monthwiseDeliveryCount.get(month)+ delivery.getDeliveryCount());
            }
        }
        return monthwiseDeliveryCount;
    }

    private Map<YearMonth,Long> aggregateMonthwisePseudoActualsDeliveryCount(List<DeliveryPseudoActualsView> deliveryPseudoActualsViewsInGivenWeightRange){
        Map<YearMonth,Long> monthwiseDeliveryCount=new TreeMap<>(new Comparator<YearMonth>() {
            public int compare(YearMonth date1, YearMonth date2) {
                return date2.compareTo(date1);
            }
        });
        for( DeliveryPseudoActualsView delivery: deliveryPseudoActualsViewsInGivenWeightRange){
            YearMonth month = new YearMonth(delivery.getDeliveryForecastVersionId().getDeliveryDate().getMonthOfYear(),delivery.getDeliveryForecastVersionId().getDeliveryDate().getYear());
            if(null==monthwiseDeliveryCount.get(month)){
                monthwiseDeliveryCount.put(month,delivery.getDeliveryCount());
            }else{
                monthwiseDeliveryCount.put(month,monthwiseDeliveryCount.get(month)+ delivery.getDeliveryCount());
            }
        }
        return monthwiseDeliveryCount;
    }

}
