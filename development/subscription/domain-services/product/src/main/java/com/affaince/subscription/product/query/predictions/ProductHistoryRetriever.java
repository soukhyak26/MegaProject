package com.affaince.subscription.product.query.predictions;

import com.affaince.subscription.common.transport.TransportationTransformer;
import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.common.vo.AggregationType;
import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mandar on 8/30/2017.
 */
@Component
public class ProductHistoryRetriever extends TransportationTransformer {
    @Autowired
    ProductActualsViewRepository productActualsViewRepository;
    @Value("${subscription.forecast.product.url}")
    private String url;

    public List<DataFrameVO> prepare(Object productId,Map<String,Object> metadata) throws JsonProcessingException {
        System.out.println("in ProductsHistoryRetriever###############");
        Iterable<ProductActualsView> actualReacords = productActualsViewRepository.findByProductVersionId_ProductId((String)productId);
        List<DataFrameVO> productHistoricalRecords = new ArrayList<>();
        EntityMetricType entityMetricType=(EntityMetricType)metadata.get("ENTITY_METRIC_TYPE");
        //allProducts.forEach(productActualsView -> {
        for(ProductActualsView productActualsView: actualReacords){
            DataFrameVO dataFrameVO=null;
            switch(entityMetricType) {
                case NEW:
                    dataFrameVO = new DataFrameVO(productActualsView.getEndDate(), "productId", productActualsView.getNewSubscriptions(), AggregationType.DAILY_NEW);
                    break;
                case CHURN:
                    dataFrameVO = new DataFrameVO(productActualsView.getEndDate(), "productId", productActualsView.getChurnedSubscriptions(), AggregationType.DAILY_NEW);
                    break;
                case TOTAL:
                    dataFrameVO = new DataFrameVO(productActualsView.getEndDate(), "productId", productActualsView.getTotalNumberOfExistingSubscriptions(), AggregationType.INCREMENTAL);

            }
            productHistoricalRecords.add(dataFrameVO);
        }


        return productHistoricalRecords;
    }

    @Override
    public void marshallSendAndReceive(Object id,Map<String,Object> metadata) {
        List<DataFrameVO> forecastRecords = super.marshallSendAndReceive(id,metadata,url);

    }
}
