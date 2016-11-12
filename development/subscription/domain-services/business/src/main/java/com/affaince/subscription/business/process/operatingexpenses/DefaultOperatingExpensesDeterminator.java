package com.affaince.subscription.business.process.operatingexpenses;


import com.affaince.subscription.business.command.domain.CommonOperatingExpense;
import com.affaince.subscription.business.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.business.query.repository.ProductViewRepository;
import com.affaince.subscription.business.query.view.ProductForecastView;
import com.affaince.subscription.business.query.view.ProductView;
import com.affaince.subscription.common.type.ProductStatus;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mandark on 02-01-2016.
 */
public class DefaultOperatingExpensesDeterminator implements OperatingExpensesDeterminator {
    @Autowired
    ProductViewRepository productViewrepository;
    @Autowired
    ProductForecastViewRepository productForecastViewRepository;

    @Override
    public Map<String, Double> calculateOperatingExpensesPerProduct(CommonOperatingExpense expense) {
        Iterable<ProductView> allActiveProducts = productViewrepository.findByProductStatus(ProductStatus.PRODUCT_ACTIVATED);
        Map<String, Double> perUnitOperatingExpenses = new HashMap<>();
        Map<YearMonth, Double> rollingExpenseForecast = expense.getRollingExpenseForecast();
        //double currentOperatingExpense = rollingExpenseForecast.get(YearMonth.now());
        double totalForecastedCommonOperatingExpense = 0;
        YearMonth lastForecastedMonth = null;
        for (Map.Entry<YearMonth, Double> entry : rollingExpenseForecast.entrySet()) {
            totalForecastedCommonOperatingExpense += entry.getValue();
            lastForecastedMonth = entry.getKey();
        }
        // double totalMonthlySaleAmount = 0;
        double totalAnnualForecastedRevenueForAllProducts = 0;
        for (ProductView tempProductView : allActiveProducts) {
            DateTime datetime = new DateTime(lastForecastedMonth.get(DateTimeFieldType.year()), lastForecastedMonth.get(DateTimeFieldType.monthOfYear()), 1, 0, 0);
            List<ProductForecastView> lastMonthProductForecast = productForecastViewRepository.findByProductVersionId_ProductIdAndProductVersionId_FromDateBetween(tempProductView.getProductId(), datetime.monthOfYear().withMinimumValue().toLocalDateTime(), datetime.monthOfYear().withMaximumValue().toLocalDateTime());
            //totalMonthlySaleAmount += tempProductView.getTotalMonthlySaleAmount();
            totalAnnualForecastedRevenueForAllProducts += (lastMonthProductForecast.get(0).getTotalNumberOfExistingSubscriptions() * tempProductView.getMRP());
        }
        //double contributorFactor = currentOperatingExpense / totalMonthlySaleAmount;
        double contributorFactor = totalForecastedCommonOperatingExpense / totalAnnualForecastedRevenueForAllProducts;
        for (ProductView productView : allActiveProducts) {
            perUnitOperatingExpenses.put(productView.getProductId(), productView.getSensitiveTo().entrySet().iterator().next().getValue() * contributorFactor * productView.getMRP());
            //productView.setCurrentOperatingExpensePerUnit(productView.getSensitiveTo().entrySet().iterator().next().getValue() * contributorFactor * productView.getMRP());
        }
        return perUnitOperatingExpenses;
    }
}
