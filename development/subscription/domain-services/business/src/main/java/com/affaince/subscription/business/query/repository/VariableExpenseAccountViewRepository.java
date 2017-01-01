package com.affaince.subscription.business.query.repository;

import com.affaince.subscription.business.query.view.OthersAccountView;
import com.affaince.subscription.business.query.view.VariableExpenseAccountView;
import org.joda.time.YearMonth;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 31-12-2016.
 */
public interface VariableExpenseAccountViewRepository extends CrudRepository<VariableExpenseAccountView, String> {
    public VariableExpenseAccountView findOne(String Id);
}
