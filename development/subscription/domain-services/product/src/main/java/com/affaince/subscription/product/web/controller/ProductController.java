package com.affaince.subscription.product.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.type.SensitivityCharacteristic;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.RegisterProductCommand;
import com.affaince.subscription.product.command.UpdateProductStatusCommand;
import com.affaince.subscription.product.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductView;
import com.affaince.subscription.product.web.exception.ProductNotFoundException;
import com.affaince.subscription.product.web.request.RegisterProductRequest;
import com.affaince.subscription.product.web.request.UpdateProductStatusRequest;
import com.affaince.subscription.repository.IdGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.*;

@RestController
@RequestMapping(value = "/product")
@Component
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final SubscriptionCommandGateway commandGateway;
    private final ProductViewRepository productViewRepository;
    private final ProductForecastMetricsViewRepository productForecastMetricsViewRepository;

    @Autowired
    IdGenerator defaultIdGenerator;

    @Autowired
    public ProductController(SubscriptionCommandGateway commandGateway, ProductViewRepository productViewRepository, ProductForecastMetricsViewRepository productForecastMetricsViewRepository) {
        this.commandGateway = commandGateway;
        this.productViewRepository = productViewRepository;
        this.productForecastMetricsViewRepository = productForecastMetricsViewRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Object> registerProduct(@RequestBody @Valid RegisterProductRequest request) throws Exception {
        Map<SensitivityCharacteristic, Double> receivedSensitivityCharactersistic = request.getSensitiveTo();
        if (null == receivedSensitivityCharactersistic) {
            receivedSensitivityCharactersistic = new HashMap<>();
            receivedSensitivityCharactersistic.put(SensitivityCharacteristic.NONE, 1.0);
        }
        String IDString = request.getProductName() + "$" + request.getCategoryId() + "$" + request.getSubCategoryId() + "$" + request.getQuantity();
        final String productId = defaultIdGenerator.generator(IDString);
        RegisterProductCommand createCommand = new RegisterProductCommand(
                productId,
                request.getProductName(),
                request.getCategoryId(),
                request.getSubCategoryId(),
                request.getQuantity(),
                request.getQuantityUnit(),
                Arrays.asList(request.getSubstitutes()),
                Arrays.asList(request.getComplements()),
                receivedSensitivityCharactersistic,
                request.getProductPricingCategory(),
                request.getPurchasePrice(),
                request.getMRP()
        );
        try {
            this.commandGateway.executeAsync(createCommand);
        } catch (Exception e) {
            throw e;
        }
        ProductController.LOGGER.info("Create product command send to Command gateway with product name: " + createCommand.getProductName() + " category:" + request.getCategoryId() + " subcategory: " + request.getSubCategoryId() + " on date: " + SysDate.now());
        return new ResponseEntity<Object>(ImmutableMap.of("id", productId), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{productId}")
    @Consumes("application/json")
    public ResponseEntity<Object> updateProductStatus(@RequestBody @Valid UpdateProductStatusRequest request, @PathVariable String productId) throws Exception {
        ProductView productView = this.productViewRepository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }
        UpdateProductStatusCommand command = new UpdateProductStatusCommand(
                productId,
                request.getCurrentPurchasePrice(),
                request.getCurrentMRP(),
                request.getCurrentStockInUnits(),
                SysDateTime.now()
        );
        try {
            this.commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/all")
    @Produces("application/json")
    public String getAllProducts () throws JsonProcessingException {
        final List <ProductView> productViews = new ArrayList<>();
        productViewRepository.findAll().forEach(productView -> productViews.add(productView));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(productViews);
        //return new ResponseEntity<List<ProductView>>(productViews, HttpStatus.OK);
    }
}
