package com.affaince.subscription.product.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.command.UpdateForecastFromActualsCommand;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
import com.affaince.subscription.product.services.forecast.DemandForecasterChain;
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
 * Created by mandar on 04-07-2016.
 */
@RestController
@RequestMapping(value = "/forecast")
@Component
public class ForecastController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private SubscriptionCommandGateway commandGateway;
    @Autowired
    private ProductForecastMetricsViewRepository productForecastMetricsViewRepository;
    @Autowired
    private ProductActualMetricsViewRepository productActualMetricsViewRepository;

    @Autowired
    ProductViewRepository productViewRepository;
    @Autowired
    private DemandForecasterChain demandForecasterChain;

    @RequestMapping(method= RequestMethod.GET, value="/findall")
    @Produces("application/json")
    public ResponseEntity<List<String>> findAllProducts() {
        List<String> target = new ArrayList<>();
        productViewRepository.findAll().forEach((item) -> {
            target.add(item.getProductId());
        });
        return new ResponseEntity<List<String>>(target, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/predict/{productId}")
    public ResponseEntity<String> forecastDemandAndChurn(@PathVariable String productId) throws Exception {
        //demandForecasterChain.buildForecasterChain(productForecastMetricsViewRepository, productActualMetricsViewRepository);
        UpdateForecastFromActualsCommand command = new UpdateForecastFromActualsCommand(productId, org.joda.time.LocalDate.now());
        commandGateway.executeAsync(command);
        return new ResponseEntity<String>(productId, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pricebucket/all")
    public ResponseEntity<List<ProductForecastMetricsView>> findAllForecastedPriceBuckets() {
        final List<ProductForecastMetricsView> forecastedPriceBucketsViews = new ArrayList<>();
        productForecastMetricsViewRepository.findAll().forEach
                (productForecastMetricsView -> forecastedPriceBucketsViews.add(productForecastMetricsView));
        return new ResponseEntity <List<ProductForecastMetricsView>> (forecastedPriceBucketsViews, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/productforecastmetrics/{productid}")
    public ResponseEntity <List<ProductForecastMetricsView>> findProductForecastMetricsByProductId (@PathVariable String productId) {
        final List<ProductForecastMetricsView> forecastedPriceBucketsViews = new ArrayList<>();
        final Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        productForecastMetricsViewRepository.findByProductVersionId_ProductId(productId, sort).forEach
                ( productForecastMetricsView -> forecastedPriceBucketsViews.add(productForecastMetricsView));
        return new ResponseEntity <List<ProductForecastMetricsView>> (forecastedPriceBucketsViews, HttpStatus.OK);
    }
}
