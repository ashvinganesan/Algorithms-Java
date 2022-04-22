/*
Ashvin Ganesan
April 26th 2020
algorithms-h
Homework 22
 */
public class Iterative {
    private int[] denominations;
    private int[][] arr;
    private int target;
    boolean hasCalculated;
    //denominations is assumed to be at least length 2;
    public Iterative(int[] denominations,int sum) {
        target = sum;
        this.denominations = denominations;
//        System.out.println("dlen: "+ denominations.length);
//        System.out.println("target len:" + target+1);
        arr = new int[denominations.length][target+1];
        hasCalculated = false;
    }
    private int calculateWays() {
        for(int i = 0; i < arr[0].length; i++) {
            //sets the first row of the matrix 
            if (i % denominations[0] == 0) {
                arr[0][i] = 1;
            }
        }
        for(int i = 1; i < denominations.length; i++) {
            for(int j = 0; j<arr[0].length;j++) {
                arr[i][j] = arr[i-1][j];
                if(j >= denominations[i]) {
                    //print();
//                    System.out.println("");
//                    System.out.println("");
//                    System.out.println("i: " + i);
//                    System.out.println("j:" + j);
//                    System.out.println("i: " + i);

                    arr[i][j]= arr[i][j]+ arr[i][j-denominations[i]];
                }
                
                
            }
        }
        return arr[denominations.length-1][target];
    }
    public int getWays() {
        if(hasCalculated) {
            return arr[denominations.length+1][target];
        }
        hasCalculated = true;
        return calculateWays();
    }
    public void print() {
        for(int[] l: arr) {
            for(int k: l) {
                System.out.print(k + "  ");
            }
            System.out.println("");
        }
    }
}
