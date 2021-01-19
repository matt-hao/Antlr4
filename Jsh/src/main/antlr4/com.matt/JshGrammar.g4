grammar JshGrammar;

/*
 * Parser Rules
 */
prog: command EOF;

command: pipe | call | command ';' command;

pipe: call '|' call | pipe '|' call;

//cat articles/*
//grep "Interesting String" < text1.txt > result.txt; cat aa.txt | grep "a" | grep "b"
//wc -l `find -name '*.java'`
//cat articles/* | grep "Interesting String"
//call: (argument)+ | argument (redirection)*
//call: argument (redirection | argument)*;
call: argument (redirection)*;

argument : (quoted | NONSPECIAL)+;
redirection: '<'  argument | '>'  argument;

quoted: SINGLEQUOTED | DOUBLEQUOTED | BACKQUOTED;

/*
 * Lexer Rules
 */
SINGLEQUOTED: '\''~['\n]*'\'';
DOUBLEQUOTED: '"'(BACKQUOTED|~[\n"`])*'"';
BACKQUOTED: '`'~[\n`]*'`';
NONSPECIAL: ~[ '"`|<>;\n\r\t]+;

COMMENT: '--' ~[\r\n]* -> skip;
WS: [ \t\n]+ -> skip;
