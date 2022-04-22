
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/*

 */
public class Homework_20 {

    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        File input = new File("src/Graphs/test.txt");
        SymbolGraph map = new SymbolGraph();
        Scanner scnr = new Scanner(reader);
        map = map.readEdgeList(scnr);
        //System.out.println(map.vertices());
        //System.out.println(map.edges());
        DFS(map, 0);
    }

    public static void DFS(SymbolGraph graph, int vertex) {
        Stack stack = new Stack();
        int[] visited = new int[graph.vertices()];
        //System.out.println(Arrays.toString(visited));
        //stack.push(vertex);
        int currentNumb = 0;
        boolean[] cyclic = new boolean[graph.vertices()];
        for (int i = 0; i< visited.length; i++) {
            if (visited[i] == 0) {
                //System.out.println("current numb:"+currentNumb);
                currentNumb = Math.abs(currentNumb);
                //System.out.println("current numb:"+currentNumb);
                currentNumb++;
                stack.push(i);
            }
            while (!stack.isEmpty()) {
                int current = (int) stack.pop();
                if (visited[current] == 0) {
                    visited[current] = currentNumb;
                    currentNumb = currentNumb*(-1);
                    SymbolGraph.Vertex currentVertex = graph.get(current);
                    List<SymbolGraph.Vertex> neighbors = currentVertex.neighbors();
                    for (SymbolGraph.Vertex neighbor : neighbors) {
                        if (visited[neighbor.index()] == 0) {
                            stack.push(neighbor.index());
                        }
                    }
                } else {
                    cyclic[Math.abs(currentNumb)] = true;
                }
            }
        }
        currentNumb = Math.abs(currentNumb);
        //System.out.println(Arrays.toString(visited));   
        System.out.println("The Graph has " + currentNumb + " components");
        System.out.println("");
        for(int i = 1; i <= currentNumb; i++) {
            System.out.println("Component #" + i +":");
            int count =0;
            int edges = 0;
            boolean bipartite = true;
            for(int element:visited) {
                if(Math.abs(element) == i) {
                    count++;
                    edges+=graph.get(i).neighbors().size();
                    List<SymbolGraph.Vertex> neighbors = graph.get(i).neighbors();
                    for (SymbolGraph.Vertex neighbor : neighbors) {
                        if (visited[neighbor.index()] == element) {
                            bipartite = false;
                        }
                    }
                }
            }
            System.out.print("\t"+count+" Vertices: ");
            for(int e = 0; e < visited.length; e++) {
                if(Math.abs(visited[e]) == i) {
                    System.out.print(graph.get(e).name());
                    System.out.print(", ");
                }
            }
            System.out.println("");
//            System.out.println("count:" + count);
//            System.out.println("edges:" + edges);
            if(cyclic[i]) {
                System.out.println("\t This component is cyclic.");
                System.out.println("\t This component is not a tree");
            } else {
                System.out.println("\t This component is acyclic.");
                if(edges == count){
                    System.out.println("\t This component is a tree");
                } else {
                    System.out.println("\t This component is not a tree");

                }
            }
            //System.out.println(Arrays.toString(visited));   
            if(bipartite) {
                System.out.println("\t This component is bipartite");
            } else {
                System.out.println("\t This component is not bipartite");

            }
            //Checking if its bipartite

            
        }
    }
}
