package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.common.vo.EntityMetadata;
import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.common.vo.EntityType;
import com.affaince.subscription.subscriber.command.event.SubscriptionAnalyserCreatedEvent;
import com.affaince.subscription.subscriber.query.predictions.DeliveryHistoryRetriever;
import com.affaince.subscription.subscriber.query.predictions.SubscribersHistoryRetriever;
import com.affaince.subscription.subscriber.query.predictions.SubscriptionsHistoryRetriever;
import com.affaince.subscription.subscriber.query.view.DeliveryForecastTrendView;
import com.affaince.subscription.subscriber.query.view.SubscriberForecastTrendView;
import com.affaince.subscription.subscriber.query.view.SubscriptionForecastTrendView;
import com.affaince.subscription.subscriber.services.trend.DeliveryTrendChangeDetector;
import com.affaince.subscription.subscriber.services.trend.SubscriberTrendChangeDetector;
import com.affaince.subscription.subscriber.services.trend.SubscriptionTrendChangeDetector;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 10/1/2017.
 */
public class SubscriptionAnalyser extends AbstractAnnotatedAggregateRoot<Integer> {
    @AggregateIdentifier
    private Integer subscriptionAnalyserId;

    public SubscriptionAnalyser() {
    }

    public SubscriptionAnalyser(Integer subscriptionAnalyserId) {
        apply(new SubscriptionAnalyserCreatedEvent(subscriptionAnalyserId));
    }

    @EventSourcingHandler
    public void on(SubscriptionAnalyserCreatedEvent event){
        this.subscriptionAnalyserId=event.getSubscriptionAnalyserId();
    }
    public void initiateSubscriberForecast(EntityMetricType entityMetricType, SubscribersHistoryRetriever subscribersHistoryRetriever){
        Map<String,Object> metadata= new HashMap<>();
        metadata.put("ENTITY_TYPE", EntityType.SUBSCRIBER);
        metadata.put("ENTITY_METRIC_TYPE", entityMetricType);
        subscribersHistoryRetriever.marshallSendAndReceive(null,metadata);
    }

    public void initiateSubscriptionForecast(EntityMetricType entityMetricType, SubscriptionsHistoryRetriever subscriptionsHistoryRetriever){
        Map<String,Object> metadata= new HashMap<>();
        metadata.put("ENTITY_TYPE", EntityType.SUBSCRIPTION);
        metadata.put("ENTITY_METRIC_TYPE", entityMetricType);
        subscriptionsHistoryRetriever.marshallSendAndReceive(null,metadata);
    }


