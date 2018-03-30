package com.verifier.domains.product.vo;

/**
 * Created by mandar on 08-10-2016.
 */
/*
@JsonSerialize(using = PricingChoiceTypeSerializer.class)
@JsonDeserialize(using = PricingChoiceTypeDeserializer.class)
*/
public enum PricingChoiceType {
    ACCEPT_RECOMMENDATION(0), CONTINUE_CURRENT_PRICE(1), OVERRIDE_RECOMMENDATION(2);

    private int choiceValue;

    PricingChoiceType(int choiceValue) {
        this.choiceValue = choiceValue;
    }

    public static PricingChoiceType valueOf(int value) {
        for (PricingChoiceType r : PricingChoiceType.values()) {
            if (r.getChoiceValue() == value) {
                return r;
            }
        }
        throw new IllegalArgumentException();
    }

    public int getChoiceValue() {
        return choiceValue;
    }
}