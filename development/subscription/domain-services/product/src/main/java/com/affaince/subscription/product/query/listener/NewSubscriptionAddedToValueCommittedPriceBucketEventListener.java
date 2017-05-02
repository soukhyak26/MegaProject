package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.command.event.NewSubscriptionAddedToValueCommittedPriceBucketEvent;
import com.affaince.subscription.product.query.repository.PriceBucketTransactionViewRepository;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketTransactionView;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.vo.PriceBucketTransactionId;
import com.affaince.subscription.product.vo.ProductwisePriceBucketId;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 29-01-2017.
 */
@Component
public class NewSubscriptionAddedToValueCommittedPriceBucketEventListener {
    private final ProductActualsViewRepository productActualsViewRepository;
    private final PriceBucketViewRepository priceBucketViewRepository;
    private final PriceBucketTransactionViewRepository priceBucketTransactionViewRepository;
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public NewSubscriptionAddedToValueCommittedPriceBucketEventListener(ProductActualsViewRepository productActualsViewRepository, PriceBucketViewRepository priceBucketViewRepository,PriceBucketTransactionViewRepository priceBucketTransactionViewRepository,SubscriptionCommandGateway commandGateway) {
        this.productActualsViewRepository = productActualsViewRepository;
        this.priceBucketViewRepository = priceBucketViewRepository;
        this.priceBucketTransactionViewRepository=priceBucketTransactionViewRepository;
        this.commandGateway = commandGateway;
    }


    @EventHandler
    public void on(NewSubscriptionAddedToValueCommittedPriceBucketEvent event) throws Exception {

        //find today's productActualsView
        List<ProductActualsView> productActualsViewForSubscriptionChangeDate = productActualsViewRepository.findByProductVersionId(new ProductVersionId(event.getProductId(), event.getSubscriptionChangedDate()));
        //very first record of ProductActualsView
        if (null != productActualsViewForSubscriptionChangeDate && productActualsViewForSubscriptionChangeDate.size() > 0) {
            ProductActualsView productActualsView = productActualsViewForSubscriptionChangeDate.get(0);
            productActualsView.addToNewSubscriptionCount(event.getAddedSubscriptionCount());
            productActualsView.addToTotalSubscriptionCount(event.getAddedSubscriptionCount());
            productActualsViewRepository.save(productActualsView);
        } else {
            long latestTotalSubscriptionCount=0;
            ProductActualsView  latestProductActualsView=
                    productActualsViewRepository.findFirstByProductVersionId_ProductIdOrderByProductVersionId_FromDateDesc
                            (event.getProductId());
            if(null !=latestProductActualsView) {
                latestTotalSubscriptionCount = latestProductActualsView.getTotalNumberOfExistingSubscriptions();
            }
            latestProductActualsView = new ProductActualsView(new ProductVersionId(event.getProductId(), event.getSubscriptionChangedDate()), event.getSubscriptionChangedDate(), 0, 0, 0);
            latestProductActualsView.setNewSubscriptions(0);
            latestProductActualsView.setTotalNumberOfExistingSubscriptions(latestTotalSubscriptionCount);
            latestProductActualsView.addToNewSubscriptionCount(event.getAddedSubscriptionCount());
            latestProductActualsView.addToTotalSubscriptionCount(event.getAddedSubscriptionCount());
            productActualsViewRepository.save(latestProductActualsView);
            //latest record is of the later date of the event generated.. probably because event has reached here too late.. should not happen
        }

        PriceBucketView priceBucketView = priceBucketViewRepository.findOne(new ProductwisePriceBucketId(event.getProductId(), event.getPriceBucketId()));
        final long revisedNewSubscriptionCountOfPriceBucket = event.getNewSubscriptionCount();
        final long revisedTotalSubscriptionCountOfPriceBucket = event.getTotalSubscriptionCount();
        //final long addedSubscriptionCount= event.getAddedSubscriptionCount();
        priceBucketView.setNumberOfNewSubscriptionsAssociatedWithAPrice(revisedNewSubscriptionCountOfPriceBucket);
        priceBucketView.setNumberOfExistingSubscriptionsAssociatedWithAPrice(revisedTotalSubscriptionCountOfPriceBucket);
        priceBucketViewRepository.save(priceBucketView);

        PriceBucketTransactionView priceBucketTransactionView = priceBucketTransactionViewRepository.findOne(new PriceBucketTransactionId(event.getProductId(),event.getPriceBucketId(),event.getSubscriptionChangedDate()));
        if(null == priceBucketTransactionView){
            priceBucketTransactionView = new PriceBucketTransactionView(new PriceBucketTransactionId(event.getProductId(),event.getPriceBucketId(),event.getSubscriptionChangedDate()));
        }
        priceBucketTransactionView.addToNewSubscriptions(event.getAddedSubscriptionCount());
        priceBucketTransactionViewRepository.save(priceBucketTransactionView);

/*
        try {
            CalculateExpectedProfitPerPriceBucketCommand command = new CalculateExpectedProfitPerPriceBucketCommand(event.getProductId(), event.getPriceBucketId());
            commandGateway.executeAsync(command);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
*/
    }


}
