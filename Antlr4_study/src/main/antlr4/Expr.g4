grammar Expr;

/* the grammar name and file name must match */

// Start model.Variable
prog: (decl | expr) + EOF   # Program
    ;

decl: ID ':' INT_TYPE '=' NUM   # Declaration
    ;

expr: expr '*' expr # Multiplication
    | expr '+' expr # Addition
    | ID    # Variable
    | NUM   # Number
    ;


/* Tokens */
ID: [a-z][a-zA-Z0-9_]*; //identifiers
NUM: '0' | '-'?[1-9][0-9]*;
INT_TYPE: 'INT';
COMMENT: '--' ~[\r\n]* -> skip;
WS: [ \t\n]+ -> skip;