package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.UpdateFixedExpenseToProductCommand;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.services.operatingexpense.OperatingExpenseService;
import com.affaince.subscription.product.services.pricing.calculator.breakevenprice.BreakEvenPriceCalculator;
import com.affaince.subscription.product.vo.CostHeaderType;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.List;

/**
 * Created by mandar on 16-10-2016.
 */
@Component
public class UpdateFixedExpenseToProductCommandHandler {
    private final Repository<Product> repository;
    private BreakEvenPriceCalculator breakEvenPriceCalculator;

    @Autowired
    public UpdateFixedExpenseToProductCommandHandler(Repository<Product> repository,BreakEvenPriceCalculator breakEvenPriceCalculator) {
        this.repository = repository;
        this.breakEvenPriceCalculator=breakEvenPriceCalculator;
    }

    @CommandHandler
    public void on(UpdateFixedExpenseToProductCommand command) {
        final Product product = repository.load(command.getProductId());
        List<CostHeaderType> costHeaderTypes=product.getProductConfiguration().getCostHeaderTypes();
        product.updateFixedExpenses(command,costHeaderTypes,breakEvenPriceCalculator);
    }
}
