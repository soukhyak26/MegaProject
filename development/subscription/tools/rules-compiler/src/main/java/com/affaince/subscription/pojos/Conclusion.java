package com.affaince.subscription.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonSerialize(using= ConclusionSerializer.class)
public class Conclusion implements RuleSetPojo {
    private final String unit;
    private final double benefitValue;

    public Conclusion(String unit, double value) {
        this.unit = unit;
        this.benefitValue = value;
    }

    @JsonProperty ("unit")
    public String getUnit() {
        return unit;
    }

    @JsonProperty ("benefitValue")
    public double getBenefitValue() {return benefitValue;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conclusion)) return false;

        Conclusion that = (Conclusion) o;

        if (Double.compare(that.benefitValue, benefitValue) != 0) return false;
        return unit.equals(that.unit);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = unit.hashCode();
        temp = Double.doubleToLongBits(benefitValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
