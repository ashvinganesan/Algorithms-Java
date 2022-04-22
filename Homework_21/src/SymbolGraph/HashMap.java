public interface HashMap<Key,Value> extends Iterable<Key> {

    public int size();
    public int capacity();
    default public boolean isEmpty() {
        return this.size() == 0;
    };

    public Value find(Key key);
    public boolean contains(Key key);

    public void add(Key key, Value value);
    public void remove(Key key);

    public interface Visit<Key,Value> {
        public void visit(Key key, Value value);
    }

    public void traverse(Visit<Key,Value> visit);

    default public void dump() {}; // Override for debugging
    public int hash(Key key); // Publicly visible for testing

    public Stats stats();
    public static class Stats {

        public int totalProbes = 0;
        public int maxProbes   = 0;
        public int searches    = 0;
        public int misses      = 0;
        public int hits        = 0;

        public double averageProbes() {
            return (double) totalProbes / (double) searches;
        }

        public void update(int probes, boolean found) {
            if (probes > this.maxProbes) {
                this.maxProbes = probes;
            }
            this.totalProbes += probes;
            if (found) {
                this.hits++;
            } else {
                this.misses++;
            }
            this.searches++;
        }
    
        public void reset() {
            this.totalProbes = 0;
            this.maxProbes = 0;
            this.searches = 0;
            this.misses = 0;
            this.hits = 0;
        }
    }
}
