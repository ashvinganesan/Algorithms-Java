//Ashvin Ganesan
//Algorithms
//HW 2b
// Tuesday september 1st
import java.awt.Color;
public class Parameters {

    private Parameter.Color color = Parameter.Color.BLACK;
    private double xmin = -1.0;
    private double xmax = +1.0;
    private double ymin = -1.0;
    private double ymax = +1.0;
    private int width = 750;
    private int height = 750;
    private int limit = 1000;
    private int errors = 0;

    public Parameters(String[] args) {
//        for(String arg: args) {
//            System.out.println(arg);
//        }
        Parameter.Kind kind;
        for (int i = 0; i < args.length; i++) {
            String first = args[i].substring(0, 1);
            if (first.equals("-")) {
                args[i] = args[i].substring(1);
            }
//            System.out.println(args[i]);
//            System.out.println("i is " + i);
            kind = Parameter.classify(args[i]);
            if (kind == Parameter.Kind.OPTION) {
                //System.out.println(args[i]);

                Parameter.Kind kinds = Parameter.kindOfValue(Parameter.getOptionValue(args[i]));
                //System.out.println(kinds);
                switch (kinds) {

                    case INTEGER:
                        try {
                            setIntValue(Parameter.getOptionValue(args[i]), args[i + 1]);
                            i++;
                        } catch (IllegalArgumentException e) {
                            //errors++;
                            System.out.println("Illegal Argument expected Integer but received " + args[i + 1]);
                        }
                        break;
                    case DOUBLE:
                        try {
                            setDoubleValue(Parameter.getOptionValue(args[i]), args[i + 1]);
                            i++;
                        } catch (IllegalArgumentException e) {
                            //errors++;
                            System.out.println("Illegal Argument expected Double but received " + args[i + 1]);
                        }
                        break;
                    case COLOR:
                        try {
                            setColorValue(Parameter.getOptionValue(args[i]), args[i + 1]);
                            i++;
                        } catch (IllegalArgumentException e) {
                            //errors++;
                            System.out.println("Illegal Argument expected Color but received " + args[i + 1]);
                        }
                        break;

                }
            } else {
                errors++;
                System.out.println("Invalid Parameter:" + args[i]);
            }

            // Use your class Parameter to classify, check validity
            // and update the values of the various parameters for
            // the Mandelbrot drawing program that we will be writing
            // for our next assignent (Homework_3).
            // If a valid value is specified for a parameter, it will
            // overwrite the default value of the parameter in the
            // instance variables above.
        }
    }

    // Private methods to set the value of an option:
    private void setIntValue(Parameter.Option option, String arg) throws IllegalArgumentException {
        int value = -1;
        try {
            value = Parameter.getIntValue(arg);
        } catch (Error e) {
            System.out.println("arg:" + arg);
        }

        switch (option) {
            case LIMIT:
                this.limit = value;
                break;

            case WIDTH:
                this.width = value;
                break;

            case HEIGHT:
                this.height = value;
                break;

            default:
                errors++;
                throw new Error("Expected Integer Did not Receive Integer");
            // An error.  Throw an exception?
        }
    }

    private void setDoubleValue(Parameter.Option option, String arg) throws IllegalArgumentException {
        double value = Parameter.getDoubleValue(arg);
        switch (option) {
            case XMIN:
                this.xmin = value;
                break;

            case XMAX:
                this.xmax = value;
                break;

            case YMIN:
                this.ymin = value;
                break;

            case YMAX:
                this.ymax = value;
                break;

            default:
                errors++;
                throw new Error("Expected Double Did not Receive Double");

            // An error.  Throw an exception?
        }
    }

    private void setColorValue(Parameter.Option option, String arg) throws IllegalArgumentException {
        Parameter.Color value = Parameter.getColorValue(arg);
        switch (option) {
            case COLOR:
                this.color = value;
                break;

            default:
                errors++;
                throw new Error("Expected Color Did not Receive Color");
        }
    }

    // Selector functions to return the values of the various parameters.
    public double xmin() {
        return this.xmin;
    }

    public double xmax() {
        return this.xmax;
    }

    public double ymin() {
        return this.ymin;
    }

    public double ymax() {
        return this.ymax;
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public int limit() {
        return this.limit;
    }

    public Parameter.Color color() {
        return this.color;
    }
    public Color color(boolean var) {
        if (this.color == Parameter.Color.RED) {
            return Color.RED;
        } else if(this.color == Parameter.Color.BLUE) {
            return Color.BLUE;
        } else if(this.color == Parameter.Color.GREEN) {
            return Color.GREEN;
        }else  {
            return Color.BLACK;
        }
    }
    public int errors() {
        return this.errors;
    }

}
