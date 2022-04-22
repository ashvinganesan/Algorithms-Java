import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/*
Ashvin Ganesan
Algorithms
Thursday Jan 14th 2020
Homework 12
*/
public class Read {

    public static void readFile(String filename) throws IOException {
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
    }

    public static void readStandardInput() throws IOException{
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            for (String arg : args) {
                try {
                    readFile(arg);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            try {
                readStandardInput();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
