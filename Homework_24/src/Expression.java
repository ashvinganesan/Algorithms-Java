//Ashvin G
//Algorithms H
// HW 24
//friday may 14th 2021
import java.util.HashMap;
import java.util.Map;

public class Expression {

    public static class SymbolTable {
        private Map<String, Double> variables;
        public SymbolTable() {
            variables = new HashMap<>();
        }

        public void assign(String name, double value) {
            //System.out.println(name + " " + value);
            variables.put(name, value);
        }

        public double value(String name) {
            //System.out.println(name);
            return variables.get(name);
        }

    }

    protected static SymbolTable symbols = new SymbolTable();


    public static abstract class Node {
        public abstract double evaluate();
        public abstract String format();
    }

    public static abstract class Leaf extends Node {
    }

    public static class Literal extends Leaf {
        private double value;
        
        public Literal(Double value) {
            this.value = value;
        }
        @Override
        public double evaluate() {
            return this.value;
        }

        @Override
        public String format() {
            return "" + this.value;
        }
    }

    public static class Variable extends Leaf {
        private String name;

        public Variable(String name) {
            this.name = name;
        }

        @Override
        public double evaluate() {
            //System.out.println(this.name);
            return symbols.value(this.name);
        }

        @Override
        public String format() {
            return this.name;
        }
    }


    public static abstract class Binary extends Node {

        private Node left;
        private Node right;

        public Binary(Node left, Node right) {
            this.left = left;
            this.right = right;
        }

        public abstract double eval(double left, double right);
        public abstract String operator();

        @Override
        public double evaluate() {
            double left = this.left.evaluate();
//            System.out.println(left);
//            System.out.println(right);
            double right = this.right.evaluate();
            return this.eval(left, right);
        }

        @Override
        public String format() {
            // TO DO: precedence and parenthetization.
            //I made it so format will always have one set of extra paranthesis (at the end and beginning) but it is ok I think
            //Everything else should only really be essential paranthesis :P 
            String left = this.left.format();
            String right = this.right.format();
            if(this.operator().equals(" + ") || this.operator().equals(" - ")) {
                if(left.substring(0,1).equals("(")) {
                    left = left.substring(1,left.length()-1);
                }
                if(right.substring(0,1).equals("(")) {
                    right = right.substring(1,right.length()-1);
                }
                //return "("+left + this.operator() + right +")";
            }
//            return left + this.operator() + right;
            return "("+left + this.operator() + right +")";

        }
    }


    public static class Add extends Binary {

        public Add(Node left, Node right) {
            super(left, right);
        }

        public double eval(double left, double right) {
            return left + right;
        }

        public String operator() {
            return " + ";
        }
    }
    public static class Subtract extends Binary {

        public Subtract(Node left, Node right) {
            super(left, right);
        }

        public double eval(double left, double right) {
            return left - right;
        }

        public String operator() {
            return " - ";
        }
    }
    public static class Multiply extends Binary {

        public Multiply(Node left, Node right) {
            super(left, right);
        }

        public double eval(double left, double right) {
            return left * right;
        }

        public String operator() {
            return " * ";
        }
    }
    public static class Divide extends Binary {

        public Divide(Node left, Node right) {
            super(left, right);
        }

        public double eval(double left, double right) {
            return left / right;
        }

        public String operator() {
            return " / ";
        }
    }
    public static abstract class Assignment extends Node {

        private Node left;
        private Node right;

        public Assignment(Node left, Node right) {
            this.left = left;
            this.right = right;
        }

        public abstract double eval(Node left, double right);
        public abstract String operator();

        @Override
        public double evaluate() {   
            double right = this.right.evaluate();
            return this.eval(this.left, right);
        }

        @Override
        public String format() {
            // TO DO: precedence and parenthetization.
            String left = this.left.format();
            String right = this.right.format();
            return left + this.operator() + right;
        }
    }
    public static class Assign extends Assignment {

        public Assign(Node left, Node right) {
            super(left, right);
            eval(left, right.evaluate());
        }

        public double eval(Node left, double right) {
            symbols.assign(left.format(), right);
            return right;
        }

        public String operator() {
            return " = ";
        }
    }
    
    public static class PlusEquals extends Assignment {

