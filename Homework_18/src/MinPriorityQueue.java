import java.util.Comparator;

public class MinPriorityQueue<T extends Comparable<T>> {

    private Comparator<T> comparator;
    private T[] queue;
    private int size;

    public MinPriorityQueue(int capacity, Comparator<T> comparator) {
        this.queue = (T[]) new Comparable[capacity+1];
        this.comparator = comparator;
        this.size = 0;
    }

    public MinPriorityQueue(int capacity) {
        this(capacity, null);
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public T min() {
        return this.queue[1];
    }

    public void add(T item) {
        this.queue[++this.size] = item;
        this.swim(this.size);
    }

    public T removeMin() {
        T result = this.queue[1];
        if (this.size > 0) {
            this.queue[1] = this.queue[this.size];
            this.queue[this.size] = null;
            this.size--;
            this.sink(1);
        }
        return result;
    }

    private boolean isLess(int i, int j) {
        if (this.comparator == null) {
            return this.queue[i].compareTo(this.queue[j]) < 0;
        } else {
            return this.comparator.compare(this.queue[i], this.queue[j]) < 0;
        }
    }

    private void swap(int i, int j) {
        T temp = this.queue[i];
        this.queue[i] = this.queue[j];
        this.queue[j] = temp;
    }

    private void sink(int index) {
        int child = 2*index;
        while (child <= this.size) {
            if (child < this.size && isLess(child+1, child)){
                child++;
            }
            if (!isLess(child, index)) {
                break;
            }
            swap(index, child);
            index = child;
            child = 2*index;
        }
    }

    private void swim(int index) {
        int parent = index/2;
        while (parent > 0 && isLess(index, parent)) {
            swap(parent, index);
            index = parent;
            parent = index/2;
        }
    }

    @Override
    public String toString() {
        String result = "[";
        String separator = "";
        for (int i = 1; i <= this.size; i++) {
            result += separator;
            result += this.queue[i];
            separator = ", ";
        }
        return result + "]";
    }

}
