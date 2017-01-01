package com.affaince.subscription.business.query.repository;

import com.affaince.subscription.business.query.view.BenefitAccountView;
import com.affaince.subscription.business.query.view.PurchaseCostAccountView;
import org.joda.time.YearMonth;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 31-12-2016.
 */
public interface PurchaseCostAccountViewRepository extends CrudRepository<PurchaseCostAccountView, String> {
    public PurchaseCostAccountView findOne(String Id);
}
