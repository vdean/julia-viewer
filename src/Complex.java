public class Complex {
	private double re, im;
	public Complex(double a, double b) {
		this.re = a;
		this.im = b;
		
	}
	
	public Complex add(Complex num) {
		double re = this.re + num.re;
		double im = this.im + num.im;
		Complex result = new Complex(re,im);
		return result;
	}
	
	public Complex multiply(Complex num) {
		double re = this.re * num.re - this.im * num.im;
		double im = this.re * num.im + this.im * num.re;
		Complex result = new Complex(re,im);
		return result;
	}
	
	public Complex exp(int exponent) {
		Complex num = new Complex(this.re, this.im);
		Complex result = new Complex(this.re, this.im);
		for (int i = 0; i < exponent - 1; i++) {
			result = result.multiply(num);
		}
		return result;
	}
	
	public double getReal() {
		return this.re;
	}
	
	public double getImaginary() {
		return this.im;
	}
	
	public boolean equals(Object num) {
		if (num == null) {
			return false;
		}
		if (num instanceof Complex) {
			Complex complex = (Complex) num;
			if (this.re == complex.re && this.im == complex.im) {
				return true;
			}
		}
		return false;
	}
	
	public double getMagnitude() {
		return Math.sqrt(Math.pow(this.re, 2) + Math.pow(this.im,2));
	}

}
