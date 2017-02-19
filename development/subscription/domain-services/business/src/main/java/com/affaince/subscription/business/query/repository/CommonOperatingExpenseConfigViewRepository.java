package com.affaince.subscription.business.query.repository;

import com.affaince.subscription.business.query.view.CommonOperatingExpenseConfigView;
import com.affaince.subscription.business.vo.CommonOperatingExpenseHeader;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 19-02-2017.
 */
public interface CommonOperatingExpenseConfigViewRepository extends CrudRepository<CommonOperatingExpenseConfigView,CommonOperatingExpenseHeader> {
}
