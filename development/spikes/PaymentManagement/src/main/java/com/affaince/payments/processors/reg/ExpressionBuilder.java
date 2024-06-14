package com.affaince.payments.processors.reg;

import com.affaince.payments.expressions.*;
import com.affaince.payments.grammer.PaymentsParser;
import com.affaince.payments.scheme.Scheme;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;


public class ExpressionBuilder {
    private final Scheme scheme;
    public ExpressionBuilder(Scheme scheme){
        this.scheme=scheme;
    }
    public Expression buildExpression(PaymentsParser.ExpressionContext expressionContext) {
        return processExpression(expressionContext);
    }

    private Expression processExpression(PaymentsParser.ExpressionContext expressionContext) {
        if (null != expressionContext.conditionalExpression()) {
            Expression lhs = processConditionalExpression(expressionContext.conditionalExpression());
            if (null != expressionContext.ASSIGN() && null != expressionContext.expression()) {
                Expression rhs = processExpression(expressionContext.expression());
                return new ArithmeticExpression(ArithmeticOperator.ASSIGN, lhs, rhs);
            } else {
                return lhs;
            }
        }
        return null;
    }

    private Expression processConditionalExpression(PaymentsParser.ConditionalExpressionContext conditionalExpressionContext) {
        Expression rhs = null;
        if (null != conditionalExpressionContext.conditionalOrExpression()) {
            Expression lhs = processConditionalOrExpression(conditionalExpressionContext.conditionalOrExpression());
            if (null != conditionalExpressionContext.QUESTIONMARK() && null != conditionalExpressionContext.expression()) {
                Expression preExpression= lhs;
                lhs = processExpression(conditionalExpressionContext.expression());
                if (null != conditionalExpressionContext.COLON() && null != conditionalExpressionContext.conditionalExpression()) {
                    rhs = processConditionalExpression(conditionalExpressionContext.conditionalExpression());
                }
                Expression resultExpression = new ArithmeticExpression(ArithmeticOperator.TERNARY, lhs, rhs);
                resultExpression.setPreExpression(preExpression);
                return resultExpression;
            } else {
                return lhs;
            }
        }
        return null;
    }

    Expression processConditionalOrExpression(PaymentsParser.ConditionalOrExpressionContext conditionalOrExpressionContext) {
        if (null != conditionalOrExpressionContext.conditionalAndExpression() && !conditionalOrExpressionContext.conditionalAndExpression().isEmpty()) {

            Expression lhsExpression = processConditionalAndExpression(conditionalOrExpressionContext.conditionalAndExpression(0));

            if (null != conditionalOrExpressionContext.connectorOr() &&
                    !conditionalOrExpressionContext.connectorOr().isEmpty() &&
                    null != conditionalOrExpressionContext.conditionalAndExpression() &&
                    !conditionalOrExpressionContext.conditionalAndExpression().isEmpty()) {
                List<Expression> booleanExpressions = new ArrayList<>();
                booleanExpressions.add(lhsExpression);
                for (int i = 1; i < conditionalOrExpressionContext.conditionalAndExpression().size(); i++) {
                    Expression booleanExpression = processConditionalAndExpression(conditionalOrExpressionContext.conditionalAndExpression().get(i));
                    booleanExpressions.add(booleanExpression);
                }
                return new ArithmeticComparisonExpression(ArithmeticOperator.OR,new UnaryExpression(booleanExpressions,UnaryType.OBJECT),null);
            }else {
                return lhsExpression;
            }
        }
        return null;
    }