        public PlusEquals(Node left, Node right) {
            super(left, right);
            eval(left, right.evaluate());
        }

        public double eval(Node left, double right) {
            symbols.assign(left.format(), symbols.value(left.format()) + right);
            return right;
        }

        public String operator() {
            return " += ";
        }
    }
    public static class MinusEquals extends Assignment {

        public MinusEquals(Node left, Node right) {
            super(left, right);
            eval(left, right.evaluate());
        }

        public double eval(Node left, double right) {
            symbols.assign(left.format(), symbols.value(left.format()) - right);
            return right;
        }

        public String operator() {
            return " -= ";
        }
    }
    public static class TimesEquals extends Assignment {

        public TimesEquals(Node left, Node right) {
            super(left, right);
            eval(left, right.evaluate());
        }

        public double eval(Node left, double right) {
            symbols.assign(left.format(), symbols.value(left.format()) * right);
            return right;
        }

        public String operator() {
            return " *= ";
        }
    }
    public static class DivideEquals extends Assignment {

        public DivideEquals(Node left, Node right) {
            super(left, right);
            eval(left, right.evaluate());
        }

        public double eval(Node left, double right) {
            symbols.assign(left.format(), symbols.value(left.format()) / right);
            return right;
        }

        public String operator() {
            return " /= ";
        }
    }
    
    public static abstract class Unary extends Node {

        private Node left;

        public Unary(Node left) {
            this.left = left;
        }

        public abstract double eval(double left);
        public abstract String operator();

        @Override
        public double evaluate() {
            double left = this.left.evaluate();
            return this.eval(left);
        }

        @Override
        public String format() {
            // TO DO: precedence and parenthetization.
            String left = this.left.format();
            if(this.operator().equals(" abs ")) {
                return "|"+left+"|";
            }
            return this.operator() + left;
        }
    }  
    public static class Negate extends Unary {

        public Negate(Node left) {
            super(left);
        }

        public double eval(double left) {
            return left * (-1);
        }

        public String operator() {
            return "-";
        }
    }
    public static class Abs extends Unary {

        public Abs(Node left) {
            super(left);
        }

        public double eval(double left) {
            if(left < 0) {
                return left * (-1);
            }
            return left;
        }

        public String operator() {
            return " abs ";
        }
    }
    public static abstract class UnaryVar extends Node {

        private Node left;

        public UnaryVar(Node left) {
            this.left = left;
        }

        public abstract double eval(Node left);
        public abstract String operator();
        public abstract boolean isPre();
        @Override
        public double evaluate() {

            return this.eval(left);
        }

        @Override
        public String format() {
            // TO DO: precedence and parenthetization.
            String left = this.left.format();
            if(this.isPre()) {
                return this.operator() + left;
            }
            return left + this.operator();
        }
    }
    public static class PreIncrement extends UnaryVar {

        public PreIncrement(Variable left) {

            super(left);
            
        }

        public double eval(Node left) {
            symbols.assign(left.format(), left.evaluate()+1);

            return left.evaluate();
        }
        public boolean isPre() {
            return true;
        }
        
        public String operator() {
            return " ++";
        }
        
    }
    public static class PreDecrement extends UnaryVar {

        public PreDecrement(Variable left) {

            super(left);
            
        }
        public boolean isPre() {
            return true;
        }

        public double eval(Node left) {
            symbols.assign(left.format(), left.evaluate()-1);
            return left.evaluate();
        }

        public String operator() {
            return " --";
        }
    }
    public static class PostIncrement extends UnaryVar {

        public PostIncrement(Variable left) {

            super(left);
            
        }

        public double eval(Node left) {
            symbols.assign(left.format(), left.evaluate()+1);
            return left.evaluate()-1;
        }
        public boolean isPre() {
            return false;
        }

        public String operator() {
            return "++ ";
        }
    }
    public static class PostDecrement extends UnaryVar {

        public PostDecrement(Variable left) {

            super(left);
            
        }
        public boolean isPre() {
            return false;
        }
        public double eval(Node left) {
            symbols.assign(left.format(), left.evaluate()-1);
            return left.evaluate()+1;
        }

        public String operator() {
            return "-- ";
        }
    }
    
}
