package com.affaince.subscription.expensedistribution.determinator;

import com.affaince.subscription.expensedistribution.query.repository.ProductViewRepository;
import com.affaince.subscription.expensedistribution.vo.OperatingExpenseDistributionStrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by rsavaliya on 20/3/16.
 */
public class OperatingExpenseStrategyDeterminator {

    @Value("${subscription.expensedistribution.strategy}")
    private String expenseDistributionStrategy;
    public OperatingExpenseDistributionStrategyType decideOperatingExpenseStrategy () {
        return OperatingExpenseDistributionStrategyType.valueOf(expenseDistributionStrategy);
    }
}
