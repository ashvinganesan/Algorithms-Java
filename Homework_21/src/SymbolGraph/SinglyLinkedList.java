import java.io.Console;
import java.util.Iterator;

public class SinglyLinkedList<T> implements List<T> {

    private Node head;
    private Node tail;
    private int size;

    public class Node {
    
        private T item;
        private Node next;

        private Node(T item) {
            this.item = item;
            this.next = null;
        }
    }

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public SinglyLinkedList(T item) {
        this();
        this.append(item);
    }

    public SinglyLinkedList(T[] items) {
        this();
        for (T item : items) {
            this.append(item);
        }
    }

    public SinglyLinkedList(List<T> other) {
        this();
        for (T item : other) {
            this.append(item);
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean contains(T item) {
        Node rover = this.head;
        while (rover != null) {
            if (rover.item.equals(item)) return true;
            rover = rover.next;
        }
        return false;
    }

    public int occurrences(T item) {
        int count = 0;
        Node rover = this.head;
        while (rover != null) {
            if (rover.item.equals(item)) count++;
            rover = rover.next;
        }
        return count;
    }

    public Node locate(T item) {
        Node rover = this.head;
        while (rover != null) {
            if (rover.item.equals(item)) return rover;
            rover = rover.next;
        }
        return null;
    }

    public void prepend(T item) {
        Node node = this.new Node(item);
        node.next = head;
        if (this.head == null) {
            this.tail = node;
        }
        this.head = node;
        this.size++;
    }

    public void append(T item) {
        Node node = this.new Node(item);
        if (this.tail == null) {
            this.head = node;
        } else {
            this.tail.next = node;
        }
        this.tail = node;
        this.size++;
    }

    private Node predecessor(Node node) {
        Node rover = this.head;
        Node previous = null;
        while (rover != node) {
            previous = rover;
            rover = rover.next;
        }
        return previous;
    }

    public void insertBefore(Node before, T item) {
        if (before == null) {
            append(item);
        } else {
            Node node = new Node(item);
            Node previous = predecessor(before);

            node.next = before;
            if (previous != null) {
                previous.next = node;
            } else {
                this.head = node;
            }
            this.size++;
        }
    }

    public void insertAfter(Node after, T item) {
        if (after == null) {
            prepend(item);
        } else {
            Node node = new Node(item);
            node.next = after.next;
            after.next = node;
            if (node.next == null) {
                this.tail = node;
            }
            this.size++;
        }
    }

    public void remove(Node node) {
        Node previous = predecessor(node);
        if (previous != null) {
            previous.next = node.next;
        } else {
            this.head = node.next;
        }

        if (node.next == null) {
            this.tail = previous;
        }
        this.size--;
    }

    public void removeAll(T item) {
        Node rover = this.head;
        Node previous = null;
        while (rover != null) {
            if (rover.item.equals(item)) {
                if (previous != null) {
                    previous.next = rover.next;
                } else {
                    this.head = rover.next;
                }
                if (rover.next == null) {
                    this.tail = previous;
                }
                this.size--;
            } else {
                previous = rover;
            }
            rover = rover.next;
        }
    }

    public T head() {
        return this.size > 0 ? this.head.item : null;
    }

    public T tail() {
        return this.size > 0 ? this.tail.item : null;
    }

    public T removeHead() {
        T result = null;
        if (this.head != null) {
            result = this.head.item;
            this.head = this.head.next;
            if (this.head == null) {
                this.tail = null;
            }
            this.size--;
        }
        return result;
    }

    public T removeTail() {
        T result = null;
        Node rover = this.head;
        Node last = null;
        Node penultimate = null;

        // Find the last and next to last nodes.

        while (rover != null) {
            penultimate = last;
            last = rover;
            rover = rover.next;
        }

        // Make the penultimate item be the last one.
        // I.e., remove the last element from the list.

        if (penultimate != null) {
            penultimate.next = null;
        } else {
            this.head = null;
        }

        this.tail = penultimate;
        if (this.tail == null) {
            this.head = null;
        }
        this.size--;

        return last != null ? last.item : null;
    }

    public boolean equals(SinglyLinkedList other) {
        Node thisRover = this.head;
        Node otherRover = other.head;
        while (thisRover != null && otherRover != null) {
            if (!thisRover.item.equals(otherRover.item)) return false;
            thisRover = thisRover.next;
            otherRover = otherRover.next;
        }
        return thisRover == otherRover; // Both null?
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof SinglyLinkedList && this.equals((SinglyLinkedList) other);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        Node rover = this.head;
        while (rover != null) {
            hash = 31 * hash + rover.item.hashCode();
            rover = rover.next;
        }
        return hash;
    }

    @Override
    public String toString() {
        Node rover = this.head;
        String separator = "";
        String result = "[";
        while (rover != null) {
            result += separator;
            result += rover.item.toString();
            separator = ",";
            rover = rover.next;
        }
        result += "]";
        return result;
    }

    public Iterator<T> iterator() {
        return this.new ListIterator();
    }

    private class ListIterator implements Iterator<T> {

        private Node rover;

        public ListIterator() {
            this.rover = SinglyLinkedList.this.head;
        }

        @Override
        public boolean hasNext() {
            return this.rover != null;
        }

        @Override
        public T next() {
            T result = this.rover.item;
            this.rover = this.rover.next;
            return result;
        }
    }


    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>(args);

        Console console = System.console();
        if (console == null) {
            System.out.println("No console");
            return;
        }

        boolean done = false;
        do {
            String line = console.readLine("Command: ");
            if (line == null) break;

            String command = "";
            String value1 = "";
            String value2 = "";

            String[] fields = line.split(" ");
            if (fields.length > 0) command = fields[0];
            if (fields.length > 1) value1 = fields[1];
            if (fields.length > 2) value2 = fields[2];

            try {
                switch (command.toLowerCase()) {
                    case "size":
                        System.out.println(list.size());
                        break;

                    case "isempty":
                        System.out.println(list.isEmpty());
                        break;

                    case "contains":
                        System.out.println(list.contains(value1));
                        break;

                    case "occurs":
                    case "occurrences":
                        System.out.println(list.occurrences(value1));
                        break;

                    case "prepend":
                        list.prepend(value1);
                        break;

                    case "append":
                        list.append(value1);
                        break;

                    case "insertafter":
                        list.insertAfter(list.locate(value1), value2);
                        break;

                    case "insertbefore":
                        list.insertBefore(list.locate(value1), value2);
                        break;

                    case "remove":
                        list.remove(list.locate(value1));
                        break;

                    case "removeall":
                        list.removeAll(value1);
                        break;

                    case "head":
                        System.out.println(list.head());
                        break;

                    case "tail":;
                        System.out.println(list.tail());
                        break;

                    case "removehead":
                        System.out.println(list.removeHead());
                        break;

                    case "removetail":
                        System.out.println(list.removeTail());
                        break;

                    case "tostring":
                        System.out.println(list.toString());
                        break;

                    case "print":
                        for (String s : list) {
                            System.out.println(s);
                        }
                        break;

                    case "clear":
                        list = new SinglyLinkedList();
                        break;

                    case "new":
                        String[] values = new String[fields.length-1];
                        for (int i = 1; i < fields.length; i++) {
                            values[i-1] = fields[i];
                        }
                        list = new SinglyLinkedList(values);
                        break;

                    case "exit":
                    case "quit":
                        done = true;
                        break;

                    case "help":
                        System.out.println("size              Print size()");
                        System.out.println("isEmpty           Print isEmpty");
                        System.out.println("contains x        Print constains(x)");
                        System.out.println("occurrences x     Print occurrences(x)");
                        System.out.println("append x          Invoke append(x)");
                        System.out.println("prepend x         Invoke prepend(x)");
                        System.out.println("insertAfter x y   Invoke insertAfter(locate(x), y)");
                        System.out.println("insertBefore x y  Invoke insertBefore(locate(x), y)");
                        System.out.println("remove x          Invoke remove(locate(x))");
                        System.out.println("removeAll x       Invoke removeAll(x)");
                        System.out.println("removeHead        Print removeHead())");
                        System.out.println("removeTail        Print removeHead()");
                        System.out.println("head              Print head()");
                        System.out.println("tail              Print tail()");
                        System.out.println("toString          Print toString()");
                        System.out.println("print             Print list using the iterator");
                        System.out.println("clear             new SinglyLinekdList()");
                        System.out.println("new a b ... z     new SinglyLinkedList(new T() {a, b, ..., z}");
                        System.out.println("quit              exits progam");
                        System.out.println("exit              exits progam");
                        break;

                    default:
                        System.out.println("Invalid command: " + command);
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!done);
    }
}
