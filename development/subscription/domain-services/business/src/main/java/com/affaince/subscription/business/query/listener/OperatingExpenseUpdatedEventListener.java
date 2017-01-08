package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.RemovePastCommonExpenseTypesCommand;
import com.affaince.subscription.business.command.event.OperatingExpenseUpdatedEvent;
import com.affaince.subscription.business.query.repository.CommonOperatingExpenseViewRepository;
import com.affaince.subscription.business.query.view.CommonOperatingExpenseView;
import com.affaince.subscription.common.type.ExpenseType;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mandark on 24-01-2016.
 */
public class OperatingExpenseUpdatedEventListener {
    private final CommonOperatingExpenseViewRepository operatingExpenseViewRepository;
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    @Autowired
    public OperatingExpenseUpdatedEventListener(CommonOperatingExpenseViewRepository operatingExpenseViewRepository) {
        this.operatingExpenseViewRepository = operatingExpenseViewRepository;
    }

    @EventHandler
    public void on(OperatingExpenseUpdatedEvent event) throws Exception {
            final List<CommonOperatingExpenseView> commonOperatingExpenseViews = operatingExpenseViewRepository.findByExpenseHeaderAndMonthOfYear(event.getExpenseHeader(), YearMonth.now());
            if (null != commonOperatingExpenseViews && null != commonOperatingExpenseViews.get(0)) {
                CommonOperatingExpenseView view = commonOperatingExpenseViews.get(0);
                view.setAmount(event.getExpenseAmount());
                operatingExpenseViewRepository.save(view);

                //Update same amount for future months
                //YearMonth monthOfYear = new YearMonth(event.getForYear(), event.getForMonth());
                YearMonth monthOfYear=YearMonth.now();
                CommonOperatingExpenseView commonExpenseFutureView = null;
                for (int i = 1; i <= 11; i++) {
                    monthOfYear = monthOfYear.plusMonths(i);
                    List<CommonOperatingExpenseView> commonOperatingExpenseFutureViews = operatingExpenseViewRepository.findByExpenseHeaderAndMonthOfYear(event.getExpenseHeader(), monthOfYear);
                    if (null != commonOperatingExpenseFutureViews && null != commonOperatingExpenseFutureViews.get(0)) {
                        commonExpenseFutureView = commonOperatingExpenseFutureViews.get(0);
                        commonExpenseFutureView.setAmount(event.getExpenseAmount());

                    } else {
                        commonExpenseFutureView = new CommonOperatingExpenseView(event.getCommonOperatingExpenseId(), event.getExpenseHeader(), event.getExpenseAmount(), monthOfYear);
                        commonExpenseFutureView.setAmount(event.getExpenseAmount());
                    }
                    operatingExpenseViewRepository.save(commonExpenseFutureView);
                }
            }
            RemovePastCommonExpenseTypesCommand removePastCommonExpenseTypesCommand = new RemovePastCommonExpenseTypesCommand(event.getCommonOperatingExpenseId(), event.getExpenseHeader(), YearMonth.now());
            commandGateway.executeAsync(removePastCommonExpenseTypesCommand);
    }
}
