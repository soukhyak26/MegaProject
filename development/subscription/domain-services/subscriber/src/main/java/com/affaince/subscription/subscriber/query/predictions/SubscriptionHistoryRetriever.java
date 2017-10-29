package com.affaince.subscription.subscriber.query.predictions;

import com.affaince.subscription.common.transport.TransportationTransformer;
import com.affaince.subscription.common.vo.AggregationType;
import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.subscriber.query.repository.SubscriptionViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryView;
import com.affaince.subscription.subscriber.query.view.SubscriptionView;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by mandar on 10/29/2017.
 */
public class SubscriptionHistoryRetriever extends TransportationTransformer {
    @Autowired
    SubscriptionViewRepository subscriptionViewRepository;
    @Value("${subscription.forecast.subscription.url}")
    private String url;

    public List<DataFrameVO> prepare(Object subscriptionId, Map<String,Object> metadata) throws JsonProcessingException {
        System.out.println("in DeliveryHistoryRetriever###############");
        //TODO: Need to change it to all records prior to upto 2 years
        Iterable<SubscriptionView> allSubscriptions = subscriptionViewRepository.findAll();
        Iterable<SubscriptionView> subscriptionsMatchFilterCriteria=filterSubscriptions(allSubscriptions,metadata);
        Map<LocalDate,Integer> deliveryCountsPerDate=aggregateSubscriptionsPerDate(subscriptionsMatchFilterCriteria);
        List<DataFrameVO> deliveryWeightRecords = new ArrayList<>();
        deliveryCountsPerDate.forEach((date,count) -> {
            DataFrameVO dataFrameVO = new DataFrameVO(date, "deliveries", count, AggregationType.DAILY_NEW);
            deliveryWeightRecords.add(dataFrameVO);
        });
        return deliveryWeightRecords;
    }

    private Iterable<SubscriptionView> filterSubscriptions(Iterable<SubscriptionView> actualSubscriptions, Map<String, Object> metadata) {
        final int minimumWeight = (Integer) metadata.get("MIN_WEIGHT");
        final int maximumWeight = (Integer) metadata.get("MAX_WEIGHT");
        List<SubscriptionView> subscriptionsMatchingFilterCriteria = new ArrayList<>();

        for (SubscriptionView subscriptionView : actualSubscriptions) {
            double subscriptionAmount = subscriptionView.getTotalAmountAfterDiscount();
            if (subscriptionAmount >= minimumWeight && subscriptionAmount < maximumWeight) {
                subscriptionsMatchingFilterCriteria.add(subscriptionView);
            }
        }
        return subscriptionsMatchingFilterCriteria;
    }

    private Map<LocalDate,Integer> aggregateSubscriptionsPerDate(Iterable<SubscriptionView> subscriptionViews){
        Map<LocalDate,Integer> datewiseSusbcriptionsCount= new TreeMap<>();
        for(SubscriptionView subscriptionView:subscriptionViews){
            if(datewiseSusbcriptionsCount.containsKey(subscriptionView.getBasketCreatedDate())){
                datewiseSusbcriptionsCount.put(subscriptionView.getBasketCreatedDate(),datewiseSusbcriptionsCount.get(subscriptionView.getBasketCreatedDate()) + 1);
            }else{
                datewiseSusbcriptionsCount.put(subscriptionView.getBasketCreatedDate(),1);
            }
        }
        return datewiseSusbcriptionsCount;
    }

    @Override
    public void marshallSendAndReceive(Object id, Map<String,Object> metadata) {
        super.marshallSendAndReceive(id,metadata,url);
    }

}
