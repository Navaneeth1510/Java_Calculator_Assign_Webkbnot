import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String exp = sc.next();
        System.out.println("The result of the expression is : "+evaluateExpression(exp));
    }
    public static int precedence(char op) {
        return (op == '+' || op == '-') ? 1 : (op == '*' || op == '/') ? 2 : 0;
    }

    public static double calculate(double a, double b, char op) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            default -> 0;
        };
    }

    public static double evaluateExpression(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        int i=0;
        while(i<expression.length()){
            char ch = expression.charAt(i);
            if(ch==' '){
                i++;
                continue;
            }
            if (Character.isDigit(ch)) {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    num.append(expression.charAt(i++));
                }
                values.push(Double.parseDouble(num.toString()));
                continue;
            }

            if(ch=='('){
                ops.push(ch);
            }
            else if (ch == ')') {
                while (!ops.isEmpty() && ops.peek() != '(') {
                    values.push(calculate(values.pop(), values.pop(), ops.pop()));
                }
                ops.pop();
            }
            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(ch)) {
                    values.push(calculate(values.pop(), values.pop(), ops.pop()));
                }
                ops.push(ch);
            }

            i++;
        }

        while(!ops.isEmpty()){
            values.push(calculate(values.pop(), values.pop(), ops.pop()));
        }

        return values.pop();
    }

}