    Expression processConditionalAndExpression(PaymentsParser.ConditionalAndExpressionContext conditionalAndExpressionContext) {
        if (null != conditionalAndExpressionContext.relationalExpression() && !conditionalAndExpressionContext.relationalExpression().isEmpty()) {
            Expression lhsExpression = processRelationalExpression(conditionalAndExpressionContext.relationalExpression(0));

            if (null != conditionalAndExpressionContext.connectorAnd() &&
                    !conditionalAndExpressionContext.connectorAnd().isEmpty() &&
                    null != conditionalAndExpressionContext.relationalExpression() &&
                    !conditionalAndExpressionContext.relationalExpression().isEmpty()) {
                List<Expression> booleanExpressions = new ArrayList<>();
                booleanExpressions.add(lhsExpression);
                for (int i = 1; i < conditionalAndExpressionContext.relationalExpression().size(); i++) {
                    Expression booleanExpression = processRelationalExpression(conditionalAndExpressionContext.relationalExpression().get(i));
                    booleanExpressions.add(booleanExpression);
                }
                return new ArithmeticComparisonExpression(ArithmeticOperator.AND,new UnaryExpression(booleanExpressions,UnaryType.OBJECT),null);
            }
            return lhsExpression;
        }
        return null;
    }

    Expression processRelationalExpression(PaymentsParser.RelationalExpressionContext relationalExpressionContext) {
        if (null != relationalExpressionContext.additiveExpression() && !relationalExpressionContext.additiveExpression().isEmpty()) {
            Expression lhsExpression = processAdditiveExpression(relationalExpressionContext.additiveExpression(0));
            if (null != relationalExpressionContext.relationalOp() &&
                    !relationalExpressionContext.relationalOp().isEmpty() &&
                    null != relationalExpressionContext.additiveExpression() &&
                    !relationalExpressionContext.additiveExpression().isEmpty()) {

                for (int i = 1; i < relationalExpressionContext.additiveExpression().size(); i++) {
                    PaymentsParser.RelationalOpContext relationalOp = relationalExpressionContext.relationalOp(i-1);
                    Expression rhsExpression = processAdditiveExpression(relationalExpressionContext.additiveExpression().get(i));
                    if(rhsExpression instanceof UnaryExpression){
                        if(((UnaryExpression)rhsExpression).getType() == UnaryType.NUMBER){
                            lhsExpression = new ArithmeticComparisonExpression(resolveOperatorForRelationalContext(relationalOp,false), lhsExpression,rhsExpression );
                        }else {
                            lhsExpression = new StringComparisonExpression(resolveOperatorForRelationalContext(relationalOp,false), lhsExpression,rhsExpression );
                        }
                    }
                }
            }
            return lhsExpression;
        } else if (null != relationalExpressionContext.iterativeStatement()) {
            Expression lhsExpression = processIterativeStatement(relationalExpressionContext.iterativeStatement());
            if (null != relationalExpressionContext.relationalOp() &&
                    !relationalExpressionContext.relationalOp().isEmpty() &&
                    null != relationalExpressionContext.additiveExpression() &&
                    !relationalExpressionContext.additiveExpression().isEmpty()) {

                for (int i = 0; i < relationalExpressionContext.additiveExpression().size(); i++) {
                    PaymentsParser.RelationalOpContext relationalOp = relationalExpressionContext.relationalOp(i);
                    Expression rhsExpression = processAdditiveExpression(relationalExpressionContext.additiveExpression().get(i));
                    if(rhsExpression instanceof UnaryExpression){
                        if(((UnaryExpression)rhsExpression).getType() == UnaryType.NUMBER){
                            lhsExpression = new ArithmeticComparisonExpression(resolveOperatorForRelationalContext(relationalOp,false), lhsExpression,rhsExpression );
                        }else {
                            lhsExpression = new StringComparisonExpression(resolveOperatorForRelationalContext(relationalOp,false), lhsExpression,rhsExpression );
                        }
                    }

                }
            }
            return lhsExpression;
        }
        return null;
    }

