package com.affaince.payments.expressions;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ArithmeticComparisonExpression.class, name = "ArithmeticComparisonExpression"),
        @JsonSubTypes.Type(value = ArithmeticExpression.class, name = "ArithmeticExpression"),
        @JsonSubTypes.Type(value = StringComparisonExpression.class, name = "StringComparisonExpression"),
        @JsonSubTypes.Type(value = StringExpression.class, name = "StringExpression"),
        @JsonSubTypes.Type(value = VariableExpression.class, name = "VariableExpression"),
        @JsonSubTypes.Type(value = VariableIdentifierExpression.class, name = "VariableIdentifierExpression"),
        @JsonSubTypes.Type(value = UnaryExpression.class, name = "UnaryExpression")

})
public abstract class Expression {
    private static final long serialVersionUID = -5873300401604877065L;
    private ArithmeticOperator operator;
    private Expression leftHandSide;
    private Expression rightHandSide;
    private Expression preExpression; //just for ternary

    public Expression(){}
    public Expression(ArithmeticOperator operator, Expression leftHandSide, Expression rightHandSide) {
        this.operator = operator;
        this.leftHandSide = leftHandSide;
        this.rightHandSide = rightHandSide;
    }

    public ArithmeticOperator getOperator() {
        return operator;
    }

    public Expression getPreExpression() {
        return preExpression;
    }

    public void setPreExpression(Expression preExpression) {
        this.preExpression = preExpression;
    }

    public Expression getLeftHandSide() {
        return leftHandSide;
    }

    public Expression getRightHandSide() {
        return rightHandSide;
    }

    public void setLeftHandSide(Expression leftHandSide) {
        this.leftHandSide = leftHandSide;
    }

    public void setRightHandSide(Expression rightHandSide) {
        this.rightHandSide = rightHandSide;
    }

    public Object apply(){ return null;};

    public Expression searchVariableOnLHS(String variableName){
        if(null != this && this instanceof  VariableExpression ){
            String leftHandSideVariableName =((VariableIdentifierExpression)this.getLeftHandSide()).getIdentifier();
            if(leftHandSideVariableName.equals(variableName)){
                return this;
            }
        }else if( null != this && this instanceof ArithmeticExpression){
            Expression lhsExpression = this.getLeftHandSide();
            return lhsExpression.searchVariableOnLHS(variableName);
        }
        return null;
    }
}
