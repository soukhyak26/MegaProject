package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.common.aggregate.AggregatorFactory;
import com.affaince.subscription.common.aggregate.aggregators.MetricsAggregator;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.common.vo.EntityHistoryPacket;
import com.affaince.subscription.common.vo.EntityMetadata;
import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.subscriber.command.event.DeliveryForecastCreatedEvent;
import com.affaince.subscription.subscriber.query.repository.DeliveryForecastViewRepository;
import com.affaince.subscription.subscriber.query.repository.DeliveryPseudoActualsViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryForecastView;
import com.affaince.subscription.subscriber.query.view.DeliveryPseudoActualsView;
import com.affaince.subscription.subscriber.services.trend.DeliveryTrendChangeDetector;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 9/23/2017.
 */
@Component
public class DeliveryForecastCreatedEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryForecastCreatedEventListener.class);
    private final DeliveryForecastViewRepository deliveryForecastViewRepository;
    private DeliveryPseudoActualsViewRepository deliveryPseudoActualsViewRepository;
    private final AggregatorFactory<DataFrameVO> aggregatorFactory;
    private final DeliveryTrendChangeDetector deliveryTrendChangeDetector;

    @Autowired
    public DeliveryForecastCreatedEventListener(DeliveryForecastViewRepository deliveryForecastViewRepository, DeliveryPseudoActualsViewRepository deliveryPseudoActualsViewRepository, AggregatorFactory<DataFrameVO> aggregatorFactory,DeliveryTrendChangeDetector deliveryTrendChangeDetector) {
        this.deliveryForecastViewRepository = deliveryForecastViewRepository;
        this.aggregatorFactory = aggregatorFactory;
        this.deliveryPseudoActualsViewRepository = deliveryPseudoActualsViewRepository;
        this.deliveryTrendChangeDetector=deliveryTrendChangeDetector;
    }
    @EventHandler
    public void on(DeliveryForecastCreatedEvent event) {
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
        deliveryTrendChangeDetector.determineTrendChange(null,minWeight,maxWeight);
    }

    private void expireOverlappingActiveForecast(LocalDate forecastDate) {
        List<DeliveryForecastView> earlierForecastsWithOverlappingPeriods = deliveryForecastViewRepository.findByForecastContentStatusAndDeliveryVersionId_ForecastDateLessThan(ForecastContentStatus.ACTIVE, forecastDate);
        for (DeliveryForecastView earlierView : earlierForecastsWithOverlappingPeriods) {
            earlierView.setForecastContentStatus(ForecastContentStatus.EXPIRED);
        }
        if (null != earlierForecastsWithOverlappingPeriods && earlierForecastsWithOverlappingPeriods.size() > 0) {
            deliveryForecastViewRepository.save(earlierForecastsWithOverlappingPeriods);
        }
    }

    private void expireOverlappingActivePseudoActuals(LocalDate forecastDate) {
        List<DeliveryPseudoActualsView> earlierPseudoActualsWithOverlappingPeriods = deliveryPseudoActualsViewRepository.findByForecastContentStatusAndDeliveryVersionId_ForecastDateLessThan(ForecastContentStatus.ACTIVE, forecastDate);
        for (DeliveryPseudoActualsView earlierView : earlierPseudoActualsWithOverlappingPeriods) {
            earlierView.setForecastContentStatus(ForecastContentStatus.EXPIRED);
        }
        if (null != earlierPseudoActualsWithOverlappingPeriods && earlierPseudoActualsWithOverlappingPeriods.size() > 0) {
            deliveryPseudoActualsViewRepository.save(earlierPseudoActualsWithOverlappingPeriods);
        }
    }

    private void updateForecast(Object entityId, List<DataFrameVO> dataFrameVOs, LocalDate forecastDate, EntityMetadata entityMetadata) {
        List<DeliveryForecastView> forecastViews = new ArrayList<>();
        MetricsAggregator<DataFrameVO> aggregator = this.aggregatorFactory.getAggregator(30, DataFrameVO.class);
        List<DataFrameVO> aggregatedVOs = aggregator.aggregate(dataFrameVOs, 30);
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
        for (DataFrameVO vo : dataFrameVOs) {
            DeliveryForecastView view = new DeliveryForecastView(forecastDate, vo.getStartDate(), vo.getEndDate(),minWeight,maxWeight);
            view.setDeliveryCount(Double.valueOf(vo.getValue()).longValue());
            forecastViews.add(view);
        }
        deliveryForecastViewRepository.save(forecastViews);
    }

    private void updatePseudoActuals(Object entityId, List<DataFrameVO> dataFrameVOs, LocalDate forecastDate, EntityMetadata entityMetadata) {
        List<DeliveryPseudoActualsView> pseudoActualsViews = new ArrayList<>();
        double minWeight = 0;
        double maxWeight = 0;
        Map<String, Object> namedMetadata = entityMetadata.getNamedEntries();
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

        for (DataFrameVO vo : dataFrameVOs) {
            DeliveryPseudoActualsView view = new DeliveryPseudoActualsView(forecastDate, vo.getStartDate(),minWeight,maxWeight);
            view.setDeliveryCount(Double.valueOf(vo.getValue()).longValue());
            pseudoActualsViews.add(view);
        }
        deliveryPseudoActualsViewRepository.save(pseudoActualsViews);
    }

}