    private ArithmeticOperator resolveOperatorForRelationalContext(PaymentsParser.RelationalOpContext relationalOpContext,boolean isOperatorForLoopVariable) {
        if (null != relationalOpContext.EQUAL()) {
            if(isOperatorForLoopVariable){
                return ArithmeticOperator.LOOPEQUALTO;
            }else {
                return ArithmeticOperator.EQUALTO;
            }
        } else if (null != relationalOpContext.LT()) {
            if(isOperatorForLoopVariable){
                return ArithmeticOperator.LOOPLESSTHAN ;
            }else {
                return ArithmeticOperator.LESSTHAN;
            }
        } else if (null != relationalOpContext.LE()) {
            if(isOperatorForLoopVariable){
             return ArithmeticOperator.LOOPLESSTHANEQUALTO ;
            }else {
                return ArithmeticOperator.LESSTHANEQUALTO;
            }
        } else if (null != relationalOpContext.GT()) {
            if(isOperatorForLoopVariable){
                return ArithmeticOperator.LOOPGREATERTHAN ;
            }else {
                return ArithmeticOperator.GREATERTHAN;
            }
        } else if (null != relationalOpContext.GE()) {
            if(isOperatorForLoopVariable){
                return ArithmeticOperator.LOOPGREATERTHANEQUALTO ;
            }else {
                return ArithmeticOperator.GREATERTHANEQUALTO;
            }
        } else if (null != relationalOpContext.NOTEQUAL()) {
            if(isOperatorForLoopVariable){
                return ArithmeticOperator.LOOPNOTEQUALTO ;
            }else {
                return ArithmeticOperator.NOTEQUALTO;
            }
        }
        return null;
    }


    private Expression processIterativeStatement(PaymentsParser.IterativeStatementContext iterativeStatementContext) {
        if (null != iterativeStatementContext.EACH()) {
            if (null != iterativeStatementContext.expression()) {
                //the only possibility here is the comparison of some variable/value with EACH element of the array
                //hardcoded obtaining of relational context should suffice.
                PaymentsParser.RelationalExpressionContext relationalExpressionContext = iterativeStatementContext.expression().conditionalExpression().
                        conditionalOrExpression().conditionalAndExpression(0).relationalExpression(0);
                List<PaymentsParser.AdditiveExpressionContext> additiveExpressionContexts = relationalExpressionContext.additiveExpression();

                //LHS : in case of EACH lhs is always a variable who is an array;
                PaymentsParser.PrimaryContext lhsPrimaryContext = additiveExpressionContexts.get(0).multiplicativeExpression(0).unaryExpression(0).primary();
                String lhsCollectionVariableName = lhsPrimaryContext.variableName().getText();
                Expression lhsVariableExpression = scheme.searchVariableExpression(lhsCollectionVariableName);

                //RELATIONAL OP : in case of each only one relationalOp
                PaymentsParser.RelationalOpContext relationalOpContext = relationalExpressionContext.relationalOp(0);

                //RHS: Single primary value or variable Name
                PaymentsParser.PrimaryContext rhsPrimaryContext = additiveExpressionContexts.get(1).multiplicativeExpression(0).unaryExpression(0).primary();
                if (null != rhsPrimaryContext.variableName()) {
                    String rhsVariableName = lhsPrimaryContext.variableName().getText();
                    Expression rhsVariableExpression = scheme.searchVariableExpression(rhsVariableName);
                    return new ArithmeticComparisonExpression(resolveOperatorForRelationalContext(relationalOpContext,true), lhsVariableExpression, rhsVariableExpression);
                } else if (null != rhsPrimaryContext.literal()) {
                    Integer value = Integer.parseInt(rhsPrimaryContext.literal().NUMBER().getText());
                    UnaryExpression rhsUnaryExpression = new UnaryExpression(value,UnaryType.NUMBER);
                    return new ArithmeticComparisonExpression(resolveOperatorForRelationalContext(relationalOpContext,true), lhsVariableExpression, rhsUnaryExpression);
                }
            }
        }
        return null;
    }


