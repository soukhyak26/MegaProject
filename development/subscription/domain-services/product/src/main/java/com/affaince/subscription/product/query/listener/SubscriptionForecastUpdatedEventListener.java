package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.command.event.SubscriptionForecastUpdatedEvent;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;

/**
 * Created by mandar on 06-07-2016.
 */
public class SubscriptionForecastUpdatedEventListener {
    private final ProductForecastMetricsViewRepository productForecastMetricsViewRepository;
    private final ProductActualMetricsViewRepository productActualMetricsViewRepository;
    @Value("${subscription.forecast.period}")
    private String forecastPeriod;

    @Autowired
    public SubscriptionForecastUpdatedEventListener(ProductForecastMetricsViewRepository productForecastMetricsViewRepository, ProductActualMetricsViewRepository productActualMetricsViewRepository) {
        this.productForecastMetricsViewRepository = productForecastMetricsViewRepository;
        this.productActualMetricsViewRepository = productActualMetricsViewRepository;
    }

    @EventHandler
    public void on(SubscriptionForecastUpdatedEvent event) {
        LocalDate endDate;
        if (forecastPeriod.equals("WEEK")) {
            endDate = event.getStartDate().plusDays(7);
        } else if (forecastPeriod.equals("MONTH")) {
            endDate = event.getStartDate().plusDays(daysOfMonth(event.getStartDate()));
        } else {
            endDate = event.getStartDate().plusDays(1);
        }
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        ProductForecastMetricsView latestProductForecastMetricsView = productForecastMetricsViewRepository.findByProductVersionId_ProductId(event.getProductId(), sort).get(0);
        latestProductForecastMetricsView.setEndDate(LocalDate.now());
        //forecasted new subscriptions per period= (new forecasted total subscription - latest forecasted total subscription + new forecasted churned subscriptions)
        double newSubscriptions = event.getTotalSubscriptionForecast() - latestProductForecastMetricsView.getTotalNumberOfExistingSubscriptions() + event.getChurnedSubscriptionForecast();
        ProductForecastMetricsView newProductForecastMetricsView = new ProductForecastMetricsView(new ProductVersionId(event.getProductId(), event.getStartDate()), endDate);
        newProductForecastMetricsView.setTotalNumberOfExistingSubscriptions(event.getTotalSubscriptionForecast());
        newProductForecastMetricsView.setChurnedSubscriptions(event.getChurnedSubscriptionForecast());
        newProductForecastMetricsView.setNewSubscriptions(Double.valueOf(newSubscriptions).longValue());
        productForecastMetricsViewRepository.save(newProductForecastMetricsView);

    }

    private int daysOfMonth(LocalDate dt) {
        int month = dt.getMonthOfYear();
        int month2 = month;
        int days = dt.dayOfMonth().get();
        LocalDate dt2 = dt;
        while (month == month2) {
            days++;
            dt2.plusDays(1);
            month2 = dt2.getMonthOfYear();
        }
        return (days - 1);
    }
}
