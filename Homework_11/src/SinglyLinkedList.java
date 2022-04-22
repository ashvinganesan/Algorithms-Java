/*
Ashvin Ganesan
Mr. Paige
Algorithms-H
HW#11
Friday December 18th 2020
 */

import java.io.Console;
import java.util.Iterator;

public class SinglyLinkedList implements List<String> {

    private Node head;
    private Node tail;
    private int size;

    public void sort() {
        SinglyLinkedList [] radixArr = new SinglyLinkedList[128];
        for(int i = 0; i <128; i++) {
            radixArr[i] = new SinglyLinkedList();
        }
        int maxLen = getMaxLength();
        for(int i = maxLen-1; i >=0; i--) {
            while(head != null) {
                Node current = removeHeadNode();
                int c = getChar(current.item, i);
                radixArr[c].appendNode(current);
            }
            // Walk the Array from 0 to 127
            for(SinglyLinkedList list: radixArr) {
                append(list);
            }
        }
        
    }
    
    public int getChar(String current, int i) {
        String val = current;
        int len = val.length();
        if(i < len) {
            return val.charAt(i);
        }
        else {
            return 32;
        }
    }

    public int getMaxLength() {
        int max = 0;
        Node current = this.head;
        while (current.next != null) {
            if (current.item.length() > max) {
                max = current.item.length();
            }
            current = current.next;
        }
        return max;
    }

    private Node removeNode(Node previous, Node r) {
        previous.next = r.next;
        r.next = null; // this might supposed to be commented out
        return r;
    }

    private void appendNode(Node node) {
        size++;
        if (head == null) {
            head = node;
            head.next = null;
            tail = node;
            return;
        }
        node.next = null;
        tail.next = node;
        tail = node;
    }

    private void append(SinglyLinkedList other) {
        if(other.head == null) {
            return;
        }
        size += other.size;
        if (head == null) {
            this.head = other.head;
            this.tail = other.tail;
        } else {
            tail.next = other.head;
            this.tail = other.tail;
        }
        other.head = null;
        other.size = 0;
        other.tail = null;
    }

    private Node removeHeadNode() {
        if (this.head == null) {
            return null;
        }
        Node temp = this.head;
        this.head = this.head.next;
        temp.next = null;
        return temp;
    }

    public class Node {

        private String item;
        private Node next;

        private Node(String item) {
            this.item = item;
            this.next = null;
        }
    }

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public SinglyLinkedList(String item) {
        this();
        this.append(item);
    }

    public SinglyLinkedList(String[] items) {
        this();
        for (String item : items) {
            this.append(item);
        }
    }

    public SinglyLinkedList(List<String> other) {
        this();
        for (String item : other) {
            this.append(item);
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean contains(String item) {
        Node rover = this.head;
        while (rover != null) {
            if (rover.item.equals(item)) {
                return true;
            }
            rover = rover.next;
        }
        return false;
    }

    public int occurrences(String item) {
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

    public Node locate(String item) {
        Node rover = this.head;
        while (rover != null) {
            if (rover.item.equals(item)) {
                return rover;
            }
            rover = rover.next;
        }
        return null;
    }

    public void prepend(String item) {
        Node node = this.new Node(item);
        node.next = head;
        if (this.head == null) {
            this.tail = node;
        }
        this.head = node;
        this.size++;
    }

    public void append(String item) {
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

    public void insertBefore(Node before, String item) {
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

    public void insertAfter(Node after, String item) {
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

    public void removeAll(String item) {
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

    public String head() {
        return this.size > 0 ? this.head.item : null;
    }

    public String tail() {
        return this.size > 0 ? this.tail.item : null;
    }

    public String removeHead() {
        String result = null;
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

    public String removeTail() {
        String result = null;
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

    public Iterator<String> iterator() {
        return this.new ListIterator();
    }

    private class ListIterator implements Iterator<String> {

        private Node rover;

        public ListIterator() {
            this.rover = SinglyLinkedList.this.head;
        }

        @Override
        public boolean hasNext() {
            return this.rover != null;
        }

        @Override
        public String next() {
            String result = this.rover.item;
            this.rover = this.rover.next;
            return result;
        }
    }
}
