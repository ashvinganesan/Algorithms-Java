//Ashvin Ganesan
// Mr Paige
//Algorithms 
// October First 2020
// Homework #6
public interface List<T> extends Iterable<T> {

    public int size();
    public boolean isEmpty();
    
    public boolean contains(T item);
    public int occurrences(T item);

    public void prepend(T item);
    public void append(T item);
    public void removeAll(T item);

    public T head();
    public T tail();

    public T removeHead();
    public T removeTail();

}
