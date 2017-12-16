package com.affaince.subscription.product.domain.exception;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 4/21/2017.
 */
public class PriceBucketLifecycleMismatchException extends Exception {
        private static final String message = "For product %s,end date of pricebucket %s is , BEFORE delivery of its item on %s ";

    public PriceBucketLifecycleMismatchException(String message) {
            super(message);
        }

    public static PriceBucketLifecycleMismatchException build(String productId,String priceBucketId,LocalDate dispatchDate) {
        return new PriceBucketLifecycleMismatchException(String.format(message, productId, priceBucketId,dispatchDate.toString()));
    }
}
