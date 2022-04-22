import java.util.Iterator; 

public class LinearProbingHashMap<Key,Value> implements HashMap<Key,Value>, Iterable<Key> {

    private static final int MIN_CAPACITY = 4;
    private static final double MIN_LOAD_FACTOR = 0.125;
    private static final double MAX_LOAD_FACTOR = 0.5;

    private Key[]   keys;
    private Value[] values;
    private int     capacity;
    private int     size;
    private int     step;

    private HashMap.Stats stats;

    public LinearProbingHashMap(int capacity, int step) {
        this.capacity = capacity;
        this.step = step;
        this.size = 0;
        this.allocate(capacity);
        this.stats = new HashMap.Stats();
    }

    public LinearProbingHashMap(int capacity) {
        this(capacity, 1);
    }

    public LinearProbingHashMap() {
        this(MIN_CAPACITY);
    }


    private void allocate(int capacity) {
        capacity = Math.max(capacity, MIN_CAPACITY);
        capacity = Primes.nextProbablePrime(capacity-1);
        this.keys = (Key[]) new Object[capacity];
        this.values = (Value[]) new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    private void resize(int capacity) {
        Key[] savedKeys = this.keys;
        Value[] savedValues = this.values;
        this.allocate(capacity);
        for (int i = 0; i < savedKeys.length; i++) {
            Key key = savedKeys[i];
            Value value = savedValues[i];
            if (key != null) {
                add(key, value);
            }
        }
    }


    public int hash(Key key) {
        return (key.hashCode() & 0x7FFFFFFF) % this.capacity;
    }

    private boolean isEmpty(int index) {
        return this.keys[index] == null;
    }

    private int next(int index) {
        return (index + this.step) % this.capacity;
    }

    private int locate(Key key) {
        int index = hash(key);
        int count = 0;
        
        while (!isEmpty(index)) {
            count++;
            if (this.keys[index].equals(key)) {
                this.stats.update(count, true);
                return index;
            }
            index = this.next(index);
        }
        this.stats.update(count, false);
        return index;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public boolean contains(Key key) {
        int index = this.locate(key);
        return this.keys[index] != null;
    }

    @Override
    public Value find(Key key) {
        int index = this.locate(key);
        return this.values[index];
    }

    @Override
    public void add(Key key, Value value) {
        if (loadFactor() > MAX_LOAD_FACTOR) {
            this.resize(2 * this.capacity);
        }
        int index = this.locate(key);
        if (isEmpty(index)) this.size++;
        this.keys[index] = key;
        this.values[index] = value;
    }

    @Override
    public void remove(Key key) {
        int index = this.locate(key);
        if (isEmpty(index)) return;
        this.keys[index] = null;
        this.values[index] = null;
        this.size--;

        index = next(index);
        while (this.keys[index] != null) {
            Key savedKey = this.keys[index];
            Value savedValue = this.values[index];
            this.keys[index] = null;
            this.values[index] = null;
            int newIndex = this.locate(key);
            this.keys[newIndex] = savedKey;
            this.values[newIndex] = savedValue;
            index = next(index);
        }
    }

    public double loadFactor() {
        return (double) this.size / (double) this.capacity;
    }

    @Override
    public HashMap.Stats stats() {
        return this.stats;
    }

    @Override
    public void traverse(HashMap.Visit<Key,Value> visit) {
        for (int i = 0; i < this.keys.length; i++) {
            if (!isEmpty(i)) {
                visit.visit(this.keys[i], this.values[i]);
            }
        }
    }

    public void dump(HashMap.Visit<Key,Value> visit) {
        for (int i = 0; i < this.keys.length; i++) {
            visit.visit(this.keys[i], this.values[i]);
        }
    }

    @Override
    public Iterator<Key> iterator() {
        return new MapIterator();
    }

    private class MapIterator implements Iterator<Key> {

        private LinearProbingHashMap<Key,Value> map;
        private int current;
        private int remain;

        public MapIterator() {
            this.map = LinearProbingHashMap.this;
            this.remain = map.size;
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            return this.remain > 0;
        }

        @Override
        public Key next() {
            while(map.keys[this.current] == null) {
                this.current++;
            }
            this.remain--;
            return this.map.keys[this.current++];
        }
    }
}
