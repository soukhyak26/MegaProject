package com.verifier.controller;

import com.verifier.domains.benefits.repository.BenefitsBenefitViewRepository;
import com.verifier.domains.benefits.view.BenefitView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "benefits")
public class BenefitController {
    private final BenefitsBenefitViewRepository benefitsBenefitViewRepository;

    @Autowired
    public BenefitController(BenefitsBenefitViewRepository benefitsBenefitViewRepository) {
        this.benefitsBenefitViewRepository = benefitsBenefitViewRepository;
    }
    @RequestMapping(method = RequestMethod.GET, value = "{benefitId}")
    public ResponseEntity<BenefitView> getBenefitView(@PathVariable String benefitId){
        BenefitView benefitView = benefitsBenefitViewRepository.findOne(benefitId);
        return new ResponseEntity<>(benefitView, HttpStatus.OK);
    }
}
