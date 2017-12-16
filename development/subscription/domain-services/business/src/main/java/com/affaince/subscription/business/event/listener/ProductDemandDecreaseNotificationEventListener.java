package com.affaince.subscription.business.event.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.ReduceFromPurchaseCostAccountCommand;
import com.affaince.subscription.business.event.ProductDemandIncreaseNotificationEvent;
import com.affaince.subscription.business.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.business.query.repository.ProductViewRepository;
import com.affaince.subscription.business.query.view.ProductForecastView;
import com.affaince.subscription.business.query.view.ProductView;
import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.date.SysDate;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 2/23/2017.
 */
@Component
public class ProductDemandDecreaseNotificationEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDemandDecreaseNotificationEventListener.class);
    private final ProductForecastViewRepository productForecastViewRepository;
    private final ProductViewRepository productViewRepository;
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public ProductDemandDecreaseNotificationEventListener(ProductForecastViewRepository productForecastViewRepository, ProductViewRepository productViewRepository, SubscriptionCommandGateway commandGateway) {
        this.productForecastViewRepository = productForecastViewRepository;
        this.productViewRepository = productViewRepository;
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void on(ProductDemandIncreaseNotificationEvent event) throws Exception {
        List<ProductForecastView> existingForecastViews = this.productForecastViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(event.getProductId(), event.getFromDate(), event.getEndDate());
        //forecast should not be newly added if it already exists in the view
        long revisedTotalSubscriptionCount = 0;
        if (null != existingForecastViews && !existingForecastViews.isEmpty()) {
            ProductForecastView currentForecastView = existingForecastViews.get(0);
            long currentTotalSubscriptions = currentForecastView.getTotalNumberOfExistingSubscriptions();

            if (event.getReferenceNewSubscriptionCount() == Double.MAX_VALUE) {
                revisedTotalSubscriptionCount = currentTotalSubscriptions + Math.round(event.getReferenceNewSubscriptionCount());
            } else {
                revisedTotalSubscriptionCount = currentTotalSubscriptions + Math.round(event.getReferenceNewSubscriptionCount() * event.getExpectedChangeInNewSubscriptionCount());
            }
            if (event.getReferenceChurnedSubscriptionCount() == Double.MAX_VALUE) {
                revisedTotalSubscriptionCount -= Math.round(event.getReferenceChurnedSubscriptionCount());
            } else {
                revisedTotalSubscriptionCount -= Math.round(event.getReferenceChurnedSubscriptionCount() * event.getExpectedChangeInChurnedSubscriptionCount());
            }
            if (revisedTotalSubscriptionCount > currentTotalSubscriptions) {
                currentForecastView.setTotalNumberOfExistingSubscriptions(revisedTotalSubscriptionCount);
                productForecastViewRepository.save(currentForecastView);
            }
        } else {
            if (event.getReferenceNewSubscriptionCount() == Double.MAX_VALUE) {
                revisedTotalSubscriptionCount = Math.round(event.getReferenceNewSubscriptionCount());
            } else {
                revisedTotalSubscriptionCount = Math.round(event.getReferenceNewSubscriptionCount() * event.getExpectedChangeInNewSubscriptionCount());
            }
            if (event.getReferenceChurnedSubscriptionCount() == Double.MAX_VALUE) {
                revisedTotalSubscriptionCount -= Math.round(event.getReferenceChurnedSubscriptionCount());
            } else {
                revisedTotalSubscriptionCount -= Math.round(event.getReferenceChurnedSubscriptionCount() * event.getExpectedChangeInChurnedSubscriptionCount());
            }
            ProductForecastView productForecastView = new ProductForecastView(new ForecastVersionId(event.getProductId(), event.getFromDate(),SysDate.now()), event.getEndDate(), revisedTotalSubscriptionCount);
            productForecastViewRepository.save(productForecastView);
        }

        //retrieve product view for this product and set its budgeted purchase cost
        ProductView productView = productViewRepository.findByProductId(event.getProductId());
        double productPurchasePrice = productView.getPurchasePrice();
        ReduceFromPurchaseCostAccountCommand command = new ReduceFromPurchaseCostAccountCommand(SysDate.now().getYear(), event.getProductId(), revisedTotalSubscriptionCount, productPurchasePrice, SysDate.now());
        commandGateway.executeAsync(command);
    }

}
