// Ashvin Ganesan
// Algorithms-H
// Mr. Paige
// HW #4
// Tuesday September 15th 2020

import java.util.Iterator;

public class Array<T> implements Iterable<T> {

    // An ADT for a variable size array.
    private T[] array;
    private int size;

    public Array() {
        this(16);
    }

    public Array(int capacity) {
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    public Array(T[] items) {
        this(items.length);
        for (int i = 0; i < items.length; i++) {
            this.array[i] = items[i];
        }
        this.size = items.length;
    }

    public Array(Array<T> other) {
        // Copy constructor.
        this(other.array);
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.array.length;
    }

    private void resizeIfNecessary(int minimumSize) {
        T[] arr  = (T[]) new Object[minimumSize];
        for(int i = 0; i < array.length;i++){
            arr[i] = array[i];
        }
        
        array = arr;
    
        // Increase the size of the array to be at least the specified size

        // !!! TO DO !!!
    }

    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayIndexOutOfBoundsException(index);
        } else {
            return this.array[index];
        }
    }

    public void set(int index, T value) {
        if (index < 0 || index >= this.size) {
            throw new ArrayIndexOutOfBoundsException(index);
        } else {
            this.array[index] = value;
        }
    }

    public boolean contains(T item) {
        return locate(item) >= 0;
    }

    public int occurrences(T item) {
        int count = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.array[i].equals(item)) {
                count++;
            }
        }
        return count;
    }

    public int locate(T item) {  // aka find
        return locate(item, 0);
    }

    public int locate(T item, int start) {
        for (int i = start; i < this.size; i++) {
            if (this.array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public void append(T item) {
        
        if(size !=0 && this.array[array.length-1] !=null) {
            resizeIfNecessary(size*2);
        }
        this.array[this.size++] = item;
    }

    public void prepend(T item) {
        insertBefore(item, 0);
    }

    public void insertBefore(T item, int before) {
        int temp = size+1;
        

        T[] arr = (T[]) new Object[temp];
        //System.out.println(temp);
        if(temp == 1) {
           //System.out.println("quits here");
           arr[0] = item;
           array = arr;
           size++;
           return;
        }
        for(int i = 0; i < before;i++) {
            arr[i]= array[i];
        }
        arr[before] = item;
        for(int j = before; j < array.length;j++) {
            arr[j+1] = array[j];
        }
        array = arr;
        size++;

        // Insert the new item in the slot just preceding the specified index position

        // !!! TO DO !!!
    }

    public void insertAfter(T item, int after) {
        // Insert the new item in the slot just following the specified index position
        insertBefore(item, after++);
        // !!! TO DO !!!
    }

    public void remove(int index) {
        int temp = size()-1;
        T[] arr = (T[]) new Object[temp];
        for(int i = 0; i < index;i++) {
            arr[i]= array[i];
        }
        for(int j = index; j < arr.length;j++) {
            arr[j] = array[j+1];
        }
        array = arr;  
    }
      

    public void removeAll(T item) {
        // Remove all occurrences of the specified item.

        // !!! TO DO !!!
    }

    public boolean equals(Array other) {
        if (other == null) {
            return false;
        }
        if (this.size != other.size) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            if (!this.array[i].equals(other.array[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Array && this.equals((Array) other);
    }

    @Override
    public String toString() {
        String result = "[";
        String separator = "";
        for (int i = 0; i < this.size; i++) {
            result += separator;
            result += this.array[i];
            separator = ", ";
        }
        result += "]";
        return result;
    }

    public static enum Direction {
        FORWARD, REVERSE
    }

    public Iterator<T> iterator(Direction direction) {
        switch (direction) {
            case FORWARD:
                return new ForwardArrayIterator(this);
            case REVERSE:
                return new ReverseArrayIterator(this);
            default:
                return null;
        }
    }

    public Iterator<T> iterator() {
        return new ForwardArrayIterator<T>(this);
    }

    private static class ForwardArrayIterator<T> implements Iterator<T> {

        private Array<T> array;
        private int index;

        public ForwardArrayIterator(Array<T> array) {
            this.array = array;
            this.index = 0;
        }

        public boolean hasNext() {
            return index < this.array.size;
        }

        public T next() {
            return this.array.array[this.index++];
        }
    }

    private static class ReverseArrayIterator<T> implements Iterator<T> {

        private Array<T> array;
        private int index;
        // An iterator that will iterator over the items in the array in
        // reverse order (from last to first).
        // !!! TO DO !!!
        public ReverseArrayIterator(Array<T> array) {
            this.array = array;
            this.index = array.size() -1;
        }

        public boolean hasNext() {
            return index > 0;
        }

        public T next() {
            return this.array.array[this.index--];
        }
    }
}
