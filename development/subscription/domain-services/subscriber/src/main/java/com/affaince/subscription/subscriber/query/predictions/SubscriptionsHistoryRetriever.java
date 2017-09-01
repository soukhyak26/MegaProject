package com.affaince.subscription.subscriber.query.predictions;

import com.affaince.subscription.common.transport.TransportationTransformer;
import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.subscriber.query.repository.SubscriptionActualsViewRepoitory;
import com.affaince.subscription.subscriber.query.view.SubscriptionActualsView;
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
public class SubscriptionsHistoryRetriever extends TransportationTransformer {
@Autowired
    SubscriptionActualsViewRepoitory subscriptionActualsViewRepoitory;
    @Value("${subscription.forecast.subscriptions.url}")
    private String url;
    public List<DataFrameVO> prepare(Object subscriptionId) throws JsonProcessingException {
        System.out.println("in ProductsHistoryRetriever###############");
        //TODO: Need to change it to all records prior to upto 2 years
        Iterable<SubscriptionActualsView> allSubscriptions = subscriptionActualsViewRepoitory.findAll();
        List<DataFrameVO> subscriptionHistoricalRecords = new ArrayList<>();
        allSubscriptions.forEach(subscriptionActualsView -> {
            DataFrameVO dataFrameVO=new DataFrameVO(subscriptionActualsView.getRegistrationDate(),"subscription",subscriptionActualsView.getTotalSubscriptions());
            subscriptionHistoricalRecords.add(dataFrameVO);
        });
        return subscriptionHistoricalRecords;
    }

    @Override
    public void marshallSendAndReceive(Object id) {
        super.marshallSendAndReceive(id,url);
    }

}
