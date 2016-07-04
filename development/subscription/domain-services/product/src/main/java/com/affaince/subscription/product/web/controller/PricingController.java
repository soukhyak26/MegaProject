package com.affaince.subscription.product.web.controller;

import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductView;
import com.affaince.subscription.product.services.forecast.*;
import com.google.common.collect.ImmutableList;
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
@RequestMapping(value = "/product")
@Component

public class PricingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductViewRepository productViewRepository;
    @Autowired
    DemandForecasterChain demandForecasterChain;
    @Autowired
    public PricingController() {
        //this.productViewRepository = productViewRepository;
    }
    @RequestMapping(method= RequestMethod.GET, value="/pricing/findall")
    @Produces("application/json")
    public ResponseEntity<Object> findAllProducts(){
        List<ProductView> target= new ArrayList<>();
        productViewRepository.findAll().forEach(target::add);
        return new ResponseEntity<Object>(ImmutableList.of(target),HttpStatus.CREATED);
    }
    @RequestMapping(value ="/forecastdemand/{productid}" )
    public ResponseEntity<Object> foreCastDemand(@PathVariable String productId){
        ProductDemandForecaster forecaster1 = new SimpleMovingAverageDemandForecaster();
        ProductDemandForecaster forecaster2 = new SimpleExponentialSmoothingDemandForecaster();
        ProductDemandForecaster forecaster3 = new TripleExponentialSmoothingDemandForecaster();
        ProductDemandForecaster forecaster4 = new ARIMABasedDemandForecaster();
        forecaster1.addNextForecaster(forecaster2);
        forecaster2.addNextForecaster(forecaster3);
        forecaster3.addNextForecaster(forecaster4);
        demandForecasterChain.addForecaster(forecaster1);
        demandForecasterChain.forecast( productId);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
