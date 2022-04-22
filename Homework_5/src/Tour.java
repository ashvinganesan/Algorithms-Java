// Ashvin Ganesan
// Algorithms-H
// Mr. Paige
// HW #5
// Thursday September 24th 2020

import java.util.Iterator;

public class Tour implements Iterable<City> {
    DoublyLinkedList<City> cities = new DoublyLinkedList<>();
    // A class to represent a TSP tour.
    // You MUST use your variable-sized array ADT
    // in your implementation of this class.
    public Tour(State[] states) throws CityInTourException {
        for(State state: states)  {
            try {
                City current = state.capital();
                cities.append(current);
            } catch (Exception CityInTourException) {
                System.out.println("city " + state.capital() +" is already in the tour");
            }
           
        } 
            
    }

    public void append(City city) throws CityInTourException {
        if(contains(city)== true) {
            throw new CityInTourException(city.name());
        }
        cities.append(city);
    }
    public class CityInTourException extends Exception {
        public CityInTourException(String city) {
            super("city " + city + " is already in the tour");
        }
    }
    public boolean contains(City city) {
        return cities.contains(city);
    }
    public void prepend(City city) throws CityInTourException {
        if(contains(city)== true) {
            throw new CityInTourException(city.name());
        }
        cities.prepend(city);
    }

    public int size() {
        return cities.size(); 
    }

    public double length() {
        // Use the iterator from your Array class.
        // Either explicity (calling hasNext and next)
        // or implicityly using a foreach loop.
        if (cities.size() == 0) {
            return 0;
        }
        City previous = cities.get(0);
        double length = 0;
        for(City city: cities) {
            length += previous.distance(city); // the first time it iterates through it will add 0 to it. 
            previous = city;
        }
        
        return length;// This method is off by a a bit, but I'm not entirely sure why. I'm pretty sure it has to come from the distance function
        // in city class. 
    }

    @Override
    public String toString() {
        String output = "";
        for(City city: cities) {
            output+= city.name() +"\n";
        }
        return (output);
    }

    @Override
    public Iterator<City> iterator() {
        return cities.iterator(DoublyLinkedList.Direction.FORWARD);
    }
}
