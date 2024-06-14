package com.affaince.payments.scheme;

import com.affaince.payments.context.MetricsContext;
import com.affaince.payments.expressions.Expression;
import com.affaince.payments.scheme.compilation.units.AdvancePayUnit;
import com.affaince.payments.scheme.compilation.units.GivenUnit;
import com.affaince.payments.scheme.compilation.units.ResidualPayUnit;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.joda.time.LocalDate;

import java.util.UUID;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Scheme {
    private String schemeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private PaymentsSchemeContext paymentsSchemeContext;
    private GivenUnit givenUnit;
    private AdvancePayUnit advancePayUnit;
    private ResidualPayUnit residualPayUnit;
    private CommonInputContext commonInputContext;

    public Scheme() {
        schemeId = UUID.randomUUID().toString();
        this.startDate = LocalDate.now();
        this.endDate = new LocalDate(9999,12,31);
        paymentsSchemeContext = new PaymentsSchemeContext();
        givenUnit = new GivenUnit();
        //computeUnit = new ComputeUnit();
        advancePayUnit = new AdvancePayUnit();
        residualPayUnit =new ResidualPayUnit();
    }

    public String getSchemeId() {
        return schemeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public AdvancePayUnit getAdvancePayUnit() {
        return advancePayUnit;
    }

    public PaymentsSchemeContext getPaymentsSchemeContext() {
        return paymentsSchemeContext;
    }

    public CommonInputContext getCommonInputContext() {
        return commonInputContext;
    }

    public GivenUnit getGivenUnit() {
        return givenUnit;
    }

    public ResidualPayUnit getResidualPayUnit() {
        return residualPayUnit;
    }

    public void setMetricsContext(MetricsContext metricsContext){
        this.paymentsSchemeContext.setMetricsContext(metricsContext);
    }
    public Expression searchVariableExpression(String variableName){
        Expression expression = this.getGivenUnit().searchVariableExpression(variableName);
        if(null !=expression){
            return expression;
        }else{
            expression = this.getAdvancePayUnit().searchVariableExpression(variableName);
            if(null != expression){
                return expression;
            }
        }
        return null;
    }

    public Scheme executeScheme(){
        this.getGivenUnit().execute(this.getPaymentsSchemeContext());
        this.getAdvancePayUnit().execute(this.getPaymentsSchemeContext());
        this.getResidualPayUnit().execute(this.paymentsSchemeContext);
        return this;

    }

}
