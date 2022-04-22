//Ashvin G
//Algorithms HW 13
//Monday Jan 25th 2020

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    
    private Node root;
    
    private static enum Direction {
        LEFT,
        RIGHT,
        NIL;
    }
    

    // An inner class for the nodes in the Binary Search Tree.
    // Each node contains a Key/Value pair as well as pointers
    // to the children.

    private class Node {
    
        Key   key;
        Value value;
        Node  left;
        Node  right;
        
        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
        
        public Node(Key key, Value value) {
            this(key, value, null, null);
        }
    }

    
    public BinarySearchTree() {
        this.root = null;
    }

    
    // Number of nodes in the tree.  The size of the tree is not stored
    // as part of the tree; it is computed as needed.

    private int size(Node node) {
        if (node != null) {
            return 1 + size(node.left) + size(node.right);
        } else {
            return 0;
        }
    }
    
    public int size() {
        return size(this.root);
    }

    public boolean isEmpty() {
        return this.root == null;
    }
    

    // The height of the tree: the maximum number of nodes along any path
    // from a root to a leaf.

    private int height(Node node) {
        if (node != null) {
            return 1 + Math.max(height(node.left), height(node.right));
        } else {
            return 0;
        }
    }
    
    public int height() {
        return height(this.root);
    }
    

    // Look up the value associated with a given key.  Returns null
    // if the key is not found in the binary search tree.  Note
    // that contains(key) is not the same as find(key) != null
    // since it is possible to associate a value of null with a
    // given key in the tree.

    public Value find(Key key) {
        Node rover = this.root;
        while (rover != null) {
            int compare = key.compareTo(rover.key);
            if (compare < 0) {
                rover = rover.left;
            } else if (compare > 0) {
                rover = rover.right;
            } else {
                return rover.value;
            }
        }
        return null;
    }

    public boolean contains(Key key) {
        Node rover = this.root;
        while (rover != null) {
            int compare = key.compareTo(rover.key);
            if (compare < 0) {
                rover = rover.left;
            } else if (compare > 0) {
                rover = rover.right;
            } else {
                return true;
            }
        }
        return false;
    }


    // Methods to determine the min (max) key value in the tree.
    
    public Key min() {
        if(root == null) {
            return null;
        }
        Node rover = this.root;
        while (rover.left != null) { 
            rover = rover.left;
        }
        
        return rover.key;
    }
    public Node minNode(Node n) {
        Node rover = n;
        while (rover != null) { 
            rover = rover.left;
        }
        
        return rover;
    }
    
    
    public Key max() {
        if(root.key == null) {
            return null;
        }
        Node rover = this.root;
        while (rover != null) { 
            rover = rover.right;
        }
        
        return rover.key;
    }
    public Node maxNode(Node n) {
        Node rover = n;
        while (rover != null) { 
            rover = rover.right;
        }
        
        return rover;
    }
    

    // A method to add new keys to a BST.  If the key is not in the map
    // a new node is created.  If the key is present in the map, its
    // value is updated.
    
    public void add(Key key, Value value) {
        Direction direction = Direction.NIL;
        Node rover = this.root;
        Node parent = null;
        
        while (rover != null) {
            int compare = key.compareTo(rover.key);
            if (compare < 0) {
                parent = rover;
                rover = rover.left;
                direction = Direction.LEFT;
            } else if (compare > 0) {
                parent = rover;
                rover = rover.right;
                direction = Direction.RIGHT;
            } else {
                rover.value = value;
                return;
            } 
        }
                
        Node node = new Node(key, value);
        switch (direction) {
            case LEFT:
                parent.left = node;
                break;
                
            case RIGHT:
                parent.right = node;
                break;

            case NIL:
                root = node;
        }
    }
    

    // A method to remove keys (and associated values) rom a BST.  If
    // the key is not found in the tree, this method does nothing.
    
    public void remove(Key key) {
        Node rover = this.root;
        Node toRemove = null;
        Node previous = null;
        while (rover != null) {
            int compare = key.compareTo(rover.key);
            System.out.println(compare);
            if (compare < 0) {
                previous = rover;
                rover = rover.left;
            } else if (compare > 0) {
                previous = rover;
                rover = rover.right;
            } else {
//                System.out.println("Found key" + rover.key);
                
                if(rover.left == null && rover.right == null) {
                    if(root == rover) {
                        root = null;
                        return;
                    }
//                    System.out.println("rover value is " + rover.value);
//                    System.out.println("rover value is " + previous.value);
                    if(previous.left == rover) {
                        System.out.println(previous.value);
                        previous.left = null;
                    }
                    if(previous.right == rover) {
                        System.out.println(previous.value);
                        previous.right = null;
                    }
//                    System.out.println("both children null");
                    rover.key = null;
                    rover.value = null;                                                
                    rover = null;
                    return;
                }
                
                toRemove = rover;
                if(rover.left == null) {
                    while(rover.right != null) {
                        previous = rover;
                        rover = rover.right;
                    }
//                    System.out.println("rover is "+ rover.value);
                    toRemove.key = rover.key;
                    toRemove.value = rover.value;
                    previous.right = null;
                    
                    
                    
                }
                if(rover.right == null) {
                    while(rover.left != null) {
                        previous = rover;
                        rover = rover.left;
                    }
                    toRemove = rover;
                    previous.left = null;
                }
// I decided to implement it so it always deletes one way instead of alternating.               
//                Random rd = new Random();
//                boolean rand = rd.nextBoolean();
//                if(rand == true) {
//                    rover = maxNode(rover.left);
//                } else {
//                    rover = maxNode(rover.right);
//                }
                
                
            }
            
        }
        return;
    }


    // Method(s) to display a tree.  The tree is printed using an in-order
    // traversal with one node per line.  Each node is indented according
    // to its depth in the tree.

    private void print(Key key, Value value, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        System.out.print(key);
        if (value != null) {
            System.out.print(" (" + value + ")");
        }
        System.out.println();
    }


    private void print(Node node, int depth) {
        if (node != null) {
            print(node.left, depth+1);
            print(node.key, node.value, depth);
            print(node.right, depth+1);
        }
    }

    public void print() {
        print(this.root, 0);
    }


    // ------------------------------------------------------------------

    // Support methods for parsing a command line.
    //
    //    A command line consists of a command followed by optional words
    //    (arguments) separated by spaces or tabs.  Missing arguments are
    //      considered to have the value null.

    private static String getWord(String line, int index) {
        String[] words = line.split("[ \t]+");
        if (words.length > index) {
            return words[index];
        } else {
            return null;
        }
    }

    private static String getCommand(String line) {
        // The command is the first word of a command line.
        return getWord(line, 0);
    }

    private static String getKey(String line) {
        // The key is always the second word of a command line (if present).
        return getWord(line, 1);
    }

    public static String getValue(String line) {
        // The value is always the third word of a command line (if present).
        return getWord(line, 2);
    }

    // ----------------------------------------------------------------------------------------------------  

    // This main program builds a BST from the command line arguments.  Then a command
    // interpreeter allows users to type various commands to examine and modify the
    // resulting tree.

    // Commands are:
    //
    //    size               Display the number of nodes in the tree.
    //    height             Display the height of the tree.
    //    contains <key>     Determine if the tree contains a given key.
    //    find <key>         Lookup the value associated with a given key.
    //    min                Display the minimum key value in the tree.
    //    max                Display the maximum key value in the tree.
    //    add <key> <value>  Add (or update) a key value pair in the map.
    //    remove <key>       Remove a given key from the map (if present).
    //    clear              Clear the current tree (create a new empty tree).
    //    print              Display the tree (indented format).
    //    exit               Terminate the program.


    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static String readLine(String prompt) {
        try {
            System.out.print(prompt);
            return reader.readLine();
        } catch(IOException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<String, String> tree = new BinarySearchTree();

        for (String arg : args) {
            tree.add(arg, "");
        }

        String prompt = "Command: ";
        String line = readLine(prompt);

        while (line != null) {
            String command = getCommand(line);
            if (command == null) continue;
            switch (command) {
                case "size":
                    System.out.println(tree.size());
                    break;

                case "height":
                    System.out.println(tree.height());
                    break;

                case "contains":
                    System.out.println(tree.contains(getKey(line)));
                    break;
            
                case "min":
                case "minimum":
                    System.out.println(tree.min());
                    break;

                case "max":
                case "maximum":
                    System.out.println(tree.max());
                    break;

                case "find":
                    System.out.println(tree.find(getKey(line)));
                    break;

                case "add":
                    tree.add(getKey(line), getValue(line));
                    break;

                case "remove":
                case "delete":
                    tree.remove(getKey(line));
                    break;

                case "clear":
                    tree = new BinarySearchTree();
                    break;

                case "print":
                    tree.print();
                    break;

                case "quit":
                case "exit":
                    return;

                default:
                    System.out.println("Unknown command: " + command);
                    break;
            }

            line = readLine(prompt);
        }
    }
}
