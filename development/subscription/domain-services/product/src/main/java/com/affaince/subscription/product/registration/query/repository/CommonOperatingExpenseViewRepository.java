package com.affaince.subscription.product.registration.query.repository;

import com.affaince.subscription.product.registration.query.view.CommonOperatingExpenseView;
import org.joda.time.YearMonth;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
public interface CommonOperatingExpenseViewRepository extends CrudRepository<CommonOperatingExpenseView, String> {
    List<CommonOperatingExpenseView> findByExpenseHeaderAndMonthOfYear(String expenseHeader, YearMonth monthOfYear);
}
