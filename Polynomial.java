import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Polynomial{
	double[] non_zero_coefficients;
	int[] exponents;

	public Polynomial(){
		double temp[] = {}; //empty array make
		this.non_zero_coefficients = temp;
		int temp2[] = {};
		this.exponents = temp2;
	}

	public Polynomial(double[] non_zero_coefficients, int[] exponents){
		this.non_zero_coefficients = non_zero_coefficients;
    	this.exponents = exponents;



	}

	public Polynomial(File file) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		reader.close();

		line = line.replaceAll("-", "+-");
		String[] nums = line.split("\\+");
		HashMap<Integer, Double> new_p = new HashMap<Integer, Double>();

		for (String s : nums) {
            s = s.trim();
            if (s.isEmpty()) continue;

            if (s.indexOf('x') == -1) {
                // Constant term
                double constant = Double.parseDouble(s);
                new_p.put(0, new_p.getOrDefault(0, 0.0) + constant);
            } else {
                int xIndex = s.indexOf('x');
                int exp;
                if (xIndex + 1 < s.length()) {
                    // Exponent exists
                    exp = Integer.parseInt(s.substring(xIndex + 1));
                } else {
                    // Exponent is 1 if not specified
                    exp = 1;
                }

                double coefficient;
                if (xIndex == 0) {
                    // Coefficient is 1 or -1
                    coefficient = 1.0;
                } else if (xIndex == 1 && s.charAt(0) == '-') {
                    coefficient = -1.0;
                } else {
                    // Parse the coefficient
                    coefficient = Double.parseDouble(s.substring(0, xIndex));
                }

                new_p.put(exp, new_p.getOrDefault(exp, 0.0) + coefficient);
            }
        }
		int[] temp_e = new int[new_p.size()];
		double[] temp_nzc = new double[new_p.size()];
		int count = 0;
		for (Map.Entry<Integer, Double> entry : new_p.entrySet()) {
		    temp_e[count] = entry.getKey();
		    temp_nzc[count] = entry.getValue();
		    count++;
		}
		this.non_zero_coefficients = temp_nzc;
		this.exponents = temp_e;
	}

	public Polynomial add(Polynomial p2) {
        HashMap<Integer, Double> result = new HashMap<>();
        for (int i = 0; i < this.exponents.length; i++) {
            result.put(this.exponents[i], this.non_zero_coefficients[i]);
        }
        for (int i = 0; i < p2.exponents.length; i++) {
            result.put(p2.exponents[i], result.getOrDefault(p2.exponents[i], 0.0) + p2.non_zero_coefficients[i]);
        }
        int[] temp_exponents = new int[result.size()];
        double[] temp_coefficients = new double[result.size()];
        int count = 0;
        for (Map.Entry<Integer, Double> entry : result.entrySet()) {
            temp_exponents[count] = entry.getKey();
            temp_coefficients[count] = entry.getValue();
            count++;
        }
        return new Polynomial(temp_coefficients, temp_exponents);
    }


	public double evaluate(double x){
		double result = 0;
		for (int i = 0; i < exponents.length; i++){
			result += non_zero_coefficients[i] * Math.pow(x, exponents[i]);
		}
		return result;
	}

	public boolean hasRoot(double root){
		return evaluate(root) == 0;
	}
	public Polynomial multiply(Polynomial p2){
		HashMap<Integer, Double> result = new HashMap<>();

        for (int i = 0; i < this.exponents.length; i++) {
            int exp1 = this.exponents[i];
            double coeff1 = this.non_zero_coefficients[i];

            for (int j = 0; j < p2.exponents.length; j++) {
                int exp2 = p2.exponents[j];
                double coeff2 = p2.non_zero_coefficients[j];

                int expSum = exp1 + exp2;
                double coeffProduct = coeff1 * coeff2;

                result.put(expSum, result.getOrDefault(expSum, 0.0) + coeffProduct);
            }
        }

        result.entrySet().removeIf(entry -> entry.getValue() == 0.0);

        int size = result.size();
        int[] temp_e = new int[size];
        double[] temp_nzc = new double[size];

        int i = 0;
        for (Map.Entry<Integer, Double> entry : result.entrySet()) {
            temp_e[i] = entry.getKey();
            temp_nzc[i] = entry.getValue();
            i++;
        }

        return new Polynomial(temp_nzc, temp_e);

	}
	public void saveToFile(String fileName) throws IOException{
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < exponents.length; i++){
			double coeff = non_zero_coefficients[i];
			int exponent = exponents[i];
			if (coeff >= 0 && i > 0) {
                str.append("+");
            } else if (coeff < 0) {
                str.append("-");
            }

            coeff = Math.abs(coeff);
            if (coeff != 1 || exponent == 0) {
                str.append(coeff);
            }
            if (exponent != 0) {
                str.append("x");
                if (exponent != 1) {
                    str.append(exponent);
                }
            }
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(str.toString());
        writer.close();

	}
}