import java.util.List;
import java.util.ListIterator;

/*
    Класс для парсинга списка из лексем, полученных в лексере, в абстрактное синтасическое дерево
    При создании класса передается список лексем
    Основной метод - parseToAst()
    Возвращает ссылку на корневой узел
 */
public class AST {
    //Список лексем
    private final List<Lexeme> lexemes;
    public AST(List<Lexeme> lexemes){
        this.lexemes = lexemes;
    }

    //Парсинг происходит по грамматике, заданной в задании A
    public AstNode parseToAST(){
        return expression(lexemes.listIterator());
    }

    static AstNode expression(ListIterator<Lexeme> iterator){
        AstNode node = disjunction(iterator);
        while (iterator.hasNext() && iterator.next().getType() == Token.TOKEN_IMPLICATION){
            node = new BinaryNode(node, expression(iterator), "->");
        }
        iterator.previous();
        return node;
    }

    static AstNode disjunction(ListIterator<Lexeme> iterator){
        AstNode node = conjunction(iterator);
        while (iterator.hasNext() && iterator.next().getType() == Token.TOKEN_OR){
            node = new BinaryNode(node, conjunction(iterator), "|");
        }
        iterator.previous();
        return node;
    }

    static AstNode conjunction(ListIterator<Lexeme> iterator){
        AstNode node = negation(iterator);
        while (iterator.hasNext() && iterator.next().getType() == Token.TOKEN_AND){
            node = new BinaryNode(node, negation(iterator), "&");
        }
        iterator.previous();
        return node;
    }

   static AstNode negation(ListIterator<Lexeme> iterator){
        Lexeme lexeme = iterator.next();
        if(lexeme.getType() == Token.LEFT_BRACKET){
            AstNode node = expression(iterator);
            if(iterator.hasNext()) iterator.next();
            return node;
        }
        else if(lexeme.getType() == Token.TOKEN_NOT){
            return new UnaryNode(negation(iterator), "!");
        }
        else{
            iterator.previous();
            return variable(iterator);}
    }


    static AstNode variable(ListIterator<Lexeme> iterator){
        return new VariableNode(iterator.next().getValue());
    }
}
