//Ashvin Ganesan
// Algorithms
// HW 5
// Thursday September 24th 2020
import java.util.Iterator;
import java.lang.Iterable;

public class DoublyLinkedList<T> implements Iterable<T> {
    private Node head;
    private Node tail;
    private int size;
    
        public DoublyLinkedList(){
            head = null;
            tail = null;
            size = 0;
        }
        public DoublyLinkedList(T[] items) {
        this();
        for (T item : items) {
            this.append(item);
        }
    }

    // This class implements a doubly linked list of items of class T.
    // Each node in the list has pointer to both the next item and the
    // previous item in the list.  This should allow for fast insertion
    // and removal of elements when a position (Node) within the list
    // is known.  It should also permit iteration of the list in the
    // reverse order.

    public T getHeadVal(){
        return head.item;
    }
    public T getTailVal(){
        return tail.item;
    }
    // this was needed because i wanted to test something. 
    public Node getHead(){
        return this.head;
    }
    public class Node {
        public Node previous;//maybe make these private. 
        public Node next;
        public T item;
        

        // Make sure that clients cannot create or modify nodes.
        // They can pass a Node to methods in this class or they
        // can get the value field out of a node ... nothing else.

        private Node(T data) {
            item = data;
            next = null;
            previous = null;
                    
            
        }
        
        public T get() { 
            return item;
        }
    }

    // The number of items currently in this linked list

    public int size() { 
        return size;
    }
    
    
    public boolean isEmpty() { 
        if(head != null) {
            return true;
        }
        return false; 
    }


    // Check to see if a given item is in a list.

    public boolean contains(T item) { 
        Node rover = this.head;
        while(rover != null) {
            if(rover.item == item) {
                return true;
            }
        }
        return false; 
    }
    public Node find(T item) { 
        Node rover = this.head;
        while(rover != null) {
            if(rover.item == item) {
                return rover;
            }
            rover = rover.next;
        } 
        return null;//returns null if it doesn't find the item in the node. 
    }


    // Set/get the value of the ith element in this linked list.
    // Throw an IndexOutOfBoundsException if necessary.

    public T get(int index) { 
        Node rover = this.head;
        if(index > this.size-1) {
            return null;
        }
        for(int i = 0; i < index; i++) {
            rover = rover.next;
        }
        return rover.item; 
    }
    public void set(int index, T value) {
        if (index >= this.size()) {
            return;
        }
        Node rover = this.head;
        for(int i = 0; i < index; i++) {
            rover = rover.next;
        }
        rover.item = value; 
    }


    // The obvious methods for adding nodes to a linked list.
    // The remove method will eliminate all matching items.

    public void append(T item) {
        Node n = new Node(item);
        if (head == null) {
            head = n;
            tail = n;
        }
        n.previous = tail;
        tail.next = n;
        tail = n;
        size++;
    }
    public void prepend(T item) {
        Node n = new Node(item);
        if (head == null) {
            head = n;
            tail = n;
        }
        n.next = head;
        head.previous = n;
        head = n;
        size++;
    }
    public void remove(T item) {
        Node rover = this.head;
        Node next = rover.next;

        while(head.item.equals(item)) {
            if(head == tail) {
                head = null;
                tail = null;
                return;
                                               
            }
            next.previous = null;
            head = next;
            
        }
        rover = head.next;
        
        while(rover != null) {
            Node previous = rover.previous;
            next = rover.next;
            if(rover.item.equals(item)) {
                
                previous.next = next;
                
                if (next != null) {
                    next.previous = previous;
                } else {
                    tail = previous;
                }
                size--;
            }
            rover = rover.next;
        }
    }
    //I couldn't really figure out how to run insert before and after from the commandline so I just did this
    // kinda jank thing. 
    public void test(T test){
        
        insertBefore(head.next, test);
        insertAfter(tail.previous, test);
    }

    public void insertAfter(Node node, T value) {

        if(node == tail) {
            append(value);
            return;
        }
        Node val = new Node(value);
        Node next = node.next; 
        node.next = val;
        val.previous = node;
        val.next = next;
        next.previous = val;
    }
    public void insertBefore(Node node, T value) {
        if(node == head) {
            prepend(value);
            return;
        }
        Node val = new Node(value);
        Node previous = node.previous;
        previous.next = val;
        val.previous = previous;
        val.next = node;
        node.previous = val;
    }


    // Of course we always need equality predicates.

    public boolean equals(DoublyLinkedList<T> other) {
        Node rover = head;
        Node rover2 = other.head;
        if(this.size != other.size) {
            return false;
        }
        while(rover.next!= null) {
            if (rover.item != rover2.item) {
                return false;
            }
            rover = rover.next;
            rover2 = rover2.next;
        }
        return true;
    } 

    @Override
    public boolean equals(Object other) {
        if (other != null && other instanceof DoublyLinkedList) {
            DoublyLinkedList<T> list = (DoublyLinkedList<T>) other;
            return this.equals(list);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        boolean first = true;
        String result = "[";
        for (T item : this) {
            if (!first) result += ", ";
            result += item;
            first = false;
        }
        result += "]";
        return result;
    }


    // Iterators for examining the elements in a linked list.

    @Override
    public Iterator<T> iterator() { 
        return this.new ForwardListIterator(); 
    } // Returns the forward iterator
    
    private class ForwardListIterator implements Iterator<T> {

        private Node rover;

        public ForwardListIterator() {
            this.rover = DoublyLinkedList.this.head;
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
    private class BackwardListIterator implements Iterator<T> {

        private Node rover;

        public BackwardListIterator() {
            this.rover = DoublyLinkedList.this.tail;
        }

        @Override
        public boolean hasNext() {
            return this.rover != null;
        }

        @Override
        public T next() {
            T result = this.rover.item;
            this.rover = this.rover.previous;
            return result;
        }
    }

    public static enum Direction { FORWARD, REVERSE }
    public Iterator<T> iterator(Direction direction) { 
        if(direction == Direction.FORWARD) {
            return this.new ForwardListIterator(); 
        }
        if(direction == Direction.REVERSE) {
            return this.new BackwardListIterator(); 
        }
        return null; 
    }
    

}
