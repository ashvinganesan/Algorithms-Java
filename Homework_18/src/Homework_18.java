 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


public class Homework_18 {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

//        File tale = new File("/Users/ashvin/Documents/Algorithms/Homework_18/src/TaleOfTwoCities.txt"); 
        LinearProbingHashMap<String,Integer> map = new LinearProbingHashMap<>(1,6);
        Scanner scnr = new Scanner(reader);
        //Scanner scnr = new Scanner(tale);
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();
            if(map.contains(line)) {
                map.add(line, (map.find(line) + 1));
            }
            else{
                map.add(line, 1);
            }
            
        }
        LinearProbingHashMap.Node[] n = map.nodes();
        
        //LinearProbingHashMap.Node[] frequent = new LinearProbingHashMap.Node[20];
        MinPriorityQueue mostFrequent = new MinPriorityQueue(20);
        MaxPriorityQueue correctOrder = new MaxPriorityQueue(20);
 
        for(int i = 0; i< 20; i++) {
            mostFrequent.add(n[i]);
        }
        for(int i = 20; i< n.length;i++) {
            int compare = mostFrequent.min().compareTo(n[i]);
            if(compare > 0) {
                
            }else {
                mostFrequent.removeMin();
                mostFrequent.add(n[i]);
            }
        }
        while(!mostFrequent.isEmpty()) {
            correctOrder.add(mostFrequent.removeMin());
        }
        while(!correctOrder.isEmpty()) {
            System.out.println(correctOrder.removeMax());
        }
        System.out.println("");
        System.out.println("");
        System.out.println("Probes: " + map.searches);
        System.out.println("Hits: " +map.hits);
        System.out.println("Misses: " + map.misses);     
        System.out.println("Avg: " +(map.searches/((float)(map.hits+map.misses))));
        System.out.println("Max: " + map.max);
            
//        
//        Arrays.sort(n);
//        for(int i = n.length-20; i < n.length; i++) {
//            frequent[n.length-(i+1)] = n[i];
//        }
//        for(LinearProbingHashMap.Node node: frequent) {
//            System.out.println(node);
//        }
//        for(LinearProbingHashMap.Node j: n) {
//            
//        }
//        map.traverse(
//                new LinearProbingHashMap.Visit<String, String>() {
//            @Override
//            public void visit(String key, String value) {
//            System.out.println(key + " (" + value + ")");
//            }
//            });
        
    }
    
}
