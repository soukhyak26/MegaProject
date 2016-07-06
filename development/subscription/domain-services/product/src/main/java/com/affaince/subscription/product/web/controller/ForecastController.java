package com.affaince.subscription.product.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.command.UpdateForecastFromActualsCommand;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductView;
import com.affaince.subscription.product.services.forecast.*;
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
import java.time.LocalDate;
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
    private final ProductViewRepository productViewRepository;
    private final DemandForecasterChain demandForecasterChain;
    private final SubscriptionCommandGateway commandGateway;
    @Autowired
    private ProductForecastMetricsViewRepository productForecastMetricsViewRepository;
    @Autowired
    private ProductActualMetricsViewRepository productActualMetricsViewRepository;

    @Autowired
    public ForecastController(SubscriptionCommandGateway commandGateway,ProductViewRepository productViewRepository,DemandForecasterChain demandForecasterChain) {
        this.commandGateway=commandGateway;
        this.productViewRepository=productViewRepository;
        this.demandForecasterChain=demandForecasterChain;
    }
    @RequestMapping(method= RequestMethod.GET, value="/findall")
    @Produces("application/json")
    public ResponseEntity<Object> findAllProducts(){
        List<String> target= new ArrayList<>();
        productViewRepository.findAll().forEach((item)->{target.add(item.getProductId());});
        return new ResponseEntity<Object>(target,HttpStatus.CREATED);
    }
    @RequestMapping(method= RequestMethod.PUT,value ="/predict/{productid}" )
    public ResponseEntity<String> forecastDemandAndChurn(@PathVariable String productId) throws Exception{
        demandForecasterChain.buildForecasterChain(productForecastMetricsViewRepository,productActualMetricsViewRepository);
        DemandGrowthAndChurnForecast forecast=demandForecasterChain.forecast( productId);
        UpdateForecastFromActualsCommand command= new UpdateForecastFromActualsCommand(productId, org.joda.time.LocalDate.now(),forecast.getForecastedTotalSubscriptionCount(),forecast.getForecastedChurnedSubscriptionCount());
        commandGateway.executeAsync(command);
        return new ResponseEntity<String>(productId,HttpStatus.OK);
    }

}
