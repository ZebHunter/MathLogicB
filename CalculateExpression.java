import java.util.Map;

/*
    Класс, для реализации операции над другими объектами по паттерну Посетитель
    Имплементирует Visitor
 */
public class CalculateExpression implements Visitor {
    // Коллекция переменных со значениями
    private Map<String, Boolean> value;

    public CalculateExpression(Map<String, Boolean> value){
        this.value = value;
    }
    public Boolean visit(BinaryNode node){
        switch(node.getOperation()){
            case "->":
                return !node.getLeftBranch().accept(this) || node.getRightBranch().accept(this);
            case "&":
                return node.getLeftBranch().accept(this) && node.getRightBranch().accept(this);
            case "|":
                return node.getLeftBranch().accept(this) || node.getRightBranch().accept(this);
        }
        return false;
    }

    public Boolean visit(UnaryNode node){
        return !node.getUnaryBranch().accept(this);
    }

    public Boolean visit(VariableNode node){
        return value.get(node.getName());
    }

}
