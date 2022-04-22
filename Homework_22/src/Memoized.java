
import java.util.Arrays;

/*
Ashvin Ganesan
April 26th 2020
algorithms-h
Homework 22
 */

public class Memoized {
    private int[] denominations;
    private int target;
    private int[][] arr;

    public Memoized(int[] denominations,int sum) {
        this.denominations = denominations;
        //I'm doing this sorting bc it shouldn't really effect the runtime and it makes it go smoother when testing. 
        this.target = sum;
        arr = new int[denominations.length][target+1];
        for(int i = 0; i< arr.length;i++) {
            for(int j= 0; j < arr[i].length; j++) {
                arr[i][j] = -1;
            }
        }
    }
    public int getWays() {
        return calculateWays(denominations, target);
    }
    private int calculateWays(int[] d, int sum) {
        //print();
//        if(arr[d.length-1][sum] != -1) {
//            return arr[d.length-1][sum];
//        }
        int total = 0;
        if(d.length == 1) {
            if(sum%d[0]==0) {
                //System.out.println("returning 1");
                arr[d.length-1][sum] =1;
                return 1;
            }
            else  {
                arr[d.length-1][sum] =0;
                return 0;
            }
        }
        if(d.length > 1) {
            int[] temp = new int[d.length-1];
            for(int i = 0;i < temp.length; i++) {
                temp[i] = d[i];
            }
            if(sum-d[d.length-1] >= 0) {
//                System.out.println("sum: " + sum );
//                System.out.println("d : " + d.length);
//                System.out.println("last element of d: " + d[d.length-1]);
                //System.out.println("nonsimple calculation");  
                int tempVal = calculateWays(temp, sum) + calculateWays(d, sum-d[d.length-1]);
                arr[d.length-1][sum] = tempVal;
                return calculateWays(temp, sum) + calculateWays(d, sum-d[d.length-1]);
            }
            //System.out.println("simple calculation");
            int tempVal = calculateWays(temp, sum);
            arr[d.length-1][sum] = tempVal; 
            return calculateWays(temp, sum);
        }
        //System.out.println("something went wrong :(");
        return -1;
        
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
