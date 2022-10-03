import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Polynomial {
	public double[] coefficients;
	public int[] powers;
	
	public Polynomial() {
		this.coefficients = new double[1];
		this.powers = new int[1];
	}
	

	public Polynomial(double[] coefficients, int[] powers) {
		this.coefficients = new double[coefficients.length];
		this.powers = new int[powers.length];
		for (int i = 0; i < coefficients.length; i++) {
			this.coefficients[i] = coefficients[i];
			this.powers[i] = powers[i];
		}
	}
	
	public Polynomial(File f) throws Exception {
        Scanner r = new Scanner(f);
        String s = r.nextLine();
        r.close();
        int l = 0;
        for (int i = 1; i < s.length(); i++)
            if (s.charAt(i) == '+' || s.charAt(i) == '-')
                l++;
        int[] signs = new int[l + 1];
        if (s.charAt(0) != '-')
            signs[0] = 1;
        for (int i = 1; i < s.length(); i++)
            if (s.charAt(i) == '+')
                signs[i] = 1;
        s = s.replace('+', ' ');
        s = s.replace('-', ' ');
        String[] a = s.split(" ");
        this.coefficients = new double[a.length];
        this.powers = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            if (a[i].charAt(0) != 'x')
                this.coefficients[i] = Double.parseDouble(a[i].substring(0, 1));
            else
                this.coefficients[i] = 1;
            if (signs[i] == 0)
                this.coefficients[i] *= -1;
            if (s.contains("x"))
                this.powers[i] = Integer.parseInt(a[i].substring(2, 3));
            
        }
        int t;
		double u;
	    boolean swapped;
	    do {
	    	swapped = false;
	    	for (int i = 0; i < this.powers.length - 1; i++) {
	    		if (this.powers[i] > this.powers[i + 1]) {
	    			u = this.coefficients[i];
	    			t = this.powers[i];
	    			this.coefficients[i] = this.coefficients[i + 1];
	    			this.powers[i] = this.powers[i + 1];
	    			this.coefficients[i + 1] = u;
	    			this.powers[i + 1] = t;
	    			swapped = true;
	    		}
	    	}
	   } while (swapped);
    }

	private boolean in(int[] a, int b) {
		for (int value : a)
			if (value == b)
				return true;
		return false;
	}
	
	private int index(int a, int[] b) {
		for (int i = 0; i < b.length; i++)
			if (a == b[i])
				return i;
		return -1;
	}
	
	public Polynomial add(Polynomial p) {
		int length = this.powers.length;
		for (int v : p.powers)
			if (!in(this.powers, v))
				length++;
		double[] c = new double[length];
		int[] q = new int[length];
		for (int i = 0; i < this.powers.length; i++) {
			c[i] = this.coefficients[i];
			q[i] = this.powers[i];
		}
		for (int i = 0; i < p.powers.length; i++) {
			if (!in(this.powers, p.powers[i])) {
				c[i + this.coefficients.length] = p.coefficients[i];
				q[i + this.powers.length] = p.powers[i];
			} else {
				c[index(this.powers[i], q)] += p.coefficients[i];
			}
		}
		int t;
		double s;
	    boolean swapped;
	    do {
	    	swapped = false;
	    	for (int i = 0; i < q.length - 1; i++) {
	    		if (q[i] > q[i + 1]) {
	    			s = c[i];
	    			t = q[i];
	    			c[i] = c[i + 1];
	    			q[i] = q[i + 1];
	    			c[i + 1] = s;
	    			q[i + 1] = t;
	    			swapped = true;
	    		}
	    	}
	   } while (swapped);
	   return new Polynomial(c, q); 
	}
	
	public double evaluate(double x) {
		double sum = 0;
		for (int i = 0; i < this.coefficients.length; i++)
			sum += Math.pow(coefficients[i], powers[i]);
		return sum;
	}
	
	public boolean hasRoot(double x) {
		return evaluate(x) == 0;
	}
	
	public Polynomial multiply(Polynomial p) {
        Polynomial[] d = new Polynomial[this.coefficients.length];
        for (int i = 0; i < d.length; i++)
            d[i] = new Polynomial(p.coefficients, p.powers);
        for (int i = 0; i < d.length; i++) {
            for(int j = 0; j < d[i].coefficients.length; j++) {
                d[i].coefficients[j] = d[i].coefficients[j] * this.coefficients[i];
                d[i].powers[j] = d[i].powers[j] + this.powers[i];
            }
        }
        Polynomial q = new Polynomial(d[0].coefficients, d[0].powers);
        for (int i = 1; i < d.length; i++)
            q = q.add(d[i]);
        return q;
    }
	
	public void saveToFile(String fileName) throws Exception {
		FileWriter f = new FileWriter(new File(fileName));
	    for (int i = 0; i < this.coefficients.length; i++) {
	    	String left = Double.toString(this.coefficients[i]);
	    	String right = Integer.toString(this.powers[i]);
	    	if (this.coefficients[i] % 1 == 0)
	    		left = Integer.toString((int)(this.coefficients[i]));
	    	if (left.equals("-1") || left.equals("1"))
	    		left = "";
	    	if ((right.equals("0")) || (right.equals("1")))
	    		right = "";
	    	f.write(left);
	    	if (this.powers[i] != 0) f.write("x");
	    	f.write(right);
	    	if (i < this.coefficients.length - 1) {
	    		if (left.charAt(0) == '-') f.write("-");
	    		if (left.charAt(0) != '-') f.write("+");
	    	}
	    }
	    f.close();
	  }
}
