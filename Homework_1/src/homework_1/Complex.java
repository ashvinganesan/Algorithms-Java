//Ashvin Ganesan
//Algorithms
// HW 1
// August 24th 2020
package homework_1;
public class Complex {
    private static final Complex ZERO = new Complex(0,0);
    private static final Complex ONE = new Complex(1,0);
    private static final Complex I = new Complex(0,1);
    private double real;
    private double imaginary;
    
    
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
    public Complex add(Complex v) {
        return new Complex(this.real+v.real,this.imaginary+v.imaginary);
    }
    public Complex multiply(Complex v) {
        return new Complex(this.real*v.real -this.imaginary*v.imaginary,this.real*v.imaginary +this.imaginary*v.real);
    }
    public Complex subtract(Complex v) {
        return new Complex(this.real-v.real,this.imaginary-v.imaginary);
    }
     
    public boolean equals(Complex v) {
        return (this.real==v.real &&this.imaginary==v.imaginary);
    }
    public Complex square() {
        return multiply(new Complex(real, imaginary));
    }
    public Complex divide(Complex v) {
        return new Complex(this.real/v.real +this.imaginary/v.imaginary,this.real/v.imaginary +this.imaginary/v.real);

    }
    public Complex exponent(double v) {
        if (v == 0) {
            return new Complex(1,0);
        }
        Complex temp = new Complex(this.real, this.imaginary);
        for(int x=0;x < v-1;x++){
            temp = temp.multiply(temp);
        }
        return temp;
    }
    public Complex conjugate() {
        return new Complex(this.real, -this.imaginary);
    }
    public double modulus() {
        return Math.sqrt(real*real + imaginary*imaginary);
    }
    public String toString() {
        if (imaginary>=0){
             return (real + " + "+ imaginary + "i");
        }
        return (real + " - "+ (-imaginary) + "i");
    }
}
