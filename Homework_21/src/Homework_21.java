//Ashvin Ganesan
// Algorithsm HW 21
public class Homework_21 {

    
    public static void main(String[] args) {
        State[] states = States.continentalStates;
        SymbolGraph g = new SymbolGraph();
        for(State state: states) {
            g.addVertex(state.toString());
            
        }
        for(State state: states) {
            State[] neighbors = state.neighbors();
            for(State n: neighbors) {
                g.addEdge(state.toString(), n.toString());
            }
        }
        GraphProperties r = new GraphProperties(g);
        int radius = r.radius();
        int diameter = r.diameter();
        String center = r.center().name();
        System.out.println("radius is " + radius);
        System.out.println("diamter is " + diameter);
        System.out.println("center is " + center);
    }
    
}