    private Expression buildArrayVariable(String variableName, PaymentsParser.VariableInitializerContext variableInitializerContext) {
        List<Expression> arrayElements = new ArrayList<>();
        List<PaymentsParser.VariableInitializerContext> initializerContexts = variableInitializerContext.arrayInitializer().variableInitializer();
        for (PaymentsParser.VariableInitializerContext initializerContext : initializerContexts) {
            Expression expression = processExpression(initializerContext.expression());
            arrayElements.add(expression);
        }
        return new VariableExpression(new VariableIdentifierExpression(variableName),new UnaryExpression(arrayElements,UnaryType.OBJECT));
    }

    private Expression buildNonArrayVariable(String variableName, PaymentsParser.VariableInitializerContext variableInitializerContext) {
        PaymentsParser.ExpressionContext initializerContext = variableInitializerContext.expression();
        Expression unaryExpression = processExpression(initializerContext);
        return new VariableExpression(new VariableIdentifierExpression(variableName),unaryExpression);
    }

    public Expression processVariableDeclaration(PaymentsParser.VariableDeclarationStatementContext variableDeclarationStatementContext) {
        if (null != variableDeclarationStatementContext) {
            PaymentsParser.VariableInitializerContext variableInitializerContext = variableDeclarationStatementContext.variableInitializer();
            if (null != variableDeclarationStatementContext.variableDeclaratorId()) {
                PaymentsParser.VariableDeclaratorIdContext variableDeclaratorIdContext = variableDeclarationStatementContext.variableDeclaratorId();
                String variableName = variableDeclaratorIdContext.variableName().getText();
                if (null != variableDeclaratorIdContext.LBRACK() && null != variableDeclaratorIdContext.RBRACK()) {      //it is an array
                    if (null != variableDeclarationStatementContext.ASSIGN()) {   //assignment statement
                        return buildArrayVariable(variableName, variableInitializerContext);
                    } else if (null != variableDeclarationStatementContext.ASINPUT()) {
                        Expression expression =  scheme.searchVariableExpression(variableName);
                        if(null !=expression){
                            return expression;
                        }else {
                            return new VariableExpression(new VariableIdentifierExpression(variableName),null);
                        }
                    }
                } else {
                    if (null != variableDeclarationStatementContext.ASSIGN()) {   //assignment statement
                        return buildNonArrayVariable(variableName, variableInitializerContext);
                    } else if (null != variableDeclarationStatementContext.ASINPUT()) {
                        //Put InputValuePlaceHolder here as at the time of parsing the tree the variable value in absent
                        //UnaryExpression value = new UnaryExpression(new InputValuePlaceHolder(variableName));
                        //return new VariableExpression(new UnaryExpression(variableName),value);
                        Expression expression =  scheme.searchVariableExpression(variableName);
                        if(null !=expression){
                            return expression;
                        }else {
                            return new VariableExpression(new VariableIdentifierExpression(variableName),null);
                        }
                    }
                }
            }
        }
        return null;
    }


    Expression processAdditiveExpression(PaymentsParser.AdditiveExpressionContext additiveExpressionContext) {
        if (null != additiveExpressionContext.multiplicativeExpression(0)) {
            Expression lhs = processMultiplicationExpression(additiveExpressionContext.multiplicativeExpression(0));
            if (null != additiveExpressionContext.ADD() &&
                    !additiveExpressionContext.ADD().isEmpty() &&
                    null != additiveExpressionContext.multiplicativeExpression() &&
                    !additiveExpressionContext.multiplicativeExpression().isEmpty()) {
                for (int i=1; i< additiveExpressionContext.multiplicativeExpression().size();i++) {
                    Expression rhs = processMultiplicationExpression(additiveExpressionContext.multiplicativeExpression().get(i));
                    lhs = new ArithmeticExpression(ArithmeticOperator.ADDITION,lhs,rhs);
                }
            } else if (null != additiveExpressionContext.SUB() &&
                    !additiveExpressionContext.SUB().isEmpty() &&
                    null != additiveExpressionContext.multiplicativeExpression() &&
                    !additiveExpressionContext.multiplicativeExpression().isEmpty()) {
                for (int i=1; i< additiveExpressionContext.multiplicativeExpression().size();i++) {
                    Expression rhs = processMultiplicationExpression(additiveExpressionContext.multiplicativeExpression().get(i));
                    lhs = new ArithmeticExpression(ArithmeticOperator.SUBTRACTION,lhs,rhs);
                }
            }
            return lhs;
        }else if(null != additiveExpressionContext.iterativeAggregationExpression()){
            return processIterativeAggregationExpression(additiveExpressionContext.iterativeAggregationExpression());
        }
        return null;
    }

