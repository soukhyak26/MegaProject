package com.affaince.subscription.business.query.repository;

import com.affaince.subscription.business.query.view.CommonExpenseAccountView;
import com.affaince.subscription.business.query.view.VariableExpenseAccountView;
import org.joda.time.YearMonth;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 31-12-2016.
 */
public interface CommonExpenseAccountViewRepository extends CrudRepository<CommonExpenseAccountView, String> {
    public CommonExpenseAccountView findOne(String Id);
}
