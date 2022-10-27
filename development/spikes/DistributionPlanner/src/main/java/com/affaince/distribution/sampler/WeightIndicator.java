package com.affaince.distribution.sampler;

import com.affaince.subscription.common.type.QuantityUnit;

import java.util.Objects;

public class WeightIndicator {
    private final double netQuantity;
    private final QuantityUnit quantityUnit;

    public WeightIndicator(double netQuantity, QuantityUnit quantityUnit) {
        this.netQuantity = netQuantity;
        this.quantityUnit = quantityUnit;
    }

    public double getNetQuantity() {
        return netQuantity;
    }

    public QuantityUnit getQuantityUnit() {
        return quantityUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightIndicator that = (WeightIndicator) o;
        return netQuantity == that.netQuantity &&
                quantityUnit == that.quantityUnit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(netQuantity, quantityUnit);
    }
}
