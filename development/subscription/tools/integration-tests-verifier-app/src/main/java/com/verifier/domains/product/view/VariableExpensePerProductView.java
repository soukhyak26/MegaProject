package com.verifier.domains.product.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.verifier.domains.product.vo.ProductwiseVariableExpenseId;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 4/26/2017.
 */
public class VariableExpensePerProductView {
    private ProductwiseVariableExpenseId productwiseVariableExpenseId;
    private double variableExpensePerProductPerUnit;
   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    public VariableExpensePerProductView() {
    }

    public VariableExpensePerProductView(ProductwiseVariableExpenseId productwiseVariableExpenseId, double variableExpensePerProductPerUnit) {
        this.productwiseVariableExpenseId = productwiseVariableExpenseId;
        this.variableExpensePerProductPerUnit = variableExpensePerProductPerUnit;
        this.endDate=new LocalDate(999,12,31);
    }

    public ProductwiseVariableExpenseId getProductwiseVariableExpenseId() {
        return productwiseVariableExpenseId;
    }

    public double getVariableExpensePerProductPerUnit() {
        return variableExpensePerProductPerUnit;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
