package com.affaince.subscription.compiler;

import com.affaince.subscription.common.type.DiscountUnit;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by rbsavaliya on 22-05-2016.
 */
public class Offer {

    private double offerPointValue;
    private double offeredBenefitValue;
    private DiscountUnit offeredBenefitType;

    @JsonProperty ("OfferPointValue")
    public double getOfferPointValue() {
        return offerPointValue;
    }

    public void setOfferPointValue(double offerPointValue) {
        this.offerPointValue = offerPointValue;
    }

    @JsonProperty ("OfferedBenefitValue")
    public double getOfferedBenefitValue() {
        return offeredBenefitValue;
    }

    public void setOfferedBenefitValue(double offeredBenefitValue) {
        this.offeredBenefitValue = offeredBenefitValue;
    }

    @JsonProperty ("OfferedBenefitType")
    public DiscountUnit getOfferedBenefitType() {
        return offeredBenefitType;
    }

    public void setOfferedBenefitType(DiscountUnit offeredBenefitType) {
        this.offeredBenefitType = offeredBenefitType;
    }
}
