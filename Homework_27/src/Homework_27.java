/*
Ashvin Ganesan
Homework 27
Wednesday, June 2nd, 2021
 */
public class Homework_27 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Parser parser = new Parser();

		for (String s : args) {
			try {
				parser.parse(s);
				System.out.println(s + ": OK");
			} catch (Parser.SyntaxError e) {
				System.out.println(s + ": " + e.getMessage());
			}
		}
    }

}
