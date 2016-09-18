package com.affaince.subscription.product.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.type.ProductForecastStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.command.UpdateForecastFromActualsCommand;
import com.affaince.subscription.product.command.UpdatePseudoActualsFromActualsCommand;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.query.repository.ProductPseudoActualsViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductConfigurationView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.query.view.ProductPseudoActualsView;
import com.affaince.subscription.product.query.view.ProductView;
import com.affaince.subscription.product.vo.ProductForecastParameter;
import com.affaince.subscription.product.web.exception.ProductForecastAlreadyExistsException;
import com.affaince.subscription.product.web.exception.ProductForecastModificationException;
import com.affaince.subscription.product.web.exception.ProductNotFoundException;
import com.affaince.subscription.product.web.request.AddForecastParametersRequest;
import com.affaince.subscription.product.web.request.NextCalendarRequest;
import com.affaince.subscription.product.web.request.UpdateForecastRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
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
    private final ProductViewRepository productViewRepository;
    private final ProductForecastViewRepository productForecastViewRepository;
    private final ProductPseudoActualsViewRepository productPseudoActualsViewRepository;
    private final ProductConfigurationViewRepository productConfigurationViewRepository;
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public ForecastController(ProductViewRepository productViewRepository, ProductForecastViewRepository productForecastViewRepository, ProductPseudoActualsViewRepository productPseudoActualsViewRepository, ProductConfigurationViewRepository productConfigurationViewRepository, SubscriptionCommandGateway commandGateway) {
        this.productViewRepository = productViewRepository;
        this.productForecastViewRepository = productForecastViewRepository;
        this.productPseudoActualsViewRepository = productPseudoActualsViewRepository;
        this.productConfigurationViewRepository = productConfigurationViewRepository;
        this.commandGateway = commandGateway;
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

    @RequestMapping(method = RequestMethod.PUT, value = "/predictforecast/{productId}")
    public ResponseEntity<String> forecastDemandAndChurn(@PathVariable String productId) throws Exception {
        UpdateForecastFromActualsCommand command = new UpdateForecastFromActualsCommand(productId, SysDate.now());
        commandGateway.executeAsync(command);
        return new ResponseEntity<String>(productId, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/predictstepforecast/{productId}")
    public ResponseEntity<String> forecastPseudoActualDemandAndChurn(@PathVariable String productId) throws Exception {
        UpdatePseudoActualsFromActualsCommand command = new UpdatePseudoActualsFromActualsCommand(productId, SysDate.now());
        commandGateway.executeAsync(command);
        return new ResponseEntity<String>(productId, HttpStatus.OK);
    }

    //API to add forecast manually
    @RequestMapping(method = RequestMethod.PUT, value = "addforecast/{productId}")
    @Consumes("application/json")
    public ResponseEntity<Object> addForecast(@RequestBody @Valid AddForecastParametersRequest request,
                                              @PathVariable String productId) throws Exception {
        ProductView productView = this.productViewRepository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }

        ProductForecastParameter[] forecastParameters = request.getProductForecastParameters();
        for (ProductForecastParameter parameter : forecastParameters) {
            List<ProductForecastView> existingForecastViews = this.productForecastViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(productId, parameter.getStartDate(), parameter.getEndDate());
            //forecast should not be newly added if it already exists in the view
            if (null != existingForecastViews && existingForecastViews.size() > 0) {
                throw ProductForecastAlreadyExistsException.build(productId, parameter.getStartDate(), parameter.getEndDate());
            }
            ProductForecastView productForecastView = new ProductForecastView(new ProductVersionId(productId, parameter.getStartDate()), parameter.getEndDate(), parameter.getNumberofNewSubscriptions(), parameter.getNumberOfChurnedSubscriptions(), parameter.getNumberOfTotalSubscriptions(), ProductForecastStatus.ACTIVE);
            productForecastViewRepository.save(productForecastView);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    //API to override existing forecast manually
    @RequestMapping(method = RequestMethod.PUT, value = "updateforecast/{productId}")
    @Consumes("application/json")
    public ResponseEntity<Object> updateForecast(@RequestBody @Valid UpdateForecastRequest request,
                                                 @PathVariable String productId) throws Exception {
        ProductView productView = this.productViewRepository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }
        List<ProductForecastView> existingForecastViews = this.productForecastViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(productId, request.getStartDate(), request.getEndDate());
        ProductForecastView modifiedView = null;
        //Set status of earlier forecast to OVVERRIDEN and insert new forecast
        if (null != existingForecastViews && existingForecastViews.size() == 0) {
            for (ProductForecastView eachView : existingForecastViews) {
                //this.productForecastViewRepository.delete(eachView);
                eachView.setProductForecastStatus(ProductForecastStatus.EXPIRED);
                this.productForecastViewRepository.save(eachView);
                modifiedView = new ProductForecastView(new ProductVersionId(productId, request.getStartDate()), request.getEndDate(), request.getNumberofNewSubscriptions(), request.getNumberOfChurnedSubscriptions(), request.getNumberOfTotalSubscriptions(), ProductForecastStatus.ACTIVE);
            }
        } else {
            throw ProductForecastModificationException.build(productId, request.getStartDate(), request.getEndDate());
        }
        //this has to change
        productForecastViewRepository.save(modifiedView);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    //API to add immediate forecasts manually
    @RequestMapping(method = RequestMethod.PUT, value = "addstepforecast/{productId}")
    @Consumes("application/json")
    public ResponseEntity<Object> addPseudoActuals(@RequestBody @Valid AddForecastParametersRequest request,
                                                   @PathVariable String productId) throws Exception {
        ProductView productView = this.productViewRepository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }
        ProductForecastParameter[] forecastParameters = request.getProductForecastParameters();
        for (ProductForecastParameter parameter : forecastParameters) {
            List<ProductPseudoActualsView> existingPseudoActualsViews = this.productPseudoActualsViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(productId, parameter.getStartDate(), parameter.getEndDate());
            //forecast should not be newly added if it already exists in the view
            if (null != existingPseudoActualsViews && existingPseudoActualsViews.size() > 0) {
                throw ProductForecastAlreadyExistsException.build(productId, parameter.getStartDate(), parameter.getEndDate());
            }
            ProductPseudoActualsView productPseudoActualsView = new ProductPseudoActualsView(new ProductVersionId(productId, parameter.getStartDate()), parameter.getEndDate(), parameter.getNumberofNewSubscriptions(), parameter.getNumberOfChurnedSubscriptions(), parameter.getNumberOfTotalSubscriptions());
            productPseudoActualsView.setProductForecastStatus(ProductForecastStatus.ACTIVE);
            productPseudoActualsViewRepository.save(productPseudoActualsView);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }


    //API to override existing  immediate forecast manually
    @RequestMapping(method = RequestMethod.PUT, value = "updatestepforecast/{productId}")
    @Consumes("application/json")
    public ResponseEntity<Object> updatePseudoActuals(@RequestBody @Valid UpdateForecastRequest request,
                                                      @PathVariable String productId) throws Exception {
        ProductView productView = this.productViewRepository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }
        List<ProductPseudoActualsView> existingPseudoActualsViews = this.productPseudoActualsViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(productId, request.getStartDate(), request.getEndDate());
        ProductPseudoActualsView modifiedView = null;
        //Set status of earlier forecast to OVVERRIDEN and insert new forecast
        if (null != existingPseudoActualsViews && existingPseudoActualsViews.size() == 0) {
            for (ProductPseudoActualsView eachView : existingPseudoActualsViews) {
                //this.productForecastViewRepository.delete(eachView);
                eachView.setProductForecastStatus(ProductForecastStatus.EXPIRED);
                this.productPseudoActualsViewRepository.save(eachView);
                modifiedView = new ProductPseudoActualsView(new ProductVersionId(productId, request.getStartDate()), request.getEndDate(), request.getNumberofNewSubscriptions(), request.getNumberOfChurnedSubscriptions(), request.getNumberOfTotalSubscriptions());
                modifiedView.setProductForecastStatus(ProductForecastStatus.ACTIVE);
            }
        } else {
            throw ProductForecastModificationException.build(productId, request.getStartDate(), request.getEndDate());
        }
        //this has to change
        productPseudoActualsViewRepository.save(modifiedView);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{productId}")
    public String findProductForecastByProductId(@PathVariable String productId) throws JsonProcessingException {
        final List<ProductForecastView> productForecastViews = new ArrayList<>();
        final Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        productForecastViewRepository.findByProductVersionId_ProductId(productId, sort).forEach
                (productForecastMetricsView -> productForecastViews.add(productForecastMetricsView));
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(productForecastViews);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "setnextcalendar/forecast/{productId}")
    @Consumes("application/json")
    public ResponseEntity<Object> setCalendarForNextForecast(@RequestBody @Valid NextCalendarRequest request, @PathVariable String productId) {
        ProductConfigurationView productConfigurationView = this.productConfigurationViewRepository.findOne(productId);
        productConfigurationView.setNextForecastDate(request.getNextForecastDate());
        this.productConfigurationViewRepository.save(productConfigurationView);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
