package com.affaince.subscription.common.comparator;

import com.affaince.subscription.common.type.ProductStatus;

import java.util.Comparator;

/**
 * Created by anayonkar on 28/2/16.
 */
//TODO: looks unnecessary
public class ProductStatusComparator implements Comparator<ProductStatus> {
    @Override
    public int compare(ProductStatus o1, ProductStatus o2) {
        return o1.getStatusCode() - o2.getStatusCode();
    }
}
