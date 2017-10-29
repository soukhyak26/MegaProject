package com.affaince.subscription.subscriber.query.predictions;

import com.affaince.subscription.common.transport.TransportationTransformer;
import com.affaince.subscription.common.vo.AggregationType;
import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.subscriber.query.repository.SubscribersActualsViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscribersActualsView;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 9/1/2017.
 */
@Component
public class SubscriberMetricsHistoryRetriever extends TransportationTransformer {
    @Autowired
    SubscribersActualsViewRepository subscribersActualsViewRepository;
    @Value("${subscription.forecast.subscribers.url}")
    private String url;

    public List<DataFrameVO> prepare(Object subscriptionId, Map<String, Object> metadata) throws JsonProcessingException {
        System.out.println("in ProductsHistoryRetriever###############");
        //TODO: Need to change it to all records prior to upto 2 years
        Iterable<SubscribersActualsView> allSubscribers = subscribersActualsViewRepository.findAll();
        EntityMetricType entityMetricType = (EntityMetricType) metadata.get("ENTITY_METRIC_TYPE");
        List<DataFrameVO> subscribersHistoricalRecords = new ArrayList<>();
        for (SubscribersActualsView subscribersActualsView : allSubscribers) {
            DataFrameVO dataFrameVO = null;
            switch (entityMetricType) {
                case NEW:
                    dataFrameVO = new DataFrameVO(subscribersActualsView.getRegistrationDate(), "subscriber", subscribersActualsView.getNewSubscribers(), AggregationType.DAILY_NEW);
                    break;
                case CHURN:
                    dataFrameVO = new DataFrameVO(subscribersActualsView.getRegistrationDate(), "subscriber", subscribersActualsView.getChurnedSubscribers(), AggregationType.DAILY_NEW);
                    break;
                case TOTAL:
                    dataFrameVO = new DataFrameVO(subscribersActualsView.getRegistrationDate(), "subscriber", subscribersActualsView.getTotalSubscribers(), AggregationType.INCREMENTAL);
                    break;

            }
            subscribersHistoricalRecords.add(dataFrameVO);
        }
        return subscribersHistoricalRecords;
    }

    @Override
    public void marshallSendAndReceive(Object id, Map<String, Object> metadata) {
        super.marshallSendAndReceive(id, metadata, url);
    }

}
