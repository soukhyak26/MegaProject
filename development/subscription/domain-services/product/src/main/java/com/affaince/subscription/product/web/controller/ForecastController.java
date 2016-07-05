package com.affaince.subscription.product.web.controller;

import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductView;
import com.affaince.subscription.product.services.forecast.*;
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

   // private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductViewRepository productViewRepository;
    private final DemandForecasterChain demandForecasterChain;
    @Autowired
    public ForecastController(ProductViewRepository productViewRepository,DemandForecasterChain demandForecasterChain) {
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
    public ResponseEntity<String> forecastDemandAndChurn(@PathVariable String productId){
        ProductDemandForecaster forecaster1 = new SimpleMovingAverageDemandForecaster();
        ProductDemandForecaster forecaster2 = new SimpleExponentialSmoothingDemandForecaster();
        ProductDemandForecaster forecaster3 = new TripleExponentialSmoothingDemandForecaster();
        ProductDemandForecaster forecaster4 = new ARIMABasedDemandForecaster();
        forecaster1.addNextForecaster(forecaster2);
        forecaster2.addNextForecaster(forecaster3);
        forecaster3.addNextForecaster(forecaster4);
        demandForecasterChain.addForecaster(forecaster1);
        demandForecasterChain.forecast( productId);
        return new ResponseEntity<String>(productId,HttpStatus.OK);
    }

}
