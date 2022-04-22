
import java.util.Arrays;

/*
Ashvin ganesan
Algorithms-H
Monday October 26th 2020
HW 7

 */
public class ClosestPair {
    public double[] findPair(double[] arr) {
        double[] array = arr;
        Arrays.sort(array); //I'm asuming that this takes O(NlnN)as such its still linearithmic
        double smallest = array[0];
        double largest = array[1];
        double distance = largest -smallest;
        for(int i = 0; i < array.length -1; i++ ) {// I decided to just remove the boundary and check it manually because it was easier
            if (array[i+1]-array[i] < distance) {
                smallest = array[i];
                largest = array[i+1];
                distance = largest-smallest;                     
            }
        }
        if(array[array.length -1] -array[array.length -2] < distance) {
            smallest = array[array.length-2];
            largest = array[array.length-1];
            distance = largest -smallest;
        }
        double[] solution = new double[2];
        solution[0] = smallest;
        solution[1] = largest;
        //System.out.println(""+smallest+" " + largest);
        return solution;
    }
}
