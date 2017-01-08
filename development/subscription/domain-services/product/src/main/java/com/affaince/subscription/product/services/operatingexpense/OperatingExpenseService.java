package com.affaince.subscription.product.services.operatingexpense;

import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.query.repository.BusinessAccountViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 06-01-2017.
 */
@Component
public class OperatingExpenseService {

    @Autowired
    private BusinessAccountViewRepository businessAccountViewRepository;

    public double fetchDefaultPercentFixedExpensePerUnitPrice() {
        return businessAccountViewRepository.findOne(SysDate.now().getYear()).getDefaultPercentFixedExpensePerUnitPrice();
    }

    public double fetchDefaultPercentVariableExpensePerUnitPrice() {
        return businessAccountViewRepository.findOne(SysDate.now().getYear()).getDefaultPercentVariableExpensePerUnitPrice();
    }
}
