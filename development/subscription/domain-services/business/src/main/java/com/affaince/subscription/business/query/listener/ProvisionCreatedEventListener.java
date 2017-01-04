package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.ProvisionCreatedEvent;
import com.affaince.subscription.business.query.repository.*;
import com.affaince.subscription.business.query.view.*;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * Created by mandar on 31-12-2016.
 */
@Component
public class ProvisionCreatedEventListener {

    private PurchaseCostAccountViewRepository purchaseCostAccountViewRepository;
    private LossesAccountViewRepository lossesAccountViewRepository;
    private BenefitAccountViewRepository benefitAccountViewRepository;
    private TaxesAccountViewRepository taxesAccountViewRepository;
    private OthersAccountViewRepository othersAccountViewRepository;
    private CommonExpenseAccountViewRepository commonExpenseAccountViewRepository;
    private NodalAccountViewRepository nodalAccountViewRepository;
    private BookingAmountAccountViewRepository bookingAmountAccountViewRepository;
    private VariableExpenseAccountViewRepository variableExpenseAccountViewRepository;
    private BusinessAccountViewRepository businessAccountViewRepository;

    @Autowired
    public ProvisionCreatedEventListener(PurchaseCostAccountViewRepository purchaseCostAccountViewRepository, LossesAccountViewRepository lossesAccountViewRepository, BenefitAccountViewRepository benefitAccountViewRepository, TaxesAccountViewRepository taxesAccountViewRepository, OthersAccountViewRepository othersAccountViewRepository, CommonExpenseAccountViewRepository commonExpenseAccountViewRepository, NodalAccountViewRepository nodalAccountViewRepository, BookingAmountAccountViewRepository bookingAmountAccountViewRepository, VariableExpenseAccountViewRepository variableExpenseAccountViewRepository, BusinessAccountViewRepository businessAccountViewRepository) {
        this.purchaseCostAccountViewRepository = purchaseCostAccountViewRepository;
        this.lossesAccountViewRepository = lossesAccountViewRepository;
        this.benefitAccountViewRepository = benefitAccountViewRepository;
        this.taxesAccountViewRepository = taxesAccountViewRepository;
        this.othersAccountViewRepository = othersAccountViewRepository;
        this.commonExpenseAccountViewRepository = commonExpenseAccountViewRepository;
        this.nodalAccountViewRepository = nodalAccountViewRepository;
        this.bookingAmountAccountViewRepository = bookingAmountAccountViewRepository;
        this.variableExpenseAccountViewRepository = variableExpenseAccountViewRepository;
        this.businessAccountViewRepository = businessAccountViewRepository;
    }

    @EventHandler
    public void on(ProvisionCreatedEvent event) {
        final Integer currentYear = event.getProvisionDate().getYear();
        LocalDateTime startDate = event.getProvisionDate();

        LocalDateTime endDate = new LocalDateTime(currentYear,12,31,12,59,59);
        purchaseCostAccountViewRepository.save(new PurchaseCostAccountView(currentYear,event.getProvisionForPurchaseCost(),
                0.0, startDate,endDate));

        lossesAccountViewRepository.save(new LossesAccountView(currentYear,event.getProvisionForLosses(),
                0.0, startDate,endDate));

        benefitAccountViewRepository.save(new BenefitAccountView(currentYear,event.getProvisionForBenefits(),
                0.0, startDate,endDate));

        taxesAccountViewRepository.save(new TaxesAccountView(currentYear,event.getProvisionForTaxes(),
                0.0, startDate,endDate));

        othersAccountViewRepository.save(new OthersAccountView(currentYear,event.getProvisionForOthers(),
                0.0, startDate,endDate));

        commonExpenseAccountViewRepository.save(new CommonExpenseAccountView(currentYear,event.getProvisionForCommonExpenses(),
                0.0, startDate,endDate));

        nodalAccountViewRepository.save(new NodalAccountView(currentYear,0.0,
                0.0, startDate,endDate));

        //TODO: Need to check actual usage and change accordingly.
        bookingAmountAccountViewRepository.save(new BookingAmountAccountView(currentYear,0.0,
                0.0, startDate,endDate));

        variableExpenseAccountViewRepository.save(new VariableExpenseAccountView(currentYear,event.getProvisionForSubscriptionSpecificExpenses(),
                0.0, startDate,endDate));

        businessAccountViewRepository.save(new BusinessAccountView(currentYear, startDate, endDate,
                event.getDefaultPercentFixedExpensePerUnitPrice(),
                event.getDefaultPercentVariableExpensePerUnitPrice()));
    }
}