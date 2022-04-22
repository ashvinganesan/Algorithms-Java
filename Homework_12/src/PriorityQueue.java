
public interface PriorityQueue<T extends Comparable<T>> extends Iterable<T> {

    public int size();

    public boolean isEmpty();

    public void enqueue(T item);

    public T dequeue();

    public T head();

}
