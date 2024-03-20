import java.util.*;

public class Lexer {
    //Список лексем, который мы хотим получить при анализе строки
    private final List<Lexeme> lexemes;
    //Строка, разбиваемая на лексемы
    private final String strToParse;
    //Множество имен переменных, чтобы не было повторений
    private Set<String> names;

    public Lexer(String strToParse, Set<String> names){
        this.strToParse = strToParse;
        this.names = names;
        lexemes = new ArrayList<Lexeme>();
    }
    //Проверка на пробелы
    private boolean isSpace(char c){
        return c == ' ' || c == '\n' || c == '\r' || c == '\t';
    }
    //Проверка на операцию
    private boolean isOperation(char c){
        return c == '&' || c == '|' || c == '-' || c == '!' || c == '(' || c == ')' || c == '>';
    }

    public Lexer (List<Lexeme> lexemes, String strToParse){
        this.strToParse = strToParse;
        this.lexemes = lexemes;
    }

    public List<Lexeme> getLexemes(){
        int position = 0;      // Позиция элемента в строке
        char c;                // Элемент строки
        while (position < strToParse.length()) {
            c = strToParse.charAt(position);
            switch(c){
                case '(':
                    lexemes.add(new Lexeme(Token.LEFT_BRACKET, "("));
                    position++;
                    break;
                case ')':
                    lexemes.add(new Lexeme(Token.RIGHT_BRACKET, ")"));
                    position++;
                    break;
                case '!':
                    lexemes.add(new Lexeme(Token.TOKEN_NOT, "!"));
                    position++;
                    break;
                case '&':
                    lexemes.add(new Lexeme(Token.TOKEN_AND, "&"));
                    position++;
                    break;
                case '|':
                    lexemes.add(new Lexeme(Token.TOKEN_OR, "|"));
                    position++;
                    break;
                case '-':
                    lexemes.add(new Lexeme(Token.TOKEN_IMPLICATION, "->"));
                    position = position + 2;
                    break;
                default:
                    if (isSpace(c)){
                        position++;
                        break;
                    }
                    else{

                        List<Character> name = new ArrayList<>();
                        while ((!isSpace(c) && !isOperation(c)) && (position < strToParse.length())){
                            c = strToParse.charAt(position);
                            if(isOperation(c) || isSpace(c)) {
                                break;
                            }
                            else{
                                name.add(c);
                                position++;
                            }
                        }

                        StringJoiner joiner = new StringJoiner("");
                        for (Character character: name){
                            joiner.add(character.toString());
                        }
                        String var = joiner.toString();
                        lexemes.add(new Lexeme(Token.TOKEN_VARIABLE, var));
                        names.add(var);
                        break;
                    }

            }
        }
        return lexemes;
    }

    public Set<String> getNames(){
        return names;
    }

}
