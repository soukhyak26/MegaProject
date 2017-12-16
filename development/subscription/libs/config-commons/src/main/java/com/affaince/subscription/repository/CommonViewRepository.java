package com.affaince.subscription.repository;

import com.affaince.subscription.query.view.CommonView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by anayonkar on 24/3/16.
 */
public interface CommonViewRepository extends CrudRepository<CommonView, String> {
    public CommonView findMetadataByUUID(String UUID);
}
