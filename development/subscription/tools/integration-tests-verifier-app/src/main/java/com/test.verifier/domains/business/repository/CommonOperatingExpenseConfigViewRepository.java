package com.test.verifier.domains.business.repository;

import com.test.verifier.domains.business.view.CommonOperatingExpenseConfigView;
import com.test.verifier.domains.business.vo.CommonOperatingExpenseHeader;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 19-02-2017.
 */
public interface CommonOperatingExpenseConfigViewRepository extends CrudRepository<CommonOperatingExpenseConfigView,CommonOperatingExpenseHeader> {
}
