//Ashvin Ganesan
//Algorithms
// HW 3
// Tuesday september 8ths

public class Mandelbrot {
    private int m;
    
    public Mandelbrot(int m) {
        this.m = m;
    }
    public boolean isInMandelBrotSet(Complex c) {
        Complex z= new Complex(0,0);
        for(int i = 0; i < m; i++) {
            z= z.square().add(c);
            if(z.modulus() >2) {
                return false;
            }
        }
        return true;
    }
 
    public int escapeTime(Complex c){
        Complex z= new Complex(0,0);
        for(int i = 0; i < m; i++) {
            z= z.square().add(c);
            if(z.modulus() >2) {
                return i;
            }
        }
        return m;
    }
    
}
