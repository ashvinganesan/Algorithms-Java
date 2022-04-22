import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
Ashvin Ganesan
Algorithms
Thursday Jan 14th 2020
Homework 12
*/
public class Homework_12 {

    public static void main(String[] args)  throws FileNotFoundException {
        PQue longestWords = new PQue(10);    
        File dict = new File("/Users/ashvin/Documents/Algorithms/Homework_12/src/dictionary.txt");
        Scanner scnr = new Scanner(dict);
        while(scnr.hasNextLine()) {
            String line = scnr.nextLine();
            longestWords.attemptToAdd(line);
            
        }
        longestWords.print();
    }
    
}
