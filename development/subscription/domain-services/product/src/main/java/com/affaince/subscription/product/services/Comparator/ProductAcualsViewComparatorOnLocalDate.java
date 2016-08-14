package com.affaince.subscription.product.services.Comparator;

import com.affaince.subscription.product.query.view.ProductActualsView;

import java.util.Comparator;

/**
 * Created by rbsavaliya on 14-08-2016.
 */
public class ProductAcualsViewComparatorOnLocalDate implements Comparator <ProductActualsView> {
    @Override
    public int compare(ProductActualsView o1, ProductActualsView o2) {
        return o1.getProductVersionId().getFromDate().compareTo(
                o2.getProductVersionId().getFromDate()
        );
    }
}
