package com.affaince.subscription.subscriber.query.predictions;

import com.affaince.subscription.common.transport.TransportationTransformer;
import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.subscriber.query.repository.SubscribersActualsViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscribersActualsView;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 9/1/2017.
 */
@Component
public class SubscribersHistoryRetriever extends TransportationTransformer {
    @Autowired
    SubscribersActualsViewRepository subscribersActualsViewRepository;
    @Value("${subscription.forecast.subscribers.url}")
    private String url;
    public List<DataFrameVO> prepare(Object subscriptionId) throws JsonProcessingException {
        System.out.println("in ProductsHistoryRetriever###############");
        //TODO: Need to change it to all records prior to upto 2 years
        Iterable<SubscribersActualsView> allSubscriptions = subscribersActualsViewRepository.findAll();
        List<DataFrameVO> subscribersHistoricalRecords = new ArrayList<>();
        allSubscriptions.forEach(subscriberActualsView -> {
            DataFrameVO dataFrameVO = new DataFrameVO(subscriberActualsView.getRegistrationDate(), "subscription", subscriberActualsView.getTotalSubscribers());
            subscribersHistoricalRecords.add(dataFrameVO);
        });
        return subscribersHistoricalRecords;
    }

    @Override
    public void marshallSendAndReceive(Object id) {
        super.marshallSendAndReceive(id,url);
    }

}
