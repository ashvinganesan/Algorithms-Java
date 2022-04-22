
import java.util.Arrays;

/*
Ashvin Ganesan
April 26th 2020
algorithms-h
Homework 22
 */

public class Recursive {
    private int[] denominations;
    private int target;
    public Recursive(int[] denominations,int sum) {
        this.denominations = denominations;
        this.target = sum;
    }
    public int getWays() {
        return calculateWays(denominations, target);
    }
    private int calculateWays(int[] d, int sum) {
        int total = 0;
        if(d.length == 1) {
            if(sum%d[0]==0) {
                //System.out.println("returning 1");
                return 1;
            }
            else  {
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
                return calculateWays(temp, sum) + calculateWays(d, sum-d[d.length-1]);
            }
//            System.out.println("simple calculation");
            return calculateWays(temp, sum);
        }
        System.out.println("something went wrong :(");
        return -1;
        
    }
}
