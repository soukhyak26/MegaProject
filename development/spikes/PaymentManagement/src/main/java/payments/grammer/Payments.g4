grammar Payments;


@header {
package com.affaince.payments.grammer;
}

// Scheme structure
scheme
    : givenUnit  advancePayUnit  THEN  residualPayUnit  EOF
    ;

givenUnit
    :   GIVEN   givenBody
    ;

givenBody
    :   variableDeclarationStatement*
    ;


computeUnit
    :   COMPUTE computeBlock
    ;

computeBlock
    :   block
    ;

//PAY 40% OF CURRENT SUBSCRIPTION AMOUNT ON SUBSCRIPTION CONFIRMATION
advancePayUnit
      :   PAY computeBlock ON ADVANCE_PAYMENT_EVENT SEMI
      ;

// PAY variableName (payBefore | payAfter) expressionList payMultiplier variableName proportionExpression SEMI
residualPayUnit
    :  PAY computeBlock (payBefore | payAfter) expression payMultiplier variableName(COMMA expression payMultiplier variableName)* proportionExpression SEMI
    ;


eligibilityUnit
   :    ELIGIBLEWHEN block
   ;


payBefore
    :   BEFORE
    ;

payAfter
    :   AFTER
    ;

payMultiplier
    :   OF
    ;
variableDeclarationStatement
    :   variableDeclaratorId SEMI
    |   variableDeclaratorId ASSIGN variableInitializer SEMI
    |   variableDeclaratorId ASINPUT SEMI
    ;
variableDeclaratorId
    :   variableName ('[' ']')?    //* is removed as we do not intend to support multidimensional arrays as of now
    ;

variableInitializer
    :   arrayInitializer
    |   expression
    |
    ;

arrayInitializer
    :   '{' variableInitializer (',' variableInitializer)* '}'
    ;


conditionalExpression
    :   conditionalOrExpression ( QUESTIONMARK expression COLON conditionalExpression )?
    ;

conditionalOrExpression
    :   conditionalAndExpression ( connectorOr conditionalAndExpression )*
    ;

conditionalAndExpression
    :   relationalExpression ( connectorAnd relationalExpression )*
    ;
relationalOp
    :   GT | GE | LE | LT | EQUAL | NOTEQUAL
    ;
relationalExpression
    :   additiveExpression ( relationalOp additiveExpression )*
    |   iterativeStatement ( relationalOp additiveExpression )*
    ;

additiveExpression
    :   multiplicativeExpression ( (ADD | SUB) multiplicativeExpression )*
    |   iterativeAggregationExpression
    ;

multiplicativeExpression
    :   unaryExpression ( ( MUL | DIV | MOD ) unaryExpression )*
    ;

unaryExpression
    :   '+' unaryExpression
    |   '-' unaryExpression
    |   primary
    ;

primary
    :   parExpression
    |   literal
    |   variableName
    ;

variableName
    :   IDENTIFIER
    ;

connectorOr
    :   ORSTR
    ;
parExpression
    :   LPAREN expression RPAREN
    ;


nonDefaultProportionExpression
    :    NUMBER COLON NUMBER (COLON NUMBER)+
    ;
proportionExpression
    :   IN  (DEFAULT | nonDefaultProportionExpression)   PROPORTION
    ;

expressionList
    :   expression (COMMA expression)*
    ;

expression
    :   conditionalExpression (ASSIGN expression)?
    ;

literal
    :   NUMBER
    |   CharacterLiteral
    |   StringLiteral
    |   BooleanLiteral
    |   'null'
    ;
iterativeStatement
    :   EACH expression
    ;
iterativeAggregationExpression
    :   SUMOF EACH (variableDeclarationStatement | expression)
    ;
statement
     :  statementExpression (SEMI)?                       //block
     //|  statementExpression (SEMI)?
     ;

statementExpression
    :   expression
    ;
block
    :  blockStatement*
    ;


blockStatement
    :   statement
    |   variableDeclarationStatement
    ;


//end of expressions
//Lexer
GIVEN
    :   'given'
    ;

ASINPUT
    :   ' as input'
    ;

COMPUTE
    :   'compute'
    ;

EACH
    :   'each '
    ;

SUMOF
    :   'sumOf '
    ;

ELIGIBLEWHEN
    :   'eligibleWhen'
    ;

PAY
    :   'pay'
    ;

BEFORE
    :   'before'
    ;

AFTER
    :   'after'
    ;

IN
    :   'in'
    ;

ON
    : 'on'
    ;
PROPORTION
    :   'proportion'
    ;

DEFAULT
    :   'default'
    ;

OF
    :   'of'
    ;

connectorAnd
    : 'and'
    ;
THEN
    :   'then'
    ;

ORSTR
    :   'or'
    ;
RESIDUALPAY
    : 'residual due amount'
    ;
CURRENTSUBSCRIPTIONAMOUNT
    : 'current subscription amount'
    ;
ADVANCE_PAYMENT_EVENT
    : 'subscription confirmation'
    ;
EACHDELIVERY
    : 'each delivery'
    ;
FOR
    : 'for'
    ;
DELIVERIES
    : 'deliveries'
    ;
PLUS
    : 'plus'
    ;
NullLiteral
	:	'null'
	;
//end -keywords



NUMBER
    :   Sign? ('.' [0-9]+ | [0-9]+ ('.' [0-9]*)? )
    ;


//now define all operators to be used
LPAREN : '(';
RPAREN : ')';
LBRACE : '{';
RBRACE : '}';
LBRACK : '[';
RBRACK : ']';
COMMA : ',';
SEMI : ';';
COLON : ':' ;
ASSIGN : '=';
GT : '>';
LT : '<';
BANG : '!';
EQUAL : '==';
LE : '<=';
GE : '>=';
NOTEQUAL : '!=';
INC : '++';
DEC : '--';
ADD : '+';
SUB : '-';
MUL : '*';
DIV : '/';
MOD : '%';
QUESTIONMARK  : '?' ;

//end-operators


fragment
Sign
	:	[+\-]
	;
BooleanLiteral
	:	'true'
	|	'false'
	;
CharacterLiteral
	:	'\'' SingleCharacter '\''
	|	'\'' EscapeSequence '\''
	;

fragment
SingleCharacter
	:	~['\\]
	;
// §3.10.5 String Literals
StringLiteral
	:	'"' StringCharacters? '"'
	;
fragment
StringCharacters
	:	StringCharacter+
	;
fragment
StringCharacter
	:	~["\\]
	|	EscapeSequence
	;
// §3.10.6 Escape Sequences for Character and String Literals
fragment
EscapeSequence
	:	'\\' [btnfr"'\\]
	//:   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
	;
//Identifiers (must appear after all keywords in the grammar)
IDENTIFIER
    :   SchemeLetter SchemeLetterOrDigit*
    ;
fragment
SchemeLetter
	:	[a-zA-Z$_]
	;
fragment
SchemeLetterOrDigit
	:	[a-zA-Z0-9$_]
	;
WS  :  [ \t\r\n\u000C]+ -> skip
    ;

COMMENT
    :   '/*' .*? '*/' -> skip
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> skip
    ;


