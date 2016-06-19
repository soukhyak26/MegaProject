package com.affaince.subscription.expensedistribution.determinator;

import com.affaince.subscription.expensedistribution.query.repository.DeliveryViewRepository;
import com.affaince.subscription.expensedistribution.vo.OperatingExpenseDistributionStrategyType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by rsavaliya on 20/3/16.
 */
public class OperatingExpenseStrategyDeterminator {

    @Autowired
    private DeliveryViewRepository deliveryViewRepository;

    public OperatingExpenseDistributionStrategyType decideOperatingExpenseStrategy () {
        // TODO: Yet to implemented
        //if (deliveryViewRepository.count() > )
        return OperatingExpenseDistributionStrategyType.DEFAULT_STRATEGY;
    }
}
