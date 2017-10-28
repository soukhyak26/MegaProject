package com.affaince.subscription.product.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.service.interpolate.InterpolatorChain;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.type.ProductReadinessStatus;
import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.common.vo.*;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.command.*;
import com.affaince.subscription.product.query.predictions.ProductHistoryRetriever;
import com.affaince.subscription.product.query.repository.*;
import com.affaince.subscription.product.query.view.*;
import com.affaince.subscription.product.validator.ProductConfigurationValidator;
import com.affaince.subscription.product.vo.ManualProductForecastHelper;
import com.affaince.subscription.product.vo.ProductTargetParameters;
import com.affaince.subscription.product.web.exception.ProductReadinessException;
import com.affaince.subscription.product.web.request.*;
import com.affaince.subscription.query.exception.ProductForecastAlreadyExistsException;
import com.affaince.subscription.product.web.exception.ProductForecastModificationException;
import com.affaince.subscription.product.web.exception.ProductNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.Days;
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
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;
    private final ProductPseudoActualsViewRepository productPseudoActualsViewRepository;
    private final ProductConfigurationViewRepository productConfigurationViewRepository;
    private final TargetSettingViewRepository targetSettingViewRepository;
    private final SubscriptionCommandGateway commandGateway;
    private final ProductHistoryRetriever productHistoryRetriever;
    private final ManualProductForecastHelper manualProductForecastHelper;


    @Autowired
    public ForecastController(ProductViewRepository productViewRepository, ProductForecastViewRepository productForecastViewRepository,ProductPseudoActualsViewRepository productPseudoActualsViewRepository, ProductConfigurationViewRepository productConfigurationViewRepository,ProductActivationStatusViewRepository productActivationStatusViewRepository, TargetSettingViewRepository targetSettingViewRepository, ProductHistoryRetriever productHistoryRetriever,ManualProductForecastHelper manualProductForecastHelper,SubscriptionCommandGateway commandGateway) {
        this.productViewRepository = productViewRepository;
        this.productForecastViewRepository = productForecastViewRepository;
        this.productPseudoActualsViewRepository = productPseudoActualsViewRepository;
        this.productActivationStatusViewRepository=productActivationStatusViewRepository;
        this.productConfigurationViewRepository = productConfigurationViewRepository;
        this.targetSettingViewRepository = targetSettingViewRepository;
        this.productHistoryRetriever = productHistoryRetriever;
        this.manualProductForecastHelper=manualProductForecastHelper;
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

    @RequestMapping(method = RequestMethod.PUT, value = "/predict/{productid}/{metrictype}")
    public ResponseEntity<String> forecastDemandAndChurn(@PathVariable("productid") String productId,@PathVariable("metrictype") String metricType ) throws Exception {
        //hardcoded productanalyser id as 1
        final int productAnalyserId=1;
        EntityMetricType entityMetricType=null;
        switch(metricType){
            case "new":
                entityMetricType=EntityMetricType.NEW;
                break;
            case "churn":
                entityMetricType=EntityMetricType.CHURN;
                break;
            case "total":
                entityMetricType=EntityMetricType.TOTAL;
                break;
            default:
                entityMetricType=EntityMetricType.TOTAL;
        }
        UpdateForecastFromActualsCommand command = new UpdateForecastFromActualsCommand(productAnalyserId,productId,entityMetricType, SysDate.now());
        commandGateway.executeAsync(command);
        return new ResponseEntity<String>(productId, HttpStatus.OK);
    }

    //API to add forecast manually
    @RequestMapping(method = RequestMethod.PUT, value = "addforecast/{productid}")
    @Consumes("application/json")
    public ResponseEntity<Object> addForecast(@RequestBody @Valid AddForecastParametersRequest request,
                                              @PathVariable("productid") String productId) throws Exception {
        manualProductForecastHelper.addForecast(productId,request.getProductForecastParameters(),SysDate.now());
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
        List<ProductForecastView> existingForecastViews = this.productForecastViewRepository.findByForecastVersionId_ProductIdAndEndDateBetween(productId, request.getStartDate(), request.getEndDate());
        ProductForecastView modifiedView = null;
        //Set status of earlier forecast to OVVERRIDEN and insert new forecast
        if (null != existingForecastViews && existingForecastViews.size() == 0) {
            for (ProductForecastView eachView : existingForecastViews) {
                //this.productForecastViewRepository.delete(eachView);
                eachView.setForecastContentStatus(ForecastContentStatus.EXPIRED);
                this.productForecastViewRepository.save(eachView);
                modifiedView = new ProductForecastView(new ForecastVersionId(productId, request.getStartDate(),SysDate.now()), request.getEndDate(), request.getNumberofNewSubscriptions(), request.getNumberOfChurnedSubscriptions(), request.getNumberOfTotalSubscriptions());
            }
        } else {
            throw ProductForecastModificationException.build(productId, request.getStartDate(), request.getEndDate());
        }
        //this has to change
        productForecastViewRepository.save(modifiedView);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{productid}")
    public String findProductForecastByProductId(@PathVariable("productid") String productId) throws JsonProcessingException {
        final List<ProductForecastView> productForecastViews = new ArrayList<>();
        final Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        productForecastViewRepository.findByForecastVersionId_ProductId(productId, sort).forEach
                (productForecastMetricsView -> productForecastViews.add(productForecastMetricsView));
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(productForecastViews);
    }

    @RequestMapping(method = RequestMethod.GET, value = "lastforecast/totalsubscription/{productid}")
    public Long findActiveProductForecastByProductId(@PathVariable("productid") String productId) throws JsonProcessingException {
        final List<ProductForecastView> productForecastViews = new ArrayList<>();
        final Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        productForecastViewRepository.
                findByForecastVersionId_ProductIdAndForecastContentStatusOrderByForecastVersionId_FromDateAsc
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
