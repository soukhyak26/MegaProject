package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.product.registration.command.event.OperatingExpenseReceivedEvent;
import com.affaince.subscription.product.registration.query.repository.CommonOperatingExpenseViewRepository;
import com.affaince.subscription.product.registration.query.repository.SubscriptionSpecificOperatingExpenseViewRepository;
import com.affaince.subscription.product.registration.query.view.CommonOperatingExpenseView;
import com.affaince.subscription.product.registration.query.view.SubscriptionSpecificOperatingExpenseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by rsavaliya on 21/1/16.
 */
@Component
public class OperatingExpenseReceivedEventListener {
    private final CommonOperatingExpenseViewRepository commonOperatingExpensesViewRepository;
    private final SubscriptionSpecificOperatingExpenseViewRepository subscriptionSpecificOperatingExpenseViewRepository;

    public OperatingExpenseReceivedEventListener(CommonOperatingExpenseViewRepository commonOperatingExpensesViewRepository, SubscriptionSpecificOperatingExpenseViewRepository subscriptionSpecificOperatingExpenseViewRepository) {
        this.commonOperatingExpensesViewRepository = commonOperatingExpensesViewRepository;
        this.subscriptionSpecificOperatingExpenseViewRepository = subscriptionSpecificOperatingExpenseViewRepository;
    }

    @Autowired


    public void on(OperatingExpenseReceivedEvent event) {
        ExpenseType expenseType = event.getExpenseType();
        if (expenseType == ExpenseType.COMMON_EXPENSE) {
            CommonOperatingExpenseView commonOperatingExpenseView = new CommonOperatingExpenseView(
                    UUID.randomUUID().toString(), event.getExpenseHeader(), event.getExpenseAmount(),
                    event.getPeriod()
            );
        } else {
            SubscriptionSpecificOperatingExpenseView subscriptionSpecificOperatingExpense = new SubscriptionSpecificOperatingExpenseView(

            );
        }

    }
}
