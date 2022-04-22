
import java.util.HashMap;
import java.util.Map;

/*
Ashvin Ganesan
Homework 27
Wednesday, June 2nd, 2021
 */
public class Parser {

    public class Node {

        Node left;
        Node right;
        Token token;
        Node parent;

        public Node(Token t) {
            token = t;
        }
    }

    public static class SyntaxError extends Exception {

        public SyntaxError(Token token) {
            super("Syntax error at position " + token.position());
        }
        
    } 
    public static class UndefinedVariableException extends Exception {

        public UndefinedVariableException(String name) {
            super("Undefined variable: " + name);
        }
        
    }
    public static class SymbolTable  {

        private Map<String, Double> variables;

        public SymbolTable() {
            variables = new HashMap<>();
        }

        public void assign(String name, double value) {
            //System.out.println(name + " " + value);
            variables.put(name, value);
        }

        public double value(String name) throws UndefinedVariableException {
            //System.out.println(name);
            if(variables.containsKey(name) != true) {
                throw new UndefinedVariableException(name);
            }
            return variables.get(name);
        }

    }

    protected static SymbolTable symbols = new SymbolTable();

    private Scanner scanner;
    private Token currentToken;

    private void checkToken(Token.Kind kind) throws SyntaxError {
        if (this.currentToken.kind() != kind) {
            //System.out.println("CT throwing syntax error...");
            throw new SyntaxError(currentToken);
        } else {
            currentToken = scanner.nextToken();
        }
    }

    private Node parseExpression() throws SyntaxError {
        Node term = parseTerm();
        Token assign = currentToken;
        if (currentToken.isAssignmentOperator()) {
            Token token = currentToken;
            currentToken = scanner.nextToken();

            Node right = parseExpression();
            Node root = new Node(token);
            root.left = term;
            term.parent = root;
            root.right = right;
            right.parent = root;
            return root;
        } else {
            return term;
        }

    }

    private Node parseTerm() throws SyntaxError {
        Node factor = parseFactor();

        if (currentToken.isAddingOperator()) {
//            System.out.println("IN parseterm");
            Token t = currentToken;
            
            currentToken = scanner.nextToken();
            
            Node right = parseTerm();
            Node root = new Node(t);
            root.left = factor;
            factor.parent = root;
            root.right = right;
            right.parent = root;
            return root;
        } else {
            return factor;
        }

    }

    private Node parseFactor() throws SyntaxError {
        Node primary = parsePrimary();

        if (currentToken.isMultiplyingOperator()) {
//            System.out.println("IN parsefactor");
            Token operator = currentToken;
            Node root = new Node(operator);

            currentToken = scanner.nextToken();
            Node right = parseFactor();

            root.left = primary;
            root.right = right;
            primary.parent = right.parent = root;
            return root;
        } else {
            return primary;
        }

    }

    private Node parsePrimary() throws SyntaxError {
        //System.out.println("Parse primary is being called: " + currentToken.image());
        Token operator;
        Node n;
        switch (currentToken.kind()) {
            case NUMBER:
            case NUMBER_DOUBLE:
                //System.out.println("case number");
                Token number = currentToken;
                //System.out.println("number's token is " + number.image());
                currentToken = scanner.nextToken();
                //System.out.println("PP Next token is: " + currentToken.image());
                return new Node(number);
            case IDENTIFIER:
                Token identifier = currentToken;
                currentToken = scanner.nextToken();
                n = new Node(identifier);
                if (currentToken.kind() == Token.Kind.PLUS_PLUS || currentToken.kind() == Token.Kind.MINUS_MINUS) {
                    //THIS IS FOR POST
                    //POST WILL ALWAYS BE RIGHT CHILD WHEREAS PRE = LEFT CHILD
                    Node right = new Node(currentToken);
                    n.right = right;
                    right.parent = n;
                    currentToken = scanner.nextToken();
                }
                return n;
            case PLUS:
            case MINUS:
                operator = currentToken;
                currentToken = scanner.nextToken();
                n = parsePrimary();
                Node current = new Node(currentToken);
                n.left = current;
                current.parent = n;
                return n;

            case LEFT_PAREN:
                currentToken = scanner.nextToken();
                Node node = parseExpression();
                checkToken(Token.Kind.RIGHT_PAREN);
                return node;
                
            case PLUS_PLUS:
            case MINUS_MINUS:
                operator = currentToken;
                currentToken = scanner.nextToken();
                Node var = new Node(currentToken);
                Node left = new Node(operator);
                var.left = left;
                left.parent = var;
                currentToken = scanner.nextToken();
                return var;
            default:
                throw new SyntaxError(currentToken);
        }
    }

