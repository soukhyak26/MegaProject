package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.product.registration.command.RemovePastCommonExpenseTypesCommand;
import com.affaince.subscription.product.registration.command.event.CommonExpenseTypeUpdatedEvent;
import com.affaince.subscription.product.registration.query.repository.CommonOperatingExpenseViewRepository;
import com.affaince.subscription.product.registration.query.view.CommonOperatingExpenseView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mandark on 24-01-2016.
 */
public class CommonExpenseTypeUpdatedEventListener {
    @Autowired
    private SubscriptionCommandGateway commandGateway;
    private final CommonOperatingExpenseViewRepository operatingExpenseViewRepository;

    @Autowired
    public CommonExpenseTypeUpdatedEventListener(CommonOperatingExpenseViewRepository operatingExpenseViewRepository) {
        this.operatingExpenseViewRepository = operatingExpenseViewRepository;
    }

    @EventHandler
    public void on(CommonExpenseTypeUpdatedEvent event) throws Exception {
        if (event.getExpenseType() == ExpenseType.COMMON_EXPENSE) {
            final List<CommonOperatingExpenseView> commonOperatingExpenseViews = operatingExpenseViewRepository.findByExpenseHeaderAndMonthOfYear(event.getExpenseHeader(), new YearMonth(event.getForYear(), event.getForMonth())
            );
            if (null != commonOperatingExpenseViews && null != commonOperatingExpenseViews.get(0)) {
                CommonOperatingExpenseView view = commonOperatingExpenseViews.get(0);
                view.setAmount(event.getExpenseAmount());
                operatingExpenseViewRepository.save(view);

                //Update same amount for future months
                YearMonth monthOfYear = new YearMonth(event.getForYear(), event.getForMonth());
                CommonOperatingExpenseView commonExpenseFutureView = null;
                for (int i = 1; i <= 11; i++) {
                    monthOfYear = monthOfYear.plusMonths(i);
                    List<CommonOperatingExpenseView> commonOperatingExpenseFutureViews = operatingExpenseViewRepository.findByExpenseHeaderAndMonthOfYear(event.getExpenseHeader(), monthOfYear);
                    if (null != commonOperatingExpenseFutureViews && null != commonOperatingExpenseFutureViews.get(0)) {
                        commonExpenseFutureView = commonOperatingExpenseFutureViews.get(0);
                        commonExpenseFutureView.setAmount(event.getExpenseAmount());

                    } else {
                        commonExpenseFutureView = new CommonOperatingExpenseView(event.getCommonOperatingExpenseId(), event.getExpenseHeader(), event.getExpenseAmount(), event.getPeriod(), monthOfYear);
                        commonExpenseFutureView.setAmount(event.getExpenseAmount());
                    }
                    operatingExpenseViewRepository.save(commonExpenseFutureView);
                }
            }
            RemovePastCommonExpenseTypesCommand removePastCommonExpenseTypesCommand = new RemovePastCommonExpenseTypesCommand(event.getCommonOperatingExpenseId(), event.getExpenseHeader(), new YearMonth(event.getForYear(), event.getForMonth()));
            commandGateway.executeAsync(removePastCommonExpenseTypesCommand);

        }

    }
}
