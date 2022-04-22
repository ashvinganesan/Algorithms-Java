//Ashvin Ganesan
// Mr Paige
//Algorithms 
// October First 2020
// Homework #6
public class ArrayStack<T>  {
    
    protected Array<T> stack;

    public ArrayStack() {
        stack = new Array<T>();
    }


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
