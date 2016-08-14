package com.affaince.subscription.product.services.aggregators;

/**
 * Created by mandar on 23-07-2016.
 */
public interface AggregationVisitor<T> {
    T visit(T t);
}
