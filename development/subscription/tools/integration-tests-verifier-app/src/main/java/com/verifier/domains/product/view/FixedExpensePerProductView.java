package com.verifier.domains.product.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.verifier.domains.product.vo.ProductwiseFixedExpenseId;
import org.joda.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 4/26/2017.
 */
@Document(collection = "FixedExpensePerProductView")
public class FixedExpensePerProductView {
    private ProductwiseFixedExpenseId productwiseFixedExpenseId;
    private double fixedExpensePerProductPerUnit;
   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    public FixedExpensePerProductView() {
    }

    public FixedExpensePerProductView(ProductwiseFixedExpenseId productwiseFixedExpenseId, double fixedExpensePerProductPerUnit) {
        this.productwiseFixedExpenseId = productwiseFixedExpenseId;
        this.fixedExpensePerProductPerUnit = fixedExpensePerProductPerUnit;
        this.endDate=new LocalDate(999,12,31);
    }

    public ProductwiseFixedExpenseId getProductwiseFixedExpenseId() {
        return productwiseFixedExpenseId;
    }

    public double getFixedExpensePerProductPerUnit() {
        return fixedExpensePerProductPerUnit;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
