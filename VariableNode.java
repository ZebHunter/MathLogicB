/*
    Класс, описывающий узел переменной
    Не имеет веток (лист, т.е. есть только ветка родителя
    Имплементирует AstNode
    Реализует метод accept для паттерна Посетитель
 */
public class VariableNode implements AstNode{
    //Название переменной
    private final String name;

    public VariableNode(String name) {
        this.name = name;
    }

    public Boolean accept(Visitor visitor){
        return visitor.visit(this);
    }

    public String getName(){
        return name;
    }
}
