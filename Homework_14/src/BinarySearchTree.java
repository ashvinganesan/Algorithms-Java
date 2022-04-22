//Ashvin Ganesan
//Algorithms HW 15
//monday february 1st 2020

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private Node root;

    private static enum Direction {
        LEFT,
        RIGHT,
        NIL;
    }

    private static class Found extends Exception {

        public Found() {
            super();
        }
    }

    private static class NotFound extends Exception {

        public NotFound() {
            super();
        }
    }

    // An inner class for the nodes in the Binary Search Tree.
    // Each node contains a Key/Value pair as well as pointers
    // to the children.
    private class Node {

        Key key;
        Value value;
        Node left;
        Node right;
        int size;

        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.size = 1;
        }

        public Node(Key key, Value value) {
            this(key, value, null, null);
        }
    }

    public BinarySearchTree() {
        this.root = null;
    }

    private int size(Node node) {
        return (node != null) ? node.size : 0;
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
    
    public void set(Key key, Value v) {
        Node rover = this.root;
        while (rover != null) {
            int compare = key.compareTo(rover.key);
            if (compare < 0) {
                rover = rover.left;
            } else if (compare > 0) {
                rover = rover.right;
            } else {
                rover.value = v;
                return;
            }
        }
        return;
    }

    public Node findVal(Key key) {
        Node rover = this.root;
        while (rover != null) {
            int compare = key.compareTo(rover.key);
            if (compare < 0) {
                rover = rover.left;
            } else if (compare > 0) {
                rover = rover.right;
            } else {
                return rover;
            }
        }
        return null;
    }

    // The floor function finds the largest key in the tree that is
    // no greater than a specified key value.  The ceiling function
    // finds the smallest key in the tree that is no less than a
    // specified key.  For example, in a tree containing
    //
    //    { "ant", "cat", "dog", "pig" }
    //
    // floor("cat") = "cat"
    // floor("cow") = "cat"
    // ceiling("dog") = "dog"
    // ceiling("cow" = "dog"
    public Key floor(Key key) {
        Key result = null;
        Node rover = this.root;
        while (rover != null) {
            int compare = key.compareTo(rover.key);
            if (compare < 0) {
                rover = rover.left;
            } else if (compare > 0) {
                result = rover.key;
                rover = rover.right;
            } else {
                return rover.key;
            }
        }
        return result;
    }

    public Key ceiling(Key key) {
        Key result = null;
        Node rover = this.root;
        while (rover != null) {
            int compare = key.compareTo(rover.key);
            if (compare < 0) {
                result = rover.key;
                rover = rover.left;
            } else if (compare > 0) {
                rover = rover.right;
            } else {
                return rover.key;
            }
        }
        return result;
    }

    // Methods to determine the min (max) key value in the tree.
    public Key min() {
        if (root == null) {
            return null;
        }
        Node rover = this.root;
        while (rover.left != null) {
            rover = rover.left;
        }
        return rover.key;
    }

    public Key max() {
        if (root == null) {
            return null;
        }
        Node rover = this.root;
        while (rover.right != null) {
            rover = rover.right;
        }
        return rover.key;
    }

    // A method to add new keys to a BST.  If the key is not in the map
    // a new node is created.  If the key is present in the map, its
    // value is updated.  WARNING: Does NOT update the size field.
    public void iterativeAdd(Key key, Value value) {
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

    // An alternative implementation of add.  This one is recursive.
    private Node add(Node node, Key key, Value value) throws Found {
        if (node == null) {
            return new Node(key, value);
        } else {
            int compare = key.compareTo(node.key);
            if (compare < 0) {
                node.left = add(node.left, key, value);
                node.size++;
            } else if (compare > 0) {
                node.right = add(node.right, key, value);
                node.size++;
            } else {
                node.value = value;
                throw new Found();
            }
            return node;
        }
    }

    public void add(Key key, Value value) {
        try {
            this.root = add(this.root, key, value);
        } catch (Found e) {
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
            //System.out.println(compare);
            
            if (compare < 0) {
                rover.size -= 1;
                previous = rover;
                rover = rover.left;
            } else if (compare > 0) {
                rover.size -= 1;
                previous = rover;
                rover = rover.right;
            } else {
                //System.out.println("Found key" + rover.key);

                if (rover.left == null && rover.right == null) {
                    if (root == rover) {
                        root = null;
                        return;
                    }
//                    System.out.println("rover value is " + rover.value);
//                    System.out.println("rover value is " + previous.value);
                    if (previous.left == rover) {
                        //System.out.println(previous.value);
                        previous.left = null;
                    }
                    if (previous.right == rover) {
                        //System.out.println(previous.value);
                        previous.right = null;
                    }
//                    System.out.println("both children null");
                    previous.size = previous.size- 1;
                    rover.key = null;
                    rover.value = null;
                    rover = null;
                    return;
                }
                if(rover.left == null) {
                    if(previous == null) {
                        root = rover.right;
                        return;
                    }
                    previous.right = rover.right;
                    return;
                }
                if(rover.right == null) {
                    if(previous == null) {
                        root = rover.left;
                        return;
                    }
                    previous.left = rover.left;
                    return;
                }

                toRemove = rover;
                if (rover.left != null) {
                    previous = rover;
                    rover = rover.left;
                    while (rover.right != null) { 
                        previous = rover;
                        rover = rover.right;
                    }
//                    System.out.println("rover is "+ rover.value);
                    toRemove.key = rover.key;
                    toRemove.value = rover.value;
                    toRemove.size--;

                    previous.right = null;
                    return;

                }
                if (rover.right != null) {
                    
                    while (rover.left != null) {
                        previous = rover;
                        rover = rover.left;
                    }
                    toRemove.key = rover.key;
                    toRemove.value = rover.value;
                    toRemove.size--;
//                    System.out.println(previous.value);
//                    System.out.println(toRemove.value);
                    previous.left = null;
                    return;
                }
                System.out.println("SOMETHING WENT WRONG");
                return;
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

    // A method to find the rank (0-based) of a given key in the tree.
    // Returns -1 if the key is not found in the tree.
    public int rank(Key key) {
        if(!contains(key)) return -1;
        
        return rank(key, root);
    }

    private int rank(Key k, Node node) {
        if (node == null) {
           // System.out.println("null node");
            return -1;
        }
//        System.out.println("key "+k);
//        System.out.println("node "+node.key);
//        System.out.println(size(node.left));
        int compare = k.compareTo(node.key);

        if (compare < 0) {
            return rank(k, node.left);
        } else if (compare > 0) {
            
            return size(node.left) + rank(k, node.right) + 1;
        } else {
            return size(node.left);
        }

    }

    public Key select(int rank) {
        // !!! See solutions to Homework #14 !!!
        if (rank >= size()) {
            return null;
        }
        return select(root, rank); // No node with that rank in the tree.
    }

    private Key select(Node n, int r) {
        if (n == null) {
            return null;
        }
        if (size(n.left) > r) {
            return select(n.left, r);
        } else if (size(n.left) < r) {
            return select(n.right, r - size(n.left) - 1);
        } else {
            return n.key;
        } 
    }

    // Passive Iterators.
    //
    // Each of these iterators will traverse (walk) the entire tree
    // and invoke the visit method passed on each node as it is
    // visited during the traversal of the tree.
    public static interface Visit<Key, Value> {

        public void visit(Key key, Value value);
    }

    public void preOrderTraversal(Visit<Key, Value> visit) {
        preOrderTraversal(root, visit);
    }

    private void preOrderTraversal(Node node, Visit<Key, Value> visit) {
        if (node != null) {
            visit.visit(node.key, node.value);
            preOrderTraversal(node.left, visit);
            preOrderTraversal(node.right, visit);
        }
    }

    public void inOrderTraversal(Visit<Key, Value> visit) {
        inOrderTraversal(root, visit);
    }

    private void inOrderTraversal(Node node, Visit<Key, Value> visit) {
        if (node != null) {
            inOrderTraversal(node.left, visit);
            visit.visit(node.key, node.value);
            inOrderTraversal(node.right, visit);
        }
    }

    public void postOrderTraversal(Visit<Key, Value> visit) {
        postOrderTraversal(root, visit);
    }

    private void postOrderTraversal(Node node, Visit<Key, Value> visit) {
        if (node != null) {
            postOrderTraversal(node.left, visit);
            postOrderTraversal(node.right, visit);
            visit.visit(node.key, node.value);
        }
    }

    // ------------------------------------------------------------------
    //
    // All the code below this line is part of the command processor that
    // allows the user to build a tree interactively and then perform the
    // various operations above on the tree.
    // Method(s) to display a tree.  The tree is printed using an in-order
    // traversal with one node per line.  Each node is indented according
    // to its depth in the tree.
    private void print(Key key, Value value, int size, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        System.out.print(key);
        if (value != null) {
            System.out.print(" (" + value + ")");
        }
        System.out.println(" [size = " + size + "]");
    }

    private void print(Node node, int depth) {
        if (node != null) {
            print(node.left, depth + 1);
            print(node.key, node.value, node.size, depth);
            print(node.right, depth + 1);
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
    //    find <key>         Lookup the value associated with a given key.
    //    contains <key>     Determine if the tree contains a given key.
    //    floor <key>        Find the largest key no greater than the specified key.
    //    ceiling <key>      Find the smallest key no less than the specivied key.
    //    min                Display the minimum key value in the tree.
    //    max                Display the maximum key value in the tree.
    //    add <key> <value>  Add (or update) a key value pair in the map.
    //    remove <key>       Remove a given key from the map (if present).
    //    rank <key>         Prints the rank of the node with the given key.
    //    select #           Prints the key of the node with the given rank.
    //    clear              Clear the current tree (create a new empty tree).
    //    print              Display the tree (indented format).
    //    inorder            List the nodes in in-order.
    //    preorder           List the nodes in pre-order.
    //    postorder          List the nodes in post-order.
    //    exit               Terminate the program.
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static String readLine(String prompt) {
        try {
            System.out.print(prompt);
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

//    public static void main(String[] args) {
//        BinarySearchTree<String, String> tree = new BinarySearchTree();
//
//        for (String arg : args) {
//            tree.add(arg, "");
//        }
//
//        String prompt = "Command: ";
//        String line = readLine(prompt);
//
//        while (line != null) {
//            String command = getCommand(line);
//            if (command == null) {
//                continue;
//            }
//            switch (command) {
//                case "size":
//                    System.out.println(tree.size());
//                    break;
//
//                case "height":
//                    System.out.println(tree.height());
//                    break;
//
//                case "contains":
//                    System.out.println(tree.contains(getKey(line)));
//                    break;
//
//                case "min":
//                case "minimum":
//                    System.out.println(tree.min());
//                    break;
//
//                case "max":
//                case "maximum":
//                    System.out.println(tree.max());
//                    break;
//
//                case "find":
//                    System.out.println(tree.find(getKey(line)));
//                    break;
//
//                case "floor":
//                    System.out.println(tree.floor(getKey(line)));
//                    break;
//
//                case "ceiling":
//                    System.out.println(tree.ceiling(getKey(line)));
//                    break;
//
//                case "add":
//                    tree.add(getKey(line), getValue(line));
//                    break;
//
//                case "remove":
//                case "delete":
//                    tree.remove(getKey(line));
//                    break;
//
//                case "rank":
//                    System.out.println(tree.rank(getKey(line)));
//                    break;
//
//                case "select":
//                    try {
//                        int rank = Integer.parseInt(getWord(line, 1));
//                        System.out.println(tree.select(rank));
//                    } catch (NumberFormatException e) {
//                        System.out.println("Invalid rank: " + getWord(line, 1));
//                    }
//                    break;
//
//                case "clear":
//                    tree = new BinarySearchTree();
//                    break;
//
//                case "print":
//                    tree.print();
//                    break;
//
//                case "pre":
//                case "preorder":
//                    tree.preOrderTraversal(
//                            new BinarySearchTree.Visit<String, String>() {
//                        @Override
//                        public void visit(String key, String value) {
//                            System.out.println(key + " (" + value + ")");
//                        }
//                    });
//                    break;
//
//                case "post":
//                case "postorder":
//                    tree.postOrderTraversal(
//                            new BinarySearchTree.Visit<String, String>() {
//                        @Override
//                        public void visit(String key, String value) {
//                            System.out.println(key + " (" + value + ")");
//                        }
//                    });
//                    break;
//
//                case "in":
//                case "inorder":
//                    tree.inOrderTraversal(
//                            new BinarySearchTree.Visit<String, String>() {
//                        @Override
//                        public void visit(String key, String value) {
//                            System.out.println(key + " (" + value + ")");
//                        }
//                    });
//                    break;
//
//                case "quit":
//                case "exit":
//                    return;
//
//                default:
//                    System.out.println("Unknown command: " + command);
//                    break;
//            }
//
//            line = readLine(prompt);
//        }
//    }
}
