public class Driver { 
	public static void main(String [] args) { 
		double[] d1 = {1, 7, 8};
		int[] i1 = {1, 2, 3};
		double[] d2 = {6, 7, 9};
		int[] i2 = {0, 1, 2};
		Polynomial t1 = new Polynomial(d1, i1);
		Polynomial t2 = new Polynomial(d2, i2);
		Polynomial t = t1.multiply(t2);
		System.out.print("t(0.5) = " + t.evaluate(0.5));
		} 
}


