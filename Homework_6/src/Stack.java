//Ashvin Ganesan
// Mr Paige
//Algorithms 
// October First 2020
// Homework #6
public interface Stack<T> {

    public static class OverflowException extends RuntimeException {
        public OverflowException() {
            super();
        }
    }

    public static class UnderflowException extends RuntimeException {
        public UnderflowException() {
            super();
        }
    }

    public boolean isEmpty();
    public void push(T item);
    public T pop();
    public T top();

}
