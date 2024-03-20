/*
    Перечисление всех типов-токенов, допустимых в полученной для разбора стоке
 */

public enum Token {
    LEFT_BRACKET,   // '('
    RIGHT_BRACKET,    // ')'
    TOKEN_OR,       // '|'
    TOKEN_AND,      //  '&'
    TOKEN_IMPLICATION,     // '->'
    TOKEN_NOT,          // '!'
    TOKEN_VARIABLE     // '_имя_переменной_'
}
