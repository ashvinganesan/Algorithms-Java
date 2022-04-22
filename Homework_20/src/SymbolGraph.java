import java.util.Iterator;
import java.util.Scanner;

public class SymbolGraph implements Iterable<SymbolGraph.Vertex> {

    private HashMap<String, Vertex> map;  // Map from name -> vertex
    private Array<Vertex> vertices;       // List of vertices in graph
    private int edges;                    // Number edges in graph

    public SymbolGraph() {
        this.map = new LinearProbingHashMap<String, Vertex>();
        this.vertices = new Array<Vertex>();
        this.edges = 0;
    }
    public int vertices() { return this.vertices.size(); }
    public int edges()    { return this.edges; }

    public Vertex get(int index) {
        // Returns the ith vertex
        return this.vertices.get(index);
    }

    public Vertex find(String name) {
        // Find a vertex by name; add it if it is not already there
        Vertex result = this.map.find(name);
        if (result == null) {
            result = new Vertex(name);
            this.vertices.append(result);
        }
        return result;
    }

    public Vertex addVertex(String name) {
        return find(name);
    }

    public void addEdge(int from, int to) {
        addEdge(this.vertices.get(from), this.vertices.get(to));
    }

    public void addEdge(String from, String to) {
        addEdge(find(from), find(to));
    }

    public void addEdge(Vertex from, Vertex to) {
        from.neighbor(to);
    }

    public Iterator<Vertex> iterator() {
        // Iterator for the vertices in the graph
        return this.vertices.iterator();
    }


    public class Vertex {
        
        private List<Vertex> neighbors;
        private String name;
        private int index;

        public Vertex(String name) {
            this.neighbors = new SinglyLinkedList<Vertex>();
            this.index = SymbolGraph.this.map.size();
            SymbolGraph.this.map.add(name, this);
            this.name = name;
        }

        public int index() {
            return this.index;
        }

        public String name() {
            return this.name;
        }

        public int degree() {
            return this.neighbors.size();
        }

        public boolean isAdjacent(Vertex to) {
            return this.neighbors.contains(to);
        }

        public void neighbor(Vertex neighbor) {
            this.neighbors.prepend(neighbor);
            SymbolGraph.this.edges++;
        }

        public List<Vertex> neighbors() {
            return this.neighbors;
        }

        public String toString() {
            return this.name + ": " + this.neighbors;
        }
    }

    // -- Read Methods -----------------------------------------------------------

    //    These methods read a graph from an input stream in the same format as
    //    written out by the Write methods (which follow).

    public static SymbolGraph readVertexList(Scanner scanner) {
        // Read a list of vertex names and construct a graph with those vertices
        SymbolGraph graph = new SymbolGraph();
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            if (name.trim().length() == 0) break;
            graph.addVertex(name);
        }
        return graph;
    }

    public static SymbolGraph readEdgeList(Scanner scanner) {
        // Read and construct a graph from an edge list representation
        SymbolGraph graph = new SymbolGraph();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int comma = line.indexOf(',');
            if (comma >= 0) {
                String from = line.substring(0, comma).trim();
                String to   = line.substring(comma+1).trim();
                graph.addEdge(from, to);
            } else {
                graph.addVertex(line);
            }
        }
        return graph;
    }

    public static SymbolGraph readAdjacencyList(Scanner scanner) {
        // Read and construct a graph from an adjacency list representation
        SymbolGraph graph = new SymbolGraph();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] words = line.split("[ :,]+");
            Vertex from = null;
            for (String word : words) {
                if (from != null) {
                    graph.addEdge(from, graph.find(word));
                } else {
                    from = graph.find(word);
                }
            }
        }
        return graph;
    }

    public void readAdjacencyMatrix(Scanner scanner) {
        // Read an adjacency matrix and add the corresponding edges to a graph.
        // Should use readVertices beforehand to establish the list of vertices.
        int from = 0;
        int to = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] words = line.split("[ ,]+");
            for (String word : words) {
                int value = Integer.parseInt(word);
                if (value > 0) addEdge(from, to);
                to++;
            }
            from++;
            to = 0;
        }
    }
        
    // -- Write Methods ----------------------------------------------------------

    //    These methods write a graph to an output stream in the same format as
    //    expected by the Read methods (above).

    private String id(Vertex vertex, boolean name) {
        return name ? vertex.name() : Integer.toString(vertex.index());
    }

    public void writeVertexList(boolean names) {
        for (Vertex vertex : this) {
            if (!names) System.out.print(vertex.index() + ": ");
            System.out.println(vertex.name);
        }
        System.out.println();
    }

    public void writeEdgeList(boolean names) {
        for (Vertex from : this) {
            if (from.degree() > 0) {
                for (Vertex to : from.neighbors()) {
                    System.out.println(id(from, names) + "," + id(to, names));
                }
            } else {
                System.out.println(id(from, names));
            }
        }
    }

    public void writeAdjacencyList(boolean names) {
        for (Vertex vertex : this) {
            System.out.print(id(vertex, names) + ": ");
            String separator = "";
            for (Vertex neighbor : vertex.neighbors()) {
                System.out.print(separator);
                System.out.print(id(neighbor, names));
                separator = ", ";
            }
            System.out.println();
        }
    }

    public void writeAdjacencyMatrix(boolean commas) {
        int n = this.vertices();
        for (int i = 0; i < n; i++) {
            Vertex from = this.get(i);
            String separator = "";
            for (int j = 0; j < n; j++) {
                Vertex to = this.get(j);
                System.out.print(separator);
                System.out.print(from.isAdjacent(to) ? 1 : 0);
                separator = commas ? "," : " ";
            }
            System.out.println();
        }
    }
}