    private Expression processIterativeAggregationExpression(PaymentsParser.IterativeAggregationExpressionContext iterativeAggregationExpressionContext){
        if(null != iterativeAggregationExpressionContext.SUMOF() && null != iterativeAggregationExpressionContext.EACH()){
            if(null != iterativeAggregationExpressionContext.variableDeclarationStatement()){
                String variableName = iterativeAggregationExpressionContext.variableDeclarationStatement().variableDeclaratorId().variableName().getText();
                return new ArithmeticExpression(ArithmeticOperator.LOOPADDITION, scheme.searchVariableExpression(variableName),null);
            }else if(null != iterativeAggregationExpressionContext.expression()){
                PaymentsParser.ExpressionContext expressionContext =iterativeAggregationExpressionContext.expression();
                PaymentsParser.PrimaryContext lhsPrimaryContext = expressionContext.conditionalExpression().conditionalOrExpression().conditionalAndExpression(0).relationalExpression(0).additiveExpression(0).multiplicativeExpression(0).unaryExpression(0).primary();
                if(lhsPrimaryContext.variableName() != null) {
                    TerminalNode LHS_IDENTIFIER = lhsPrimaryContext.variableName().IDENTIFIER();
                    if (null != LHS_IDENTIFIER) {
                        //String lhsVariableName = LHS_IDENTIFIER.getText();
                        TerminalNode RHS_IDENTIFIER = expressionContext.conditionalExpression().conditionalOrExpression().conditionalAndExpression(0).relationalExpression(0).additiveExpression(0).iterativeAggregationExpression().variableDeclarationStatement().variableDeclaratorId().variableName().IDENTIFIER();
                        if (null != RHS_IDENTIFIER) {
                            String rhsVariableName = RHS_IDENTIFIER.getText();
                            Expression rhsVariableExpression = scheme.searchVariableExpression(rhsVariableName);
                            return new ArithmeticExpression(ArithmeticOperator.LOOPADDITION, rhsVariableExpression, null);
                        }
                    }
                }else if(lhsPrimaryContext.parExpression() != null){
                    return processParExpression(lhsPrimaryContext.parExpression());
                }
            }
        }
        return null;
    }

