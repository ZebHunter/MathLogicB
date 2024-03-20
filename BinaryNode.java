/*
    Класс, описывающий узел с двумя веткми (т.е. узел бинарной операции)
    Имплементирует AstNode
    Реализует метод accept для паттерна Посетитель
 */
public class BinaryNode implements AstNode{
    //Левая ветка
    private AstNode leftBranch;
    //Правая ветка
    private AstNode rightBranch;
    //Операция, находящаяся в узле
    private String operation;

    public BinaryNode(AstNode leftBranch, AstNode rightBranch, String operation) {
        this.leftBranch = leftBranch;
        this.rightBranch = rightBranch;
        this.operation = operation;
    }

    public Boolean accept(Visitor visitor){
        return visitor.visit(this);
    }

    public String getOperation(){
        return operation;
    }

    public void setOperation(String operation){
        this.operation = operation;
    }

    public AstNode getLeftBranch(){
        return leftBranch;
    }

    public AstNode getRightBranch(){
        return rightBranch;
    }



}
