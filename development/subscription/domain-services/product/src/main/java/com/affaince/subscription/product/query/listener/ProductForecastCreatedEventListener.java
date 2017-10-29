package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.aggregate.AggregatorFactory;
import com.affaince.subscription.common.aggregate.aggregators.MetricsAggregator;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.common.vo.EntityMetadata;
import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.product.command.DetectChangeInProductTrendCommand;
import com.affaince.subscription.product.command.event.ProductForecastCreatedEvent;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.query.repository.ProductPseudoActualsViewRepository;
import com.affaince.subscription.product.query.view.ProductConfigurationView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.query.view.ProductPseudoActualsView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 9/29/2017.
 */
@Component
public class ProductForecastCreatedEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductForecastCreatedEventListener.class);
    private final ProductForecastViewRepository productForecastViewRepository;
    private final ProductPseudoActualsViewRepository productPseudoActualsViewRepository;
    private final ProductConfigurationViewRepository productConfigurationViewRepository;
    private final AggregatorFactory<DataFrameVO> aggregatorFactory;
    private final SubscriptionCommandGateway commandGateway;
    @Autowired
    public ProductForecastCreatedEventListener(ProductForecastViewRepository productForecastViewRepository, ProductPseudoActualsViewRepository productPseudoActualsViewRepository,ProductConfigurationViewRepository productConfigurationViewRepository, AggregatorFactory<DataFrameVO> aggregatorFactory,SubscriptionCommandGateway commandGateway) {
        this.productForecastViewRepository = productForecastViewRepository;
        this.productPseudoActualsViewRepository = productPseudoActualsViewRepository;
        this.productConfigurationViewRepository=productConfigurationViewRepository;
        this.aggregatorFactory = aggregatorFactory;
        this.commandGateway=commandGateway;
    }

    @EventHandler
    public void on(ProductForecastCreatedEvent event) throws Exception{
        final List<DataFrameVO> forecastData = event.getDataFrameVOs();
        final LocalDate forecastDate = event.getForecastDate();
        final EntityMetadata entityMetadata = event.getEntityMetadata();
        final Object productId=event.getId();

        expireOverlappingActiveForecast(productId, forecastDate);
        expireOverlappingActivePseudoActuals(productId, forecastDate);
        updatePseudoActuals(productId, forecastData, forecastDate, entityMetadata);
        updateForecast(productId, forecastData, forecastDate, entityMetadata);

        ProductConfigurationView productConfigurationView=productConfigurationViewRepository.findOne((String)productId);
        //this is a dummy next forecast date.. actual date will be overriden by the pricing calculator
        productConfigurationView.setNextForecastDate(forecastDate.plusMonths(2));
        productConfigurationViewRepository.save(productConfigurationView);

        //hardcoded productanalyserId
        DetectChangeInProductTrendCommand command= new DetectChangeInProductTrendCommand(1,(String)productId,entityMetadata,forecastDate);
        commandGateway.executeAsync(command);

    }

    private void expireOverlappingActiveForecast(Object entityId,LocalDate forecastDate) {
        List<ProductForecastView> earlierForecastsWithOverlappingPeriods = productForecastViewRepository.findByForecastVersionId_ProductIdAndForecastContentStatusAndForecastDateLessThan((String)entityId,ForecastContentStatus.ACTIVE, forecastDate);
        for (ProductForecastView earlierView : earlierForecastsWithOverlappingPeriods) {
            earlierView.setForecastContentStatus(ForecastContentStatus.EXPIRED);
        }
        if (null != earlierForecastsWithOverlappingPeriods && earlierForecastsWithOverlappingPeriods.size() > 0) {
            productForecastViewRepository.save(earlierForecastsWithOverlappingPeriods);
        }
    }

    private void updateForecast(Object entityId, List<DataFrameVO> dataFrameVOs, LocalDate forecastDate, EntityMetadata entityMetadata) {
        List<ProductForecastView> forecastViews = new ArrayList<>();
        MetricsAggregator<DataFrameVO> aggregator = this.aggregatorFactory.getAggregator(30, DataFrameVO.class);
        List<DataFrameVO> aggregatedVOs = aggregator.aggregate(dataFrameVOs, 30);
        Map<String, Object> namedMetadata = entityMetadata.getNamedEntries();
        EntityMetricType entityMetricType = null;
        for (String s : namedMetadata.keySet()) {
            switch (s) {
                case "ENTITY_METRIC_TYPE":
                    entityMetricType = (EntityMetricType) namedMetadata.get(s);
                    break;
            }
        }

        for (DataFrameVO vo : dataFrameVOs) {
            ProductForecastView view=null;
            List<ProductForecastView> alreadySavedViews= productForecastViewRepository.findByProductVersionId_ProductIdAndForecastContentStatusAndForecastDate((String) entityId,ForecastContentStatus.ACTIVE,forecastDate);
            if(null==alreadySavedViews && alreadySavedViews.isEmpty()) {
                view = new ProductForecastView(new ForecastVersionId((String) entityId, vo.getStartDate(), forecastDate), vo.getEndDate());
            }else{
                view=alreadySavedViews.get(0);
            }
            //view.
            switch (entityMetricType) {
                case NEW:
                    view.setNewSubscriptions(Double.valueOf(vo.getValue()).longValue());
                    break;
                case CHURN:
                    view.setChurnedSubscriptions(Double.valueOf(vo.getValue()).longValue());
                    break;
                case TOTAL:
                    view.setTotalNumberOfExistingSubscriptions(Double.valueOf(vo.getValue()).longValue());
            }
            forecastViews.add(view);
        }
        productForecastViewRepository.save(forecastViews);


    }

    private void updatePseudoActuals(Object entityId, List<DataFrameVO> dataFrameVOs, LocalDate forecastDate, EntityMetadata entityMetadata) {
        List<ProductPseudoActualsView> pseudoActualsViews = new ArrayList<>();
        for (DataFrameVO vo : dataFrameVOs) {
            ProductPseudoActualsView view=null;
            List<ProductPseudoActualsView> alreadySavedViews= productPseudoActualsViewRepository.findByProductVersionId_ProductIdAndForecastContentStatusAndForecastDate((String) entityId,ForecastContentStatus.ACTIVE,forecastDate);
            if(null == alreadySavedViews && alreadySavedViews.isEmpty()) {
                ForecastVersionId forecastVersionId=new ForecastVersionId((String) entityId,vo.getStartDate(), forecastDate);
                view = new ProductPseudoActualsView(forecastVersionId, vo.getEndDate());
            }else{
                view= alreadySavedViews.get(0);
            }
            Map<String, Object> namedMetadata = entityMetadata.getNamedEntries();
            EntityMetricType entityMetricType = null;
            for (String s : namedMetadata.keySet()) {
                switch (s) {
                    case "ENTITY_METRIC_TYPE":
                        entityMetricType = (EntityMetricType) namedMetadata.get(s);
                        break;
                }
            }

            switch (entityMetricType) {
                case NEW:
                    view.setNewSubscriptions(Double.valueOf(vo.getValue()).longValue());
                    break;
                case CHURN:
                    view.setChurnedSubscriptions(Double.valueOf(vo.getValue()).longValue());
                    break;
                case TOTAL:
                    view.setTotalNumberOfExistingSubscriptions(Double.valueOf(vo.getValue()).longValue());
            }
            pseudoActualsViews.add(view);
        }
        productPseudoActualsViewRepository.save(pseudoActualsViews);
    }

    private void expireOverlappingActivePseudoActuals(Object entityId,LocalDate forecastDate) {
        List<ProductPseudoActualsView> earlierPseudoActualsWithOverlappingPeriods = productPseudoActualsViewRepository.findByProductVersionId_ProductIdAndForecastContentStatusAndForecastDateLessThan((String) entityId,ForecastContentStatus.ACTIVE, forecastDate);
        for (ProductPseudoActualsView earlierView : earlierPseudoActualsWithOverlappingPeriods) {
            earlierView.setForecastContentStatus(ForecastContentStatus.EXPIRED);
        }
        if (null != earlierPseudoActualsWithOverlappingPeriods && earlierPseudoActualsWithOverlappingPeriods.size() > 0) {
            productPseudoActualsViewRepository.save(earlierPseudoActualsWithOverlappingPeriods);
        }
    }

}
