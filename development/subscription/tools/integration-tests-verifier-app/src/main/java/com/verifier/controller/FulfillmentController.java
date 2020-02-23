package com.verifier.controller;

import com.affaince.subscription.date.SysDate;
import com.verifier.domains.fulfillment.repository.DispatchableDeliveryViewRepository;
import com.verifier.domains.fulfillment.repository.ProductOrderViewRepository;
import com.verifier.domains.fulfillment.repository.ProductStockProvisionRequestProxyRepository;
import com.verifier.domains.fulfillment.view.ProductOrderView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/fulfillment")
@Component

public class FulfillmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FulfillmentController.class);

    private ProductOrderViewRepository productOrderViewRepository;
    private DispatchableDeliveryViewRepository dispatchableDeliveryViewRepository;
    private ProductStockProvisionRequestProxyRepository productStockProvisionRequestProxyRepository;

    @Autowired
    public FulfillmentController(ProductOrderViewRepository productOrderViewRepository, DispatchableDeliveryViewRepository dispatchableDeliveryViewRepository, ProductStockProvisionRequestProxyRepository productStockProvisionRequestProxyRepository) {
        this.productOrderViewRepository = productOrderViewRepository;
        this.dispatchableDeliveryViewRepository = dispatchableDeliveryViewRepository;
        this.productStockProvisionRequestProxyRepository = productStockProvisionRequestProxyRepository;
    }


    @RequestMapping(method = RequestMethod.GET, value = "orders")
    public ResponseEntity<List<ProductOrderView>> getOrders() {
        List<ProductOrderView> views = productOrderViewRepository.findByProductOrderId_OrderDate(SysDate.now());
        return new ResponseEntity<>(views, HttpStatus.OK);
    }
}
