//Ashvin Ganesan
//Homework 23
//Algorithms
//Friday May 7th 2021
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Farthest {
    private State start;
    private Set<String> unvisited;
    private Map<String, Double> distance;
    private Map<State, State> parent;
    private boolean can;
    
    
    public Farthest(State name, boolean canada) {
        can = canada;
        start = name;
        State[] states;
        if(canada) {
            states = Canada.states;
        }
        else {
            states = States.states;
        }
        unvisited = new HashSet<>();
        distance = new HashMap<>();
        parent = new HashMap<>();
        
        for(State state: states) {
            unvisited.add(state.name());
            distance.put(state.name(), Double.MAX_VALUE);
        }
        distance.put(start.name(), 0.0);
        dijkstra();
    }
    
    public Farthest(String name,boolean canada) {
        this(Canada.find(name), canada);//This line of code makes me sad IDK how to deal with canada.find(name) vs state.find(name) bc this has to be the 1st line of the constructor
    }
    public Farthest(String name) {
        this(States.find(name), false);
    }
    
    
    public double farthestDistance() {
        Map.Entry<String, Double> highest = null;
        for (Map.Entry<String,Double> entry : distance.entrySet()) {
            if(highest == null) {
                if(!unvisited.contains(entry.getKey()))
                    highest = entry;
            }
            else if(highest.getValue() < entry.getValue()) {
                if(!unvisited.contains(entry.getKey()))
                    highest = entry;
            }
                
        }
        return highest.getValue();
    }
    public String farthestState(){
//        System.out.println("____________________");
//        for(String s: unvisited) {
//            System.out.println(s);
//        }
        Map.Entry<String, Double> highest = null;
        for (Map.Entry<String,Double> entry : distance.entrySet()) {
            if(!unvisited.contains(entry.getKey()))
                if(highest == null) {
                    highest = entry;
                }
                else if(highest.getValue() < entry.getValue()) {
                    highest = entry;
                }
                
        }
        if(can) {
            return Canada.find(highest.getKey()).code();
        }
        return States.find(highest.getKey()).code();
    }
    public String lowestDist() {
        Double lowest = null;
        String name = "";
        for (String s: unvisited) {
            if(lowest == null) {
                lowest = distance.get(s);
                name = s;
                
            }
            else if(lowest > distance.get(s)) {
                lowest = distance.get(s);
                name = s;
            }
                
        }
        return name;
    }
    private void dijkstra() {
        parent.put(start, null);
        if(start.neighbors().length == 0) {
            unvisited.remove(start.name());
            return;
        }
        
        
        while(!unvisited.isEmpty()) {
            String currentNode = lowestDist();
            //System.out.println("Current Node is " + currentNode);
            State currentState; 
            if (!can) {
                currentState= States.find(currentNode);
            } else {
                currentState = Canada.find(currentNode);
            }
            State[] neighbors = currentState.neighbors(); 
            if(neighbors.length == 0) {
                break;
            }
            unvisited.remove(currentNode);
            Double tentativeDist = 100000.0;
            for(State neighbor: neighbors) {
                tentativeDist = distance.get(currentNode) + currentState.capital().distance(neighbor.capital());
                if(tentativeDist < distance.get(neighbor.name())) {
                    distance.put(neighbor.name(), tentativeDist);
                    parent.put(neighbor, currentState);
                }        
            }
        }
        
    }
    
    public String getPathToFarthestState() {
        State current = States.find(farthestState());
        String str = "";
        while(parent.get(current) != null) {
            str = current.code() + " " + str;
            //System.out.print(current);
            current = parent.get(current);
        }
        str = start.code() + " " + str;
        return str;
    }
    
}
