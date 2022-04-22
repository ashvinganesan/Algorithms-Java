
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ashvin
 */
public class LinearProbingHashMap<Key, Value> implements Map<Key, Value>{

    private int size;
    private int capacity;
    private Node[] arr;
    private int step;
    public int searches;
    public int misses;
    public int hits;
    public int max;

    @Override
    public Iterator<Key> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    
    public class Node<key,value>  implements Comparable<Node>{
    
        Key   key;
        Value value;
        boolean empty;
        
        public Value getval() {
            return this.value;
        }
        @Override
        public int compareTo(Node n) {
            int val = ((Integer)this.value).compareTo((Integer)(n.value));
            return val;
        }
        @Override
        public String toString() {
            return (key.toString()+ ": " + value.toString());
        }
        
        
        public Node() {
            key = null;
            value = null;
            empty = true;
        }
        
    }
    // Size.
    //
    //   The size method returns the number of keys in the map.
    //   the capacity method returns the number of items that
    //   the map can hold without reorganization (resizing) of
    //   the map.
    public LinearProbingHashMap() {
        this(1,3);
    }
    public LinearProbingHashMap(int step, int capacity) {
        this.size = 0;
        this.step = step;
        this.capacity = Primes.nextProbablePrime(capacity);//9973;
        //I'm pretty sure this is the only thing that isn't working properly It says I can't create an array of type Node because of 
        //generics but I couldn't quite figure out why not. Also it can't cast to this.     
       // Node[] arr = new Node[capacity];
        arr = new Node[capacity];
        for(int i = 0; i < capacity; i++) {
            arr[i] = new Node();
        }
        int searches = 0;
        int misses = 0;
        int hits = 0;
        int max = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    private void resize() {
        
        if(size <= capacity/2) {
            return;
        }
        
        Node[] nodes = nodes();
        capacity = capacity * 2 + 1;
        size = 0;
        capacity = Primes.nextProbablePrime(capacity);
        Node[] temp = new Node[capacity];
        
        for(int i = 0; i < capacity; i++){
            temp[i] = new Node();
        }
        arr = temp;
        for(int j = 0; j < nodes.length;j++) {
            //System.out.println(j + " " + nodes[j].key + " " +nodes[j].value);
            if(nodes[j].value != null) {
                add((Key)nodes[j].key,(Value)nodes[j].value,true);
            }
            else {
                add((Key)nodes[j].key,true);
            }
            
        }       
        
            
    }

    // Lookup.
    //
    //   Value returns the value associated with a specified key
    //   and returns null if the key is not found in the map.
    @Override
    public boolean contains(Key key) {
        int count = hash(key);
        int s = 1;
        while(true) {
            searches++;
            if(arr[count].key != null && arr[count].key.equals(key)) {
                hits++;
                if(s > max) {
                    max = s;
                }
                return true; 
            }
            if(arr[count].empty) {
                if(s > max) {
                    max = s;
                }
                misses++;
                return false;
            }
            s++;
            count+=step;
            
            count = count % (capacity);
        }
        
    }

    @Override
    public Value find(Key key) {
        if(!contains(key)) {
            return null;
        }
        int count = hash(key);
        while(true) {
            if(arr[count].key.equals(key)) {
                return (Value) arr[count].value;
            }
            count+=step;
            count = count%capacity;
                    
        }
    }

    // Add and remove keys from the map.
    //
    //   Add will update the value if the key is found in the map.
    //   Remove is a no-op if the key is not in the map.
    public void add(Key key) {
        add(key, null, false);
    }
    private void add(Key key, boolean inresize) {
        add(key, null, inresize);
    }
    public void add(Key key, Value value) {
        add(key, value, false);
    }
    private void add(Key key, Value value, Boolean inresize) {
        if(key == null) {
            return;
        }
        int count = hash(key);
        //System.out.println(count);
        if(key == null) {
            return;
        }
        
        while(true) {
            if(arr[count].key ==(null) || arr[count].key.equals(key)) {
                //System.out.println("in inner");    
                if(arr[count].key == null) {
                    size++;
                    
                }
                arr[count].key = key;
                arr[count].value = value;
                arr[count].empty = false;
                if(!inresize){
                    resize();
                }
                return;
            }
            count+=step;
            count = count%capacity;
        }

    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    @Override
    public void remove(Key key) {
        if(!contains(key)) {
            return;
        }
        size--;
        int count = hash(key);
        while(true) {
            if(arr[count].key.equals(key)) {
                arr[count].key = null;
                arr[count].value = null;
                return;
            }
            count+=step;
            count = count%capacity;
        }
    }

    // Iteration.
    //
    //   Note that Map is iterable and thus inherits interable()
    //   The traverse method is a passive iterator for the map.
    public  Node[] nodes() {
        int count = 0;
        Node[] ret = new Node[size];
        for(Node n: arr) {
            if(n.key != null) {
                ret[count] = (Node)n;
                count++;
            }
        }
        return ret;
    }
    
    public void clear() {
        size = 0;
        arr = new Node[capacity];
        for(int i = 0; i < capacity; i++) {
            arr[i] = new Node();
        }
    }

    //@Override
    public void traverse(Visit visit) {
        for(int i = 0; i< capacity;i++) {
            if(arr[i].key != null)
                visit.visit(arr[i].key, arr[i].value);
        }
    }
    //@Override
    public interface Visit<Key, Value> {
        public void visit(Key key, Value value);
    }

}
