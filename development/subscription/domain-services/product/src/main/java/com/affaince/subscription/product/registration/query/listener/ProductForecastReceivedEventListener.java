package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.registration.command.AddForecastParametersCommand;
import com.affaince.subscription.product.registration.command.event.ProductForecastReceivedEvent;
import com.affaince.subscription.product.registration.query.repository.ProductRepository;
import com.affaince.subscription.product.registration.vo.ForecastedPriceParameter;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
@Component
public class ProductForecastReceivedEventListener {

    private final ProductRepository itemRepository;
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    @Autowired
    public ProductForecastReceivedEventListener(ProductRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @EventHandler
    public void on(ProductForecastReceivedEvent event) throws Exception {
        ForecastedPriceParameter forecastedPriceParameter = new ForecastedPriceParameter(event.getPurchasePricePerUnit(),
                event.getOfferedPricePerUnit(),
                event.getMRP(),
                event.getNumberOfNewCustomersAssociatedWithAPrice(),
                event.getNumberOfChurnedCustomersAssociatedWithAPrice(),
                event.getNumberOfExistingCustomersAssociatedWithAPrice()
        );
/*
        AddForecastParametersCommand command = new AddForecastParametersCommand(
                event.getProductId(),
                event.getFromDate(),
                event.getToDate(),
                forecastedPriceParameter
        );
*/
        AddForecastParametersCommand command = new AddForecastParametersCommand();
        command.setProductId(event.getProductId());
        command.setFromDate(event.getFromDate());
        command.setToDate(event.getToDate());
        command.addForecastedPriceParameter(forecastedPriceParameter);
        commandGateway.executeAsync(command);
    }
}
