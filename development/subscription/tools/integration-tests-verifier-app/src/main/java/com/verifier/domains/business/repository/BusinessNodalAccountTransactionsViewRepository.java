package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.NodalAccountTransactionsView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 22-01-2017.
 */
public interface BusinessNodalAccountTransactionsViewRepository extends CrudRepository<NodalAccountTransactionsView, Long> {
}
