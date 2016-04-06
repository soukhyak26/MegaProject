package com.affaince.subscription.pricing.processor.camel;

import com.affaince.subscription.pricing.processor.RegressionBasedDemandFunctionProcessor;
import com.affaince.subscription.pricing.processor.FunctionProcessor;
import com.affaince.subscription.pricing.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.pricing.query.view.PriceBucketView;
import com.affaince.subscription.pricing.query.view.ProductView;
import com.affaince.subscription.pricing.vo.FunctionCoefficients;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mandark on 28-02-2016.
 */
public class DemandFunctionCamelProcessor implements Processor {
    @Autowired
    PriceBucketViewRepository priceBucketViewRepository;

    @Override
    public void process(Exchange exchange) throws Exception {
        ProductView productView = exchange.getIn().getBody(ProductView.class);
        List<PriceBucketView> priceBucketStats = priceBucketViewRepository.findByProductVersionId_ProductId(productView.getProductId());
        FunctionProcessor processor = new RegressionBasedDemandFunctionProcessor();
        FunctionCoefficients coefficients = processor.processFunction(productView.getProductId(), priceBucketStats);
        exchange.getIn().setBody(coefficients);
    }
}
