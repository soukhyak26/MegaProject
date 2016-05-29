package com.affaince.subscription.product.registration.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.type.SensitivityCharacteristic;
import com.affaince.subscription.product.registration.command.AddCurrentOfferedPriceCommand;
import com.affaince.subscription.product.registration.command.RegisterProductCommand;
import com.affaince.subscription.product.registration.command.SetProductConfigurationCommand;
import com.affaince.subscription.product.registration.command.UpdateProductStatusCommand;
import com.affaince.subscription.product.registration.query.repository.ForecastedPriceBucketViewRepository;
import com.affaince.subscription.product.registration.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.registration.query.repository.ProductViewRepository;
import com.affaince.subscription.product.registration.query.view.ForecastedPriceBucketsView;
import com.affaince.subscription.product.registration.query.view.ProductForecastMetricsView;
import com.affaince.subscription.product.registration.query.view.ProductView;
import com.affaince.subscription.product.registration.vo.ForecastedPriceParameter;
import com.affaince.subscription.product.registration.web.exception.ProductNotFoundException;
import com.affaince.subscription.product.registration.web.request.*;
import com.google.common.collect.ImmutableMap;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@RestController
@RequestMapping(value = "/product")
@Component
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final SubscriptionCommandGateway commandGateway;
    private final ProductViewRepository repository;
    private final ProductForecastMetricsViewRepository productForecastMetricsViewRepository;
    private final ForecastedPriceBucketViewRepository forecastedPriceBucketViewRepository;

    @Autowired
    public ProductController(SubscriptionCommandGateway commandGateway, ProductViewRepository repository, ProductForecastMetricsViewRepository productForecastMetricsViewRepository, ForecastedPriceBucketViewRepository forecastedPriceBucketViewRepository) {
        this.commandGateway = commandGateway;
        this.repository = repository;
        this.productForecastMetricsViewRepository = productForecastMetricsViewRepository;
        this.forecastedPriceBucketViewRepository = forecastedPriceBucketViewRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Object> registerProduct(@RequestBody @Valid RegisterProductRequest request) throws Exception {
        Map<SensitivityCharacteristic, Double> receivedSensitivityCharactersistic = request.getSensitiveTo();
        if (null == receivedSensitivityCharactersistic) {
            receivedSensitivityCharactersistic = new HashMap<>();
            receivedSensitivityCharactersistic.put(SensitivityCharacteristic.NONE, 1.0);
        }
        RegisterProductCommand createCommand = new RegisterProductCommand(
                request.getProductId(),
                request.getProductName(),
                request.getCategoryId(),
                request.getSubCategoryId(),
                request.getQuantity(),
                request.getQuantityUnit(),
                Arrays.asList(request.getSubstitutes()),
                Arrays.asList(request.getComplements()),
                receivedSensitivityCharactersistic
        );
        try {
            this.commandGateway.executeAsync(createCommand);
        } catch (Exception e) {
            throw e;
        }
        ProductController.LOGGER.info("Create product command send to Command gateway with Id: " + createCommand.getProductId());
        return new ResponseEntity<Object>(ImmutableMap.of("id", request.getProductId()), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{productId}")
    @Consumes("application/json")
    public ResponseEntity<Object> updateProductStatus(@RequestBody @Valid UpdateProductStatusRequest request, @PathVariable String productId) throws Exception {
        ProductView productView = this.repository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }
        UpdateProductStatusCommand command = new UpdateProductStatusCommand(
                productId,
                request.getCurrentPurchasePrice(),
                request.getCurrentMRP(),
                request.getCurrentStockInUnits(),
                LocalDate.now()
        );
        try {
            this.commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addprojectionparameters/{productId}")
    @Consumes("application/json")
    public ResponseEntity<Object> addProjectionParameters(@RequestBody @Valid AddForecastParametersRequest request,
                                                          @PathVariable String productId) throws Exception {
        ProductView productView = this.repository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }
        ForecastedPriceParameter[] forecastParameters = request.getForecastedPriceParameters();
        for (ForecastedPriceParameter parameter : forecastParameters) {
/*
            AddForecastParametersCommand command = new AddForecastParametersCommand(productId, parameter);

            try {
                this.commandGateway.executeAsync(command);
            } catch (Exception e) {
                throw e;
            }
*/
            ProductForecastMetricsView productForecastMetricsView = new ProductForecastMetricsView(productId, parameter.getFromDate(), parameter.getToDate(), parameter.getDemandDensity(), parameter.getAverageDemandPerSubscriber(), parameter.getNumberofNewSubscriptions(), parameter.getNumberOfChurnedSubscriptions());
            productForecastMetricsViewRepository.save(productForecastMetricsView);

            ForecastedPriceBucketsView forecastedPriceBucketsView = new ForecastedPriceBucketsView(productId, parameter.getFromDate(), parameter.getToDate(), parameter.getPurchasePricePerUnit(), parameter.getMRP(), parameter.getNumberofNewSubscriptions(), parameter.getNumberOfChurnedSubscriptions());
            forecastedPriceBucketViewRepository.save(forecastedPriceBucketsView);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/setcurrentofferedprice/{productId}")
    @Consumes("application/json")
    public ResponseEntity<Object> setCurrentOfferedPrice(@PathVariable String productId, @RequestBody @Valid AddCurrentOfferedPriceRequest request) throws Exception {
        ProductView productView = this.repository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }
        AddCurrentOfferedPriceCommand command = new AddCurrentOfferedPriceCommand(productId, request.getCurrentOfferedPrice());
        try {
            this.commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/setproductconfig/{productId}")
    @Consumes("application/json")
    public ResponseEntity<Object> setProductConfiguration(@PathVariable String productId, @RequestBody @Valid ProductConfigurationRequest request) throws Exception {
        ProductView productView = this.repository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }

        SetProductConfigurationCommand command = new SetProductConfigurationCommand(
                productId, request.getDemandCurvePeriod(), request.getRevenueChangeThresholdForPriceChange(),
                request.isCrossPriceElasticityConsidered(), request.isAdvertisingExpensesConsidered(),
                Arrays.asList(request.getDemandWiseProfitSharingRules())
        );
        try {
            this.commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
