package com.affaince.subscription.repository;

import com.affaince.subscription.date.SysDateView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rbsavaliya on 17-08-2016.
 */
public interface SysDateViewRepository extends CrudRepository<SysDateView, LocalDate> {

}
