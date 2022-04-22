
import java.util.Comparator;

//Ashvin G
// Mr. paige
// algorithms
//11/19/2020
public class Homework_10 {

    
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        for(String arg: args) {
            list.append(arg);
        }
        
        
//        list.append("a");
//        list.append("c");
//        list.append("b");
//        list.append("e");
//        list.append("z");
//        list.append("h");
//        list.append("y");
//        list.append("d");
//        System.out.println(list);

        list.sort(new SortbyValue());
        System.out.println(list);    
    }
    
}
class SortbyValue implements Comparator<String> {
        // Used for sorting in ascending order of
        // name

        @Override
        public int compare(String a, String b) {
            return b.toUpperCase().compareTo(a.toUpperCase());
        }
}