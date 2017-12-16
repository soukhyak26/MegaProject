package com.affaince.subscription.product.event.exception;

import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 12-11-2016.
 */
public class MultipleActualViewsOnSingleDateException extends Exception {
    private static final String message = "For product %s, there are multiple actual views created for s single date %s ";


    public MultipleActualViewsOnSingleDateException(String s) {
        super(message);
    }

    public static MultipleActualViewsOnSingleDateException build(String productId,LocalDate date) {
        return new MultipleActualViewsOnSingleDateException(String.format(message, productId,date.toString()));
    }

}