    public void initiateDeliveryForecast(EntityMetricType entityMetricType, DeliveryHistoryRetriever deliveryHistoryRetriever){
        Map<String,Object> metadata= new HashMap<>();
        metadata.put("ENTITY_TYPE", EntityType.DELIVERY);
        metadata.put("ENTITY_METRIC_TYPE", entityMetricType);
        deliveryHistoryRetriever.marshallSendAndReceive(null,metadata);
    }

/*
    public void analyseSubscriptionTrendChange(EntityMetadata entityMetadata, LocalDate forecastDate, SubscriptionTrendChangeDetector subscriptionTrendChangeDetector) {
        List<SubscriptionForecastTrendView> futureTrend= subscriptionTrendChangeDetector.determineTrendChange(null,entityMetadata);
        for(SubscriptionForecastTrendView trend: futureTrend){
            double expectedChangeInTotalSubscriptionCount = trend.getChangeInTotalSubscriptionCount();
            double expectedChangeInChurnedSubscriptionCount=trend.getChangeInChurnedSubscriptionCount();
            double expectedChangeInNewSubscriptionCount=trend.getChangeInNewSubscriptionCount();
            double contingencyStockPercentage=0.1;
            if(expectedChangeInTotalSubscriptionCount > contingencyStockPercentage){
                apply(new ChangeOfDeliveryCostNotificationEvent(expectedChangeInTotalSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                if(expectedChangeInNewSubscriptionCount > 0 || expectedChangeInChurnedSubscriptionCount <0){
                    apply(new SubscriptionBusinessIncreaseNotificationEvent(expectedChangeInNewSubscriptionCount,expectedChangeInChurnedSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                }
            }else if(expectedChangeInTotalSubscriptionCount < 0 ){
                apply(new ChangeOfDeliveryCostNotificationEvent(expectedChangeInTotalSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                if(expectedChangeInNewSubscriptionCount < 0 || expectedChangeInChurnedSubscriptionCount >0){
                    apply(new SubscriptionBusinessDecreaseNotificationEvent(expectedChangeInNewSubscriptionCount,expectedChangeInChurnedSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                }
            }
        }
    }


    public void analyseSubscriberTrendChange(EntityMetadata entityMetadata, LocalDate forecastDate, SubscriberTrendChangeDetector subscriberTrendChangeDetector) {
        List<SubscriberForecastTrendView> futureTrend= subscriberTrendChangeDetector.determineTrendChange(null,entityMetadata);
        for(SubscriberForecastTrendView trend: futureTrend){
            double expectedChangeInTotalSubscriberCount = trend.getChangeInTotalSubscriberCount();
            double expectedChangeInChurnedSubscriberCount=trend.getChangeInChurnedSubscriberCount();
            double expectedChangeInNewSubscriberCount=trend.getChangeInNewSubscriberCount();
            double contingencyStockPercentage=0.1;
            if(expectedChangeInTotalSubscriberCount > contingencyStockPercentage){
                apply(new ChangeOfDeliveryCostNotificationEvent(expectedChangeInTotalSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                if(expectedChangeInNewSubscriptionCount > 0 || expectedChangeInChurnedSubscriptionCount <0){
                    apply(new SubscriptionBusinessIncreaseNotificationEvent(expectedChangeInNewSubscriptionCount,expectedChangeInChurnedSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                }
            }else if(expectedChangeInTotalSubscriptionCount < 0 ){
                apply(new ChangeOfDeliveryCostNotificationEvent(expectedChangeInTotalSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                if(expectedChangeInNewSubscriptionCount < 0 || expectedChangeInChurnedSubscriptionCount >0){
                    apply(new SubscriptionBusinessDecreaseNotificationEvent(expectedChangeInNewSubscriptionCount,expectedChangeInChurnedSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                }
            }
        }
    }

    public void analyseDelliveryTrendChange(EntityMetadata entityMetadata, LocalDate forecastDate, DeliveryTrendChangeDetector deliveryTrendChangeDetector) {
        List<DeliveryForecastTrendView> futureTrend= deliveryTrendChangeDetector.determineTrendChange(null,entityMetadata);
        for(DeliveryForecastTrendView trend: futureTrend){
            double expectedChangeInTotalDeliveryCount = trend.getChangeInTotalDeliveriesCount();
            double contingencyStockPercentage=0.1;
            if(expectedChangeInTotalDeliveryCount > contingencyStockPercentage){
                apply(new ChangeOfDeliveryCostNotificationEvent(expectedChangeInTotalSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                if(expectedChangeInNewSubscriptionCount > 0 || expectedChangeInChurnedSubscriptionCount <0){
                    apply(new SubscriptionBusinessIncreaseNotificationEvent(expectedChangeInNewSubscriptionCount,expectedChangeInChurnedSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                }
            }else if(expectedChangeInTotalSubscriptionCount < 0 ){
                apply(new ChangeOfDeliveryCostNotificationEvent(expectedChangeInTotalSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                if(expectedChangeInNewSubscriptionCount < 0 || expectedChangeInChurnedSubscriptionCount >0){
                    apply(new SubscriptionBusinessDecreaseNotificationEvent(expectedChangeInNewSubscriptionCount,expectedChangeInChurnedSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                }
            }
        }
    }
*/

}
