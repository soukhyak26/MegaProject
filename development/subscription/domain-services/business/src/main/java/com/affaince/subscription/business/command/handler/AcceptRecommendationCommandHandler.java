package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.AcceptRecommendationCommand;
import com.affaince.subscription.business.command.domain.BusinessAccount;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 18-02-2017.
 */
@Component
public class AcceptRecommendationCommandHandler {
    private final Repository<BusinessAccount> repository;
    @Autowired
    public AcceptRecommendationCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    public void handle(AcceptRecommendationCommand command){
        BusinessAccount businessAccount=repository.load(command.getBusinessAccountId());
        businessAccount.acceptRecommendations(command.getAcceptedRecommendations(),command.getRejectedRecommendations());
    }
}
