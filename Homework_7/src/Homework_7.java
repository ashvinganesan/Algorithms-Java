/*
Ashvin ganesan
Algorithms-H
Monday October 26th 2020
HW 7
 */
public class Homework_7 {
    public static void main(String[] args) {
        double[] arr = new double[6];
        arr[0] = 10.0;
        arr[1] = -3.0;
        arr[2] = 12.0;
        arr[3] = 4.0;
        arr[4] = 7.0;
        arr[5] = 13.0;
        //FarthestPair pair = new FarthestPair();
        ClosestPair pair = new ClosestPair();
        double[] solution = pair.findPair(arr);

        for(double element: solution) {
            System.out.println(element);
        }
        
    }
}
