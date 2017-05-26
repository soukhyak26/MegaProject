package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.command.RecalculateOfferPriceCommand;
import com.affaince.subscription.product.command.event.ProductStatusUpdatedEvent;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.repository.TaggedPriceVersionsViewRepository;
import com.affaince.subscription.product.query.view.ProductView;
import com.affaince.subscription.product.query.view.TaggedPriceVersionsView;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProductStatusUpdatedEventListener {

    private final ProductViewRepository productViewRepository;
    private final TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository;
    private final ProductActualsViewRepository productActualsViewRepository;
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public ProductStatusUpdatedEventListener(ProductViewRepository productViewRepository ,TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository, ProductActualsViewRepository productActualsViewRepository,SubscriptionCommandGateway commandGateway) {
        this.productViewRepository= productViewRepository;
        this.taggedPriceVersionsViewRepository = taggedPriceVersionsViewRepository;
        this.productActualsViewRepository = productActualsViewRepository;
        this.commandGateway=commandGateway;
    }

    @EventHandler
    public void on(ProductStatusUpdatedEvent event) throws Exception{
        ProductView productView = productViewRepository.findOne(event.getProductId());
        productView.setCurrentStockInUnits(event.getCurrentStockInUnits());
        productViewRepository.save(productView);
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");

        //if purchase price in incoming event and that of latest price bucket are same do nothing,as there is no change;Else create new price bucket and set new purchase price/MRP in it.
        //ProductActualsView latestView = productActualsViewRepository.findByProductVersionId_ProductId(event.getProductId(),sort).get(0);


        List<TaggedPriceVersionsView> latestTaggedPriceVersionsViews = taggedPriceVersionsViewRepository.findByProductwiseTaggedPriceVersionId_ProductId(event.getProductId(),sort);
        PriceTaggedWithProduct newTaggedPriceVersion = event.getNewTaggedPrice();
        if (latestTaggedPriceVersionsViews.isEmpty() ||
                (latestTaggedPriceVersionsViews.get(0).getPurchasePricePerUnit() != newTaggedPriceVersion.getPurchasePricePerUnit())) {
            //DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyy");
            TaggedPriceVersionsView newTaggedPrice = new TaggedPriceVersionsView(event.getProductId(),newTaggedPriceVersion.getTaggedPriceVersionId(), newTaggedPriceVersion.getPurchasePricePerUnit(), newTaggedPriceVersion.getMRP(), newTaggedPriceVersion.getTaggedStartDate(),newTaggedPriceVersion.getTaggedEndDate());
            taggedPriceVersionsViewRepository.save(newTaggedPrice);
            RecalculateOfferPriceCommand command = new RecalculateOfferPriceCommand(event.getProductId(),newTaggedPriceVersion.getTaggedPriceVersionId(), newTaggedPriceVersion.getPurchasePricePerUnit(), newTaggedPriceVersion.getMRP(),newTaggedPriceVersion.getTaggedStartDate().toDateTimeAtCurrentTime().toLocalDateTime(),newTaggedPriceVersion.getTaggedEndDate().toDateTimeAtCurrentTime().toLocalDateTime());
            commandGateway.executeAsync(command);
        }
    }
}
