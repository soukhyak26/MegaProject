package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.BusinessAccountConfigurationView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 10/8/2017.
 */
public interface BusinessBusinessAccountConfigurationViewRepository extends CrudRepository<BusinessAccountConfigurationView,String> {
    public List<BusinessAccountConfigurationView> findAll();
}
