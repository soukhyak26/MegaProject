package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.subscriber.command.AddDeliveryToSubscriptionCommand;
import com.affaince.subscription.subscriber.command.DeleteDeliveryCommand;
import com.affaince.subscription.subscriber.command.UpdateDeliveryCommand;
import com.affaince.subscription.subscriber.query.repository.LatestPriceBucketViewRepository;
import com.affaince.subscription.subscriber.query.repository.ProductViewRepository;
import com.affaince.subscription.subscriber.query.view.ProductView;
import com.affaince.subscription.subscriber.web.request.AddDeliveryRequest;
import com.affaince.subscription.subscriber.web.request.DeliveryItem;
import com.affaince.subscription.subscriber.web.request.UpdateDeliveryRequest;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import java.util.Arrays;

/**
 * Created by rbsavaliya on 23-10-2016.
 */
@RestController
@RequestMapping(value = "delivery")
public class DeliveryController {

    private final SubscriptionCommandGateway commandGateway;
    private final ProductViewRepository productViewRepository;
    private final LatestPriceBucketViewRepository latestPriceBucketViewRepository;

    @Autowired
    public DeliveryController(SubscriptionCommandGateway commandGateway, ProductViewRepository productViewRepository, LatestPriceBucketViewRepository latestPriceBucketViewRepository) {
        this.commandGateway = commandGateway;
        this.productViewRepository = productViewRepository;
        this.latestPriceBucketViewRepository = latestPriceBucketViewRepository;
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
        final DeleteDeliveryCommand command = new DeleteDeliveryCommand(subscriberId, deliveryId);
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(ImmutableMap.of("id", deliveryId), HttpStatus.OK);
    }

    @RequestMapping(value = "update/{subscriberId}/{deliveryId}", method = RequestMethod.PUT)
    @Consumes("application/json")
    public ResponseEntity<Object> updateDelivery(@PathVariable String subscriberId, @PathVariable String deliveryId,
                                                 UpdateDeliveryRequest request) throws Exception {
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
}
