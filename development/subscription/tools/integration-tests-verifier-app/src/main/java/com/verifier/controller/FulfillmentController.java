package com.verifier.controller;

import com.affaince.subscription.date.SysDate;
import com.verifier.domains.fulfillment.repository.DispatchableDeliveryViewRepository;
import com.verifier.domains.fulfillment.repository.ProductOrderViewRepository;
import com.verifier.domains.fulfillment.repository.ProductStockProvisionRequestProxyRepository;
import com.verifier.domains.fulfillment.view.ProductOrderView;
import com.verifier.domains.fulfillment.view.ProductStockProvisionRequestProxy;
import com.verifier.domains.fulfillment.vo.OrderDetail;
import com.verifier.domains.fulfillment.vo.ProductInventoryUpdateId;
import com.verifier.domains.product.repository.TaggedPriceVersionsViewRepository;
import com.verifier.domains.product.view.TaggedPriceVersionsView;
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
    private TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository;

    @Autowired
    public FulfillmentController(ProductOrderViewRepository productOrderViewRepository, DispatchableDeliveryViewRepository dispatchableDeliveryViewRepository, ProductStockProvisionRequestProxyRepository productStockProvisionRequestProxyRepository,TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository) {
        this.productOrderViewRepository = productOrderViewRepository;
        this.dispatchableDeliveryViewRepository = dispatchableDeliveryViewRepository;
        this.productStockProvisionRequestProxyRepository = productStockProvisionRequestProxyRepository;
        this.taggedPriceVersionsViewRepository = taggedPriceVersionsViewRepository;
    }


    @RequestMapping(method = RequestMethod.GET, value = "orders")
    public ResponseEntity<List<ProductOrderView>> getOrders() {
        List<ProductOrderView> views = productOrderViewRepository.findByProductOrderId_OrderDate(SysDate.now());
        return new ResponseEntity<>(views, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "feedorders")
    public ResponseEntity<Object> feedOrders() {
        List<ProductOrderView> views = productOrderViewRepository.findByProductOrderId_OrderDate(SysDate.now());
        for(ProductOrderView order:views ){
            List<OrderDetail> orders = order.getOrderDetails();
            for(OrderDetail orderDetail: orders) {
                ProductStockProvisionRequestProxy temp = new ProductStockProvisionRequestProxy();
                ProductInventoryUpdateId id = new ProductInventoryUpdateId(order.getProductOrderId().getProductId(), order.getProductOrderId().getOrderDate());
                temp.setProductInventoryUpdateId(id);
                temp.setReceivedProductCount(orderDetail.getCount());
                temp.setPeriodStartDate(orderDetail.getStartDate());
                temp.setPeriodEndDate(orderDetail.getEndDate());

                List<TaggedPriceVersionsView> taggedPrices = taggedPriceVersionsViewRepository.findByProductwiseTaggedPriceVersionId_ProductId(order.getProductOrderId().getProductId());
                temp.setPurchasePricePerUnit(taggedPrices.get(0).getPurchasePricePerUnit());
                temp.setMRP(taggedPrices.get(0).getMRP());
                productStockProvisionRequestProxyRepository.save(temp);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
