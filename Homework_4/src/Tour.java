// Ashvin Ganesan
// Algorithms-H
// Mr. Paige
// HW #4
// Tuesday September 15th 2020

import java.util.Iterator;

public class Tour implements Iterable<City> {
    Array<City> arr;
    // A class to represent a TSP tour.
    // You MUST use your variable-sized array ADT
    // in your implementation of this class.
    public Tour() {
        arr  = new Array(100);
    }

    public void append(City city) throws CityInTourException {
        if(contains(city)== true) {
            throw new CityInTourException(city.name());
        }
        arr.append(city);
    }
    public class CityInTourException extends Exception {
        public CityInTourException(String city) {
            super("city " + city + " is already in the tour");
        }
    }
    public boolean contains(City city) {
        return arr.contains(city);
    }
    public void prepend(City city) throws CityInTourException {
        if(contains(city)== true) {
            throw new CityInTourException(city.name());
        }
        arr.prepend(city);
    }

    public int size() {
        return arr.size(); 
    }

    public double length() {
        // Use the iterator from your Array class.
        // Either explicity (calling hasNext and next)
        // or implicityly using a foreach loop.
        if (arr.size() == 0) {
            return 0;
        }
        City previous = arr.get(0);
        double length = 0;
        for(City city: arr) {
            length += previous.distance(city); // the first time it iterates through it will add 0 to it. 
            previous = city;
        }
        
        return length;// This method is off by a a bit, but I'm not entirely sure why. I'm pretty sure it has to come from the distance function
        // in city class. 
    }

    @Override
    public String toString() {
        String cities = "";
        for(City city: arr) {
            cities+= city.name() +", ";
        }
        return (cities);
    }

    @Override
    public Iterator<City> iterator() {
        return arr.iterator(Array.Direction.FORWARD);
    }
}
