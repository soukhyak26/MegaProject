package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.CalculateBreakEvenPriceCommand;
import com.affaince.subscription.product.domain.Product;
import com.affaince.subscription.product.services.operatingexpense.OperatingExpenseService;
import com.affaince.subscription.product.services.pricing.calculator.breakevenprice.BreakEvenPriceCalculator;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 5/22/2017.
 */
@Component
public class CalculateBreakEvenPriceCommandHandler {
    private Repository<Product> products;
    private BreakEvenPriceCalculator breakEvenPriceCalculator;
    private OperatingExpenseService operatingExpenseService;
    @Autowired
    public CalculateBreakEvenPriceCommandHandler(Repository<Product> products,BreakEvenPriceCalculator breakEvenPriceCalculator,OperatingExpenseService operatingExpenseService) {
        this.products = products;
        this.breakEvenPriceCalculator=breakEvenPriceCalculator;
        this.operatingExpenseService=operatingExpenseService;
    }
    @CommandHandler
    public void handle(CalculateBreakEvenPriceCommand command){
        Product product= products.load(command.getProductId());
        product.getProductAccount().calculateBreakEvenPrice(command.getProductId(),command.getCostHeaderTypes(),breakEvenPriceCalculator,operatingExpenseService);
    }
}
