grammar PaymentGrammar;

/* Lexical rules */

PAY : 'pay';
CURRENT_SUBSCRIPTION_AMOUNT : 'current subscription amount';
SUBSCRIPTION_CONFIRMATION : 'subscription confirmation';
RESIDUAL_DUE_PAYMENT : 'residual due amount';
AFTER : 'after';
BEFORE : 'before';
OF : 'of';
DELIVERIES : 'deliveries';
DELIVERY : 'delivery';
DEFAULT : 'default';
PROPORTION : 'proportion';
PERCENTAGE : '%';
IN : 'in';
ON : 'on';
N : 'N';
REMAININGN: 'remaining-N';
AMOUNT : 'amount';
FOR : 'for';
EACH : 'each';

AND : 'and' ;
OR  : 'or' ;

MULT  : '*' ;
DIV   : '/' ;
PLUS  : '+' ;
MINUS : '-' ;

GT : '>' ;
GE : '>=' ;
LT : '<' ;
LE : '<=' ;
EQ : '=' ;

// DECIMAL, IDENTIFIER, COMMENTS, WS are set using regular expressions

DECIMAL : '-'?[0-9]+('.'[0-9]+)? ;

IDENTIFIER : [a-zA-Z_][a-zA-Z_0-9]* ;

SEMI : ';' ;
COMMA : ',';

// COMMENT and WS are stripped from the output token stream by sending
// to a different channel 'skip'

COMMENT : '//' .+? ('\n'|EOF) -> skip ;

WS : [ \r\t\u000C\n]+ -> skip ;


/* Parser rules */

payment_rule_set : payment_rule ;

payment_rule : advance_payment_expr AND residual_due_payment_expr
                | advance_payment_expr OR residual_due_payment_expr
                | advance_payment_expr
                | residual_due_payment_expr;

advance_payment_expr : PAY percent_value PERCENTAGE
                       OF percent_source
                       ON payment_event;

payment_event: SUBSCRIPTION_CONFIRMATION;

residual_due_payment_expr: PAY RESIDUAL_DUE_PAYMENT
                           after_before delivery_number_list DELIVERY
                           IN proportion_value PROPORTION;


proportion_value :  DEFAULT |
                    proportion_value COMMA proportion_value |
                    DECIMAL COMMA |
                    DECIMAL;
percent_source : CURRENT_SUBSCRIPTION_AMOUNT | DECIMAL DELIVERY | DECIMAL DIV DECIMAL N DELIVERY;
percent_value: DECIMAL;
after_before : AFTER | BEFORE;
delivery_number_list : delivery_number_list COMMA delivery_number_list |
                        delivery_number_expr COMMA |
                        delivery_number_expr;

delivery_number_expr: DECIMAL DIV DECIMAL N | DECIMAL DIV DECIMAL REMAININGN;