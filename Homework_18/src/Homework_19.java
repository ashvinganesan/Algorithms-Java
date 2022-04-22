
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/*
ashvin Ganesan
Homework 19
Algorithms
 */
public class Homework_19 {

    
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //File tale = new File("/Users/ashvin/Documents/Algorithms/Homework_18/src/TaleOfTwoCities.txt"); 
        Scanner scnr = new Scanner(reader);
        //Scanner scnr = new Scanner(tale);
        int top = 20;
        int step = 1;
        int size = 6;
        boolean linear = false;
        LinearProbingHashMap<String, Integer> lmap = new LinearProbingHashMap<>(step,size);;
        QuadradicProbingHashMap<String,Integer> qmap = new QuadradicProbingHashMap<>(size);;
        //System.out.println("Args: " + Arrays.toString(args));
        if(args.length !=0) {
            
            
            for(int i = 0; i < args.length; i++) {
                if(args[i].equals("-linear")) {
                    //System.out.println("changing to linear");
                    linear = true;
                }
                if(args[i].equals("-quadratic")) {
                    linear = false;
                }
                if(args[i].equals("-top")) {
                    top = Integer.parseInt(args[i+1]);                 
                }
                if(args[i].equals("-step")) {
                    //System.out.println("step is changing");
                    step = Integer.parseInt(args[i+1]);
                }
                if(args[i].equals("-size")) {
                    size = Integer.parseInt(args[i+1]);
                }                 
            }
        } else {
            qmap = new QuadradicProbingHashMap<>();
        }
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();
            if(linear) {
                if(lmap.contains(line)) {
                    lmap.add(line, (lmap.find(line) + 1));
                }
                else{
                    lmap.add(line, 1);
                }
            }
            else {
                if(qmap.contains(line)) {
                    qmap.add(line, (qmap.find(line) + 1));
                }
                else{
                    qmap.add(line, 1);
                }   
            }
            
        }
        LinearProbingHashMap.Node[] lnodes = lmap.nodes();
        QuadradicProbingHashMap.Node[] qnodes = qmap.nodes();
        if(linear) {
            lnodes = lmap.nodes();
        } else {
            qnodes = qmap.nodes();
        }
        
        //LinearProbingHashMap.Node[] frequent = new LinearProbingHashMap.Node[20];
        //System.out.println("top:" +top);
        MinPriorityQueue mostFrequent = new MinPriorityQueue(top);
        MaxPriorityQueue correctOrder = new MaxPriorityQueue(top);
 
        for(int i = 0; i< top; i++) {
            if(linear) {
                mostFrequent.add(lnodes[i]);
            }
            else {
                mostFrequent.add(qnodes[i]);
            }
            
        }
        int nodelen = 0;
        if(linear) {
            nodelen = lnodes.length;
        }
        else{
            nodelen = qnodes.length;
        }
        for(int i = top; i< nodelen;i++) {
            int compare = 0;
            if(linear) {
                compare = mostFrequent.min().compareTo(lnodes[i]);
            }
            else {
                compare = mostFrequent.min().compareTo(qnodes[i]);
            }
            if(compare > 0) {
                
            }else {
                mostFrequent.removeMin();
                if(linear) {
                    mostFrequent.add(lnodes[i]);
                }
                else {
                    mostFrequent.add(qnodes[i]);
                }
            }
        }
        while(!mostFrequent.isEmpty()) {
            correctOrder.add(mostFrequent.removeMin());
        }
        while(!correctOrder.isEmpty()) {
            System.out.println(correctOrder.removeMax());
        }
        //my numbers turned out a bit different than yours maybe because I was checking twice. 
        if(linear) {
            System.out.println("");
            System.out.println("Probes: " + lmap.searches);
            System.out.println("Hits: " +lmap.hits);
            System.out.println("Misses: " + lmap.misses);     
            System.out.println("Avg: " +(lmap.searches/((float)(lmap.hits+lmap.misses))));
            System.out.println("Max: " + lmap.max);
        }
        else {
            System.out.println("");
            System.out.println("Probes: " + qmap.searches);
            System.out.println("Hits: " +qmap.hits);
            System.out.println("Misses: " + qmap.misses);     
            System.out.println("Avg: " +(qmap.searches/((float)(qmap.hits+qmap.misses))));
            System.out.println("Max: " + qmap.max);
        }
        
    }
    
}

    

