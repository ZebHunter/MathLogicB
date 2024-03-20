/*
    Интерфейс, для реализации паттерна Посетитель (т.е. интерфейс для абстракции над разными типами узлов)
 */

public interface AstNode {
    Boolean accept(Visitor visitor);
}
