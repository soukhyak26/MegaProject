package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.command.event.NewSubscriptionAddedToPercentCommittedPriceBucketEvent;
import com.affaince.subscription.product.query.repository.PriceBucketTransactionViewRepository;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketTransactionView;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.vo.PriceBucketTransactionId;
import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 4/23/2017.
 */
@Component
public class NewSubscriptionAddedToPercentCommittedPriceBucketEventListener {
    private final ProductActualsViewRepository productActualsViewRepository;
    private final PriceBucketViewRepository priceBucketViewRepository;
    private final PriceBucketTransactionViewRepository priceBucketTransactionViewRepository;
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public NewSubscriptionAddedToPercentCommittedPriceBucketEventListener(ProductActualsViewRepository productActualsViewRepository, PriceBucketViewRepository priceBucketViewRepository,PriceBucketTransactionViewRepository priceBucketTransactionViewRepository,SubscriptionCommandGateway commandGateway) {
        this.productActualsViewRepository = productActualsViewRepository;
        this.priceBucketViewRepository = priceBucketViewRepository;
        this.priceBucketTransactionViewRepository=priceBucketTransactionViewRepository;
        this.commandGateway = commandGateway;
    }


    @EventHandler
    public void on(NewSubscriptionAddedToPercentCommittedPriceBucketEvent event) throws Exception {

        ProductActualsView latestProductActualsView =
                productActualsViewRepository.findFirstByProductVersionId_ProductIdOrderByProductVersionId_FromDateDesc
                        (event.getProductId());

        //find today's productActualsView

        //ProductActualsView productActualsViewForToday = productActualsViewRepository.findOne(new ProductVersionId(event.getProductId(), SysDate.now()));
        //very first record of ProductActualsView
        if (latestProductActualsView == null) {
            //ProductActualsView latestProductActualsView = productActualsViewRepository.findByProductVersionId(new ProductVersionId(event.getProductId(), SysDate.now().minusDays(1))).get(0);
            //long latestSubscribedProductCount = latestProductActualsView.getTotalNumberOfExistingSubscriptions();
            latestProductActualsView = new ProductActualsView(new ProductVersionId(event.getProductId(), event.getSubscriptionChangedDate()), event.getSubscriptionChangedDate(), 0, 0, 0);
            latestProductActualsView.addToNewSubscriptionCount(event.getAddedSubscriptionCount());
            latestProductActualsView.addToTotalSubscriptionCount(event.getAddedSubscriptionCount());
            productActualsViewRepository.save(latestProductActualsView);
        } else {
            //latest record of ProductActualsView which is not of the same date when event was generated
            //if(latestProductActualsView.getProductVersionId().getFromDate().isEqual(event.getSubscriptionChangedDate())) {
                //long latestSubscribedProductCount = latestProductActualsView.getTotalNumberOfExistingSubscriptions();
                latestProductActualsView.addToNewSubscriptionCount(event.getAddedSubscriptionCount());
                latestProductActualsView.addToTotalSubscriptionCount(event.getAddedSubscriptionCount());
                productActualsViewRepository.save(latestProductActualsView);
                //Latest record is of the previous day of the event generated
            /*}else if(latestProductActualsView.getProductVersionId().getFromDate().isBefore(event.getSubscriptionChangedDate())){

                long latestTotalSubscriptionCount=latestProductActualsView.getTotalNumberOfExistingSubscriptions();
                latestProductActualsView = new ProductActualsView(new ProductVersionId(event.getProductId(), event.getSubscriptionChangedDate()), event.getSubscriptionChangedDate(), 0, 0, 0);
                latestProductActualsView.setNewSubscriptions(0);
                latestProductActualsView.setTotalNumberOfExistingSubscriptions(latestTotalSubscriptionCount);
                latestProductActualsView.addToNewSubscriptionCount(event.getAddedSubscriptionCount());
                latestProductActualsView.addToTotalSubscriptionCount(event.getAddedSubscriptionCount());
                productActualsViewRepository.save(latestProductActualsView);
                //latest record is of the later date of the event generated.. probably because event has reached here too late.. should not happen
            }else{
                List<ProductActualsView> eventDatedActualsViews=productActualsViewRepository.findByProductVersionId(new ProductVersionId(event.getProductId(),event.getSubscriptionChangedDate()));
                ProductActualsView eventDatedActualsView = null;
                if (eventDatedActualsViews.size() > 0) {
                    eventDatedActualsView = eventDatedActualsViews.get(0);
                    eventDatedActualsView.addToNewSubscriptionCount(event.getAddedSubscriptionCount());
                    eventDatedActualsView.addToTotalSubscriptionCount(event.getAddedSubscriptionCount());
                    productActualsViewRepository.save(eventDatedActualsView);

                } else {
                    eventDatedActualsView = new ProductActualsView(new ProductVersionId(event.getProductId(), event.getSubscriptionChangedDate()), event.getSubscriptionChangedDate(), 0, 0, 0);
                    eventDatedActualsView.addToNewSubscriptionCount(event.getAddedSubscriptionCount());
                    eventDatedActualsView.addToTotalSubscriptionCount(event.getAddedSubscriptionCount());
                    productActualsViewRepository.save(eventDatedActualsView);
                }

                latestProductActualsView.addToNewSubscriptionCount(event.getAddedSubscriptionCount());
                latestProductActualsView.addToTotalSubscriptionCount(event.getAddedSubscriptionCount());
                productActualsViewRepository.save(latestProductActualsView);
            }*/
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
        priceBucketTransactionView.setOfferedPrice(event.getOfferedPrice());
        priceBucketTransactionView.setPurchasePrice(event.getPurchasePrice());
        priceBucketTransactionView.setMRP(event.getMRP());

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
