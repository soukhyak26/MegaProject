package com.affaince.subscription.product.web.controller;

import com.affaince.subscription.product.services.triggers.ProductForecastTrigger;
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

import javax.ws.rs.Path;

/**
 * Created by mandar on 11/4/2017.
 */
@RestController
@RequestMapping(value = "product/triggerforecast")
@Component
public class ForecastValidationTriggerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastValidationTriggerController.class);
    private final ProductForecastTrigger productForecastTrigger;
    @Autowired
    public ForecastValidationTriggerController(ProductForecastTrigger productForecastTrigger) {
        this.productForecastTrigger=productForecastTrigger;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/shouldtrigger/{productId}")
    public ResponseEntity<Boolean> shouldTriggerProductForecast(@PathVariable String productId) {
        return new ResponseEntity<Boolean>(productForecastTrigger.shouldTriggerForecast(productId), HttpStatus.OK);
    }


}
