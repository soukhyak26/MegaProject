package com.affaince.subscription.repository;

import com.affaince.subscription.metadata.MetadataFilter;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by anayonkar on 24/3/16.
 */
public interface CommonViewRepository extends CrudRepository<MetadataFilter, String> {
    public MetadataFilter findMetadataByUUID(String UUID);
}
