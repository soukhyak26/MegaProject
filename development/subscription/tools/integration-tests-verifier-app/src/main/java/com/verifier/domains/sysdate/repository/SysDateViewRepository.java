package com.verifier.domains.sysdate.repository;

import com.verifier.domains.sysdate.view.SysDateView;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SysDateViewRepository extends CrudRepository<SysDateView,Integer> {
        List<SysDateView> findAll();
} 
