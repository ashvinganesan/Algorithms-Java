
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Ashvin G
//HW 15
public class Homework_15 {

    public static void main(String[] args) throws FileNotFoundException {
        BinarySearchTree<String, Integer> tree = new BinarySearchTree();
        File tale = new File("TaleOfTwoCities.txt"); //this doesn't read from my computer but this is what you said I should do on hw 12. 
        Scanner scnr = new Scanner(tale);
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();
            if (tree.contains(line)) {
                Integer newValue = tree.find(line) + 1;
                tree.set(line, newValue);
            } else {
                tree.add(line, 1);
            }
        }
        tree.inOrderTraversal(
                new BinarySearchTree.Visit<String, Integer>() {
            @Override
            public void visit(String key, Integer value) {
            System.out.println(key + " (" + value + ")");
            }
            });
        int h = tree.height();
        System.out.println("the hieght is " + h);
        //Height is 34
        //10778 unique words
        // optimally this would be a heigh of 14 as 2^14 is 16384 
        // This means that this tree is not very well balanced It is over double the height it should be. 
    }
}
