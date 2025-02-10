import java.util.Scanner;

public class Calculator {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String exp = sc.next();
        System.out.println("The result of the expression is : "+evaluateExpression(exp));
    }
    public static double evaluateExpression(String expression) {
        System.out.println(expression);
        return 0.0;
    }

}
