package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.command.CalculateExpectedProfitPerPriceBucketCommand;
import com.affaince.subscription.product.command.event.SubscriptionDeductedFromPriceBucketEvent;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.vo.ProductwisePriceBucketId;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.Iterator;

/**
 * Created by mandar on 29-01-2017.
 */
public class SubscriptionDeductedFromPriceBucketEventListener {

    private final ProductActualsViewRepository productActualsViewRepository;
    private final PriceBucketViewRepository priceBucketViewRepository;
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public SubscriptionDeductedFromPriceBucketEventListener(ProductActualsViewRepository productActualsViewRepository, PriceBucketViewRepository priceBucketViewRepository, SubscriptionCommandGateway commandGateway) {
        this.productActualsViewRepository = productActualsViewRepository;
        this.priceBucketViewRepository = priceBucketViewRepository;
        this.commandGateway = commandGateway;
    }


    @EventHandler
    public void on(SubscriptionDeductedFromPriceBucketEvent event) throws Exception {
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
//        ProductActualsView productActualsView = productActualsViewRepository.findByProductVersionId_ProductId(event.getProductId(), sort).get(0);
        //find today's productActualsView
        ProductActualsView productActualsViewForToday = productActualsViewRepository.findByProductVersionId(new ProductVersionId(event.getProductId(), SysDate.now())).get(0);
        if (productActualsViewForToday == null) {
            ProductActualsView latestProductActualsView = productActualsViewRepository.findByProductVersionId(new ProductVersionId(event.getProductId(), SysDate.now().minusDays(1))).get(0);
            long latestSubscribedProductCount = latestProductActualsView.getTotalNumberOfExistingSubscriptions();
            productActualsViewForToday = new ProductActualsView(new ProductVersionId(event.getProductId(), SysDate.now()), new LocalDate(9999, 12, 31), 0, 0, latestSubscribedProductCount);
        }

        PriceBucketView priceBucketView = priceBucketViewRepository.findOne(new ProductwisePriceBucketId(event.getProductId(), event.getPriceBucketId()));
        final long revisedChurnedSubscriptionCountOfPriceBucket = event.getRevisedChurnedSubscriptionCount();
        final long revisedTotalSubscriptionCountOfPriceBucket = event.getRevisedTotalSubscriptionCount();
        final long deductedSubscriptionCount= event.getDeductedSubscriptionCount();
        priceBucketView.setNumberOfChurnedSubscriptionsAssociatedWithAPrice(revisedChurnedSubscriptionCountOfPriceBucket);
        priceBucketView.setNumberOfExistingSubscriptionsAssociatedWithAPrice(revisedTotalSubscriptionCountOfPriceBucket);
        priceBucketViewRepository.save(priceBucketView);
        productActualsViewForToday.addToChurnedSubscriptionCount(Math.abs(deductedSubscriptionCount));
        productActualsViewRepository.save(productActualsViewForToday);
       try {
            CalculateExpectedProfitPerPriceBucketCommand command = new CalculateExpectedProfitPerPriceBucketCommand(event.getProductId(), event.getPriceBucketId());
            commandGateway.executeAsync(command);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
