package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.UpdateDeliveryExpenseToProductCommand;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.services.operatingexpense.OperatingExpenseService;
import com.affaince.subscription.product.services.pricing.calculator.breakevenprice.BreakEvenPriceCalculator;
import com.affaince.subscription.product.vo.CostHeaderType;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

/**
 * Created by rsavaliya on 8/5/16.
 */
@Component
public class UpdateDeliveryExpenseToProductCommandHandler {
    private final Repository <Product> productAccountRepository;
    private final BreakEvenPriceCalculator breakEvenPriceCalculator;

    @Autowired
    public UpdateDeliveryExpenseToProductCommandHandler(Repository<Product> productAccountRepository,BreakEvenPriceCalculator breakEvenPriceCalculator) {
        this.productAccountRepository = productAccountRepository;
        this.breakEvenPriceCalculator=breakEvenPriceCalculator;
    }

    @CommandHandler
    public void handle (UpdateDeliveryExpenseToProductCommand command) {
        final Product product = productAccountRepository.load(command.getProductId());
        EnumSet<CostHeaderType> costHeaderTypes=product.getProductConfiguration().getCostHeaderTypes();
        product.getProductAccount().updateSubscriptionSpecificExpenses(command, costHeaderTypes,breakEvenPriceCalculator);
    }
}
