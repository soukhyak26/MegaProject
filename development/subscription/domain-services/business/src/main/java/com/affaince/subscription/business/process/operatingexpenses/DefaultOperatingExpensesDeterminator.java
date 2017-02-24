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
        double totalForecastedCommonOperatingExpense = 0;
        totalForecastedCommonOperatingExpense = yearlyExpenseAmount;
        double totalAnnualForecastedRevenueForAllProducts = 0;
        for (ProductView tempProductView : allActiveProducts) {
            List<ProductForecastView> currentProductForcastView = productForecastViewRepository.findByProductVersionId_ProductIdAndProductVersionId_FromDateBetween(tempProductView.getProductId(), new LocalDate(LocalDate.now().getYear(),1,1),new LocalDate(LocalDate.now().getYear(),12,31));
            totalAnnualForecastedRevenueForAllProducts += (currentProductForcastView.get(0).getTotalNumberOfExistingSubscriptions() * tempProductView.getMRP());
        }
        double contributorFactor = totalForecastedCommonOperatingExpense / totalAnnualForecastedRevenueForAllProducts;
        for (ProductView productView : allActiveProducts) {
            perUnitOperatingExpenses.put(productView.getProductId(), productView.getSensitiveTo().entrySet().iterator().next().getValue() * contributorFactor * productView.getMRP());
        }
        return perUnitOperatingExpenses;
    }
}
