package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.CalculateExpectedProfitPerPriceBucketCommand;
import com.affaince.subscription.product.command.event.ProductSubscriptionUpdatedEvent;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.vo.ProductwisePriceBucketId;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Created by mandar on 19-06-2016.
 */
@Component
public class ProductSubscriptionUpdatedEventListener {
    private final ProductActualsViewRepository productActualsViewRepository;
    private final PriceBucketViewRepository priceBucketViewRepository;
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public ProductSubscriptionUpdatedEventListener(ProductActualsViewRepository productActualsViewRepository, PriceBucketViewRepository priceBucketViewRepository, SubscriptionCommandGateway commandGateway) {
        this.productActualsViewRepository = productActualsViewRepository;
        this.priceBucketViewRepository = priceBucketViewRepository;
        this.commandGateway = commandGateway;
    }


    @EventHandler
    public void on(ProductSubscriptionUpdatedEvent event) throws Exception {
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
//        ProductActualsView productActualsView = productActualsViewRepository.findByProductVersionId_ProductId(event.getProductId(), sort).get(0);
        //find today's productActualsView
        ProductActualsView productActualsViewForToday=productActualsViewRepository.findByProductVersionId(new ProductVersionId(event.getProductId(), SysDate.now())).get(0);
        if( productActualsViewForToday== null){
            ProductActualsView latestProductActualsView = productActualsViewRepository.findByProductVersionId(new ProductVersionId(event.getProductId(),SysDate.now().minusDays(1))).get(0);
            long latestSubscribedProductCount = latestProductActualsView.getTotalNumberOfExistingSubscriptions();
            productActualsViewForToday=new ProductActualsView(new ProductVersionId(event.getProductId(), SysDate.now()),new LocalDate(9999,12,31),0,0,latestSubscribedProductCount);
        }

        //final ProductMonthlyStatisticsView productActualMetricsView = statsViewRepository.findOne(productStatistics.getProductId());
        Iterator<String> iterator=event.getPriceBucketWiseSubscriptionCount().keySet().iterator();

        while(iterator.hasNext()){
            String priceBucketId= iterator.next();
            PriceBucketView priceBucketView = priceBucketViewRepository.findOne(new ProductwisePriceBucketId(event.getProductId(), priceBucketId));
            final int subscriptionCount = event.getPriceBucketWiseSubscriptionCount().get(priceBucketId);
            if (subscriptionCount > 0) {
                priceBucketView.addToNewSubscriptions(subscriptionCount);
                productActualsViewForToday.addToNewSubscriptionCount(subscriptionCount);
            } else {
                priceBucketView.addToChurnedSubscriptions(subscriptionCount);
                productActualsViewForToday.addToChurnedSubscriptionCount(Math.abs(subscriptionCount));
            }
            priceBucketViewRepository.save(priceBucketView);

            try {
                CalculateExpectedProfitPerPriceBucketCommand command = new CalculateExpectedProfitPerPriceBucketCommand(event.getProductId(), priceBucketId);
                commandGateway.executeAsync(command);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
        productActualsViewRepository.save(productActualsViewForToday);
    }
}
