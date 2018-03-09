package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.CommonOperatingExpenseConfigView;
import com.verifier.domains.business.vo.CommonOperatingExpenseHeader;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 19-02-2017.
 */
public interface CommonOperatingExpenseConfigViewRepository extends CrudRepository<CommonOperatingExpenseConfigView,CommonOperatingExpenseHeader> {
}
