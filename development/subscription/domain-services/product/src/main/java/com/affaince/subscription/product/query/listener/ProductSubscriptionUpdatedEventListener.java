package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.command.CalculateExpectedProfitPerPriceBucketCommand;
import com.affaince.subscription.product.command.event.ProductSubscriptionUpdatedEvent;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.vo.ProductwisePriceBucketId;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 19-06-2016.
 */
@Component
public class ProductSubscriptionUpdatedEventListener {
    private final ProductActualMetricsViewRepository productActualMetricsViewRepository;
    private final PriceBucketViewRepository priceBucketViewRepository;
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public ProductSubscriptionUpdatedEventListener(ProductActualMetricsViewRepository productActualMetricsViewRepository, PriceBucketViewRepository priceBucketViewRepository,SubscriptionCommandGateway commandGateway) {
        this.productActualMetricsViewRepository = productActualMetricsViewRepository;
        this.priceBucketViewRepository = priceBucketViewRepository;
        this.commandGateway=commandGateway;
    }



    @EventHandler
    public void on(ProductSubscriptionUpdatedEvent event) throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        ProductActualMetricsView productActualMetricsView = productActualMetricsViewRepository.findByProductVersionId_ProductId(event.getProductId(), sort).get(0);
        //final ProductMonthlyStatisticsView productActualMetricsView = statsViewRepository.findOne(productStatistics.getProductId());
        long subscribedProductCount = productActualMetricsView.getTotalNumberOfExistingSubscriptions();

        event.getPriceBucketWiseSubscriptionCount().keySet().stream().forEach(priceBucketId->{
            PriceBucketView priceBucketView= priceBucketViewRepository.findOne(new ProductwisePriceBucketId(event.getProductId(),priceBucketId));
            final int subscriptionCount=event.getPriceBucketWiseSubscriptionCount().get(priceBucketId);
            if(subscriptionCount>0){
                priceBucketView.addToNewSubscriptions(subscriptionCount);
                productActualMetricsView.addToNewSubscriptions(subscriptionCount);
            }else{
                priceBucketView.addToChurnedSubscriptions(subscriptionCount);
                productActualMetricsView.addToChurnedSubscriptions(subscriptionCount);
            }
            priceBucketViewRepository.save(priceBucketView);

            try {
                CalculateExpectedProfitPerPriceBucketCommand command = new CalculateExpectedProfitPerPriceBucketCommand(event.getProductId(), priceBucketId);
                commandGateway.executeAsync(command);
            }catch(Exception ex){
                //TODO: I DONT KNOW WHAT TO DO HERE !!
            }
        });
        productActualMetricsViewRepository.save(productActualMetricsView);
    }
}
