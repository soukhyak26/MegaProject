package com.affiance.business.view;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.SensitivityCharacteristic;
import com.affiance.business.vo.CommonOperatingExpenseHeader;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 19-02-2017.
 */
@Document(collection = "CommonOperatingExpenseConfigView")
public class CommonOperatingExpenseConfigView {
    @Id
    private CommonOperatingExpenseHeader expenseHeader;
    private double amount;
    private Period period;
    private SensitivityCharacteristic sensitivityCharacteristic;

    public CommonOperatingExpenseConfigView(CommonOperatingExpenseHeader expenseHeader, double amount, Period period, SensitivityCharacteristic sensitivityCharacteristic) {
        this.expenseHeader = expenseHeader;
        this.amount = amount;
        this.period = period;
        this.sensitivityCharacteristic = sensitivityCharacteristic;
    }

    public CommonOperatingExpenseHeader getExpenseHeader() {
        return expenseHeader;
    }

    public double getAmount() {
        return amount;
    }

    public Period getPeriod() {
        return period;
    }

    public SensitivityCharacteristic getSensitivityCharacteristic() {
        return sensitivityCharacteristic;
    }
}
