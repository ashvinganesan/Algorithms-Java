/*
Ashvin Ganesan
Mr. Paige
Algorithms-H
HW#11
Friday December 18th 2020
 */
public class Homework_11 {

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        for (String arg : args) {
            System.out.println(arg);
            list.append(arg);
        }
//        String a = "0123";
//        System.out.println("test");
//        System.out.println(list.getChar(a, 3));
        //System.out.println(list.size());
        System.out.println("--------------");
        list.sort();
        for (String item : list) {
            System.out.println(item);// Mr Paige your answer solution is wrong you have two before three, which isn't alphabetically correct. 
        }
    }

}
