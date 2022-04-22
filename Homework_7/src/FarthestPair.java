/*
Ashvin ganesan
Algorithms-H
Monday October 26th 2020
HW 7

 */
public class FarthestPair {

    public double[] findPair(double[] array) {
        double smallest = array[0];
        double largest = array[0];
        for(double dub: array) {
            if (dub > largest ) {
                largest = dub;
            }
            if (dub < smallest) {
                smallest = dub;
            }
        }
        double[] solution = new double[2];
        solution[0] = smallest;
        solution[1] = largest;
        //System.out.println(""+smallest+" " + largest);
        return solution;
    }
}
