import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Инициализируем сканер
        Scanner scanner = new Scanner(System.in);
        // Считываем строку
        String toParse = scanner.nextLine();
        // Создаем множество имен переменных для таблицы истинности
        Set<String> names = new HashSet<>();
        // Передаем строку в лексер
        Lexer lexer = new Lexer(toParse, names);
        // Анализируем строку, разбиваем на лексемы
        List<Lexeme> lexemes = lexer.getLexemes();
        // Получаем имена переменных
        names = lexer.getNames();
        // Приводим множество к списку для удобного обхода
        List<String> namesList = new ArrayList<>(names);
        // Передаем в парсер список лексем
        AST builder = new AST(lexemes);
        // Строим абстрактное синтаксическое дерево и возвращаем ссылку на корневой узел
        AstNode rootNode = builder.parseToAST();
        // Создаем таблицу
        Map<String, Boolean> valueOfExpression = new HashMap<>();
        // Создаем объект операции на деревом, не меняющим его значений
        CalculateExpression calculateExpression = new CalculateExpression(valueOfExpression);
        // Счетчик правивых утверждений
        int trues = 0;
        // Количество строк в таблице истинности
        int stringsCount = (int)Math.pow(2, names.size());
        // Проходим в цикле все значения от i до количества строк в таблице
        // Заметим, что в двоичной системе все числа представляют набор 0 и 1
        // Для каждого i строим его двоичное представление, создав строку максимум на 16 символов (из условия)
        // Переворациваем строку и разбиваем на массив
        // Устанавливаем переменным значения для выполнения операций в цикле
        for(Integer i = 0; i < stringsCount; i++){
                int varCount = names.size();
                int j = 0;
                String s = String.format("%16s", Integer.toBinaryString(i)).replace(' ', '0');
                s = rev(s);
                char[] literas = s.toCharArray();
                while (j < varCount){
                    boolean res;
                    res = literas[j] == '1';
                    valueOfExpression.put(namesList.get(j), res);
                    j++;
                }
                boolean res = rootNode.accept(calculateExpression);
                trues += res ? 1 : 0;
            }

        if (trues == 0){
            System.out.println("Unsatisfiable");
        }
        else if(trues == stringsCount){
            System.out.println("Valid");
        }
        else{
            System.out.println("Satisfiable and invalid, " + trues + " true and " + (stringsCount - trues) + " false cases");
        }

        }
        public static String rev(String s){
            return new StringBuilder(s).reverse().toString();
        }

    }


