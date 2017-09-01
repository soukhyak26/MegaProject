package com.affaince.subscription.product.query.predictions;

import com.affaince.subscription.common.transport.TransportationTransformer;
import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 8/30/2017.
 */
@Component
public class ProductHistoryRetriever extends TransportationTransformer {
    @Autowired
    ProductActualsViewRepository productActualsViewRepository;
    @Value("${subscription.forecast.product.url}")
    private String url;

    public List<DataFrameVO> prepare(Object productId) throws JsonProcessingException {
        System.out.println("in ProductsHistoryRetriever###############");
        Iterable<ProductActualsView> allProducts = productActualsViewRepository.findByProductVersionId_ProductId((String)productId);
        List<DataFrameVO> productHistoricalRecords = new ArrayList<>();
        allProducts.forEach(productActualsView -> {
            DataFrameVO dataFrameVO=new DataFrameVO(productActualsView.getEndDate(),"productId",productActualsView.getTotalNumberOfExistingSubscriptions());
            productHistoricalRecords.add(dataFrameVO);
        });
        return productHistoricalRecords;
    }

    @Override
    public void marshallSendAndReceive(Object id) {
        super.marshallSendAndReceive(id,url);
    }
}
