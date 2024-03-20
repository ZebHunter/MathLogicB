/*
    Интерфейс для обобщения оперций над узлами
    Необходим для реализации паттерна Посетитель
 */

public interface Visitor {
    Boolean visit(BinaryNode node);
    Boolean visit(UnaryNode node);
    Boolean visit(VariableNode node);
}
