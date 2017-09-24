package com.affaince.subscription.subscriber.query.predictions;

import com.affaince.subscription.common.transport.TransportationTransformer;
import com.affaince.subscription.common.vo.AggregationType;
import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.subscriber.query.repository.DeliveryViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryItem;
import com.affaince.subscription.subscriber.query.view.DeliveryView;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by mandar on 9/23/2017.
 */
public class DeliveryHistoryRetriever extends TransportationTransformer {
    @Autowired
    private DeliveryViewRepository deliveryViewRepository;
    @Value("${subscription.forecast.deliveries.url}")
    private String url;

    public List<DataFrameVO> prepare(Object subscriptionId,Map<String,Object> metadata) throws JsonProcessingException {
        System.out.println("in ProductsHistoryRetriever###############");
        //TODO: Need to change it to all records prior to upto 2 years
        Iterable<DeliveryView> allDeliveries = deliveryViewRepository.findAll();
        Iterable<DeliveryView> deliveriesMatchFilterCriteria=filterDeliveries(allDeliveries,metadata);
        Map<LocalDate,Integer> deliveryCountsPerDate=aggregateDeliveriesPerDate(deliveriesMatchFilterCriteria);
        List<DataFrameVO> deliveryWeightRecords = new ArrayList<>();
        deliveryCountsPerDate.forEach((date,count) -> {
            DataFrameVO dataFrameVO = new DataFrameVO(date, "deliveries", count, AggregationType.DAILY_NEW);
            deliveryWeightRecords.add(dataFrameVO);
        });
        return deliveryWeightRecords;
    }

    private Iterable<DeliveryView> filterDeliveries(Iterable<DeliveryView> actualDeliveries,Map<String,Object> metadata){
        final int minimumWeight=(Integer)metadata.get("MIN_WEIGHT");
        final int maximumWeight=(Integer)metadata.get("MAX_WEIGHT");
        List<DeliveryView> deliveriesMatchingFilterCriteria= new ArrayList<>();
        for(DeliveryView deliveryView:actualDeliveries){
            double deliverywiseWeight=deliveryView.getDeliveryItems().stream().mapToDouble(DeliveryItem::getWeightInGrms).sum();
            if(deliverywiseWeight>=minimumWeight && deliverywiseWeight< maximumWeight ){
                deliveriesMatchingFilterCriteria.add(deliveryView);
            }
        }
        return deliveriesMatchingFilterCriteria;
    }

    private Map<LocalDate,Integer> aggregateDeliveriesPerDate(Iterable<DeliveryView> deliveries){
        Map<LocalDate,Integer> datewiseDeliveriesCount= new TreeMap<>();
        for(DeliveryView deliveryView:deliveries){
            if(datewiseDeliveriesCount.containsKey(deliveryView.getDeliveryDate())){
                datewiseDeliveriesCount.put(deliveryView.getDeliveryDate(),datewiseDeliveriesCount.get(deliveryView.getDeliveryDate()) + 1);
            }else{
                datewiseDeliveriesCount.put(deliveryView.getDeliveryDate(),1);
            }
        }
        return datewiseDeliveriesCount;
    }
    @Override
    public void marshallSendAndReceive(Object id, Map<String,Object> metadata) {
        super.marshallSendAndReceive(id,metadata,url);
    }


}
