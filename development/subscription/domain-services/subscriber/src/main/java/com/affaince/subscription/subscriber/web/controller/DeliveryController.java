package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.command.ItemDispatchStatus;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.common.type.ReasonCode;
import com.affaince.subscription.subscriber.command.AddDeliveryToSubscriptionCommand;
import com.affaince.subscription.subscriber.command.DeleteDeliveryCommand;
import com.affaince.subscription.subscriber.command.UpdateDeliveryCommand;
import com.affaince.subscription.subscriber.command.UpdateDeliveryStatusAndDispatchDateCommand;
import com.affaince.subscription.subscriber.query.repository.DeliveryViewRepository;
import com.affaince.subscription.subscriber.query.repository.LatestPriceBucketViewRepository;
import com.affaince.subscription.subscriber.query.repository.ProductViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryView;
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


    @Autowired
    public DeliveryController(SubscriptionCommandGateway commandGateway,
                              ProductViewRepository productViewRepository,
                              LatestPriceBucketViewRepository latestPriceBucketViewRepository,
                              DeliveryViewRepository deliveryViewRepository) {
        this.commandGateway = commandGateway;
        this.productViewRepository = productViewRepository;
        this.latestPriceBucketViewRepository = latestPriceBucketViewRepository;
        this.deliveryViewRepository = deliveryViewRepository;
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
        }
        final AddDeliveryToSubscriptionCommand command = new AddDeliveryToSubscriptionCommand(
                subscriberId, deliveryId, request.getDeliveryDate(), Arrays.asList(request.getDeliveryItems())
        );
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(ImmutableMap.of("id", deliveryId), HttpStatus.CREATED);
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
        }
        final UpdateDeliveryCommand command = new UpdateDeliveryCommand(subscriberId, deliveryId, request.getDeliveryDate(),
                Arrays.asList(request.getDeliveryItems()));
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
}
