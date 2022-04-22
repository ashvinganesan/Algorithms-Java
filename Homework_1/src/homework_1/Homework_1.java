//Ashvin Ganesan
//Algorithms
// HW 1
// August 24th 2020
package homework_1;


public class Homework_1 {

 
    public static void main(String[] args) {
        Mandelbrot mand = new Mandelbrot(100);
        double real = 0.1;
        double imaginary = 0.2;
        if(mand.isInMandelBrotSet(new Complex(real, imaginary))) {
           System.out.println(real + " "+ imaginary+"i is in the mandelbrot set");
        } else {
            System.out.println(real + " "+ imaginary+"i is not in the mandelbrot set");
        }
        
    }
    
}