    private Expression processParExpression(PaymentsParser.ParExpressionContext parExpressionContext){
        if(null != parExpressionContext.LPAREN() && null != parExpressionContext.RPAREN() && null !=parExpressionContext.expression() ){
            return processExpression(parExpressionContext.expression());
        }
        return null;
    }
    private Expression processMultiplicationExpression(PaymentsParser.MultiplicativeExpressionContext
                                                       multiplicativeExpressionContext) {
        if (null != multiplicativeExpressionContext.unaryExpression(0)) {
            Expression lhs = processUnaryExpression(multiplicativeExpressionContext.unaryExpression(0));
            if (null != multiplicativeExpressionContext.MUL() &&
                    !multiplicativeExpressionContext.MUL().isEmpty() &&
                    null != multiplicativeExpressionContext.unaryExpression() &&
                    !multiplicativeExpressionContext.unaryExpression().isEmpty()) {
                for (int i=1;i<multiplicativeExpressionContext.unaryExpression().size();i++) {
                    Expression rhs = processUnaryExpression(multiplicativeExpressionContext.unaryExpression().get(i));
                    lhs = new ArithmeticExpression(ArithmeticOperator.MULTIPLICATION,lhs,rhs);
                }
            } else if (null != multiplicativeExpressionContext.DIV() &&
                    !multiplicativeExpressionContext.DIV().isEmpty() &&
                    null != multiplicativeExpressionContext.unaryExpression() &&
                    !multiplicativeExpressionContext.unaryExpression().isEmpty()) {
                for (int i=1;i<multiplicativeExpressionContext.unaryExpression().size();i++) {
                    Expression rhs = processUnaryExpression(multiplicativeExpressionContext.unaryExpression().get(i));
                    lhs = new ArithmeticExpression(ArithmeticOperator.DIVISION,lhs,rhs);
                }
            } else if (null != multiplicativeExpressionContext.MOD() &&
                    !multiplicativeExpressionContext.MOD().isEmpty() &&
                    null != multiplicativeExpressionContext.unaryExpression() &&
                    !multiplicativeExpressionContext.unaryExpression().isEmpty()) {
                for (int i=1;i<multiplicativeExpressionContext.unaryExpression().size();i++) {
                    Expression rhs = processUnaryExpression(multiplicativeExpressionContext.unaryExpression().get(i));
                    lhs = new ArithmeticExpression(ArithmeticOperator.MODULUS,lhs,rhs);
                }
            }
            return lhs;
        }
        return null;
    }
    private String escapeDoubleDoubleQuotesFromString(String literalStringValue){
       // String s = getText();
        char[] literalCharArray = literalStringValue.toCharArray();
        if(literalCharArray[0]=='"') {
            literalStringValue = literalStringValue.substring(1, literalStringValue.length() - 1); // strip the leading and trailing quotes
            literalStringValue = literalStringValue.replace("\"\"", "\""); // replace all double quotes with single quotes
            System.out.println("^^^^changed value :  " + literalStringValue);
        }
        return literalStringValue;
    }
    Expression processUnaryExpression(PaymentsParser.UnaryExpressionContext unaryExpressionContext) {
        PaymentsParser.LiteralContext literalContext = unaryExpressionContext.primary().literal();
        if (null != literalContext) {
            if (null != literalContext.NUMBER()) {
                Number literalNumericValue = Double.parseDouble(literalContext.NUMBER().getText());
                return  new UnaryExpression(literalNumericValue,UnaryType.NUMBER);
            } else if (null != literalContext.StringLiteral()) {
                String literalStringValue = literalContext.StringLiteral().getSymbol().getText();
                System.out.println("^^Unchanged : " + literalStringValue);
                return new UnaryExpression(escapeDoubleDoubleQuotesFromString(literalStringValue),UnaryType.STRING);
            } else if (null != literalContext.BooleanLiteral()) {
                Boolean literalBooleanValue = Boolean.parseBoolean(literalContext.BooleanLiteral().getText());
                return new UnaryExpression(literalBooleanValue,UnaryType.BOOLEAN);
            } else {
                String literalStringValue = literalContext.NullLiteral().getText();
                return new UnaryExpression(literalStringValue,UnaryType.NULL);
            }
        } else if (null != unaryExpressionContext.primary().variableName()) {
            Expression variableExpression = scheme.searchVariableExpression(unaryExpressionContext.primary().variableName().getText());
            if( null != variableExpression){
                return variableExpression;
            }else{
                variableExpression= new VariableExpression(new VariableIdentifierExpression(unaryExpressionContext.primary().variableName().getText()),new UnaryExpression(null,UnaryType.NULL));
                //scheme.getComputeUnit().addExpression(variableExpression);
                return  variableExpression;
            }
        }else if( null != unaryExpressionContext.primary().parExpression()){
            return processParExpression(unaryExpressionContext.primary().parExpression());
        }
        return null;
    }
}
