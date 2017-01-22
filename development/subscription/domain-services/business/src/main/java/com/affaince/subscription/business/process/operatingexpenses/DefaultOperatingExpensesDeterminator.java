package com.affaince.subscription.business.process.operatingexpenses;


import com.affaince.subscription.business.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.business.query.repository.ProductViewRepository;
import com.affaince.subscription.business.query.view.ProductForecastView;
import com.affaince.subscription.business.query.view.ProductView;
import com.affaince.subscription.common.type.ProductStatus;
import org.joda.time.DateTimeFieldType;
import org.joda.time.LocalDate;
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
    public Map<String, Double> calculateOperatingExpensesPerProduct(double yearlyExpenseAmount) {
        Iterable<ProductView> allActiveProducts = productViewrepository.findByProductStatus(ProductStatus.PRODUCT_ACTIVATED);
        Map<String, Double> perUnitOperatingExpenses = new HashMap<>();
       // Map<YearMonth, Double> rollingExpenseForecast = expense.getRollingExpenseForecast();
       // double expenseAmountPerMonth=expense.getMonthlyExpenseAmount();
        //double currentOperatingExpense = rollingExpenseForecast.get(YearMonth.now());
        double totalForecastedCommonOperatingExpense = 0;
        YearMonth lastForecastedMonth = YearMonth.now();
        //int numberOfMonthsMultiplier=12-lastForecastedMonth.getMonthOfYear();
        totalForecastedCommonOperatingExpense = yearlyExpenseAmount;
        // double totalMonthlySaleAmount = 0;
        double totalAnnualForecastedRevenueForAllProducts = 0;
        LocalDate date = new LocalDate(lastForecastedMonth.get(DateTimeFieldType.year()),lastForecastedMonth.get(DateTimeFieldType.monthOfYear()),1);
        for (ProductView tempProductView : allActiveProducts) {
            //List<ProductForecastView> lastMonthProductForecast = productForecastViewRepository.findByProductVersionId_ProductIdAndProductVersionId_FromDateBetween(tempProductView.getProductId(), date.monthOfYear().withMinimumValue(), date.monthOfYear().withMaximumValue());
            List<ProductForecastView> lastMonthOfYearProductForecast = productForecastViewRepository.findByProductVersionId_ProductIdAndProductVersionId_FromDateBetween(tempProductView.getProductId(), lastForecastedMonth.year().getYearMonth().toLocalDate(1),lastForecastedMonth.year().getYearMonth().toLocalDate(31));
            totalAnnualForecastedRevenueForAllProducts += (lastMonthOfYearProductForecast.get(0).getTotalNumberOfExistingSubscriptions() * tempProductView.getMRP());
        }
        //double contributorFactor = currentOperatingExpense / totalMonthlySaleAmount;
        double contributorFactor = totalForecastedCommonOperatingExpense / totalAnnualForecastedRevenueForAllProducts;
        for (ProductView productView : allActiveProducts) {
            perUnitOperatingExpenses.put(productView.getProductId(), productView.getSensitiveTo().entrySet().iterator().next().getValue() * contributorFactor * productView.getMRP());
        }
        return perUnitOperatingExpenses;
    }
}
