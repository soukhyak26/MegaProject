package com.affaince.subscription.subscriber.command;

import com.affaince.subscription.common.vo.EntityMetricType;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/29/2017.
 */
public class DeriveSubscriptionsInAllCostRangesFromActualsCommand {
    private Integer subscriptionAnalyserId;
    private EntityMetricType entityMetricType;
    private LocalDate forecastDate;

    public DeriveSubscriptionsInAllCostRangesFromActualsCommand(Integer subscriptionAnalyserId, EntityMetricType entityMetricType, LocalDate forecastDate) {
        this.subscriptionAnalyserId = subscriptionAnalyserId;
        this.entityMetricType = entityMetricType;
        this.forecastDate = forecastDate;
    }

    public Integer getSubscriptionAnalyserId() {
        return subscriptionAnalyserId;
    }
    public EntityMetricType getEntityMetricType() {
        return entityMetricType;
    }
    public LocalDate getForecastDate() {
        return forecastDate;
    }

}