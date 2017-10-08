package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.vo.EntityMetadata;
import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.common.vo.EntityType;
import com.affaince.subscription.product.command.event.ChangeOfTotalStockNotificationEvent;
import com.affaince.subscription.product.command.event.ProductAnalyserCreatedEvent;
import com.affaince.subscription.product.command.event.ProductDemandDecreaseNotificationEvent;
import com.affaince.subscription.product.command.event.ProductDemandIncreaseNotificationEvent;
import com.affaince.subscription.product.query.predictions.ProductHistoryRetriever;
import com.affaince.subscription.product.query.view.ProductForecastTrendView;
import com.affaince.subscription.product.services.trend.ProductTrendChangeDetector;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 10/1/2017.
 */
public class ProductAnalyser extends AbstractAnnotatedAggregateRoot<Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductAnalyser.class);
    @AggregateIdentifier
    private Integer productAnalyserId;

    public ProductAnalyser(){
    }

    public ProductAnalyser(Integer id){
        apply(new ProductAnalyserCreatedEvent(id));
    }

    @EventSourcingHandler
    public void on(ProductAnalyserCreatedEvent event){
        this.productAnalyserId= event.getProductAnalyserId();
    }

    public void initiateForecast(String productId, EntityMetricType entityMetricType,ProductHistoryRetriever productHistoryRetriever){
        Map<String,Object> metadata= new HashMap<>();
        metadata.put("ENTITY_TYPE", EntityType.PRODUCT);
        metadata.put("ENTITY_METRIC_TYPE", entityMetricType);
        productHistoryRetriever.marshallSendAndReceive(productId,metadata);
    }

    public void analyseProductTrendChange(String productId, EntityMetadata entityMetadata, LocalDate forecastDate, ProductTrendChangeDetector productTrendChangeDetector) {
        List<ProductForecastTrendView> futureTrend= productTrendChangeDetector.determineTrendChange((String)productId,entityMetadata);
        for(ProductForecastTrendView trend: futureTrend){
            double referenceTotalSubscriptionCount=trend.getReferenceTotalSubscriptionCount();
            double expectedChangeInTotalSubscriptionCount = trend.getChangeInTotalSusbcriptionCount();
            double referenceChurnedSubscriptionCount=trend.getReferenceChurnedSubscriptionCount();
            double expectedChangeInChurnedSubscriptionCount=trend.getChangeInChurnedSubscriptionCount();
            double referenceNewSubscriptionCount = trend.getReferenceNewSubscriptionCount();
            double expectedChangeInNewSubscriptionCount=trend.getChangeInNewSubscriptionCount();
            double contingencyStockPercentage=0.1;
            if(expectedChangeInTotalSubscriptionCount > contingencyStockPercentage){
                apply(new ChangeOfTotalStockNotificationEvent(productId,referenceTotalSubscriptionCount,expectedChangeInTotalSubscriptionCount,trend.getForecastVersionId().getFromDate(),trend.getEndDate(),trend.getForecastVersionId().getForecastDate()));
                if(expectedChangeInNewSubscriptionCount > 0 || expectedChangeInChurnedSubscriptionCount <0){
                    apply(new ProductDemandIncreaseNotificationEvent(productId,referenceNewSubscriptionCount,expectedChangeInNewSubscriptionCount,referenceChurnedSubscriptionCount,expectedChangeInChurnedSubscriptionCount,trend.getForecastVersionId().getFromDate(),trend.getEndDate(),trend.getForecastVersionId().getForecastDate()));
                }
            }else if(expectedChangeInTotalSubscriptionCount < 0 ){
                apply(new ChangeOfTotalStockNotificationEvent(productId,referenceTotalSubscriptionCount,expectedChangeInTotalSubscriptionCount,trend.getForecastVersionId().getFromDate(),trend.getEndDate(),trend.getForecastVersionId().getForecastDate()));
                if(expectedChangeInNewSubscriptionCount < 0 || expectedChangeInChurnedSubscriptionCount >0){
                    apply(new ProductDemandDecreaseNotificationEvent(productId,referenceNewSubscriptionCount,expectedChangeInNewSubscriptionCount,referenceChurnedSubscriptionCount,expectedChangeInChurnedSubscriptionCount,trend.getForecastVersionId().getFromDate(),trend.getEndDate(),trend.getForecastVersionId().getForecastDate()));
                }
            }
        }
    }
}
