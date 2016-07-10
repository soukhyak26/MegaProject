package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.command.event.SubscriptionForecastUpdatedEvent;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

/**
 * Created by mandar on 06-07-2016.
 */
public class SubscriptionForecastUpdatedEventListener {
    private final ProductForecastMetricsViewRepository productForecastMetricsViewRepository;
    private final ProductActualMetricsViewRepository productActualMetricsViewRepository;
    @Autowired
    public SubscriptionForecastUpdatedEventListener(ProductForecastMetricsViewRepository productForecastMetricsViewRepository, ProductActualMetricsViewRepository productActualMetricsViewRepository) {
        this.productForecastMetricsViewRepository = productForecastMetricsViewRepository;
        this.productActualMetricsViewRepository = productActualMetricsViewRepository;
    }

    @EventHandler
    public void on(SubscriptionForecastUpdatedEvent event){
            Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
            ProductForecastMetricsView latestProductForecastMetricsView= productForecastMetricsViewRepository.findByProductVersionId_ProductId(event.getProductId(),sort).get(0);
            latestProductForecastMetricsView.setEndDate(LocalDate.now());
            //forecasted new subscriptions per period= (new forecasted total subscription - latest forecasted total subscription + new forecasted churned subscriptions)
            double newSubscriptions = event.getTotalSubscriptionForecast() - latestProductForecastMetricsView.getTotalNumberOfExistingSubscriptions() +  event.getChurnedSubscriptionForecast();
            ProductForecastMetricsView newProductForecastMetricsView= new ProductForecastMetricsView(new ProductVersionId(event.getProductId(), event.getStartDate()),event.getEndDate());
            newProductForecastMetricsView.setTotalNumberOfExistingSubscriptions(event.getTotalSubscriptionForecast());
            newProductForecastMetricsView.setChurnedSubscriptions(event.getChurnedSubscriptionForecast());
            newProductForecastMetricsView.setNewSubscriptions(Double.valueOf(newSubscriptions).longValue());
            productForecastMetricsViewRepository.save(newProductForecastMetricsView);

    }
}
