
import java.awt.Color;

//Ashvin Ganesan
//Algorithms
//HW 2b
// Tuesday september 1st
public class Homework_3 {

    public static void main(String[] args) {
        Parameters parameters = new Parameters(args);
        System.out.println("XMIN = " + parameters.xmin());
        System.out.println("XMAX = " + parameters.xmax());
        System.out.println("YMIN = " + parameters.ymin());
        System.out.println("YMAX = " + parameters.ymax());
        System.out.println("LIMIT = " + parameters.limit());
        System.out.println("WIDTH = " + parameters.width());
        System.out.println("HEIGHT = " + parameters.height());
        System.out.println("COLOR = " + parameters.color());
        System.out.println("errors = " + parameters.errors());
        Color color = parameters.color(true);

        Canvas canvas = new Canvas(parameters.height(), parameters.width());
        canvas.setXRange(parameters.xmin(), parameters.xmax());
        canvas.setYRange(parameters.ymin(), parameters.ymax());
        double xstep = (parameters.xmax() - parameters.xmin()) / parameters.width();
        double ystep = (parameters.ymax() - parameters.ymin()) / parameters.height();
        for (double x = parameters.xmin(); x < parameters.xmax(); x += xstep) {
            for (double y = parameters.ymin(); y < parameters.ymax(); y += ystep) {
//                System.out.println("x = " + x);
//                System.out.println("y = " + y);
                Complex number = new Complex(x, y);
                Mandelbrot set1 = new Mandelbrot(1000);
                Mandelbrot set2 = new Mandelbrot(100);
                Mandelbrot set3 = new Mandelbrot(10);
                if (set1.isInMandelBrotSet(number)) {
                    canvas.set(x, y, color);
                    //canvas.set(x, y, Color.BLUE);
                }
                //Colored version
//                } else if (set2.isInMandelBrotSet(number)) {
//                    canvas.set(x, y, Color.CYAN);
//                } else if (set3.isInMandelBrotSet(number)) {
//                    canvas.set(x, y, Color.GREEN);
//                } else {
//                    canvas.set(x, y, Color.RED);
//                }
            }
        }
        canvas.display("Mandelbrot set image");
    }
}
