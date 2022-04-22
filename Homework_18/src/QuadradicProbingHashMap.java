
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
public class QuadradicProbingHashMap<Key, Value> implements Map<Key, Value>{

    private int size;
    private int capacity;
    private Node[] arr;
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
    public QuadradicProbingHashMap() {
        this(5);
    }
    public QuadradicProbingHashMap(int capacity) {
        this.size = 0;
        this.capacity = Primes.nextPrime(capacity);
        
 
        arr = new Node[this.capacity];
        //System.out.println("capacity:" + capacity);
        for(int i = 0; i < this.capacity; i++) {
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
        capacity = Primes.nextPrime(capacity * 2);
        size = 0;
        //System.out.println("capacity: " + capacity);
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
        int countplace = 0;
        int hash = hash(key);
        int count = hash(key);
        count = count % (capacity-1);
        if(size == 0) {
            return false;
        }
//        System.out.println("size:" + size);
//        System.out.println("capacity:" + capacity);
//        System.out.println("hash:" + hash);
//        System.out.println("count:" + count);
        int s = 1;
        //searches++;
        while(true) {
            searches++;
//            System.out.println("count:" + count);
//            System.out.println("capacity:" + capacity);
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
            countplace++;
            count = countplace*countplace+hash;
            count = count % (capacity-1);
        }
        
    }
    

    @Override
    public Value find(Key key) {
        if(!contains(key)) {
            return null;
        }
        int count = hash(key);
        int countplace = 0;
        int hash = hash(key);
        while(true) {
            if(arr[count].key.equals(key)) {
                return (Value) arr[count].value;
            }
            countplace++;
            count = countplace*countplace+hash;
            count = count % (capacity);
                    
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
        int countplace = 0;
        int hash = hash(key);
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
            countplace++;
            count = countplace*countplace+hash;
            count = count % (capacity);
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
        int countplace = 0;
        int hash = hash(key);
        int count = hash(key);
        while(true) {
            if(arr[count].key.equals(key)) {
                arr[count].key = null;
                arr[count].value = null;
                return;
            }
            countplace++;
            count = countplace*countplace+hash;
            count = count % (capacity);
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