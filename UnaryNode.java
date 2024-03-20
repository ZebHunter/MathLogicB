/*
    Класс, описывающий узел унарной оперции (т.е. ! (не))
    Имплементирует AstNode
    Реализует метод accept для паттерна Посетитель
 */
public class UnaryNode implements AstNode{
    //Единственная ветка операции
    private final AstNode unoBranch;
    //Описание операции
    private final String operation;

    public UnaryNode(AstNode unoBranch, String operation) {
        this.unoBranch = unoBranch;
        this.operation = operation;
    }

    public Boolean accept(Visitor visitor){
        return visitor.visit(this);
    }

    public String getOperation(){
        return operation;
    }

    public AstNode getUnaryBranch(){
        return unoBranch;
    }


}