    public double evalTree(Node root) throws UndefinedVariableException {
        //System.out.println("ET called: " + root.token.image());
        if ((root.left == null) && (root.right == null)) {

            if (root.token.kind() == Token.Kind.IDENTIFIER) {
                //System.out.println("root token image" + root.token.image());              
                return symbols.value(root.token.image());                   
            }

            return ScanDouble.parseDouble(root.token.image());
        }
        if (root.right != null) {
            if (root.right.token.kind() == Token.Kind.PLUS_PLUS) {
                symbols.assign(root.token.image(), symbols.value(root.token.image()) + 1);
                return symbols.value(root.token.image()) - 1;
            }
            if (root.right.token.kind() == Token.Kind.MINUS_MINUS) {
                symbols.assign(root.token.image(), symbols.value(root.token.image()) - 1);
                return symbols.value(root.token.image()) + 1;
            }
        }
        if (root.left != null) {
            if (root.left.token.kind() == Token.Kind.PLUS_PLUS) {
                symbols.assign(root.token.image(), symbols.value(root.token.image()) + 1);
                return symbols.value(root.token.image());
            }
            if (root.left.token.kind() == Token.Kind.MINUS_MINUS) {
                symbols.assign(root.token.image(), symbols.value(root.token.image()) - 1);
                return symbols.value(root.token.image());
            }
        }

        double left;
        double right;
//            System.out.println("here");
//            System.out.println("left: " + left);
//            System.out.println("right: " + right);
        switch (root.token.kind()) {
            case IDENTIFIER:
//                    System.out.println("root " + root.token.image());
//                    System.out.println("left " + root.left.token.image());
                if (root.left.token.kind() == Token.Kind.PLUS_PLUS) {
                    symbols.assign(root.token.image(), symbols.value(root.token.image()) + 1);
                    return symbols.value(root.token.image());
                } else if (root.left.token.kind() == Token.Kind.MINUS_MINUS) {
                    symbols.assign(root.token.image(), symbols.value(root.token.image()) - 1);
                    return symbols.value(root.token.image());
                }
                break;
            case PLUS:
                left = evalTree(root.left);
                right = evalTree(root.right);
                return left + right;
            case MINUS:
                left = evalTree(root.left);
                right = evalTree(root.right);
                return left - right;
            case STAR:
                left = evalTree(root.left);
                right = evalTree(root.right);
                return left * right;
            case SLASH:
                left = evalTree(root.left);
                right = evalTree(root.right);
                return left / right;
            case EQUAL:
                right = evalTree(root.right);
                symbols.assign(root.left.token.image(), right);
                return right;
//                case PLUS_PLUS:
//                    right = evalTree(root.right);
//                    System.out.println("IN PLUS PLUS");
//                    symbols.assign(root.left.token.image(), symbols.value(root.left.token.image()) + 1);
//                    return symbols.value(root.left.token.image());
//                case MINUS_MINUS:
//                    right = evalTree(root.right);
//                    symbols.assign(root.left.token.image(), symbols.value(root.left.token.image()) - 1);
//                    return symbols.value(root.left.token.image());
            case PLUS_EQUAL:
                right = evalTree(root.right);
                symbols.assign(root.left.token.image(), symbols.value(root.left.token.image()) + right);
                return symbols.value(root.left.token.image());
            case MINUS_EQUAL:
                right = evalTree(root.right);
                symbols.assign(root.left.token.image(), symbols.value(root.left.token.image()) - right);
                return symbols.value(root.left.token.image());
            case STAR_EQUAL:
                right = evalTree(root.right);
                symbols.assign(root.left.token.image(), symbols.value(root.left.token.image()) * right);
                return symbols.value(root.left.token.image());
            case SLASH_EQUAL:
                right = evalTree(root.right);
                symbols.assign(root.left.token.image(), symbols.value(root.left.token.image()) / right);
                return symbols.value(root.left.token.image());

        }
        System.out.println("something went very wrong :(");
        return 0.0;
    }

    public Node parse(String s) throws SyntaxError {
        this.scanner = new Scanner(s);
        this.currentToken = scanner.nextToken();
        Node root = parseExpression();
        //System.out.println("HERE");
        checkToken(Token.Kind.END);
        return root;
    }

    public static void main(String[] args) {
        Parser parser = new Parser();

        for (String s : args) {
//            boolean padding = false;
//            if(!(s.charAt(s.lastIndexOf(s)) == ' ')) {
//                s = s+" ";
//                padding = true;
//            }
// this code fixes the issue of the count getting off by 1 if you take the last input of a string, without ending in a space. 
// If this is here it will be 8 for the last line of the syntax, but otherwise it ends up at 9
// I wrote it but removed it because changing the input = :(
            try {
                Node root = parser.parse(s);
                try {
                double val = parser.evalTree(root); 
                System.out.println(s + ": " + val);
                
                } catch (UndefinedVariableException e) {
//                    if(padding) {
//                        System.out.println(s.substring(0, s.lastIndexOf(s)-1)+ ": " + e.getMessage());
//                    } else {
                        System.out.println(s + ": " + e.getMessage());
                    //}
                }
            } catch (SyntaxError e) {
                System.out.println(s + ": " + e.getMessage());
            }
        }
    }
}
