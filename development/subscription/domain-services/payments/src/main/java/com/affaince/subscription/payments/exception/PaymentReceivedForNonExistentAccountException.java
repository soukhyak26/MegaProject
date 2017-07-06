package com.affaince.subscription.payments.exception;

public class PaymentReceivedForNonExistentAccountException extends RuntimeException {
    private static final String message = "For Subscription %s,Payment is Received but no Payment Account is present" ;

    public PaymentReceivedForNonExistentAccountException(String message) {
        super(message);
    }

    public static PaymentReceivedForNonExistentAccountException build(String subscriptionId){
        return new PaymentReceivedForNonExistentAccountException(String.format(message, subscriptionId));
    }

}
