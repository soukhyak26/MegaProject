package com.affaince.subscription.pricing.processor.camel;

import com.affaince.subscription.pricing.processor.FunctionProcessor;
import com.affaince.subscription.pricing.processor.RegressionBasedCostFunctionProcessor;
import com.affaince.subscription.pricing.query.repository.ProductStatisticsViewRepository;
import com.affaince.subscription.pricing.query.view.ProductStatisticsView;
import com.affaince.subscription.pricing.query.view.ProductView;
import com.affaince.subscription.pricing.vo.FunctionCoefficients;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mandark on 28-02-2016.
 */
public class CostFunctionCamelProcessor implements Processor {
    @Autowired
    ProductStatisticsViewRepository productStatisticsViewRepository;
    @Override
    public void process(Exchange exchange) throws Exception {
        ProductView productView =exchange.getIn().getBody(ProductView.class);

        List<ProductStatisticsView> productStats =productStatisticsViewRepository.findByProductMonthlyVersionId_ProductId(productView.getProductId());
        FunctionProcessor processor = new RegressionBasedCostFunctionProcessor();
        FunctionCoefficients coefficients = processor.processFunction(productView.getProductId(), productStats);
        exchange.getIn().setBody(coefficients);
    }
}
