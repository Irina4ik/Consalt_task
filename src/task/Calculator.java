/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.text.DecimalFormat;
import java.util.LinkedList;

/**
 *
 * @author i.
 */
public class Calculator {

    public static void main(String[] args) {
        Calculator calcExpr = new Calculator();
        System.out.println(calcExpr.evaluate("9*(4+5) - 1- 7*(8-5)")); // 59
        System.out.println(calcExpr.evaluate("(1-7.5)*9+4")); // -54.5
        System.out.println(calcExpr.evaluate("(1.5+ 1 / (0.2+0.3))")); // 3.5
        System.out.println(calcExpr.evaluate("-7()*")); // null
        System.out.println(calcExpr.evaluate("(1.43645755+ 1 / (0.2+0.3))")); // 3.4365
    }
        
    // вычисление выражения (применим алгоритм обратной польской записи)
    public String evaluate(String expression) {
        try {
            LinkedList<Double> numberExpression = new LinkedList<>(); // хранение чисел в порядке поступления
            LinkedList<Character> operatorExpression = new LinkedList<>(); // хранение операторов в порядке поступления
            for (int i = 0; i < expression.length(); i++) { // разбор строки с выражением и вычисление
                char symbolExpression = expression.charAt(i); // проверяем символ выражения
                // пробел игнорируем
                if (isSpace(symbolExpression)) {
                    continue;
                }
                // открывающая скобка (отправляем в список с операторами)
                if (symbolExpression == '(') {
                    operatorExpression.add('(');
                // закрывающая скобка
                } else if (symbolExpression == ')') {
                    while (operatorExpression.getLast() != '(') {
                        // вычисляем выражение в скобках - берем недавно прочитанный оператор и два числа 
                        // результат добавляем в список чисел
                        calculateOperator(numberExpression, operatorExpression.removeLast());
                    }
                    operatorExpression.removeLast(); // убираем занесенную '('
                // оператор + - * / 
                } else if (isOperator(symbolExpression)) {
                    // если были невыполненные операции с большим или таким же приоритетом - вычисляем
                    while (!operatorExpression.isEmpty() && priority(operatorExpression.getLast()) >= priority(symbolExpression)) {
                        calculateOperator(numberExpression, operatorExpression.removeLast());
                    }
                    operatorExpression.add(symbolExpression); // текущий оператор храним (ещё нет второго операнда)
                // число, м.б. десятичное
                } else {
                    String number = "";
                    while (i < expression.length() && Character.isDigit(expression.charAt(i)) | expression.charAt(i) == '.') {
                        number += expression.charAt(i++); // читаем число
                    }
                    i--;
                    numberExpression.add(Double.parseDouble(number));
                }
            }
            // вычисляем выражение, соблюдая порядок выполнения действий
            while (!operatorExpression.isEmpty()) {
                calculateOperator(numberExpression, operatorExpression.removeLast());
            }
            // оставляем 4 знака после запятой
            DecimalFormat resultDF = new DecimalFormat("##.####"); //для возврата 4 знаков после запятой
            return resultDF.format(numberExpression.get(0));// полученный результат
        //    
        } catch (Exception ex) {
            return null;
        }
    }

    // true, если пробел
    static boolean isSpace(char symbolExpression) {
        return symbolExpression == ' ';
    }

     // true, если оператор + - * /
    static boolean isOperator(char symbolExpression) {
        return symbolExpression == '+' || symbolExpression == '-' || symbolExpression == '*' || symbolExpression == '/';
    }

     // расставляем приоритет операций, при + или - возврат 1, при * / 2 иначе -1
    static int priority(char operation) {
        switch (operation) {
            case '+':
            case '-':
                return 1; // меньший приоритет
            case '*':
            case '/':
                return 2; // больший приоритет
            default:
                return -1;
        }
    }

    // забираем 2 последних элемента из листа и выполяем над ними операцию
    static void calculateOperator(LinkedList<Double> numberExpression, char operation) {
        double firstNumber = numberExpression.removeLast(); 
        double secondNumber = numberExpression.removeLast();
        switch (operation) {
            case '+':
                numberExpression.add(secondNumber + firstNumber);
                break;
            case '-':
                numberExpression.add(secondNumber - firstNumber);
                break;
            case '*':
                numberExpression.add(secondNumber * firstNumber);
                break;
            case '/':
                numberExpression.add(secondNumber / firstNumber);
                break;
        }
    }
}
