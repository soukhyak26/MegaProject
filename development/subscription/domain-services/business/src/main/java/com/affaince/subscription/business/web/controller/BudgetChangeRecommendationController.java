package com.affaince.subscription.business.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.AcceptRecommendationCommand;
import com.affaince.subscription.business.query.repository.BudgetChangeRecommendationViewRepository;
import com.affaince.subscription.business.query.view.BudgetChangeRecommendationView;
import com.affaince.subscription.business.web.request.RecommendationsDecisionRequest;
import org.joda.time.YearMonth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import java.util.List;

/**
 * Created by mandar on 18-02-2017.
 */
@RestController
@RequestMapping(value = "businessacount/budgetrecommend")
public class BudgetChangeRecommendationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessAccountProvisionController.class);
    private final SubscriptionCommandGateway commandGateway;
    private final BudgetChangeRecommendationViewRepository budgetChangeRecommendationViewRepository;

    @Autowired
    public BudgetChangeRecommendationController(SubscriptionCommandGateway commandGateway, BudgetChangeRecommendationViewRepository budgetChangeRecommendationViewRepository) {
        this.commandGateway = commandGateway;
        this.budgetChangeRecommendationViewRepository = budgetChangeRecommendationViewRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "listrecommendations")
    @Consumes("application/json")
    public List<BudgetChangeRecommendationView> listAllRecommendations() {
        return budgetChangeRecommendationViewRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "acceptRecommendations")
    @Consumes("application/json")
    public ResponseEntity<Object> acceptRecommendations(@RequestBody @Valid RecommendationsDecisionRequest request) throws Exception {
        Integer id = YearMonth.now().getYear();
        AcceptRecommendationCommand command = new AcceptRecommendationCommand(id, request.getAcceptedOrOverridenRecommendations(), request.getRejectedRecommendations());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
