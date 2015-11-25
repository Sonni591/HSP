/**
* ANTLRv4 grammar to parse dat files.
* http://www.antlr.org/
*
* Author: Thomas Butz
*/
grammar Dat;

// a dat file consists of multiple variable declarations
datBody
    :   varDeclaration* EOF
    ;

// defines the structure of a variable declaration
varDeclaration
    :   varName '=' varValue ';'
    ;
    
// defines valid variable names
varName
    :   Identifier
    ;

// defines the possible values of variables
varValue
    :   singleValue
    |   arrayValue
    |   oneDimFieldValue
    |   twoDimFieldValue
    |   threeDimFieldValue
    ;

// the structure of a single value
singleValue
    :   IntegerLiteral
    |   FloatingPointLiteral
    ;

// the structure of an array
arrayValue
    :   '[' (singleValue ','?)* singleValue ']'
    ;

// one dimensional field
oneDimFieldValue
    :   '#[' (IntegerLiteral ':' singleValue)+ ']#'
    ;

// two dimensional field
twoDimFieldValue
    :   '#[' (IntegerLiteral ':' arrayValue)+ ']#'
    ;

// three dimensional field
threeDimFieldValue
    :   '#[' (IntegerLiteral ':' twoDimFieldValue)+ ']#'
    ;

//////////////// Token definitions
ASSIGN : '=';
COMMA  : ',';
SEMI   : ';';
COLON  : ':';
LARRAY : '[';
RARRAY : ']';
LFIELD : '#[';
RFIELD : ']#';

// valid variable names
Identifier
    :   Letter Character*
    ;
    
// defines the structure of integer values
IntegerLiteral
    :   '-'? DecimalValue
    ;
    
// defines the structure of floating point values
FloatingPointLiteral
    :   '-'? DecimalValue '.' Digit+
    ;

fragment
Character
    :   [a-zA-Z0-9_]
    ;

fragment
Letter
    :   [a-zA-Z]+
    ;

fragment
DecimalValue
    :   '0'
    |   NonZeroDigit Digit*
    ;

fragment
Digit
    : '0'
    | NonZeroDigit
    ;

fragment
NonZeroDigit
    : [1-9]
    ;

// skip spaces, tabs, newlines
WS  :  [ \t\r\n\u000C]+ -> skip
    ;

// skip comments
COMMENT
    :   '//' ~[\r\n]* -> skip
    ;