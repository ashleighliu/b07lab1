public class Polynomial {
	double [] coefficients;
	
	public Polynomial() {
		this.coefficients = new double[1];
	}
	
	public Polynomial(double [] coefficients) {
		this.coefficients = new double [coefficients.length];
		for (int i=0; i<coefficients.length; i++) 
			this.coefficients[i] = coefficients[i];
	}
	
	public Polynomial add (Polynomial p) {
		double[] c;
		if (p.coefficients.length > this.coefficients.length) {
			c = new double[p.coefficients.length];
			for (int i = 0; i < p.coefficients.length; i++) 
				c[i] = p.coefficients[i];
			for (int i = 0; i < this.coefficients.length; i++)
				c[i] += this.coefficients[i];
		} else {
			c = new double[this.coefficients.length];
			for (int i = 0; i < this.coefficients.length; i++) 
				c[i] = this.coefficients[i];
			for (int i = 0; i < p.coefficients.length; i++)
				c[i] += p.coefficients[i];
		}
		return new Polynomial(c);
	}
	
	public double evaluate (double x) {
		double sum = 0;
		for (int i = 0; i < this.coefficients.length; i++) {
			double c = x;
			for (int j = 0; j < i; j++)
				c *= x;
			sum += coefficients[i] * c;
		}
		return sum;
	}
	
	public boolean hasRoot (double y) {
		return evaluate(y) == 0;
	}

}

//
//public class Main {
//	public static void main(String[] args) {
//		Polynomial p = new Polynomial();
//		//p will be just the zero polynomial.
//		Polynomial q = new Polynomial({1, 2, 3});
//		//q will be the polynomial 3x^2 + 2x + 1.
//		p.add(q);
//	}
//}