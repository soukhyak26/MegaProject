package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.command.ItemDispatchStatus;
import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.common.type.ReasonCode;
import com.affaince.subscription.subscriber.command.*;
import com.affaince.subscription.subscriber.command.domain.DeliveryChargesRule;
import com.affaince.subscription.subscriber.command.domain.LatestPriceBucket;
import com.affaince.subscription.subscriber.query.repository.DeliveryChargesRuleViewRepository;
import com.affaince.subscription.subscriber.query.repository.DeliveryViewRepository;
import com.affaince.subscription.subscriber.query.repository.LatestPriceBucketViewRepository;
import com.affaince.subscription.subscriber.query.repository.ProductViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryChargesRuleView;
import com.affaince.subscription.subscriber.query.view.DeliveryView;
import com.affaince.subscription.subscriber.query.view.LatestPriceBucketView;
import com.affaince.subscription.subscriber.query.view.ProductView;
import com.affaince.subscription.subscriber.web.exception.DeliveryNotFoundException;
import com.affaince.subscription.subscriber.web.request.AddDeliveryRequest;
import com.affaince.subscription.subscriber.web.request.BasketDispatchRequest;
import com.affaince.subscription.subscriber.web.request.DeliveryItem;
import com.affaince.subscription.subscriber.web.request.UpdateDeliveryRequest;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by rbsavaliya on 23-10-2016.
 */
@RestController
@RequestMapping(value = "delivery")
public class DeliveryController {

    private final SubscriptionCommandGateway commandGateway;
    private final ProductViewRepository productViewRepository;
    private final LatestPriceBucketViewRepository latestPriceBucketViewRepository;
    private final DeliveryViewRepository deliveryViewRepository;
    private final DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository;


    @Autowired
    public DeliveryController(SubscriptionCommandGateway commandGateway,
                              ProductViewRepository productViewRepository,
                              LatestPriceBucketViewRepository latestPriceBucketViewRepository,
                              DeliveryViewRepository deliveryViewRepository,
                              DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository) {
        this.commandGateway = commandGateway;
        this.productViewRepository = productViewRepository;
        this.latestPriceBucketViewRepository = latestPriceBucketViewRepository;
        this.deliveryViewRepository = deliveryViewRepository;
        this.deliveryChargesRuleViewRepository = deliveryChargesRuleViewRepository;
    }

