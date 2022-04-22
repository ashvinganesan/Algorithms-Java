//Ashvin Ganesan
// Mr Paige
//Algorithms 
// October First 2020
// Homework #6
public abstract class ListStack<T> implements Stack<T> {

    protected List<T> stack;

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public void push(T item) {
        this.stack.append(item);
    }

    public T pop() {
        if (this.stack.isEmpty()) {
            throw new Stack.UnderflowException();
        } else {
            return this.stack.removeTail();
        }
    }

    public T top() {
        if (this.stack.isEmpty()) {
            throw new Stack.UnderflowException();
        } else {
            return this.stack.tail();
        }
    }
}
