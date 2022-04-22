//Ashvin Ganesan
//Algorithms
// Homework # 2a
//august 27th 2020. 

public class Homework_2A {

    public static void main(String[] args) {
//        String[] args = new String[2];
//        args[0] = "-XMIN";
//        args[1] = "-2.5";
//
//        Parameter arg = new Parameter();
//I had these because i was having some trouble getting the terminal to work. 
        for (int i = 0; i < args.length; i++) {
            args[i].toUpperCase();
            if (args[i].substring(0, 1).equals("-")) {
                try {
                    Double.parseDouble(args[i]);
                } catch (NumberFormatException ex) {
                    args[i] = args[i].substring(1);
                }
            }
            Parameter.Kind current = Parameter.classify(args[i]);

            if (current == Parameter.Kind.OPTION) {
                System.out.print("OPTION = " + Parameter.getOptionValue(args[i]));
                System.out.println(", expected value = " + Parameter.kindOfValue(Parameter.getOptionValue(args[i])));
            } else {
                System.out.print(current + " = ");

                if (current == Parameter.Kind.COLOR) {
                    System.out.println(Parameter.getColorValue(args[i]));
                } else if (current == Parameter.Kind.DOUBLE) {
                    System.out.println(Parameter.getDoubleValue(args[i]));
                } else if (current == Parameter.Kind.INTEGER) {
                    System.out.println(Parameter.getIntValue(args[i]));
                } else {
                    System.out.println(args[i]);
                }
            }

        }
    }
}
