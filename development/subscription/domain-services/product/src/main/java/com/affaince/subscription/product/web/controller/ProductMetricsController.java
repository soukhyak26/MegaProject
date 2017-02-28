package com.affaince.subscription.product.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.command.CalculateRevenueAndProfitCommand;
import com.affaince.subscription.product.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductForecastMetricsForOpExView;
import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 13-09-2016.
 */
@RestController
@RequestMapping(value = "/metrics")
@Component
public class ProductMetricsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final SubscriptionCommandGateway commandGateway;
    private final ProductViewRepository productViewRepository;
    private final ProductForecastMetricsViewRepository productForecastMetricsViewRepository;

    @Autowired
    public ProductMetricsController(ProductViewRepository productViewRepository,ProductForecastMetricsViewRepository productForecastMetricsViewRepository,SubscriptionCommandGateway commandGateway) {
        this.productViewRepository=productViewRepository;
        this.productForecastMetricsViewRepository = productForecastMetricsViewRepository;
        this.commandGateway=commandGateway;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findall")
    @Produces("application/json")
    public ResponseEntity<List<String>> findAllProducts() {
        List<String> target = new ArrayList<>();
        productViewRepository.findAll().forEach((item) -> {
            target.add(item.getProductId());
        });
        return new ResponseEntity<List<String>>(target, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pricebucket/all")
    public String findAllForecastedPriceBuckets() throws JsonProcessingException {
        final List<ProductForecastMetricsForOpExView> productForecastMetricsForOpExViews = new ArrayList<>();
        productForecastMetricsViewRepository.findAll().forEach
                (productForecastMetricsView -> productForecastMetricsForOpExViews.add(
                        new ProductForecastMetricsForOpExView(productForecastMetricsView.getProductVersionId().getProductId()
                                , productForecastMetricsView.getTaggedPriceVersions().first().getMRP()
                                , productForecastMetricsView.getTotalNumberOfExistingSubscriptions())
                ));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(productForecastMetricsForOpExViews);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/productforecastmetrics/{productid}")
    public ResponseEntity<List<ProductForecastMetricsView>> findProductForecastMetricsByProductId(@PathVariable String productId) {
        final List<ProductForecastMetricsView> forecastedPriceBucketsViews = new ArrayList<>();
        final Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        productForecastMetricsViewRepository.findByProductVersionId_ProductId(productId, sort).forEach
                (productForecastMetricsView -> forecastedPriceBucketsViews.add(productForecastMetricsView));
        return new ResponseEntity<List<ProductForecastMetricsView>>(forecastedPriceBucketsViews, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/revenueandprofit/{productid}")
    public ResponseEntity<Object> calculateRevenueAndProfit(@PathVariable String productId) throws Exception{
        CalculateRevenueAndProfitCommand command = new CalculateRevenueAndProfitCommand(productId);
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
