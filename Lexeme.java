/*
    Класс для описания объекта лексемы, т.е. определенного объекта лексического анализа введенной строки
 */

public class Lexeme {
    //Тип токена в лексеме
    private final Token type;
    //Значение(имена для переменных или операции)
    private final String value;

    public Lexeme(Token type, String value){
        this.type = type;
        this.value = value;
    }

    public String toString(){
        return "(" + value + ") ";
    }

    public String getValue(){
        return value;
    }

    public Token getType(){
        return type;
    }

}
