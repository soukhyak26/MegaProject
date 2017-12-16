package com.affaince.subscription.subscriber.event.listener;

import com.affaince.subscription.common.aggregate.AggregatorFactory;
import com.affaince.subscription.common.aggregate.aggregators.MetricsAggregator;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.common.vo.EntityMetadata;
import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.subscriber.event.SubscriptionForecastCreatedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionForecastViewRepository;
import com.affaince.subscription.subscriber.query.repository.SubscriptionPseudoActualsViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionForecastView;
import com.affaince.subscription.subscriber.query.view.SubscriptionPseudoActualsView;
import com.affaince.subscription.subscriber.services.trend.SubscriptionTrendChangeDetector;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 9/10/2017.
 */
@Component
public class SubscriptionForecastCreatedEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionForecastCreatedEventListener.class);
    private final SubscriptionForecastViewRepository subscriptionForecastViewRepository;
    private final SubscriptionPseudoActualsViewRepository subscriptionPseudoActualsViewRepository;
    private final AggregatorFactory<DataFrameVO> aggregatorFactory;
    private final SubscriptionTrendChangeDetector subscriptionTrendChangeDetector;

    @Autowired
    public SubscriptionForecastCreatedEventListener(SubscriptionForecastViewRepository subscriptionForecastViewRepository, SubscriptionPseudoActualsViewRepository subscriptionPseudoActualsViewRepository, AggregatorFactory<DataFrameVO> aggregatorFactory,SubscriptionTrendChangeDetector subscriptionTrendChangeDetector) {
        this.subscriptionForecastViewRepository = subscriptionForecastViewRepository;
        this.subscriptionPseudoActualsViewRepository = subscriptionPseudoActualsViewRepository;
        this.aggregatorFactory = aggregatorFactory;
        this.subscriptionTrendChangeDetector=subscriptionTrendChangeDetector;
    }

    @EventHandler
    public void on(SubscriptionForecastCreatedEvent event) {
        final List<DataFrameVO> forecastData = event.getDataFrameVOs();
        final LocalDate forecastDate = event.getForecastDate();
        final EntityMetadata entityMetadata = event.getEntityMetadata();
        expireOverlappingActiveForecast(forecastDate);
        expireOverlappingActivePseudoActuals(forecastDate);
        updatePseudoActuals(null, forecastData, forecastDate, entityMetadata);
        updateForecast(null, forecastData, forecastDate, entityMetadata);
        Map<String, Object> namedMetadata = entityMetadata.getNamedEntries();
        double minWeight = 0;
        double maxWeight = 0;
        for (String s : namedMetadata.keySet()) {
            switch (s) {
                case "MIN_WEIGHT":
                    minWeight = (Double) namedMetadata.get(s);
                    break;
                case "MAX_WEIGHT":
                    maxWeight = (Double) namedMetadata.get(s);
                    break;

            }
        }
        subscriptionTrendChangeDetector.determineTrendChange(null,entityMetadata);
    }

    private void expireOverlappingActiveForecast(LocalDate forecastDate) {
        List<SubscriptionForecastView> earlierForecastsWithOverlappingPeriods = subscriptionForecastViewRepository.findByForecastContentStatusAndSubscriptionVersionId_ForecastDateLessThan(ForecastContentStatus.ACTIVE, forecastDate);
        for (SubscriptionForecastView earlierView : earlierForecastsWithOverlappingPeriods) {
            earlierView.setForecastContentStatus(ForecastContentStatus.EXPIRED);
        }
        if (null != earlierForecastsWithOverlappingPeriods && earlierForecastsWithOverlappingPeriods.size() > 0) {
            subscriptionForecastViewRepository.save(earlierForecastsWithOverlappingPeriods);
        }
    }

    private void updateForecast(Object entityId, List<DataFrameVO> dataFrameVOs, LocalDate forecastDate, EntityMetadata entityMetadata) {
        List<SubscriptionForecastView> forecastViews = new ArrayList<>();
        MetricsAggregator<DataFrameVO> aggregator = this.aggregatorFactory.getAggregator(30, DataFrameVO.class);
        List<DataFrameVO> aggregatedVOs = aggregator.aggregate(dataFrameVOs, 30);
        Map<String, Object> namedMetadata = entityMetadata.getNamedEntries();
        EntityMetricType entityMetricType = null;
        double minWeight = 0;
        double maxWeight = 0;
        for (String s : namedMetadata.keySet()) {
            switch (s) {
                case "ENTITY_METRIC_TYPE":
                    entityMetricType = (EntityMetricType) namedMetadata.get(s);
                    break;
                case "MIN_WEIGHT":
                    minWeight = (Double) namedMetadata.get(s);
                    break;
                case "MAX_WEIGHT":
                    maxWeight = (Double) namedMetadata.get(s);
                    break;

            }
        }

        for (DataFrameVO vo : dataFrameVOs) {
            SubscriptionForecastView view=null;
            List<SubscriptionForecastView> alreadySavedViews=subscriptionForecastViewRepository.findByForecastContentStatusAndSubscriptionVersionId_ForecastDate(ForecastContentStatus.ACTIVE,forecastDate);
            if(null==alreadySavedViews && alreadySavedViews.isEmpty()) {
                view = new SubscriptionForecastView(vo.getStartDate(), vo.getEndDate(), forecastDate,minWeight,maxWeight);
            }else{
                view=alreadySavedViews.get(0);
            }
            //view.
            switch (entityMetricType) {
                case NEW:
                    view.setNewSubscriptions(Double.valueOf(vo.getValue()).longValue());
                    break;
                case CHURN:
                    view.setChurnedSubscriptions(Double.valueOf(vo.getValue()).longValue());
                    break;
                case TOTAL:
                    view.setTotalSubscriptions(Double.valueOf(vo.getValue()).longValue());
            }
            forecastViews.add(view);
        }
        subscriptionForecastViewRepository.save(forecastViews);


    }

    private void updatePseudoActuals(Object entityId, List<DataFrameVO> dataFrameVOs, LocalDate forecastDate, EntityMetadata entityMetadata) {
        List<SubscriptionPseudoActualsView> pseudoActualsViews = new ArrayList<>();
        Map<String, Object> namedMetadata = entityMetadata.getNamedEntries();
        EntityMetricType entityMetricType = null;
        double minWeight = 0;
        double maxWeight = 0;

        for (String s : namedMetadata.keySet()) {
            switch (s) {
                case "ENTITY_METRIC_TYPE":
                    entityMetricType = (EntityMetricType) namedMetadata.get(s);
                    break;
                case "MIN_WEIGHT":
                    minWeight = (Double) namedMetadata.get(s);
                    break;
                case "MAX_WEIGHT":
                    maxWeight = (Double) namedMetadata.get(s);
                    break;

            }
        }


        for (DataFrameVO vo : dataFrameVOs) {
            SubscriptionPseudoActualsView view=null;
            List<SubscriptionPseudoActualsView> alreadySavedViews= subscriptionPseudoActualsViewRepository.findByForecastContentStatusAndSubscriptionVersionId_ForecastDate(ForecastContentStatus.ACTIVE,forecastDate);
            if(null == alreadySavedViews && alreadySavedViews.isEmpty()) {
                view = new SubscriptionPseudoActualsView(forecastDate,vo.getStartDate(),minWeight,maxWeight);
            }else{
                view=alreadySavedViews.get(0);
            }
            switch (entityMetricType) {
                case NEW:
                    view.setNewSubscriptions(Double.valueOf(vo.getValue()).longValue());
                    break;
                case CHURN:
                    view.setChurnedSubscriptions(Double.valueOf(vo.getValue()).longValue());
                    break;
                case TOTAL:
                    view.setTotalSubscriptions(Double.valueOf(vo.getValue()).longValue());
            }
            pseudoActualsViews.add(view);
        }
        subscriptionPseudoActualsViewRepository.save(pseudoActualsViews);
    }

    private void expireOverlappingActivePseudoActuals(LocalDate forecastDate) {
        List<SubscriptionPseudoActualsView> earlierPseudoActualsWithOverlappingPeriods = subscriptionPseudoActualsViewRepository.findByForecastContentStatusAndSubscriptionVersionId_ForecastDateLessThan(ForecastContentStatus.ACTIVE, forecastDate);
        for (SubscriptionPseudoActualsView earlierView : earlierPseudoActualsWithOverlappingPeriods) {
            earlierView.setForecastContentStatus(ForecastContentStatus.EXPIRED);
        }
        if (null != earlierPseudoActualsWithOverlappingPeriods && earlierPseudoActualsWithOverlappingPeriods.size() > 0) {
            subscriptionPseudoActualsViewRepository.save(earlierPseudoActualsWithOverlappingPeriods);
        }
    }

}