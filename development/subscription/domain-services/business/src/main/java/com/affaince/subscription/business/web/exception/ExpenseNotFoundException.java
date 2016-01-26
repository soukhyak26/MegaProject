package com.affaince.subscription.business.web.exception;

/**
 * Created by rbsavaliya on 12-09-2015.
 */
public class ExpenseNotFoundException extends Exception {

    private String message;

    private ExpenseNotFoundException(String message) {
        this.message = message;
    }

    public static ExpenseNotFoundException build(String expenseId) {
        return new ExpenseNotFoundException(String.format("Subscriptionable Item does not found with id: %s", expenseId));
    }

    @Override
    public String getMessage() {
        return message;
    }
}
