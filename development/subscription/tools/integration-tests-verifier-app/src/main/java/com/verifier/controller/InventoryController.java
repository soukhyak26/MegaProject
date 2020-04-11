package com.verifier.controller;

import com.affaince.subscription.appconfig.validator.ProductConfigurationValidator;
import com.affaince.subscription.common.applicationevent.util.PaginatedResultsRetrivalDiscoverer;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.InventoryDeltaPerPeriod;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.repository.IdGenerator;
import com.verifier.domains.product.repository.*;
import com.verifier.domains.product.view.InventoryProvisionCalendarView;
import com.verifier.domains.product.view.ProductForecastView;
import com.verifier.domains.product.view.ProductView;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/inventory")
@Component
public class InventoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);
    private final ProductViewRepository productViewRepository;
    private final ProductViewPagingRepository productViewPagingRepository;
    private final ProductForecastViewRepository productForecastViewRepository;
    private final ProductConfigurationValidator productConfigurationValidator;
    private final ProductConfigurationViewRepository productConfigurationViewRepository;
    private final TargetSettingViewRepository targetSettingViewRepository;
    private final ProductInventoryViewRepository productInventoryViewRepository;
    private final InventoryProvisionCalendarViewRepository inventoryProvisionCalendarViewRepository;

    @Autowired
    public InventoryController(ProductViewRepository productViewRepository, ProductViewPagingRepository productViewPagingRepository, ProductForecastViewRepository productForecastViewRepository, ProductConfigurationValidator productConfigurationValidator, ProductConfigurationViewRepository productConfigurationViewRepository, TargetSettingViewRepository targetSettingViewRepository, ProductInventoryViewRepository productInventoryViewRepository, InventoryProvisionCalendarViewRepository inventoryProvisionCalendarViewRepository) {
        this.productViewRepository = productViewRepository;
        this.productViewPagingRepository = productViewPagingRepository;
        this.productForecastViewRepository = productForecastViewRepository;
        this.productConfigurationValidator = productConfigurationValidator;
        this.productConfigurationViewRepository = productConfigurationViewRepository;
        this.targetSettingViewRepository = targetSettingViewRepository;
        this.productInventoryViewRepository = productInventoryViewRepository;
        this.inventoryProvisionCalendarViewRepository = inventoryProvisionCalendarViewRepository;
    }

   /* //This should be used to fetch productIds in paged manner.. for forecasting batch.
    @RequestMapping(value = "/findpaged", params = {"page", "size"}, method = RequestMethod.GET)
    public ResponseEntity<List<String>> findPaged(@RequestParam("page") int page, @RequestParam("size") int size, UriComponentsBuilder uriBuilder, HttpServletResponse response) throws ProductNotFoundException {
        List<String> target = new ArrayList<>();
        Pageable pageable = new PageRequest(page, size);
        Page<ProductActivationStatusView> resultPage = productConfigurationValidator
                .findAllCountByProductStatusPaged(ProductStatus.PRODUCT_ACTIVATED, pageable);
        if (page > resultPage.getTotalPages()) {
            throw ProductNotFoundException.build();
        }
        resultPage.forEach((item) -> {
            target.add(item.getProductId());
        });
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<String>(String.class, uriBuilder, response, page, resultPage.getTotalPages(), size));
        Collection<String> headerNames = response.getHeaderNames();
        HttpHeaders responseHeaders = new HttpHeaders();
        headerNames.stream().forEach(headerName -> {
            responseHeaders.add(headerName, response.getHeader(headerName));
        });
        return new ResponseEntity<List<String>>(target, responseHeaders, HttpStatus.OK);
    }*/


    // retrieve cost of goods of each product form the inventory by calculating it for each product as
    //Σ quantity* purchase price of that quantity
    //and return the result.
    //The same can be used for calculating revenue and profit
    @RequestMapping(method = RequestMethod.GET, value = "/costofgoods/{productid}")
    public ResponseEntity<String> calculateCostOfGoods(@PathVariable("productid") String productId) {
        return null;
    }

    // Update invnetory of each product when new stock arrives by adding the new Inventory records
    @RequestMapping(method = RequestMethod.POST, value = "/update/{productid}")
    public ResponseEntity<String> updateInventory(@PathVariable("productid") String productId) {
        return null;
    }

}