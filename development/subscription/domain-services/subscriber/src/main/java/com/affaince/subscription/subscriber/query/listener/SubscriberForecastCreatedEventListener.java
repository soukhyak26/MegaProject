package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.common.aggregate.AggregatorFactory;
import com.affaince.subscription.common.aggregate.aggregators.MetricsAggregator;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.common.vo.EntityHistoryPacket;
import com.affaince.subscription.common.vo.EntityMetadata;
import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.subscriber.command.event.SubscriberForecastCreatedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriberPseudoActualsViewRepository;
import com.affaince.subscription.subscriber.query.repository.SubscribersForecastViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriberPseudoActualsView;
import com.affaince.subscription.subscriber.query.view.SubscribersForecastView;
import com.affaince.subscription.subscriber.query.view.SubscriptionForecastView;
import com.affaince.subscription.subscriber.query.view.SubscriptionPseudoActualsView;
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
 * Created by mandar on 9/10/2017.
 */
@Component
public class SubscriberForecastCreatedEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberForecastCreatedEventListener.class);
    private final SubscribersForecastViewRepository subscribersForecastViewRepository;
    private final SubscriberPseudoActualsViewRepository subscriberPseudoActualsViewRepository;
    private final AggregatorFactory<DataFrameVO> aggregatorFactory;
    @Autowired
    public SubscriberForecastCreatedEventListener(SubscribersForecastViewRepository subscribersForecastViewRepository,SubscriberPseudoActualsViewRepository subscriberPseudoActualsViewRepository,AggregatorFactory<DataFrameVO> aggregatorFactory) {
        this.subscribersForecastViewRepository = subscribersForecastViewRepository;
        this.subscriberPseudoActualsViewRepository=subscriberPseudoActualsViewRepository;
        this.aggregatorFactory=aggregatorFactory;
    }

    @EventHandler
    public void on(SubscriberForecastCreatedEvent event){
        final String forecastData=event.getForecastString();
        ObjectMapper mapper= new ObjectMapper();
        mapper.registerModule(new JodaModule());
        try {
            final EntityHistoryPacket forecastPacket=mapper.readValue(forecastData, new TypeReference<EntityHistoryPacket>(){});
            expireOverlappingActiveForecast(forecastPacket.getForecastDate());
            expireOverlappingActivePseudoActuals(forecastPacket.getForecastDate());
            updatePseudoActuals(null, forecastPacket.getDataFrameVOs(),forecastPacket.getForecastDate(),forecastPacket.getEntityMetadata() );
            updateForecast(null, forecastPacket.getDataFrameVOs(),forecastPacket.getForecastDate(),forecastPacket.getEntityMetadata() );

        } catch (IOException e) {
            LOGGER.error("Unable to deserialize forecasted content",e.getStackTrace());
        }
    }

    private void expireOverlappingActiveForecast(LocalDate forecastDate){
        List<SubscribersForecastView> earlierForecastsWithOverlappingPeriods = subscribersForecastViewRepository.findByForecastContentStatusAndForecastDateLessThan( ForecastContentStatus.ACTIVE, forecastDate);
        for (SubscribersForecastView earlierView : earlierForecastsWithOverlappingPeriods) {
            earlierView.setForecastContentStatus(ForecastContentStatus.EXPIRED);
        }
        if(null != earlierForecastsWithOverlappingPeriods && earlierForecastsWithOverlappingPeriods.size()>0){
            subscribersForecastViewRepository.save(earlierForecastsWithOverlappingPeriods);
        }
    }

    private void expireOverlappingActivePseudoActuals(LocalDate forecastDate){
        List<SubscriberPseudoActualsView> earlierPseudoActualsWithOverlappingPeriods = subscriberPseudoActualsViewRepository.findByForecastContentStatusAndForecastDateLessThan( ForecastContentStatus.ACTIVE, forecastDate);
        for (SubscriberPseudoActualsView earlierView : earlierPseudoActualsWithOverlappingPeriods) {
            earlierView.setForecastContentStatus(ForecastContentStatus.EXPIRED);
        }
        if(null != earlierPseudoActualsWithOverlappingPeriods && earlierPseudoActualsWithOverlappingPeriods.size()>0){
            subscriberPseudoActualsViewRepository.save(earlierPseudoActualsWithOverlappingPeriods);
        }
    }

    private void updateForecast(Object entityId, List<DataFrameVO> dataFrameVOs, LocalDate forecastDate, EntityMetadata entityMetadata){
        List<SubscribersForecastView> forecastViews= new ArrayList<>();
        MetricsAggregator<DataFrameVO> aggregator= this.aggregatorFactory.getAggregator(30,DataFrameVO.class);
        List<DataFrameVO> aggregatedVOs = aggregator.aggregate(dataFrameVOs,30);
        EntityMetricType entityMetricType=null;
        Map<String,Object> namedMetadata=entityMetadata.getNamedEntries();
        for(String s: namedMetadata.keySet()){
            switch(s){
                case "ENTITY_METRIC_TYPE":
                    entityMetricType=(EntityMetricType)namedMetadata.get(s);
                    break;
            }
        }

        for(DataFrameVO vo:dataFrameVOs){
            SubscribersForecastView view= new SubscribersForecastView(vo.getStartDate(),vo.getEndDate(),forecastDate);
            //view.
            switch (entityMetricType) {
                case NEW :
                    view.setNewSubscribers(Double.valueOf(vo.getValue()).longValue());
                    break;
                case CHURN :
                    view.setChurnedSubscribers(Double.valueOf(vo.getValue()).longValue());
                    break;
                case TOTAL :
                    view.setTotalSubscribers(Double.valueOf(vo.getValue()).longValue());
            }
            forecastViews.add(view);
        }
        subscribersForecastViewRepository.save(forecastViews);


    }
    private void updatePseudoActuals(Object entityId, List<DataFrameVO> dataFrameVOs, LocalDate forecastDate,EntityMetadata entityMetadata){
        List<SubscriberPseudoActualsView> pseudoActualsViews= new ArrayList<>();
        EntityMetricType entityMetricType=null;
        Map<String,Object> namedMetadata=entityMetadata.getNamedEntries();
        for(String s: namedMetadata.keySet()){
            switch(s){
                case "ENTITY_METRIC_TYPE":
                    entityMetricType=(EntityMetricType)namedMetadata.get(s);
                    break;
            }
        }
        for(DataFrameVO vo:dataFrameVOs){
            SubscriberPseudoActualsView view= new SubscriberPseudoActualsView(vo.getDate(),forecastDate);
            //view.
            switch (entityMetricType) {
                case NEW :
                    view.setNewSubscribers(Double.valueOf(vo.getValue()).longValue());
                    break;
                case CHURN :
                    view.setChurnedSubscribers(Double.valueOf(vo.getValue()).longValue());
                    break;
                case TOTAL :
                    view.setTotalSubscribers(Double.valueOf(vo.getValue()).longValue());
            }
            pseudoActualsViews.add(view);
        }
        subscriberPseudoActualsViewRepository.save(pseudoActualsViews);
    }
}
