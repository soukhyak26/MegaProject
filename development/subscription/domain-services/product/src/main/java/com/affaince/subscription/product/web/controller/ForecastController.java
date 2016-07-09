package com.affaince.subscription.product.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.command.UpdateForecastFromActualsCommand;
import com.affaince.subscription.product.query.repository.ForecastedPriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ForecastedPriceBucketsView;
import com.affaince.subscription.product.services.forecast.DemandForecasterChain;
import com.affaince.subscription.product.vo.DemandGrowthAndChurnForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ForecastedPriceBucketViewRepository forecastedPriceBucketViewRepository;

    @Autowired
    ProductViewRepository productViewRepository;
    @Autowired
    private DemandForecasterChain demandForecasterChain;
/*
    @Autowired
    private ForecastedPriceBucketViewRepository forecastedPriceBucketViewRepository;

    public ForecastController(ProductViewRepository productViewRepository, DemandForecasterChain demandForecasterChain, SubscriptionCommandGateway commandGateway, ProductForecastMetricsViewRepository productForecastMetricsViewRepository, ProductActualMetricsViewRepository productActualMetricsViewRepository, ForecastedPriceBucketViewRepository forecastedPriceBucketViewRepository) {
        this.productViewRepository = productViewRepository;
        this.demandForecasterChain = demandForecasterChain;
        this.commandGateway = commandGateway;
        this.productForecastMetricsViewRepository = productForecastMetricsViewRepository;
        this.productActualMetricsViewRepository = productActualMetricsViewRepository;
        this.forecastedPriceBucketViewRepository = forecastedPriceBucketViewRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findall")
*/
    @RequestMapping(method= RequestMethod.GET, value="/findall")
    @Produces("application/json")
    public ResponseEntity<List<String>> findAllProducts() {
        List<String> target = new ArrayList<>();
        productViewRepository.findAll().forEach((item) -> {
            target.add(item.getProductId());
        });
        return new ResponseEntity<List<String>>(target, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/predict/{productid}")
    public ResponseEntity<String> forecastDemandAndChurn(@PathVariable String productId) throws Exception {
        demandForecasterChain.buildForecasterChain(productForecastMetricsViewRepository, productActualMetricsViewRepository);
        DemandGrowthAndChurnForecast forecast = demandForecasterChain.forecast(productId);
        UpdateForecastFromActualsCommand command = new UpdateForecastFromActualsCommand(productId, org.joda.time.LocalDate.now(), forecast.getForecastedTotalSubscriptionCount(), forecast.getForecastedChurnedSubscriptionCount());
        commandGateway.executeAsync(command);
        return new ResponseEntity<String>(productId, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pricebucket/all")
    public ResponseEntity<List<ForecastedPriceBucketsView>> findAllForecastedPriceBuckets() {
        final List<ForecastedPriceBucketsView> forecastedPriceBucketsViews = new ArrayList<>();
        forecastedPriceBucketViewRepository.findAll().forEach
                (forecastedPriceBucketsView -> forecastedPriceBucketsViews.add(forecastedPriceBucketsView));
        return new ResponseEntity <List<ForecastedPriceBucketsView>> (forecastedPriceBucketsViews, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pricebucket/{productid}")
    public ResponseEntity <List<ForecastedPriceBucketsView>> findForecastedPriceBucketsViewByProductId (@PathVariable String productId) {
        final List<ForecastedPriceBucketsView> forecastedPriceBucketsViews = new ArrayList<>();
        forecastedPriceBucketViewRepository.findByProductId(productId).forEach
                (forecastedPriceBucketsView -> forecastedPriceBucketsViews.add(forecastedPriceBucketsView));
        return new ResponseEntity <List<ForecastedPriceBucketsView>> (forecastedPriceBucketsViews, HttpStatus.OK);
    }
}
