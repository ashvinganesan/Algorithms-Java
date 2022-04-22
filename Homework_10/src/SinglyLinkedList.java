
import java.util.Comparator;
import java.util.Iterator;

public class SinglyLinkedList<T> implements List<T> {

    private Node head;
    private Node tail;
    private int size;

    public void sort(Comparator<T> comparator) {
//        SinglyLinkedList<T> a = new SinglyLinkedList();
//        System.out.println(this);
//        while(head != null) {
//            a.appendNode(removeHeadNode());
//            System.out.println("a" + a);
//            System.out.println("list" + this);
//            
//        }
//        System.out.println(a);
          mergeSort(comparator);
    }

    private void mergeSort(Comparator<T> comparator) {
        //System.out.println("in merge");
        //System.out.println("the size at top is " + size());

        if ((head == null) || head.next == null) {
            //System.out.println("breaking");
            return;
        }
        SinglyLinkedList<T> a = new SinglyLinkedList();
        SinglyLinkedList<T> b = new SinglyLinkedList();
        while (head != null && head.next != null) {
            a.appendNode(removeHeadNode());
            b.appendNode(removeHeadNode());
        }
        if (head != null) {
            a.appendNode(removeHeadNode());
        }
        //System.out.println("the size is " + size());
        a.sort(comparator);
        b.sort(comparator);
        
        //System.out.println("the size of a is " + a.size());
        //System.out.println("the size of b is " + b.size());

        merge(a, b, comparator);
        //System.out.println("the after merge size is " + size());
        //System.out.println(this);

    }

    private boolean isLess(T a, T b, Comparator<T> comparator) {
        //System.out.println("hello");
        if (comparator != null) {
            return (comparator.compare(a, b) < 0);
        } 
//        else if (a instanceof Comparable) {
//            return ((Comparable <T> a).compareTo(b) < 0);
//        } 
//        else if (b instanceof Comparable) {
//            return ((Comparable <T> b).compareTo(a) > 0);
//        } else {
//            throw new IncomparableException();
//        }
        System.out.println("SOMETHING WENT WRONG");
        return false;

    }
//    
//
//    

    private Node appendNode(Node node) {
        size++;
        if (head == null) {
            head = node;
            head.next = null;
            tail = node;
            return node;
        }
        tail.next = node;
        tail = node;
        return node;

    }

    private Node removeHeadNode() {
        Node output = head;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        output.next = null;
        return output;

    }
    
    private void merge(SinglyLinkedList<T> a, SinglyLinkedList<T> b, Comparator<T> comparator) {

        while (a.head != null && b.head != null) {

            if (isLess(a.head.item, b.head.item, comparator)) {
                appendNode(a.removeHeadNode());
            } else {
                appendNode(b.removeHeadNode());                
            }

        }
        while(a.head != null) {
            appendNode(a.removeHeadNode());
        }
        while(b.head != null) {
            appendNode(b.removeHeadNode());
        }
        
    }

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
            if (rover.item.equals(item)) {
                return true;
            }
            rover = rover.next;
        }
        return false;
    }

    public int occurrences(T item) {
        int count = 0;
        Node rover = this.head;
        while (rover != null) {
            if (rover.item.equals(item)) {
                count++;
            }
            rover = rover.next;
        }
        return count;
    }

    public Node locate(T item) {
        Node rover = this.head;
        while (rover != null) {
            if (rover.item.equals(item)) {
                return rover;
            }
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
            this.size--;
        } else {
            this.head = null;
        }
        this.tail = penultimate;

        return last != null ? last.item : null;
    }

    public boolean equals(SinglyLinkedList other) {
        Node thisRover = this.head;
        Node otherRover = other.head;
        while (thisRover != null && otherRover != null) {
            if (!thisRover.item.equals(otherRover.item)) {
                return false;
            }
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
}
