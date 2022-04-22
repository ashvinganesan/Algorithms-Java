//Ashvin Ganesan
//Algorithms
// Homework # 2a
//august 27th 2020. 



import java.awt.Color;
public class Parameter {

    // A template for the class representing the different kinds of command
    // line parameters that the Mandelbrot Set drawing program will accept.

    // Declare (static) enumeration types for
    //
    //     Kind:   The types of parameters that may appear on the command line:
    //     Option: The various valid options (e.g., xmin, xmax, ...)
    //     Colors: The valid values for the -color option (e.g., red, green, black, ...)

    public static enum Kind {
        INTEGER, DOUBLE, COLOR, OPTION, GIBBERISH
    }

    public static enum Option {
        XMIN, XMAX, YMIN, YMAX, WIDTH, HEIGHT, LIMIT, COLOR
    }
    
    public static enum Color {
        RED, BLUE, GREEN, BLACK;
    }

        

    
    public static Parameter.Kind classify(String parameter) {
        String upper = parameter.toUpperCase();
        try {
            Integer.parseInt(upper);
            return Kind.INTEGER;
        } catch(NumberFormatException ex) {
            //System.out.println("not an int");
        }
        try {
            Double.parseDouble(upper);
            return Kind.DOUBLE;
        } catch(NumberFormatException ex) {
            //System.out.println("not a double");
        }
        try {
            Color.valueOf(upper);
            return Kind.COLOR;
        } catch(IllegalArgumentException ex) {
            //System.out.println("not a color");
        }
        try {
            Option.valueOf(upper);
            return Kind.OPTION;
        } catch(IllegalArgumentException ex) {//Check if this is right later
            //System.out.println("not an option");
        }
        return Kind.GIBBERISH;
        //
        // A method to classify a single command line argument as
        //
        //    an integer
        //    a double
        //    an option
        //    a color name
        //    jibberish (anything else)
        //
        // The valid options are
        //
        //    -xmin
        //    -xmax
        //    -ymin
        //    -ymax
        //    -width
        //    -height
        //    -limit
        //    -color
        //
        // The valid colors must include (you may add others if you want)
        //
        //    red
        //    blue
        //    green
        //    black
        //
        // Implement using try/catch blocks.  Use the enumeration type's valueOf
        // method to determine if a string is a valid value of the enum type (don't
        // use a bunch of string comparisons (or a switch statement)).
    }


    public static Parameter.Kind kindOfValue(Option option) {
         switch (option) {
            case XMIN:
                return Kind.DOUBLE;
            case XMAX:
                return Kind.DOUBLE;
            case YMIN:
                return Kind.DOUBLE;
            case YMAX:
                return Kind.DOUBLE;
            case WIDTH:
                return Kind.INTEGER;
            case HEIGHT:
                return Kind.INTEGER;
            case LIMIT:
                return Kind.INTEGER;
            case COLOR:
                return Kind.COLOR;
            
        }
        return Kind.COLOR;
        //
        // A method that returns what kind of argument each option expects
        //
        //    -xmin:    double
        //    -xmax:    double
        //    -ymin:    double
        //    -ymax:    double
        //    -width:   integer
        //    -height:  integer
        //    -limit:   integer
        //    -color:   color
        //
        // Implement using a switch statement.
    }


    public static int getIntValue(String parameter) {
        // Return the value of the parameter as an integer.
        return Integer.parseInt(parameter.toUpperCase());
    }

    public static double getDoubleValue(String parameter) {
        // Return the value of this parameter as a double.
        return Double.parseDouble(parameter);
    }

    public static Parameter.Option getOptionValue(String parameter) {
        // Return the value of this parameter as a value of the enum Parameter.Option.
        // DO NOT use string comparisons or switch statements.
        // This method should be a single line using enum methods.
       // System.out.println(parameter);
        return Option.valueOf(parameter.toUpperCase());
    }

    public static Parameter.Color getColorValue(String parameter) {
        // Return the value of this parameter as a value of the enum Parameter.Color.
        // DO NOT use string comparisons or switch statements.
        // This method should be a single line using enum methods.
        return Color.valueOf(parameter.toUpperCase());
    }
}
