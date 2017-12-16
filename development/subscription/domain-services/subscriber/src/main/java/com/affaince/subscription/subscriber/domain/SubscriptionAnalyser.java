package com.affaince.subscription.subscriber.domain;

import com.affaince.subscription.common.vo.EntityMetadata;
import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.common.vo.EntityType;
import com.affaince.subscription.subscriber.event.*;
import com.affaince.subscription.subscriber.query.predictions.DeliveryHistoryRetriever;
import com.affaince.subscription.subscriber.query.predictions.SubscriberMetricsHistoryRetriever;
import com.affaince.subscription.subscriber.query.predictions.SubscriptionHistoryRetriever;
import com.affaince.subscription.subscriber.query.predictions.SubscriptionMetricssHistoryRetriever;
import com.affaince.subscription.subscriber.query.view.DeliveryForecastTrendView;
import com.affaince.subscription.subscriber.query.view.SubscriberForecastTrendView;
import com.affaince.subscription.subscriber.query.view.SubscriptionForecastTrendView;
import com.affaince.subscription.subscriber.query.view.SubscriptionRuleView;
import com.affaince.subscription.subscriber.services.config.DeliveryChargesRuleService;
import com.affaince.subscription.subscriber.services.config.SubscriptionRuleService;
import com.affaince.subscription.subscriber.services.trend.DeliveryTrendChangeDetector;
import com.affaince.subscription.subscriber.services.trend.SubscriberTrendChangeDetector;
import com.affaince.subscription.subscriber.services.trend.SubscriptionTrendChangeDetector;
import com.affaince.subscription.subscriber.vo.RangeRule;
import com.affaince.subscription.subscriber.vo.SubscriptionValueRange;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

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
    public void initiateSubscriberMetricsForecast(EntityMetricType entityMetricType, SubscriberMetricsHistoryRetriever subscriberMetricsHistoryRetriever){
        Map<String,Object> metadata= new HashMap<>();
        metadata.put("ENTITY_TYPE", EntityType.SUBSCRIBER);
        metadata.put("ENTITY_METRIC_TYPE", entityMetricType);
        subscriberMetricsHistoryRetriever.marshallSendAndReceive(null,metadata);
    }

    public void initiateSubscriptionMetricsForecast(EntityMetricType entityMetricType, SubscriptionMetricssHistoryRetriever subscriptionMetricssHistoryRetriever, SubscriptionRuleService subscriptionRuleService){
        SubscriptionRuleView subscriptionRuleView= subscriptionRuleService.getSubscriptionConfig();
        List<SubscriptionValueRange>valueRanges= subscriptionRuleView.getSubscriptionValueRanges();
        for(SubscriptionValueRange valueRange: valueRanges) {
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("ENTITY_TYPE", EntityType.SUBSCRIPTION);
            metadata.put("ENTITY_METRIC_TYPE", entityMetricType);
            metadata.put("MIN_WEIGHT", valueRange.getMinimumValue());
            metadata.put("MAX_WEIGHT", valueRange.getMaximumValue());
            subscriptionMetricssHistoryRetriever.marshallSendAndReceive(null, metadata);
        }
    }

    public void initiateSubscriptionForecast(EntityMetricType entityMetricType,SubscriptionRuleService subscriptionRuleService, SubscriptionHistoryRetriever subscriptionHistoryRetriever){
        List<SubscriptionValueRange> subscriptionValueRanges=subscriptionRuleService.getSubscriptionConfig().getSubscriptionValueRanges();
        for(SubscriptionValueRange rule: subscriptionValueRanges) {
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("ENTITY_TYPE", EntityType.SUBSCRIPTION);
            metadata.put("ENTITY_METRIC_TYPE", entityMetricType);
            metadata.put("MIN_WEIGHT", rule.getMinimumValue());
            metadata.put("MAX_WEIGHT",rule.getMaximumValue());
            subscriptionHistoryRetriever.marshallSendAndReceive(null, metadata);
        }
    }


    public void initiateDeliveryForecast(EntityMetricType entityMetricType, DeliveryChargesRuleService deliveryChargesRuleService, DeliveryHistoryRetriever deliveryHistoryRetriever){
        List<RangeRule> weightRanges= deliveryChargesRuleService.getDeliveryConfig().getRangeRules();
        for(RangeRule rule: weightRanges) {
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("ENTITY_TYPE", EntityType.DELIVERY);
            metadata.put("ENTITY_METRIC_TYPE", entityMetricType);
            metadata.put("MIN_WEIGHT", rule.getRuleMinimum());
            metadata.put("MAX_WEIGHT",rule.getRuleMaximum());
            deliveryHistoryRetriever.marshallSendAndReceive(null, metadata);
        }
    }


    public void analyseSubscriptionTrendChange(EntityMetadata entityMetadata, SubscriptionTrendChangeDetector subscriptionTrendChangeDetector) {
        List<SubscriptionForecastTrendView> futureTrend= subscriptionTrendChangeDetector.determineTrendChange(null,entityMetadata);
        for(SubscriptionForecastTrendView trend: futureTrend){
            double expectedChangeInTotalSubscriptionCount = trend.getChangeInTotalSubscriptionCount();
            double expectedChangeInChurnedSubscriptionCount=trend.getChangeInChurnedSubscriptionCount();
            double expectedChangeInNewSubscriptionCount=trend.getChangeInNewSubscriptionCount();
            if(expectedChangeInTotalSubscriptionCount > 0){
                apply(new IncreaseInTotalSubscriptionCountNotificationEvent(expectedChangeInTotalSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                if(expectedChangeInNewSubscriptionCount > 0 ){
                    apply(new IncreaseInNewSubscriptionsNotificationEvent(expectedChangeInNewSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                }
                if(expectedChangeInChurnedSubscriptionCount < 0){
                    apply(new DecreaseInSubscriptionChurnsNotificationEvent(expectedChangeInChurnedSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                }
            }else if(expectedChangeInTotalSubscriptionCount < 0 ){
                apply(new DecreaseInTotalSubscriptionCountNotificationEvent(expectedChangeInTotalSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                if(expectedChangeInNewSubscriptionCount < 0 ){
                    apply(new DecreaseInNewSubscriptionsNotificationEvent(expectedChangeInNewSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                }
                if(expectedChangeInChurnedSubscriptionCount >0){
                    apply(new IncreaseInSubscriptionChurnsNotificationEvent(expectedChangeInChurnedSubscriptionCount,trend.getSubscriptionVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriptionVersionId().getForecastDate()));
                }
            }
        }
    }


    public void analyseSubscriberTrendChange(EntityMetadata entityMetadata, SubscriberTrendChangeDetector subscriberTrendChangeDetector) {
        List<SubscriberForecastTrendView> futureTrend= subscriberTrendChangeDetector.determineTrendChange(null,entityMetadata);
        for(SubscriberForecastTrendView trend: futureTrend){
            double expectedChangeInTotalSubscriberCount = trend.getChangeInTotalSubscriberCount();
            double expectedChangeInChurnedSubscriberCount=trend.getChangeInChurnedSubscriberCount();
            double expectedChangeInNewSubscriberCount=trend.getChangeInNewSubscriberCount();
            if(expectedChangeInTotalSubscriberCount > 0){
                apply(new IncreaseInTotalSubscriberCountNotificationEvent(expectedChangeInTotalSubscriberCount,trend.getSubscriberVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriberVersionId().getForecastDate()));
                if(expectedChangeInNewSubscriberCount > 0 ){
                    apply(new IncreaseInNewSubscribersNotificationEvent(expectedChangeInNewSubscriberCount,trend.getSubscriberVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriberVersionId().getForecastDate()));
                }
                if(expectedChangeInChurnedSubscriberCount < 0){
                    apply(new DecreaseInSubscriberChurnsNotificationEvent(expectedChangeInChurnedSubscriberCount,trend.getSubscriberVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriberVersionId().getForecastDate()));
                }
            }else if(expectedChangeInTotalSubscriberCount < 0 ){
                apply(new DecreaseInTotalSubscriberCountNotificationEvent(expectedChangeInTotalSubscriberCount,trend.getSubscriberVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriberVersionId().getForecastDate()));
                if(expectedChangeInNewSubscriberCount < 0 ){
                    apply(new DecreaseInNewSubscribersNotificationEvent(expectedChangeInNewSubscriberCount,trend.getSubscriberVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriberVersionId().getForecastDate()));
                }
                if(expectedChangeInChurnedSubscriberCount >0){
                    apply(new IncreaseInSubscriberChurnsNotificationEvent(expectedChangeInChurnedSubscriberCount,trend.getSubscriberVersionId().getStartDate(),trend.getEndDate(),trend.getSubscriberVersionId().getForecastDate()));
                }
            }
        }
    }

    public void analyseDeliveryTrendChange(EntityMetadata entityMetadata, DeliveryTrendChangeDetector deliveryTrendChangeDetector) {
        List<DeliveryForecastTrendView> futureTrend= deliveryTrendChangeDetector.determineTrendChange(null,entityMetadata);
        for(DeliveryForecastTrendView trend: futureTrend){
            double expectedChangeInTotalDeliveryCount = trend.getChangeInTotalDeliveriesCount();
            if(expectedChangeInTotalDeliveryCount > 0){
                apply(new IncreaseInTotalDeliveryCountNotificationEvent(expectedChangeInTotalDeliveryCount,trend.getDeliveryForecastVersionId().getDeliveryDate(),trend.getEndDate(),trend.getDeliveryForecastVersionId().getForecastDate()));
            }else if(expectedChangeInTotalDeliveryCount < 0 ){
                apply(new DecreaseInTotalDeliveryCountNotificationEvent(expectedChangeInTotalDeliveryCount,trend.getDeliveryForecastVersionId().getDeliveryDate(),trend.getEndDate(),trend.getDeliveryForecastVersionId().getForecastDate()));
            }
        }
    }

}
