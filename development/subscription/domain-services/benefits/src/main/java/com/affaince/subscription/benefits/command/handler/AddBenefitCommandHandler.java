package com.affaince.subscription.benefits.command.handler;

import com.affaince.subscription.benefits.command.AddBenefitCommand;
import com.affaince.subscription.benefits.command.domain.Benefit;
import com.affaince.subscription.compiler.Compiler;
import com.affaince.subscription.pojos.RuleSet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 25-10-2015.
 */
@Component
public class AddBenefitCommandHandler {

    private final Repository<Benefit> benefitRepository;

    @Autowired
    public AddBenefitCommandHandler(Repository<Benefit> benefitRepository) {
        this.benefitRepository = benefitRepository;
    }

    @CommandHandler
    public void handle(AddBenefitCommand command) {
        com.affaince.subscription.compiler.Compiler compiler = new Compiler();
        RuleSet ruleSet = compiler.compile("if basketPrice > 3000 and cycle = 5 then 3 percent;");
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(ruleSet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        final Benefit benefit = new Benefit(command.getBenefitId(), command.getBenefitEquation(), jsonString,
                new LocalDateTime(command.getActivationStartTime()), new LocalDateTime(command.getActivationEndTime()));
        benefitRepository.add(benefit);
    }
}
