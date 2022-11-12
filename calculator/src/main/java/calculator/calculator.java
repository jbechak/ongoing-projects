package calculator;

import java.util.Scanner;
import static java.lang.System.exit;

public class calculator {

    public static void main(String[] args) {
        Scanner getInput = new Scanner(System.in);
        System.out.println("\nWelcome to Mr. Calculator!");

        do {
            System.out.print("Please enter a math problem using +, -, *, or /. (Type 'q' to quit anytime): ");
            String mathProblem = getInput.nextLine().trim();
            if (mathProblem.equals("q")) {
                exit(0);
            }
            String parsingCharacter = "";
            if (mathProblem.contains("+")) {
                parsingCharacter = "+";
            } else if (mathProblem.contains("-")) {
                parsingCharacter = "-";
            } else if (mathProblem.contains("*")) {
                parsingCharacter = "*";
            } else if (mathProblem.contains("/")) {
                parsingCharacter = "/";
            }
            int num1 = Integer.parseInt(mathProblem.substring(0, mathProblem.indexOf(parsingCharacter)).trim());
            int num2 = Integer.parseInt(mathProblem.substring(mathProblem.indexOf(parsingCharacter) + 1).trim());
            int result = 0;
            double divisionResult = 0;

            switch (parsingCharacter) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    divisionResult = num1 / (double)num2;
                    System.out.println(num1 + " " + parsingCharacter + " " + num2 + " = " + divisionResult + "\n");
                    break;
            }
            if (!parsingCharacter.equals("/")) {
                System.out.println(num1 + " " + parsingCharacter + " " + num2 + " = " + result + "\n");
            }
        } while (true);
    }

}
