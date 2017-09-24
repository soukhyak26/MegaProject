package com.affaince.subscription.product.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.common.vo.EntityType;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.command.*;
import com.affaince.subscription.product.query.predictions.ProductHistoryRetriever;
import com.affaince.subscription.product.query.repository.*;
import com.affaince.subscription.product.query.view.*;
import com.affaince.subscription.product.vo.ProductTargetParameters;
import com.affaince.subscription.product.web.request.*;
import com.affaince.subscription.query.exception.ProductForecastAlreadyExistsException;
import com.affaince.subscription.product.web.exception.ProductForecastModificationException;
import com.affaince.subscription.product.web.exception.ProductNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.LocalDate;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 04-07-2016.
 */
@RestController
@RequestMapping(value = "/forecast")
@Component
public class ForecastController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastController.class);
    private final ProductViewRepository productViewRepository;
    private final ProductForecastViewRepository productForecastViewRepository;
    //private final ProductPseudoActualsViewRepository productPseudoActualsViewRepository;
    private final ProductConfigurationViewRepository productConfigurationViewRepository;
    private final TargetSettingViewRepository targetSettingViewRepository;
    private final SubscriptionCommandGateway commandGateway;
    private final ProductHistoryRetriever productHistoryRetriever;

    @Autowired
    public ForecastController(ProductViewRepository productViewRepository, ProductForecastViewRepository productForecastViewRepository, ProductConfigurationViewRepository productConfigurationViewRepository, TargetSettingViewRepository targetSettingViewRepository, ProductHistoryRetriever productHistoryRetriever,SubscriptionCommandGateway commandGateway) {
        this.productViewRepository = productViewRepository;
        this.productForecastViewRepository = productForecastViewRepository;
        //this.productPseudoActualsViewRepository = productPseudoActualsViewRepository;
        this.productConfigurationViewRepository = productConfigurationViewRepository;
        this.targetSettingViewRepository = targetSettingViewRepository;
        this.productHistoryRetriever = productHistoryRetriever;
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

    @RequestMapping(method = RequestMethod.PUT, value = "/predictforecast/{productid}")
    public ResponseEntity<String> forecastDemandAndChurn(@PathVariable("productid") String productId) throws Exception {
        UpdateForecastFromActualsCommand command = new UpdateForecastFromActualsCommand(productId, SysDate.now());
        commandGateway.executeAsync(command);
        return new ResponseEntity<String>(productId, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/predictstepforecast/{productid}")
    public ResponseEntity<String> forecastPseudoActualDemandAndChurn(@PathVariable("productid") String productId) throws Exception {
        UpdatePseudoActualsFromActualsCommand command = new UpdatePseudoActualsFromActualsCommand(productId, SysDate.now());
        commandGateway.executeAsync(command);
        return new ResponseEntity<String>(productId, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/predict/{productid}")
    public ResponseEntity<String> forecastDemand(@PathVariable("productid") String productId) throws Exception {
        Map<String,Object> metadata= new HashMap<>();
        metadata.put("ENTITY_TYPE", EntityType.PRODUCT);
        metadata.put("ENTITY_METRIC_TYPE", EntityMetricType.TOTAL);
        productHistoryRetriever.marshallSendAndReceive(productId,metadata);
        return new ResponseEntity<String>(productId, HttpStatus.OK);
    }


    //API to add forecast manually
    @RequestMapping(method = RequestMethod.PUT, value = "addforecast/{productid}")
    @Consumes("application/json")
    public ResponseEntity<Object> addForecast(@RequestBody @Valid AddForecastParametersRequest request,
                                              @PathVariable("productid") String productId) throws Exception {
        ProductView productView = this.productViewRepository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }
        AddManualForecastCommand command = new AddManualForecastCommand(productId, request.getProductForecastParameters(),SysDate.now());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    //API to add Targets manually
    @RequestMapping(method = RequestMethod.PUT, value = "settargets/{productid}")
    @Consumes("application/json")
    public ResponseEntity<Object> setTargets(@RequestBody @Valid SetTargetParameterRequest request,
                                             @PathVariable("productid") String productId) throws Exception {
        ProductView productView = this.productViewRepository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }

        ProductTargetParameters[] targetParameters = request.getProductTargetParameters();
        LocalDate firstStartDate = null;
        LocalDate lastEndDate = null;
        for (ProductTargetParameters parameter : targetParameters) {
            List<TargetSettingView> existingTargetViews = this.targetSettingViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(productId, parameter.getStartDate(), parameter.getEndDate());
            //forecast should not be newly added if it already exists in the view
            if (null != existingTargetViews && existingTargetViews.size() > 0) {
                throw ProductForecastAlreadyExistsException.build(productId, parameter.getStartDate(), parameter.getEndDate());
            }
            TargetSettingView targetSettingView = new TargetSettingView(new ProductVersionId(productId, parameter.getStartDate()), parameter.getEndDate(), parameter.getNumberofNewSubscriptions(), parameter.getNumberOfChurnedSubscriptions(), parameter.getNumberOfTotalSubscriptions(), parameter.getFixedExpensesPerPeriod(), parameter.getVariableExpensesPerPeriod());
            targetSettingViewRepository.save(targetSettingView);
            if (null == firstStartDate) {
                firstStartDate = parameter.getStartDate();
            }
            lastEndDate = parameter.getEndDate();
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    //API to override existing forecast manually
    @RequestMapping(method = RequestMethod.PUT, value = "updateforecast/{productid}")
    @Consumes("application/json")
    public ResponseEntity<Object> updateForecast(@RequestBody @Valid UpdateForecastRequest request,
                                                 @PathVariable("productid") String productId) throws Exception {
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
                eachView.setForecastContentStatus(ForecastContentStatus.EXPIRED);
                this.productForecastViewRepository.save(eachView);
                modifiedView = new ProductForecastView(new ProductVersionId(productId, request.getStartDate()), request.getEndDate(), request.getNumberofNewSubscriptions(), request.getNumberOfChurnedSubscriptions(), request.getNumberOfTotalSubscriptions(),SysDate.now());
            }
        } else {
            throw ProductForecastModificationException.build(productId, request.getStartDate(), request.getEndDate());
        }
        //this has to change
        productForecastViewRepository.save(modifiedView);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    //MOSTLY NOTION OF PSEUDOACTUALS IS NOT NEEDED
/*
    //API to add immediate forecasts manually
    @RequestMapping(method = RequestMethod.PUT, value = "addstepforecast/{productid}")
    @Consumes("application/json")
    public ResponseEntity<Object> addPseudoActuals(@RequestBody @Valid AddForecastParametersRequest request,
                                                   @PathVariable("productid") String productId) throws Exception {
        ProductView productView = this.productViewRepository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.client(productId);
        }
        ProductForecastParameter[] forecastParameters = request.getProductForecastParameters();
        LocalDateTime firstStartDate = null;
        LocalDateTime lastEndDate = null;
        Sort endDateSort=new Sort(Sort.Direction.DESC, "endDate");
        long totalSubscriptions=0;
        for (ProductForecastParameter parameter : forecastParameters) {
            List<ProductPseudoActualsView> existingPseudoActualsViews = this.productPseudoActualsViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(productId, parameter.getFromDate(), parameter.getEndDate());
            //forecast should not be newly added if it already exists in the view
            if (null != existingPseudoActualsViews && existingPseudoActualsViews.size() > 0) {
                throw ProductForecastAlreadyExistsException.client(productId, parameter.getFromDate(), parameter.getEndDate());
            }
            List<ProductPseudoActualsView> earlierPseudoActualsViews=this.productPseudoActualsViewRepository.findByProductVersionId_ProductIdAndEndDateLessThan(productId,parameter.getEndDate(),endDateSort);
            totalSubscriptions=earlierPseudoActualsViews.get(0).getTotalNumberOfExistingSubscriptions()+ parameter.getNumberOfNewSubscriptions()-parameter.getNumberOfChurnedSubscriptions();
            if (null == firstStartDate) {
                firstStartDate = parameter.getFromDate();
            }
            lastEndDate = parameter.getEndDate();

            ProductPseudoActualsView productPseudoActualsView = new ProductPseudoActualsView(new ProductVersionId(productId, parameter.getFromDate()), parameter.getEndDate(), parameter.getNumberOfNewSubscriptions(), parameter.getNumberOfChurnedSubscriptions(), totalSubscriptions);
            productPseudoActualsView.setForecastContentStatus(ForecastContentStatus.ACTIVE);
            productPseudoActualsViewRepository.save(productPseudoActualsView);
        }
        AddManualPseudoActualsCommand command = new AddManualPseudoActualsCommand(productId, forecastParameters,totalSubscriptions, firstStartDate, lastEndDate);
        commandGateway.executeAsync(command);

        return new ResponseEntity<Object>(HttpStatus.OK);
    }
*/


    //MOSTLY NOTION OF PSEUDOACTUALS IS NOT NEEDED
/*
    //API to override existing  immediate forecast manually
    @RequestMapping(method = RequestMethod.PUT, value = "updatestepforecast/{productid}")
    @Consumes("application/json")
    public ResponseEntity<Object> updatePseudoActuals(@RequestBody @Valid UpdateForecastRequest request,
                                                      @PathVariable("productid") String productId) throws Exception {
        ProductView productView = this.productViewRepository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.client(productId);
        }
        List<ProductPseudoActualsView> existingPseudoActualsViews = this.productPseudoActualsViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(productId, request.getFromDate(), request.getEndDate());
        ProductPseudoActualsView modifiedView = null;
        //Set status of earlier forecast to OVVERRIDEN and insert new forecast
        if (null != existingPseudoActualsViews && existingPseudoActualsViews.size() == 0) {
            for (ProductPseudoActualsView eachView : existingPseudoActualsViews) {
                //this.productForecastViewRepository.delete(eachView);
                eachView.setForecastContentStatus(ForecastContentStatus.EXPIRED);
                this.productPseudoActualsViewRepository.save(eachView);
                modifiedView = new ProductPseudoActualsView(new ProductVersionId(productId, request.getFromDate()), request.getEndDate(), request.getNumberofNewSubscriptions(), request.getNumberOfChurnedSubscriptions(), request.getNumberOfTotalSubscriptions());
                modifiedView.setForecastContentStatus(ForecastContentStatus.ACTIVE);
            }
        } else {
            throw ProductForecastModificationException.client(productId, request.getFromDate(), request.getEndDate());
        }
        //this has to change
        productPseudoActualsViewRepository.save(modifiedView);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
*/


    @RequestMapping(method = RequestMethod.GET, value = "/{productid}")
    public String findProductForecastByProductId(@PathVariable("productid") String productId) throws JsonProcessingException {
        final List<ProductForecastView> productForecastViews = new ArrayList<>();
        final Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        productForecastViewRepository.findByProductVersionId_ProductId(productId, sort).forEach
                (productForecastMetricsView -> productForecastViews.add(productForecastMetricsView));
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(productForecastViews);
    }

    @RequestMapping(method = RequestMethod.GET, value = "lastforecast/totalsubscription/{productid}")
    public Long findActiveProductForecastByProductId(@PathVariable("productid") String productId) throws JsonProcessingException {
        final List<ProductForecastView> productForecastViews = new ArrayList<>();
        final Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        productForecastViewRepository.
                findByProductVersionId_ProductIdAndForecastContentStatusOrderByProductVersionId_FromDateAsc
                        (productId, ForecastContentStatus.ACTIVE).forEach
                (productForecastMetricsView -> productForecastViews.add(productForecastMetricsView));
        final ObjectMapper objectMapper = new ObjectMapper();
        return productForecastViews.get(productForecastViews.size()-1).getNewSubscriptions();
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/setnextforecastdate/{productid}")
    @Consumes("application/json")
    public ResponseEntity<Object> setCalendarForNextForecast(@RequestBody @Valid NextCalendarRequest request, @PathVariable("productid") String productId) throws Exception {
        UpdateForecastDateCommand updateForecastDateCommand= new UpdateForecastDateCommand(productId,request.getNextForecastDate());
        commandGateway.executeAsync(updateForecastDateCommand);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
