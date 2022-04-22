//Ashvin Ganesan
// Algorithsm HW 21

public class GraphProperties {
    private SymbolGraph graph;
    private SymbolGraph.Vertex[] arr;
    private int[] eccentricities;
    private int diamter;
    private int radius;
    
    public GraphProperties(SymbolGraph g) {
        this.graph = g;
        int i = 0;
        diamter = 0;
        radius = Integer.MAX_VALUE;
        arr = new SymbolGraph.Vertex[graph.vertices()];
        while(i < graph.vertices()) {
//            System.out.println("here");
//            System.out.println(i);
            if(graph.get(i) != null) {
                //System.out.println(graph.get(i).degree());
                arr[i] = graph.get(i);
            } else {
                arr[i] = null;
            }
            
            i++;
        }
        eccentricities = new int[graph.vertices()];
        for(int e: eccentricities) {
            e = -1;
        }
        for(int j = 0; j < eccentricities.length; j++) {
            eccentricities[j] = eccentricity(arr[j]);
        }
    }
//I made this not static but stored all of the eccentricities. 
    public int eccentricity(SymbolGraph.Vertex vertex) {
        Array<SymbolGraph.Vertex> que = new Array<SymbolGraph.Vertex>();
        boolean[] visited = new boolean[arr.length];
        int[] distanceToSource = new int[graph.vertices()];
        for(int j: distanceToSource) {
            j = 0;
        }
        for(boolean v: visited) {
            v = false;
        }
        int eccentricity = 0;
        que.append(vertex);
        while(!que.isEmpty()) {
            SymbolGraph.Vertex current = que.removeHead();
            List<SymbolGraph.Vertex> neighbors = current.neighbors();
            for(SymbolGraph.Vertex neighbor: neighbors) {
                if(!visited[neighbor.index()]) {
                    distanceToSource[neighbor.index()] = distanceToSource[current.index()] + 1;
                    visited[neighbor.index()] = true;
                    que.append(neighbor);
                    if (distanceToSource[neighbor.index()] > eccentricity) {
                    eccentricity = distanceToSource[neighbor.index()];
                    }
                }
                
            }
        }
        return eccentricity; 
    }

    public int diameter() {
        if (diamter != 0) {
            return diamter;
        }
        int max = 0;
        for(int i: eccentricities) {
            if(max < i) {
                max = i;
            }
            
        }
        diamter = max;
        return max; // Not quite right.
    }

    public int radius() {
        if (radius != Integer.MAX_VALUE) {
            return radius;
        }
        int min = Integer.MAX_VALUE;
        for(int i: eccentricities) {
            //System.out.println("I" + i);
            if(min > i) {
                min = i;
            }
            
        }
        radius = min;
        return min; // Not quite right.
    }

    public SymbolGraph.Vertex center() {
        int r;
        if (radius != Integer.MAX_VALUE) {
            r = radius;
        }
        else {
            r = radius();
        }
        for(int i = 0; i < eccentricities.length;i++) {
            if(eccentricities[i] == r) {
                return arr[i];
            }
        }
        System.out.println("something went very wrong");
        return arr[0];  // Really ???
    }
}
