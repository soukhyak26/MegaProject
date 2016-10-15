package com.affaince.subscription.product.query.exception;

import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 15-10-2016.
 */
public class OutOfMonthAggregationException extends Exception {
    private static final String message = "For product %s, aggregation start date %s, " +
            "aggregation end date %s not falling in standard month";

    public OutOfMonthAggregationException(String s) {
        super(message);
    }

    public static OutOfMonthAggregationException build(String productId,
                                                       LocalDateTime aggregationStartDate,
                                                       LocalDateTime aggregationEndDate) {
        return new OutOfMonthAggregationException(String.format(message, productId, aggregationStartDate, aggregationEndDate));
    }

}
