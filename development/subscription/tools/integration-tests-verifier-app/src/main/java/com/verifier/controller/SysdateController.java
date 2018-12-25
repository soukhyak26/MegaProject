package com.verifier.controller;

import com.affaince.subscription.common.type.ConsumerBasketActivationStatus;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.vo.DeliveryId;
import com.affaince.subscription.common.vo.SubscriberName;
import com.verifier.domains.subscriber.repository.*;
import com.verifier.domains.subscriber.view.*;
import com.verifier.domains.sysdate.repository.SysDateViewRepository;
import com.verifier.domains.sysdate.view.SysDateView;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "sysdate")
public class SysdateController {
    private final SysDateViewRepository sysDateViewRepository;

    @Autowired
    public SysdateController(SysDateViewRepository sysDateViewRepository) {
        this.sysDateViewRepository = sysDateViewRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "date")
    public ResponseEntity<SysDateView> getCurrentDate() {
        SysDateView sysDate = sysDateViewRepository.findAll().get(0);
        return new ResponseEntity<>(sysDate, HttpStatus.OK);
    }
}
