//Ashvin Ganesan
// Algorithms
// HW 5
// Thursday September 24th 2020

import java.io.Console;

public class Homework_5 {

    public static void main(String[] args) {
        try {
            Tour tour = new Tour(States.continentalStates);
            City start = States.Alabama.capital();
            City end = States.DistOfColumbia.capital();
            double length = tour.length()+ end.distance(start);
            System.out.println(length);
            System.out.println(tour);
        } catch (Exception CityInTourException) {
            System.out.println("something went very wrong :(");
        }
//        DoublyLinkedList<String> list = new DoublyLinkedList<>(args);
//
//        Console console = System.console();
//        if (console == null) {
//            System.out.println("No console");
//            return;
//        }
//
//        boolean done = false;
//        do {
//            String line = console.readLine("Command: ");
//            if (line == null) break;
//
//            String command = "";
//            String value1 = "";
//            String value2 = "";
//
//            String[] fields = line.split(" ");
//            if (fields.length > 0) command = fields[0];
//            if (fields.length > 1) value1 = fields[1];
//            if (fields.length > 2) value2 = fields[2];
//
//            try {
//                switch (command.toLowerCase()) {
//                    case "size":
//                        System.out.println(list.size());
//                        break;
//
//                    case "isempty":
//                        System.out.println(list.isEmpty());
//                        break;
//
//                    case "contains":
//                        System.out.println(list.contains(value1));
//                        break;
//
//                    
//
//                    case "prepend":
//                        list.prepend(value1);
//                        break;
//
//                    case "append":
//                        list.append(value1);
//                        break;
//
//                    case "remove":
//                        list.remove(value1);
//                        break;
//
//                    case "head":
//                        System.out.println(list.getHeadVal());
//                        break;
//
//                    case "tail":
//                        System.out.println(list.getTailVal());
//                        break;
//                    case "test":
//                        list.test(value1);
//                        break;
//
//                    
//                    case "tostring":
//                        System.out.println(list.toString());
//                        break;
//
//                    case "print":
//                        for (String s : list) {
//                            System.out.println(s);
//                        }
//                        break;
//                      
//
//                   
//
//                    case "new":
//                        String[] values = new String[fields.length-1];
//                        for (int i = 1; i < fields.length; i++) {
//                            values[i-1] = fields[i];
//                        }
//                        list = new DoublyLinkedList(values);
//                        break;
//                                
//
//                    case "exit":
//                    case "quit":
//                        done = true;
//                        break;
//
//                    default:
//                        System.out.println("Invalid command: " + command);
//                        break;
//                }
//
//            } catch (Exception e) {
//                System.out.println("Failed: " + e);
//            }
//        } while (!done);
    }

}
