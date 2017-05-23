package com.affaince.subscription.product.command;

import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.vo.CostHeaderType;

import java.util.EnumSet;

/**
 * Created by mandar on 5/22/2017.
 */
public class CalculateBreakEvenPriceCommand {
    private String productId;
    private EnumSet<CostHeaderType> costHeaderTypes;

    public CalculateBreakEvenPriceCommand(String productId,EnumSet<CostHeaderType> costHeaderTypes) {
        this.productId = productId;
        this.costHeaderTypes=costHeaderTypes;
    }

    public String getProductId() {
        return productId;
    }


    public EnumSet<CostHeaderType> getCostHeaderTypes() {
        return costHeaderTypes;
    }
}