    @RequestMapping(value = "add/{subscriberId}", method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Object> addDelivery(@PathVariable String subscriberId, AddDeliveryRequest request) throws Exception {
        final String deliveryId = request.getDeliveryDate().getWeekOfWeekyear() + request.getDeliveryDate().getYear() + "";
        for (DeliveryItem deliveryItem : request.getDeliveryItems()) {
            final ProductView productView = productViewRepository.findOne(deliveryItem.getDeliveryItemId());
            final long productQuantity = productView.getNetQuantity();
            final QuantityUnit productQuantityUnit = productView.getQuantityUnit();
            long productQuantityInGrms = productQuantity;
            if (productQuantityUnit == QuantityUnit.KG || productQuantityUnit == QuantityUnit.LT) {
                productQuantityInGrms = productQuantity * 1000;
            } else if (productQuantityUnit == QuantityUnit.ml) {
                productQuantityInGrms = productQuantity;
            }
            deliveryItem.setQuantityInGrms(productQuantityInGrms);

            deliveryItem.setDeliveryItemOfferedPrice(latestPriceBucketViewRepository.findOne(deliveryItem.getDeliveryItemId())
                    .getOfferedPricePerUnit());
            deliveryItem.setProductPricingCategory(productView.getProductPricingCategory());
        }
        setLatestPriceParameterToDeliveryItem(request.getDeliveryItems());
        fetchDeliveryChargesRules();

        final AddDeliveryToSubscriptionCommand command = new AddDeliveryToSubscriptionCommand(
                subscriberId, deliveryId, request.getDeliveryDate(), Arrays.asList(request.getDeliveryItems()),
                fetchDeliveryChargesRules()
        );
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(ImmutableMap.of("id", deliveryId), HttpStatus.CREATED);
    }

    private DeliveryChargesRule fetchDeliveryChargesRules() {
        DeliveryChargesRuleView deliveryChargesRuleView = deliveryChargesRuleViewRepository.
                findFirstByRuleIdOrderByEffectiveDateDesc(DeliveryChargesRuleType.CHARGES_ON_DELIVERY_WEIGHT);
        DeliveryChargesRule deliveryChargesRule = new DeliveryChargesRule(DeliveryChargesRuleType.CHARGES_ON_DELIVERY_WEIGHT,
                deliveryChargesRuleView.getRangeRules(), deliveryChargesRuleView.getEffectiveDate());
        return deliveryChargesRule;
    }

    private void setLatestPriceParameterToDeliveryItem(DeliveryItem[] deliveryItems) {
        for (DeliveryItem deliveryItem: deliveryItems) {
            LatestPriceBucketView latestPriceBucketView = latestPriceBucketViewRepository.findOne(deliveryItem.getDeliveryItemId());
            deliveryItem.setPriceBucketId(latestPriceBucketView.getPriceBucketId());
            deliveryItem.setOfferedPricePerUnit(latestPriceBucketView.getOfferedPricePerUnit());
        }
    }

    @RequestMapping(value = "delete/{subscriberId}/{deliveryId}", method = RequestMethod.DELETE)
    @Consumes("application/json")
    public ResponseEntity<Object> deleteDelivery(@PathVariable String subscriberId, @PathVariable String deliveryId) throws Exception {
        final DeliveryView deliveryView = deliveryViewRepository.findOne(deliveryId);
        if (deliveryView == null) {
            throw DeliveryNotFoundException.build(deliveryId);
        }
        final DeleteDeliveryCommand command = new DeleteDeliveryCommand(subscriberId, deliveryId);
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(ImmutableMap.of("id", deliveryId), HttpStatus.OK);
    }

    @RequestMapping(value = "update/{subscriberId}/{deliveryId}", method = RequestMethod.PUT)
    @Consumes("application/json")
    public ResponseEntity<Object> updateDelivery(@PathVariable String subscriberId, @PathVariable String deliveryId,
                                                 UpdateDeliveryRequest request) throws Exception {
        final DeliveryView deliveryView = deliveryViewRepository.findOne(deliveryId);
        if (deliveryView == null) {
            throw DeliveryNotFoundException.build(deliveryId);
        }
        for (DeliveryItem deliveryItem : request.getDeliveryItems()) {
            final ProductView productView = productViewRepository.findOne(deliveryItem.getDeliveryItemId());
            final long productQuantity = productView.getNetQuantity();
            final QuantityUnit productQuantityUnit = productView.getQuantityUnit();
            long productQuantityInGrms = productQuantity;
            if (productQuantityUnit == QuantityUnit.KG || productQuantityUnit == QuantityUnit.LT) {
                productQuantityInGrms = productQuantity * 1000;
            } else if (productQuantityUnit == QuantityUnit.ml) {
                productQuantityInGrms = productQuantity;
            }
            deliveryItem.setQuantityInGrms(productQuantityInGrms);
            deliveryItem.setDeliveryItemOfferedPrice(latestPriceBucketViewRepository.findOne(deliveryItem.getDeliveryItemId())
                    .getOfferedPricePerUnit());
            deliveryItem.setProductPricingCategory(productView.getProductPricingCategory());
        }
        setLatestPriceParameterToDeliveryItem(request.getDeliveryItems());
        final UpdateDeliveryCommand command = new UpdateDeliveryCommand(subscriberId, deliveryId, request.getDeliveryDate(),
                Arrays.asList(request.getDeliveryItems()), fetchDeliveryChargesRules());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(ImmutableMap.of("id", deliveryId), HttpStatus.OK);
    }

    @RequestMapping(value = "updatestatusanddispatchdate/{subscriberId}/{subscriptionId}/{deliveryId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateStatusAndDispatchDate(@RequestBody BasketDispatchRequest request,
                                                              @PathVariable String subscriberId,
                                                              @PathVariable String subscriptionId,
                                                              @PathVariable String deliveryId) throws Exception {
        final DeliveryView deliveryView = deliveryViewRepository.findOne(deliveryId);
        if (deliveryView == null) {
            throw DeliveryNotFoundException.build(deliveryId);
        }
        final UpdateDeliveryStatusAndDispatchDateCommand command = new UpdateDeliveryStatusAndDispatchDateCommand(
                subscriberId, subscriberId, DeliveryStatus.valueOf(request.getBasketDeliveryStatus()), request.getDispatchDate(),
                Arrays.stream(request.getItemStatusRequest()).map(itemStatusRequest -> new ItemDispatchStatus(
                        itemStatusRequest.getItemId(), itemStatusRequest.getItemDeliveryStatus()
                )).collect(Collectors.toList()), ReasonCode.valueOf(request.getReasonCode()));
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "prepare/{subscriberId}/{deliveryId}")
    public ResponseEntity<Object> prepareDeliveryForDispatch(@PathVariable String subscriberId,
                                                             @PathVariable String deliveryId) throws Exception {

        DeliveryView deliveryView = deliveryViewRepository.findOne(deliveryId);
        final Map<String, LatestPriceBucket> latestPriceBucketMap = new HashMap<>(deliveryView.getDeliveryItems().size());
        deliveryView.getDeliveryItems().forEach(deliveryItem -> {
            LatestPriceBucketView latestPriceBucketView = latestPriceBucketViewRepository.findOne(deliveryItem.getDeliveryItemId());
            latestPriceBucketMap.put(deliveryItem.getDeliveryItemId(),
                    new LatestPriceBucket(latestPriceBucketView.getProductId(), latestPriceBucketView.getPriceBucketId(),
                            latestPriceBucketView.getOfferedPricePerUnit(), latestPriceBucketView.getCurrentPriceDate()));
        });
        final PrepareDeliveryForDispatchCommand command = new PrepareDeliveryForDispatchCommand(subscriberId, deliveryId,
                latestPriceBucketMap);
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
