package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.expensedistribution.vo.ProductWiseDeliveryStats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rbsavaliya on 19-06-2016.
 */
public class CalculatePerUnitExpense {

    public Map<String, Double> calculatePerUnitPrice (List<ProductWiseDeliveryStats> productWiseDeliveryStats) {
        final Map <String, Double> perUnitProductExpensesMap = new HashMap<>();
        for (ProductWiseDeliveryStats productWiseDeliveryStat: productWiseDeliveryStats) {
            perUnitProductExpensesMap.put(
                    productWiseDeliveryStat.getProductId(),
                    productWiseDeliveryStat.getTotalDeliveryExpense()/productWiseDeliveryStat.getTotalUnitsSold()
            );
        }
        return perUnitProductExpensesMap;
    }
}
