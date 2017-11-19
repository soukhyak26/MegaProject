package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.expensedistribution.client.ExpenseDistributionClient;
import com.affaince.subscription.expensedistribution.query.view.*;
import com.affaince.subscription.expensedistribution.vo.ProductWiseDeliveryStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by rsavaliya on 31/3/16.
 */
public class ForecastBasedOperatingExpenseDistributionDeterminator implements OperatingExpenseDistribution {

    @Autowired
    private ExpenseDistributionClient expenseDistributionClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastBasedOperatingExpenseDistributionDeterminator.class);

    public ForecastBasedOperatingExpenseDistributionDeterminator() {
    }

    @Override
    public Map<String, Double> distributeDeliveryExpensesToProduct() throws IOException {

        final List<ProductTotalForecastView> productTotalForecastViews = expenseDistributionClient.getProductTotalForecastViews();
        final Double totalYearlyVariableExpenses = expenseDistributionClient.getTotalVariableExpenseOfFinancialYear();

        double totalPurchasePrice = 0;
        for (ProductTotalForecastView productTotalForecastView: productTotalForecastViews) {
            totalPurchasePrice += productTotalForecastView.getPurchasePrice()*productTotalForecastView.getTotalForecast();
        }

        return calculateProductWisePerUnitDeliveryExpenses(totalPurchasePrice, totalYearlyVariableExpenses, productTotalForecastViews);
    }

    private Map<String, Double> calculateProductWisePerUnitDeliveryExpenses(double totalPurchasePrice, Double totalYearlyVariableExpenses, List<ProductTotalForecastView> productTotalForecastViews) {
        final Map<String, Double> productWisePerUnitDeliveryExpenseMap = new HashMap<>(productTotalForecastViews.size());
        productTotalForecastViews.forEach(productTotalForecastView -> {
            productWisePerUnitDeliveryExpenseMap.put(productTotalForecastView.getProductId(),
                        (productTotalForecastView.getPurchasePrice()*totalYearlyVariableExpenses)/totalPurchasePrice
                    );
        });
        return productWisePerUnitDeliveryExpenseMap;
    }
}
