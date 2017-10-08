package com.affaince.subscription.benefits.command.handler;

import com.affaince.subscription.benefits.command.AddBenefitCommand;
import com.affaince.subscription.benefits.command.domain.Benefit;
import com.affaince.subscription.compiler.BenefitCompiler;
import com.affaince.subscription.compiler.Rule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Repository;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Created by rbsavaliya on 25-10-2015.
 */
@Component
public class AddBenefitCommandHandler {

    private final Repository<Benefit> benefitRepository;
    @Autowired
    private Locale locale;

    @Autowired
    public AddBenefitCommandHandler(Repository<Benefit> benefitRepository) {
        this.benefitRepository = benefitRepository;
    }

    @CommandHandler
    public void handle(AddBenefitCommand command) throws Exception {
        BenefitCompiler benefitCompiler = new BenefitCompiler();
        Rule ruleSet = benefitCompiler.compile(command.getBenefitEquation());
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd").withLocale(locale);
        benefitRepository.newInstance(() -> new Benefit(command.getBenefitId(), command.getBenefitEquation(), mapper.writeValueAsString(ruleSet),
                dateTimeFormatter.parseLocalDate(command.getActivationStartTime()), dateTimeFormatter.parseLocalDate(command.getActivationEndTime())));
    }
}
