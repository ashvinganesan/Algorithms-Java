// Ashvin Ganesan
// Algorithms-H
// Mr. Paige
// HW #4
// Tuesday September 15th 2020

public class Homework_4 {

    public static void main(String[] args) {
        Tour forwardTour = new Tour();
        Tour reverseTour = new Tour();

        for (String arg : args) {
            State state = States.find(arg);
            if (state != null) {
                try {
                    //System.out.println(state);
                    forwardTour.append(state.capital());
                    reverseTour.prepend(state.capital());
                }catch(Tour.CityInTourException ex) {
                    System.out.println("in catch block");
                    System.out.println(ex);
                }
               
            } else {
                System.out.println("Invalid state: " + arg);
            }
        }
//        System.out.println(forwardTour);
//        System.out.println(reverseTour);
        System.out.println(forwardTour + " = " + forwardTour.length() + " miles");
        System.out.println(reverseTour + " = " + reverseTour.length() + " miles");
    }
}
