package com.verifier.domains.sysdate.repository;

import com.verifier.domains.sysdate.view.SysDateTimeView;
import com.verifier.domains.sysdate.view.SysDateView;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.data.repository.CrudRepository;

public interface SysDateTimeViewRepository extends CrudRepository<SysDateTimeView,Integer> {

} 
