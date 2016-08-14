package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.command.event.SubscriptionForecastUpdatedEvent;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.query.view.ProductConfigurationView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;

/**
 * Created by mandar on 06-07-2016.
 */
public class SubscriptionForecastUpdatedEventListener {
    private final ProductForecastViewRepository productForecastViewRepository;
    private final ProductActualsViewRepository productActualsViewRepository;
    private final ProductConfigurationViewRepository productConfigurationViewRepository;
    @Value("${subscription.forecast.period}")
    private String forecastPeriod;

    @Autowired
    public SubscriptionForecastUpdatedEventListener(ProductForecastViewRepository productForecastViewRepository, ProductActualsViewRepository productActualsViewRepository, ProductConfigurationViewRepository productConfigurationViewRepository) {
        this.productForecastViewRepository = productForecastViewRepository;
        this.productActualsViewRepository = productActualsViewRepository;
        this.productConfigurationViewRepository = productConfigurationViewRepository;
    }

    @EventHandler
    public void on(SubscriptionForecastUpdatedEvent event) {
        LocalDate endDate;
        ProductConfigurationView productConfigurationView = productConfigurationViewRepository.findOne(event.getProductId());
        endDate = event.getStartDate().plusDays(productConfigurationView.getActualsAggregationPeriodForTargetForecast());
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        ProductForecastView latestProductForecastView = productForecastViewRepository.findByProductVersionId_ProductId(event.getProductId(), sort).get(0);
        latestProductForecastView.setEndDate(LocalDate.now());
        //forecasted new subscriptions per period= (new forecasted total subscription - latest forecasted total subscription + new forecasted churned subscriptions)
        double newSubscriptions = event.getTotalSubscriptionForecast() - latestProductForecastView.getTotalNumberOfExistingSubscriptions() + event.getChurnedSubscriptionForecast();
        ProductForecastView newProductForecastView = new ProductForecastView(new ProductVersionId(event.getProductId(), event.getStartDate()), endDate);
        newProductForecastView.setTotalNumberOfExistingSubscriptions(event.getTotalSubscriptionForecast());
        newProductForecastView.setChurnedSubscriptions(event.getChurnedSubscriptionForecast());
        newProductForecastView.setNewSubscriptions(Double.valueOf(newSubscriptions).longValue());
        productForecastViewRepository.save(newProductForecastView);

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
