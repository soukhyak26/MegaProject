package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.common.aggregate.AggregatorFactory;
import com.affaince.subscription.common.aggregate.aggregators.Aggregatable;
import com.affaince.subscription.common.aggregate.aggregators.MetricsAggregator;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.common.vo.EntityHistoryPacket;
import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.subscriber.command.event.SubscriptionForecastCreatedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionForecastViewRepository;
import com.affaince.subscription.subscriber.query.repository.SubscriptionPseudoActualsViewRepository;
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

/**
 * Created by mandar on 9/10/2017.
 */
@Component
public class SubscriptionForecastCreatedEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionForecastCreatedEventListener.class);
    private final SubscriptionForecastViewRepository subscriptionForecastViewRepository;
    private final SubscriptionPseudoActualsViewRepository subscriptionPseudoActualsViewRepository;
    private final AggregatorFactory<DataFrameVO> aggregatorFactory;
    @Autowired
    public SubscriptionForecastCreatedEventListener(SubscriptionForecastViewRepository subscriptionForecastViewRepository,SubscriptionPseudoActualsViewRepository subscriptionPseudoActualsViewRepository,AggregatorFactory<DataFrameVO> aggregatorFactory) {
        this.subscriptionForecastViewRepository = subscriptionForecastViewRepository;
        this.subscriptionPseudoActualsViewRepository= subscriptionPseudoActualsViewRepository;
        this.aggregatorFactory=aggregatorFactory;
    }

    @EventHandler
    public void on(SubscriptionForecastCreatedEvent event){
        final String forecastData=event.getForecastString();
        ObjectMapper mapper= new ObjectMapper();
        mapper.registerModule(new JodaModule());
        try {
            final EntityHistoryPacket forecastPacket=mapper.readValue(forecastData, new TypeReference<EntityHistoryPacket>(){});
            expireOverlappingActiveForecast(forecastPacket.getForecastDate());
            expireOverlappingActivePseudoActuals(forecastPacket.getForecastDate());
            updatePseudoActuals(null, forecastPacket.getEntityMetricType(),forecastPacket.getDataFrameVOs(),forecastPacket.getForecastDate() );
            updateForecast(null, forecastPacket.getEntityMetricType(),forecastPacket.getDataFrameVOs(),forecastPacket.getForecastDate() );
        } catch (IOException e) {
            LOGGER.error("Unable to deserialize forecasted content",e.getStackTrace());
        }

    }

    private void expireOverlappingActiveForecast(LocalDate forecastDate){
        List<SubscriptionForecastView> earlierForecastsWithOverlappingPeriods = subscriptionForecastViewRepository.findByForecastContentStatusAndForecastDateLessThan( ForecastContentStatus.ACTIVE, forecastDate);
        for (SubscriptionForecastView earlierView : earlierForecastsWithOverlappingPeriods) {
            earlierView.setForecastContentStatus(ForecastContentStatus.EXPIRED);
        }
        if(null != earlierForecastsWithOverlappingPeriods && earlierForecastsWithOverlappingPeriods.size()>0){
            subscriptionForecastViewRepository.save(earlierForecastsWithOverlappingPeriods);
        }
    }
    private void updateForecast(Object entityId, EntityMetricType entityMetricType, List<DataFrameVO> dataFrameVOs, LocalDate forecastDate){
        List<SubscriptionForecastView> forecastViews= new ArrayList<>();
        MetricsAggregator<DataFrameVO> aggregator= this.aggregatorFactory.getAggregator(30,DataFrameVO.class);
        List<DataFrameVO> aggregatedVOs = aggregator.aggregate(dataFrameVOs,30);

        for(DataFrameVO vo:dataFrameVOs){
            SubscriptionForecastView view= new SubscriptionForecastView(vo.getStartDate(),vo.getEndDate(),forecastDate);
            //view.
            switch (entityMetricType) {
                case NEW :
                    view.setNewSubscriptions(Double.valueOf(vo.getValue()).longValue());
                    break;
                case CHURN :
                    view.setChurnedSubscriptions(Double.valueOf(vo.getValue()).longValue());
                    break;
                case TOTAL :
                    view.setTotalSubscriptions(Double.valueOf(vo.getValue()).longValue());
            }
            forecastViews.add(view);
        }
        subscriptionForecastViewRepository.save(forecastViews);


    }
    private void updatePseudoActuals(Object entityId, EntityMetricType entityMetricType,List<DataFrameVO> dataFrameVOs, LocalDate forecastDate){
            List<SubscriptionPseudoActualsView> pseudoActualsViews= new ArrayList<>();
            for(DataFrameVO vo:dataFrameVOs){
                SubscriptionPseudoActualsView view= new SubscriptionPseudoActualsView(vo.getDate(),forecastDate);
                //view.
                switch (entityMetricType) {
                    case NEW :
                        view.setNewSubscriptions(Double.valueOf(vo.getValue()).longValue());
                        break;
                    case CHURN :
                        view.setChurnedSubscriptions(Double.valueOf(vo.getValue()).longValue());
                        break;
                    case TOTAL :
                        view.setTotalSubscriptions(Double.valueOf(vo.getValue()).longValue());
                }
                pseudoActualsViews.add(view);
            }
            subscriptionPseudoActualsViewRepository.save(pseudoActualsViews);
    }
    private void expireOverlappingActivePseudoActuals(LocalDate forecastDate){
        List<SubscriptionPseudoActualsView> earlierPseudoActualsWithOverlappingPeriods = subscriptionPseudoActualsViewRepository.findByForecastContentStatusAndForecastDateLessThan( ForecastContentStatus.ACTIVE, forecastDate);
        for (SubscriptionPseudoActualsView earlierView : earlierPseudoActualsWithOverlappingPeriods) {
            earlierView.setForecastContentStatus(ForecastContentStatus.EXPIRED);
        }
        if(null != earlierPseudoActualsWithOverlappingPeriods && earlierPseudoActualsWithOverlappingPeriods.size()>0){
            subscriptionPseudoActualsViewRepository.save(earlierPseudoActualsWithOverlappingPeriods);
        }
    }

}
