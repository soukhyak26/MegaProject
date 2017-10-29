package com.affaince.subscription.subscriber.query.predictions;

import com.affaince.subscription.common.transport.TransportationTransformer;
import com.affaince.subscription.common.vo.AggregationType;
import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.subscriber.query.repository.SubscriptionActualsViewRepoitory;
import com.affaince.subscription.subscriber.query.repository.SubscriptionViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryItem;
import com.affaince.subscription.subscriber.query.view.DeliveryView;
import com.affaince.subscription.subscriber.query.view.SubscriptionActualsView;
import com.affaince.subscription.subscriber.query.view.SubscriptionView;
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
public class SubscriptionMetricssHistoryRetriever extends TransportationTransformer {
    @Autowired
    SubscriptionActualsViewRepoitory subscriptionActualsViewRepoitory;
    @Value("${subscription.forecast.subscriptions.url}")
    private String url;

    public List<DataFrameVO> prepare(Object subscriptionId, Map<String, Object> metadata) throws JsonProcessingException {
        System.out.println("in ProductsHistoryRetriever###############");
        //TODO: Need to change it to all records prior to upto 2 years
        Iterable<SubscriptionActualsView> allSubscriptionMetrics = subscriptionActualsViewRepoitory.findAll();

        EntityMetricType entityMetricType = (EntityMetricType) metadata.get("ENTITY_METRIC_TYPE");
        List<DataFrameVO> subscriptionHistoricalRecords = new ArrayList<>();
        for (SubscriptionActualsView subscriptionActualsView : allSubscriptionMetrics) {
            DataFrameVO dataFrameVO = null;
            switch (entityMetricType) {
                case NEW:
                    dataFrameVO = new DataFrameVO(subscriptionActualsView.getRegistrationDate(), "subscription", subscriptionActualsView.getNewSubscriptions(), AggregationType.DAILY_NEW);
                    break;
                case CHURN:
                    dataFrameVO = new DataFrameVO(subscriptionActualsView.getRegistrationDate(), "subscription", subscriptionActualsView.getChurnedSubscriptions(), AggregationType.DAILY_NEW);
                    break;
                case TOTAL:
                    dataFrameVO = new DataFrameVO(subscriptionActualsView.getRegistrationDate(), "subscription", subscriptionActualsView.getTotalSubscriptions(), AggregationType.INCREMENTAL);
                    break;
            }
            subscriptionHistoricalRecords.add(dataFrameVO);
        }
        return subscriptionHistoricalRecords;
    }


    @Override
    public void marshallSendAndReceive(Object id, Map<String, Object> metadata) {
        super.marshallSendAndReceive(id, metadata, url);
    }

}
