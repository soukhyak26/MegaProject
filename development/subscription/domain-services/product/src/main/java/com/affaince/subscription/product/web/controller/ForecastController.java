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
    private final InterpolatorChain interpolatorChain;

    private static final int INTERPOLATE_NEW_SUBSCRIPTIONS = 1;
    private static final int INTERPOLATE_TOTAL_SUBSCRIPTIONS = 2;

    @Autowired
    public ForecastController(ProductViewRepository productViewRepository, ProductForecastViewRepository productForecastViewRepository,ProductPseudoActualsViewRepository productPseudoActualsViewRepository, ProductConfigurationViewRepository productConfigurationViewRepository,ProductActivationStatusViewRepository productActivationStatusViewRepository, TargetSettingViewRepository targetSettingViewRepository, ProductHistoryRetriever productHistoryRetriever,SubscriptionCommandGateway commandGateway,InterpolatorChain interpolatorChain) {
        this.productViewRepository = productViewRepository;
        this.productForecastViewRepository = productForecastViewRepository;
        this.productPseudoActualsViewRepository = productPseudoActualsViewRepository;
        this.productActivationStatusViewRepository=productActivationStatusViewRepository;
        this.productConfigurationViewRepository = productConfigurationViewRepository;
        this.targetSettingViewRepository = targetSettingViewRepository;
        this.productHistoryRetriever = productHistoryRetriever;
        this.commandGateway = commandGateway;
        this.interpolatorChain=interpolatorChain;
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

    @RequestMapping(method = RequestMethod.PUT, value = "/predictstepforecast/{productid}")
    public ResponseEntity<String> forecastPseudoActualDemandAndChurn(@PathVariable("productid") String productId) throws Exception {
        UpdatePseudoActualsFromActualsCommand command = new UpdatePseudoActualsFromActualsCommand(productId, SysDate.now());
        commandGateway.executeAsync(command);
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
/*
        AddManualForecastCommand command = new AddManualForecastCommand(productId, request.getProductForecastParameters(),SysDate.now());
        commandGateway.executeAsync(command);
*/
        final LocalDate forecastDate= SysDate.now();
        final List<ProductStatus> productStatuses = productActivationStatusViewRepository.
                findByProductId(productId).getProductStatuses();
        if (ProductConfigurationValidator.getProductReadinessStatus(productStatuses).contains(
                ProductReadinessStatus.FORECASTABLE
        )) {
            ProductForecastParameter[] forecastParameters = request.getProductForecastParameters();
            LocalDate firstStartDate = null;
            LocalDate lastEndDate = null;Sort endDateSort = new Sort(Sort.Direction.DESC, "endDate");

            long totalSubscriptions = 0;
            for (ProductForecastParameter parameter : forecastParameters) {
                List<ProductForecastView> existingForecastViews = this.productForecastViewRepository.findByForecastVersionId_ProductIdAndEndDateBetween(productId, parameter.getStartDate(), parameter.getEndDate());
                //forecast should not be newly added if it already exists in the view
                if (null != existingForecastViews && existingForecastViews.size() > 0) {
                    throw ProductForecastAlreadyExistsException.build(productId, parameter.getStartDate(), parameter.getEndDate());
                }
                //find forecasts entered earlier to current forecast entry
                List<ProductForecastView> earlierForecastViews = this.productForecastViewRepository.findByForecastVersionId_ProductIdAndEndDateLessThan(productId, parameter.getEndDate(), endDateSort);
                if (earlierForecastViews.isEmpty()) {
                    totalSubscriptions = parameter.getNumberOfNewSubscriptions() - parameter.getNumberOfChurnedSubscriptions();
                } else {
                    totalSubscriptions = earlierForecastViews.get(0).getTotalNumberOfExistingSubscriptions() + parameter.getNumberOfNewSubscriptions() - parameter.getNumberOfChurnedSubscriptions();
                }
                ProductForecastView productForecastView = new ProductForecastView(new ForecastVersionId(productId, parameter.getStartDate(),forecastDate), parameter.getEndDate(), parameter.getNumberOfNewSubscriptions(), parameter.getNumberOfChurnedSubscriptions(), totalSubscriptions);
                productForecastViewRepository.save(productForecastView);
                if (null == firstStartDate) {
                    firstStartDate = parameter.getStartDate();
                }
                lastEndDate = parameter.getEndDate();
            }
            ProductActivationStatusView view = productActivationStatusViewRepository.findOne(productId);
            if (!view.getProductStatuses().contains(ProductStatus.PRODUCT_FORECASTED)) {
                view.addProductStatus(ProductStatus.PRODUCT_FORECASTED);
                productActivationStatusViewRepository.save(view);
            }
            derivePseudoActualsFromForecast(productId,firstStartDate,forecastDate);
        } else {
            ProductReadinessException.build(productId, ProductStatus.PRODUCT_FORECASTED);
        }

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    private double[] interpolateStepForecastFromForecast(String productId, int whomToInterpolate) {
        List<ProductForecastView> registeredForecastValues = productForecastViewRepository.
                findByForecastVersionId_ProductIdAndForecastContentStatusOrderByForecastVersionId_FromDateAsc
                        (productId, ForecastContentStatus.ACTIVE);
        ProductForecastView firstForecastView = registeredForecastValues.get(0);
        LocalDate dateOfPlatformBeginning = firstForecastView.getProductVersionId().getFromDate();
        double[] x = new double[registeredForecastValues.size()];     //day on which interpolated value has been taken
        double[] y = new double[registeredForecastValues.size()];     //interpolated value of total subscription
        int count = 0;
        for (ProductForecastView previousView : registeredForecastValues) {
            LocalDate endDate = previousView.getEndDate();
            int day = Days.daysBetween(dateOfPlatformBeginning, endDate).getDays(); //TODO- should we add/subtract 1 in the value?
            x[count] = day;
            if (whomToInterpolate == INTERPOLATE_TOTAL_SUBSCRIPTIONS) {
                y[count] = previousView.getTotalNumberOfExistingSubscriptions();
            } else if (whomToInterpolate == INTERPOLATE_NEW_SUBSCRIPTIONS) {
                y[count] = previousView.getNewSubscriptions();
            }
            count++;
        }
        double[] interpolatedSubscriptionsPerDay = interpolatorChain.interpolate(x, y);
        return interpolatedSubscriptionsPerDay;
    }

    private void derivePseudoActualsFromForecast(String productId,LocalDate firstStartDate,LocalDate forecastDate){

        //Now add PseudoActuals by interpolating manual forecastAdded
        double[] interpolatedPseudoActualsTotalSubscriptions = interpolateStepForecastFromForecast(productId, INTERPOLATE_TOTAL_SUBSCRIPTIONS);
        double[] interpolatedPseudoActualsNewSubscriptions = interpolateStepForecastFromForecast(productId, INTERPOLATE_NEW_SUBSCRIPTIONS);
        double previousDayTotalSubcriptionCount = 0;
        double dailychurnedSubscriptionCount = 0;
        for (int i = 0; i < interpolatedPseudoActualsTotalSubscriptions.length; i++) {
            double interpolatedTotalSubscriptionCount = interpolatedPseudoActualsTotalSubscriptions[i];
            double interpolatedNewSubscriptionCount = interpolatedPseudoActualsNewSubscriptions[i];
            if (i == 0) {
                dailychurnedSubscriptionCount = interpolatedTotalSubscriptionCount - interpolatedNewSubscriptionCount;
            } else {
                dailychurnedSubscriptionCount = previousDayTotalSubcriptionCount + interpolatedNewSubscriptionCount - interpolatedTotalSubscriptionCount;
            }
            previousDayTotalSubcriptionCount=interpolatedTotalSubscriptionCount;
            ProductPseudoActualsView productPseudoActualsView = new ProductPseudoActualsView(new ForecastVersionId(productId, firstStartDate.plusDays(i),forecastDate), firstStartDate.plusDays(i), Double.valueOf(interpolatedNewSubscriptionCount).longValue(), Double.valueOf(dailychurnedSubscriptionCount).longValue(), Double.valueOf(interpolatedTotalSubscriptionCount).longValue());
            productPseudoActualsViewRepository.save(productPseudoActualsView);
        }
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
