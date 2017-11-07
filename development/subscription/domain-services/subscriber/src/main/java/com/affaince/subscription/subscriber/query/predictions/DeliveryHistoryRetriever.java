package com.affaince.subscription.subscriber.query.predictions;

import com.affaince.subscription.common.transport.TransportationTransformer;
import com.affaince.subscription.common.vo.AggregationType;
import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.subscriber.query.repository.DeliveryActualsViewRepository;
import com.affaince.subscription.subscriber.query.repository.DeliveryViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryActualsView;
import com.affaince.subscription.subscriber.query.view.DeliveryItem;
import com.affaince.subscription.subscriber.query.view.DeliveryView;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by mandar on 9/23/2017.
 */
@Component
public class DeliveryHistoryRetriever extends TransportationTransformer {
    @Autowired
    private DeliveryActualsViewRepository deliveryActualsViewRepository;
    @Value("${subscription.forecast.deliveries.url}")
    private String url;

    public List<DataFrameVO> prepare(Object deliveryId,Map<String,Object> metadata) throws JsonProcessingException {
        System.out.println("in DeliveryHistoryRetriever###############");
        //TODO: Need to change it to all records prior to upto 2 years
        final int minimumWeight=(Integer)metadata.get("MIN_WEIGHT");
        final int maximumWeight=(Integer)metadata.get("MAX_WEIGHT");

        Iterable<DeliveryActualsView> deliveriesMatchFilterCriteria = deliveryActualsViewRepository.findByDeliveryActualsVersionId_WeightRangeMinAndDeliveryActualsVersionId_weightRangeMax(minimumWeight,maximumWeight);
        List<DataFrameVO> deliveryWeightRecords = new ArrayList<>();
        deliveriesMatchFilterCriteria.forEach(deliveryActuals -> {
            DataFrameVO dataFrameVO = new DataFrameVO(deliveryActuals.getDeliveryActualsVersionId().getDeliveryDate(), "deliveries", deliveryActuals.getDeliveryCount(), AggregationType.DAILY_NEW);
            deliveryWeightRecords.add(dataFrameVO);
        });
        return deliveryWeightRecords;
    }

    @Override
    public void marshallSendAndReceive(Object id, Map<String,Object> metadata) {
        super.marshallSendAndReceive(id,metadata,url);
    }
}
