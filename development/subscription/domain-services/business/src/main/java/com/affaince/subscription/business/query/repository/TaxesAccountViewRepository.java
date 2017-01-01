package com.affaince.subscription.business.query.repository;

import com.affaince.subscription.business.query.view.NodalAccountView;
import com.affaince.subscription.business.query.view.TaxesAccountView;
import org.joda.time.YearMonth;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 31-12-2016.
 */
public interface TaxesAccountViewRepository extends CrudRepository<TaxesAccountView, String> {
    public TaxesAccountView findOne(String Id);
}
