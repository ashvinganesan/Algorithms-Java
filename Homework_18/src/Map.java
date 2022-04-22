public interface Map<Key, Value> extends Iterable<Key> {

    // Size.
    //
    //   The size method returns the number of keys in the map.
    //   the capacity method returns the number of items that
    //   the map can hold without reorganization (resizing) of
    //   the map.

    public int size();
    public int capacity();
    


    // Lookup.
    //
    //   Value returns the value associated with a specified key
    //   and returns null if the key is not found in the map.

    public boolean contains(Key key);
    public Value find(Key key);


    // Add and remove keys from the map.
    //
    //   Add will update the value if the key is found in the map.
    //   Remove is a no-op if the key is not in the map.

    public void add(Key key, Value value);
    public void remove(Key key);


    // Iteration.
    //
    //   Note that Map is iterable and thus inherits interable()
    //   The traverse method is a passive iterator for the map.

    //public void traverse(Visit visit);
    
    public interface Visit<Key, Value> {
        public void visit(Key key, Value value);
    }

}
