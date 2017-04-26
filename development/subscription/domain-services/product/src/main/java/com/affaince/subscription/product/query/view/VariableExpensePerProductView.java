package com.affaince.subscription.product.query.view;

import com.affaince.subscription.product.vo.ProductwiseVariableExpenseId;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 4/26/2017.
 */
public class VariableExpensePerProductView {
    private final ProductwiseVariableExpenseId productwiseVariableExpenseId;
    private final double variableExpensePerProductPerUnit;
    private LocalDate endDate;

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
