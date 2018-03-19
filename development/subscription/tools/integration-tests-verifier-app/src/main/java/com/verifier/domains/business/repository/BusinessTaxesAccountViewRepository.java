package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.TaxesAccountView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 31-12-2016.
 */
public interface BusinessTaxesAccountViewRepository extends CrudRepository<TaxesAccountView, String> {
}
