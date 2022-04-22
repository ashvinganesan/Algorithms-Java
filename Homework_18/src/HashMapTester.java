import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HashMapTester {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static String getWord(String line, int index) {
        // Return the ith word of a command line.
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

    private static String readLine(String prompt) {
        try {
            System.out.print(prompt);
            return reader.readLine();
        } catch(IOException e) {
            return null;
        }
    }


    public static void main(String[] args) {
        LinearProbingHashMap<String,String> map = new LinearProbingHashMap<>();

        loop: do {
            String line = readLine("Command: ");
            String command = getCommand(line);
            switch (command.toLowerCase()) {
                case "size":
                    System.out.println(map.size());
                    break;

                case "capacity":
                    System.out.println(map.capacity());
                    break;

                case "load":
                    double capacity = map.capacity();    
                    double size = map.size();
                    System.out.println(size / capacity);
                    break;

                case "hash":
                    // You may wish to have this command print the slot #:
                    // System.out.println(map.hash(getKey(line)));
                    System.out.println(getKey(line).hashCode());
                    break;

                case "contains":
                    System.out.println(map.contains(getKey(line)));
                    break;

                case "find":
                    System.out.println(map.find(getKey(line)));
                    break;

                case "add":
                    map.add(getKey(line), getValue(line));
                    break;

                case "delete":
                case "remove":
                    map.remove(getKey(line));
                    break;

                case "clear":
                    map = new LinearProbingHashMap();
                    break;

                case "keys":
                    LinearProbingHashMap.Node[] n = map.nodes();
                    for (LinearProbingHashMap.Node node : n) {
                        System.out.println(node.key);
                    }
                    break;

                case "values":
                    LinearProbingHashMap.Node[] m = map.nodes();
                    for (LinearProbingHashMap.Node node : m) {
                        System.out.println(node.value);
                    }
                    break;

                case "print":
                    LinearProbingHashMap.Node[] j = map.nodes();
                    for (LinearProbingHashMap.Node node : j) {
                        System.out.print(node.key);
                        System.out.println(" " + node.value);
                    }
                    break;

                case "help":
                    System.out.println("size             Prints the current size of the map");
                    System.out.println("capacity         Prints the current capacity of the map");
                    System.out.println("load             Prints the load factor (size/capacity)");
                    System.out.println("contains <key>   Does the map contain the specified key?");
                    System.out.println("find <key>       Find the value associated with the specified key");
                    System.out.println("add <key> <val>  Add a key/value pair to the map");
                    System.out.println("delete <key>     Remove a specified key from the map");
                    System.out.println("remove           Synonym for remove");
                    System.out.println("clear            Remove all entries from the amp");
                    System.out.println("keys             Display the keys in the map");
                    System.out.println("values           Display the contents of the map (key/value pairs)");
                    System.out.println("print            Dump the contents of the table (debugging)");
                    System.out.println("help             Prints this help message");
                    System.out.println("exit             Exit the program");
                    System.out.println("quit             Synonym for exit");
                    System.out.println();
                    break;

                case "quit":
                case "exit":
                    break loop;

                default:
                    System.out.println("Invalid command: " + command);
                    break;

            }
        } while (true);
    }
}
