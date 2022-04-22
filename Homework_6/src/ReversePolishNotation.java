//Ashvin Ganesan
// Mr Paige
//Algorithms 
// October First 2020
// Homework #6

public class ReversePolishNotation {

    ArrayStack<Double> RPN;

    public ReversePolishNotation(String[] args) {
        RPN = new ArrayStack<Double>();
        for (String arg : args) {
            if (isNumeric(arg)) {
                RPN.push(Double.parseDouble(arg));
            } else {
                switch (arg) {
                    case "+":
                        RPN.push(RPN.pop() + RPN.pop());
                        break;
                    case "/":
                        RPN.push(RPN.pop() / RPN.pop());
                        break;
                    case "*":
                        RPN.push(RPN.pop() * RPN.pop());
                        break;
                    case "-":
                        RPN.push(RPN.pop()*-1 + RPN.pop());
                        break;
                    default:
                        System.out.println(arg + " is not an int and not a command");

                }
            }

        }
        System.out.println(RPN.top());
    }

    // This idea for finding if a string is from https://www.baeldung.com/java-check-string-number
    // I couldn't really remember how we did it before. 
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;

    }

}
