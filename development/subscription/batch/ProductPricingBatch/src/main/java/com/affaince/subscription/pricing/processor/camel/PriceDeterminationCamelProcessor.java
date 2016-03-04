package com.affaince.subscription.pricing.processor.camel;

import com.affaince.subscription.pricing.processor.DemandBasedPriceDeterminator;
import com.affaince.subscription.pricing.processor.PriceDeterminator;
import com.affaince.subscription.pricing.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.pricing.query.repository.ProductStatisticsViewRepository;
import com.affaince.subscription.pricing.vo.FunctionCoefficients;
import com.affaince.subscription.pricing.vo.PriceDeterminationCriteria;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandark on 28-02-2016.
 */
public class PriceDeterminationCamelProcessor implements Processor {
    @Autowired
    PriceBucketViewRepository priceBucketViewRepository;
    @Autowired
    ProductStatisticsViewRepository productStatisticsViewRepository;
    @Override
    public void process(Exchange exchange) throws Exception {
        List<FunctionCoefficients> demandAndCosstFunctionCoefficients = exchange.getIn().getBody(ArrayList.class);
        List<CrudRepository> crudRepositories = new ArrayList<CrudRepository>();
        crudRepositories.add(priceBucketViewRepository);
        crudRepositories.add(productStatisticsViewRepository);
        PriceDeterminator priceDeterminator = new DemandBasedPriceDeterminator();
        PriceDeterminationCriteria criteria = new PriceDeterminationCriteria(crudRepositories, demandAndCosstFunctionCoefficients);
        priceDeterminator.calculateOfferedPrice(criteria);
    }
}
