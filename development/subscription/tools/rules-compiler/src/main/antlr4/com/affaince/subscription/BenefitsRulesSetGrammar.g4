grammar BenefitsRulesSetGrammar;

/* Lixical Rules */

GIVEN : 'given';
CONFIGURE : 'configure';
ELIGIBLE : 'eligible';
CURRENCY : 'currency';
CASHBACK : 'cashback';
DISCOUNT : 'discount';
MONTH : 'month';
DAY : 'day';
YEAR : 'year';
WEEK : 'week';
REWARD_POINT : 'reward point';
APPLY : 'apply';
ALL : 'all';
WHEN : 'when';
PAYMENT_MODE : 'payment mode';
POINT : 'point';
PERCENT_ADVANCE_PAYMENT : 'percent advance payment';
WITH : 'with';
EACH : 'each';
ALTERNATE : 'alternate';
LAST : 'last';
DELIVERY : 'delivery';
SUBSCRIPTION : 'subscription';
IS : 'is';
AS : 'as';

AND : 'and' ;
OR  : 'or' ;

TRUE  : 'true' ;
FALSE : 'false' ;

MULT  : '*' ;
DIV   : '/' ;
PLUS  : '+' ;
MINUS : '-' ;

GT : '>' ;
GE : '>=' ;
LT : '<' ;
LE : '<=' ;
EQ : '=' ;

LPAREN : '(' ;
RPAREN : ')' ;

// DECIMAL, IDENTIFIER, COMMENTS, WS are set using regular expressions

DECIMAL : '-'?[0-9]+('.'[0-9]+)? ;

IDENTIFIER : [a-zA-Z_][a-zA-Z_0-9]* ;

SEMI : ';' ;

// COMMENT and WS are stripped from the output token stream by sending
// to a different channel 'skip'

COMMENT : '//' .+? ('\n'|EOF) -> skip ;

WS : [ \r\t\u000C\n]+ -> skip ;


/* Parser rules */
rule_set : single_rule;

single_rule :GIVEN convert_expr CONFIGURE AS arithmetic_expr ELIGIBLE WHEN eligibility_condition APPLY WHEN conclusion SEMI;

convert_expr:
    money_convert_expr  AND period_convert_expr
   | money_convert_expr
   | period_convert_expr;

money_convert_expr: money_expr_name IS currency_value CURRENCY EQ point_value POINT;
money_expr_name : IDENTIFIER;
currency_value : DECIMAL;
point_value : DECIMAL;

period_convert_expr: period_expr_name IS period_value MONTH EQ point_value POINT;
period_expr_name : IDENTIFIER;
period_value : DECIMAL;

/*arithmatic_expr:
    benefit_money_base_var DIV money_expr_name PLUS benefit_period_base_var DIV period_expr_name;

benefit_money_base_var : IDENTIFIER;
benefit_period_base_var : IDENTIFIER;*/

eligibility_condition:
    logical_expr;

logical_expr
 : logical_expr AND logical_expr # LogicalExpressionAnd
 | logical_expr OR logical_expr  # LogicalExpressionOr
 | comparison_expr               # ComparisonExpression
 | LPAREN logical_expr RPAREN    # LogicalExpressionInParen
 | logical_entity                # LogicalEntity
 ;

comparison_expr : comparison_operand comp_operator comparison_operand
                    # ComparisonExpressionWithOperator
                | LPAREN comparison_expr RPAREN # ComparisonExpressionParens
                ;

comparison_operand : arithmetic_expr;

comp_operator : GT
              | GE
              | LT
              | LE
              | EQ
              ;

arithmetic_expr
 : arithmetic_expr MULT arithmetic_expr  # ArithmeticExpressionMult
 | arithmetic_expr DIV arithmetic_expr   # ArithmeticExpressionDiv
 | arithmetic_expr PLUS arithmetic_expr  # ArithmeticExpressionPlus
 | arithmetic_expr MINUS arithmetic_expr # ArithmeticExpressionMinus
 | MINUS arithmetic_expr                 # ArithmeticExpressionNegation
 | LPAREN arithmetic_expr RPAREN         # ArithmeticExpressionParens
 | numeric_entity                        # ArithmeticExpressionNumericEntity
 ;

logical_entity : (TRUE | FALSE) # LogicalConst
               | IDENTIFIER     # LogicalVariable
               ;

numeric_entity : DECIMAL              # NumericConst
               | IDENTIFIER           # NumericVariable
               ;

conclusion:
    PAYMENT_MODE EQ payment_percent PERCENT_ADVANCE_PAYMENT benefit_pay_method WITH which_delivey option;

payment_percent: DECIMAL;
benefit_pay_method: 'deposit' | 'pay';
which_delivey: EACH | ALTERNATE | LAST;
option: DELIVERY | SUBSCRIPTION;

