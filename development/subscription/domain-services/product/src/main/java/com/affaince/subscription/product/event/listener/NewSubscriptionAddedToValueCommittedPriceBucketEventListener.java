package com.affaince.subscription.product.event.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.event.NewSubscriptionAddedToValueCommittedPriceBucketEvent;
import com.affaince.subscription.product.event.exception.MultipleActualViewsOnSingleDateException;
import com.affaince.subscription.product.event.exception.StaleNewSubscriptionsEventException;
import com.affaince.subscription.product.query.repository.PriceBucketTransactionViewRepository;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketTransactionView;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.vo.PriceBucketTransactionId;
import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        ProductActualsView latestProductActualsView =
                productActualsViewRepository.findFirstByProductVersionId_ProductIdOrderByProductVersionId_FromDateDesc
                        (event.getProductId());

        if (latestProductActualsView == null) {
            latestProductActualsView = new ProductActualsView(new ProductVersionId(event.getProductId(), event.getSubscriptionChangedDate()), event.getSubscriptionChangedDate(), 0, 0, 0);
            latestProductActualsView.addToNewSubscriptionCount(event.getAddedSubscriptionCount());
            latestProductActualsView.addToTotalSubscriptionCount(event.getAddedSubscriptionCount());
            productActualsViewRepository.save(latestProductActualsView);
        } else {
            if (latestProductActualsView.getProductVersionId().getFromDate().isEqual(event.getSubscriptionChangedDate())) {
                latestProductActualsView.addToNewSubscriptionCount(event.getAddedSubscriptionCount());
                latestProductActualsView.addToTotalSubscriptionCount(event.getAddedSubscriptionCount());
                productActualsViewRepository.save(latestProductActualsView);
            } else if (latestProductActualsView.getProductVersionId().getFromDate().isBefore(event.getSubscriptionChangedDate())) {
                long latestTotalSubscriptionCount = latestProductActualsView.getTotalNumberOfExistingSubscriptions();
                latestProductActualsView = new ProductActualsView(new ProductVersionId(event.getProductId(), event.getSubscriptionChangedDate()), event.getSubscriptionChangedDate(), 0, 0, 0);
                latestProductActualsView.setTotalNumberOfExistingSubscriptions(latestTotalSubscriptionCount);
                latestProductActualsView.addToNewSubscriptionCount(event.getAddedSubscriptionCount());
                latestProductActualsView.addToTotalSubscriptionCount(event.getAddedSubscriptionCount());
                productActualsViewRepository.save(latestProductActualsView);
            } else {//latest view is after event date
                List<ProductActualsView> actualsViewsOnEventDate = productActualsViewRepository.findByProductVersionId(new ProductVersionId(event.getProductId(), event.getSubscriptionChangedDate()));
                if (actualsViewsOnEventDate.size() == 0) {
                    //    long latestTotalSubscriptionCount=latestProductActualsView.getTotalNumberOfExistingSubscriptions();
                    final Sort sort = new Sort(Sort.Direction.DESC, "endDate");
                    long latestTotalSubscriptionCountBeforeEvent = productActualsViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(event.getProductId(), event.getSubscriptionChangedDate().minusDays(10), event.getSubscriptionChangedDate(), sort).get(0).getTotalNumberOfExistingSubscriptions();
                    latestProductActualsView = new ProductActualsView(new ProductVersionId(event.getProductId(), event.getSubscriptionChangedDate()), event.getSubscriptionChangedDate(), 0, 0, 0);
                    latestProductActualsView.setTotalNumberOfExistingSubscriptions(latestTotalSubscriptionCountBeforeEvent);
                    latestProductActualsView.addToNewSubscriptionCount(event.getAddedSubscriptionCount());
                    latestProductActualsView.addToTotalSubscriptionCount(event.getAddedSubscriptionCount());
                    productActualsViewRepository.save(latestProductActualsView);

                } else if (actualsViewsOnEventDate.size() > 1) {
                    throw MultipleActualViewsOnSingleDateException.build(event.getProductId(), event.getSubscriptionChangedDate());
                } else {
                    ProductActualsView eventDatedActualsView = actualsViewsOnEventDate.get(0);
                    eventDatedActualsView.addToNewSubscriptionCount(event.getAddedSubscriptionCount());
                    eventDatedActualsView.addToTotalSubscriptionCount(event.getAddedSubscriptionCount());
                    productActualsViewRepository.save(eventDatedActualsView);
                }
                int daysDifference = Days.daysBetween(event.getSubscriptionChangedDate(), latestProductActualsView.getProductVersionId().getFromDate()).getDays();
                if (daysDifference == 1) {
                    latestProductActualsView.addToTotalSubscriptionCount(event.getAddedSubscriptionCount());
                    productActualsViewRepository.save(latestProductActualsView);
                } else {
                    throw StaleNewSubscriptionsEventException.build(event.getProductId());
                }

            }
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
        priceBucketTransactionView.setMRP(event.getMrp());

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
