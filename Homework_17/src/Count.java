
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ashvin
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Count {

    public static int CountAll(File f) throws FileNotFoundException {
        int count = 0;
        File file = f;
        Scanner scnr = new Scanner(file);
        while(scnr.hasNextLine()) {
            String line = scnr.nextLine();
            count++;          
        }
        return count;
    }

    
    public static int CountBlanks(File f) throws FileNotFoundException {
        File file = f;
        int count = 0;
        Scanner scnr = new Scanner(file);
        while(scnr.hasNextLine()) {
            String line = scnr.nextLine();
            if(line.equals(line.trim())) {
                count++;        
            }
        }
        
        return count;
    }
    private static int CountChoose(File f, int m) throws FileNotFoundException{
        if(m == 0) {
            return CountAll(f);
        }
        if(m == 1) {
            return CountBlanks(f);
        }
        else {
            return CountLines(f);
        }

    }
    public static int CountLines(File f) throws FileNotFoundException {
        File file = f;
        int count = 0;
        Scanner scnr = new Scanner(file);
        while(scnr.hasNextLine()) {
            String line = scnr.nextLine();
            if(!line.equals(line.trim())) {
                count++;        
            }
        }
        return count;
    }
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static String readLine(String prompt) {
        try {
            //System.out.print(prompt);
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
    
    private static String getWord(String line, int index) {
        String[] words = line.split("[ \t]+");
        if (words.length > index) {
            return words[index];
        } else {
            return null;
        }
    }

    private static String getCommand(String line) {
        return getWord(line, 0);
    }
    
    private static String[] getVars(String line) {
        int count = 1;
        String[] arr = new String[100];
        while(getWord(line, count) != null && count <100) {
            arr[count-1] = getWord(line, count);
            count++;
        }
        return arr;
    }
    
    /**
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        //0 all count 1 means only blanks 2 means only non-blank
        int mode = 0;
        int count = 0;
        String[]arr = new String[args.length-1];
        for (String arg:args) {
            
            if(arg.equals("-b")) {
                mode = 1;
            }
            else if (arg.equals("-i")) {
                mode = 2;
            } 
            else if (arg.equals("-a")) {
                mode =0;
            }
            else {
                //System.out.println("IN ELSE");
                arr[count] = arg;
                count++;
            }
        }
       
        if (arr.length != 0) {
           //System.out.println("FILENAME CASE");
           int total = 0;
           for(int i = 0; i < arr.length; i++)  {
               File n = new File(arr[i]);
               try {
                   
                   int lines = CountChoose(n, mode);
                   total += lines;
                   System.out.printf("%8d %s\n", lines, n.getName());
               }
               catch(FileNotFoundException e) {
                   System.out.println(n.getName()+ " was not found");
               }
               
           }
            System.out.printf("%8d %s\n", total, "TOTAL");
            return;         
        }
        Scanner scnr = new Scanner(reader);
        int totalcount = 0;
        int blankcount = 0;
        String filename = "";
        while(scnr.hasNextLine()) {
            String line = scnr.nextLine();
            //System.out.println("*** " + line);
            
            if (line.trim().equals("")) {
                //System.out.println("blank *** " + line);
                blankcount++;  
            }
            
            totalcount++;
        }
        if(mode == 0) {
            System.out.printf("%8d %s", totalcount, filename);
        }
        else if (mode == 1) {
            System.out.printf("%8d %s", blankcount, filename);
        }
        else {
            System.out.printf("%8d %s", (totalcount-blankcount), filename);
        }
        
        
//        
//       
//        while (line != null) {
//            String command = getCommand(line);
//            String[] v = getVars(line);
//            if (command == null) {
//                continue;
//            }
//            switch (command) {
//                case "a":
//                    for(int i = 0; i < v.length;i++) {
//                        File temp = new File(v[i]);
//                        String result = CountAll(temp);
//                    }
//                    break;
//                case "b":
//                    for(int i = 0; i < v.length;i++) {
//                        File temp = new File(v[i]);
//                        String result = CountBlanks(temp);
//                    }
//                    break;
//                case "i":
//                    for(int i = 0; i < v.length;i++) {
//                        File temp = new File(v[i]);
//                        String result = CountLines(temp);
//                    }
//                    break;
//                case "en"
//                       
//                    
//            }
//        }
    }
}