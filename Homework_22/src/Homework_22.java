/*
Ashvin Ganesan
April 26th 2020
algorithms-h
Homework 22
 */
public class Homework_22 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] arr = new int[args.length-1];
        int target = Integer.parseInt(args[args.length-1]);
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(args[i]);
        }
        Recursive a = new Recursive(arr, target);
        Memoized b = new Memoized(arr,target);
        Iterative c = new Iterative(arr,target);
        System.out.println("Recursive: " + a.getWays());
        System.out.println("Memoized: " + b.getWays());
        System.out.println("Iterative: " + c.getWays());
        //c.print();

    }
    
}
