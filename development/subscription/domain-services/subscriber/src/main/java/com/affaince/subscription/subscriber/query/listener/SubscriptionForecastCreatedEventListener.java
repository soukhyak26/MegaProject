package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.common.vo.EntityHistoryPacket;
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
import java.util.List;

/**
 * Created by mandar on 9/10/2017.
 */
@Component
public class SubscriptionForecastCreatedEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionForecastCreatedEventListener.class);
    private final SubscriptionForecastViewRepository subscriptionForecastViewRepository;
    private final SubscriptionPseudoActualsViewRepository subscriptionPseudoActualsViewRepository;
    @Autowired
    public SubscriptionForecastCreatedEventListener(SubscriptionForecastViewRepository subscriptionForecastViewRepository,SubscriptionPseudoActualsViewRepository subscriptionPseudoActualsViewRepository) {
        this.subscriptionForecastViewRepository = subscriptionForecastViewRepository;
        this.subscriptionPseudoActualsViewRepository= subscriptionPseudoActualsViewRepository;
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
    public void updateForecast(Object entityId, List<DataFrameVO> dataFrameVOs, LocalDate ForecastDate){

    }
    public void updatePseudoActuals(Object entityId, List<DataFrameVO> dataFrameVOs, LocalDate forecastDate){
            for(DataFrameVO vo:dataFrameVOs){
                SubscriptionPseudoActualsView view= new SubscriptionPseudoActualsView(vo.getDate(),forecastDate);
                //view.
            }
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
