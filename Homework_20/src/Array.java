import java.io.Console;
import java.util.Iterator;

public class Array<T> implements List<T> {

    private static final int MIN_CAPACITY = 4;

    private T[] items;
    private int size;

    public Array() {
        this(8);
    }

    public Array(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity = " + capacity);
        }
        this.items = (T[]) new Object[capacityFor(capacity)];
        this.size = 0;
    }

    public Array(T item) {
        this();
        this.append(item);
    }

    public Array(T[] items) {
        this(items.length);
        for (T item : items) {
            this.append(item);
        }
    }

    public Array(List<T> other) {
        this(other.size());
        for (T item : other) {
            this.append(item);
        }
    }

    private int capacityFor(int size) {
        int capacity = MIN_CAPACITY;
        while (capacity < size) {
            capacity *= 2;
        }
        return capacity;
    }

    private void resizeIfNecessary(int minimumSize) {
        if (this.items.length < minimumSize) {
            T[] old = this.items;
            this.items = (T[]) new Object[capacityFor(minimumSize)];
            for (int i = 0; i < this.size; i++) {
                this.items[i] = old[i];
            }
        }
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.items.length;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean contains(T item) {
        for (int i = 0; i < this.size; i++) {
            if (this.items[i].equals(item)) return true;
        }
        return false;
    }

    public int occurrences(T item) {
        int count = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.items[i].equals(item)) count++;
        }
        return count;
    }

    public int locate(T item) {
        for (int i = 0; i < this.size; i++) {
            if (this.items[i].equals(item)) return i;
        }
        return -1;
    }

    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayIndexOutOfBoundsException("" + index);
        }
        return this.items[index];
    }

    public void set(int index, T value) {
        if (index < 0 || index >= this.size) {
            throw new ArrayIndexOutOfBoundsException("" + index);
        }
        this.items[index] = value;
    }

    public void prepend(T item) {
        insertBefore(0, item);
    }

    public void append(T item) {
        resizeIfNecessary(this.size + 1);
        this.items[this.size++] = item;
    }

    public void insertAfter(int after, T item) {
        insertBefore(after+1, item);
    }

    public void insertBefore(int before, T item) {
        if (before < 0 || before > this.size) {
            throw new IllegalArgumentException("Before = " + before);
        }
        resizeIfNecessary(this.size + 1);
        for(int i = this.size-1; i >= before; i--) {
            this.items[i+1] = this.items[i];
        }
        this.items[before] = item;
        this.size++;
    }

    public T remove (int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("Index = " + index);
        }
        T result = this.items[index];
        for (int i = index; i < this.size-1; i++) {
            this.items[i] = this.items[i+1];
        }
        this.size--;
        return result;
    }

    public void removeAll(T item) {
        int n = this.size;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (!this.items[i].equals(item)) {
                this.items[j++] = this.items[i];
                this.size--;
            }
        }
    }

    public T head() {
        return this.size > 0 ? this.items[0] : null;
    }

    public T tail() {
        return this.size > 0 ? this.items[this.size-1] : null;
    }

    public T removeHead() {
        if (this.size > 0) {
            return remove(0);
        } else {
            return null;
        }
    }

    public T removeTail() {
        if (this.size > 0) {
            return remove(this.size-1);
        } else {
            return null;
        }
    }

    public boolean equals(Array other) {
        if (other == null) return false;
        if (this.size != other.size) return false;

        for (int i = 0; i < this.size; i++) {
            if (!this.items[i].equals(other.items[i])) return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Array && this.equals((Array) other);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < this.size; i++) {
            hash = 751 * hash + this.items[i].hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        String separator = "";
        String result = "[";
        for (int i = 0; i < this.size; i++) {
            result += separator;
            result += this.items[i].toString();
            separator = ",";
        }
        result += "]";
        return result;
    }

    public Iterator<T> iterator() {
        return this.new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {

        private int index;

        public ArrayIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return this.index < Array.this.size;
        }

        @Override
        public T next() {
            return Array.this.items[this.index++];
        }
    }


    public static void main(String[] args) {
        Array<String> array = new Array<>(args);

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
                        System.out.println(array.size());
                        break;

                    case "capacity":
                        System.out.println(array.capacity());
                        break;

                    case "isempty":
                        System.out.println(array.isEmpty());
                        break;

                    case "contains":
                        System.out.println(array.contains(value1));
                        break;
    
                    case "occurs":
                    case "occurrences":
                        System.out.println(array.occurrences(value1));
                        break;

                    case "locate":
                        System.out.println(array.locate(value1));
                        break;

                    case "get":
                        System.out.println(array.get(Integer.parseInt(value1)));
                        break;

                    case "set":
                        array.set(Integer.parseInt(value1), value2);
                        break;

                    case "prepend":
                        array.prepend(value1);
                        break;

                    case "append":
                        array.append(value1);
                        break;

                    case "insertafter":
                        array.insertAfter(array.locate(value1), value2);
                        break;

                    case "insertbefore":
                        array.insertBefore(array.locate(value1), value2);
                        break;

                    case "remove":
                        array.remove(array.locate(value1));
                        break;

                    case "removeall":
                        array.removeAll(value1);
                        break;

                    case "head":
                        System.out.println(array.head());
                        break;

                    case "tail":;
                        System.out.println(array.tail());
                        break;

                    case "removehead":
                        System.out.println(array.removeHead());
                        break;

                    case "removetail":
                        System.out.println(array.removeTail());
                        break;

                    case "tostring":
                        System.out.println(array.toString());
                        break;

                    case "print":
                        for (String s : array) {
                            System.out.println(s);
                        }
                        break;

                    case "clear":
                        array = new Array();
                        break;

                    case "new":
                        String[] values = new String[fields.length-1];
                        for (int i = 1; i < fields.length; i++) {
                            values[i-1] = fields[i];
                        }
                        array = new Array(values);
                        break;

                    case "exit":
                    case "quit":
                        done = true;
                        break;

                    case "help":
                        System.out.println("size              Print size()");
                        System.out.println("capacity          Print capacity()");
                        System.out.println("isEmpty           Print isEmpty");
                        System.out.println("contains x        Print constains(x)");
                        System.out.println("occurrences x     Print occurrences(x)");
                        System.out.println("locate x          Print locate((x)");
                        System.out.println("get i             Print get(i)");
                        System.out.println("set i value       Invoke set(i, value)");
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
                        System.out.println("clear             new Array()");
                        System.out.println("new a b ... z     new Array(new T() {a, b, ..., z}");
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
