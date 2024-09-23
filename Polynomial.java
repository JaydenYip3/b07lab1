public class Polynomial{
	double[] coefficients;

	public Polynomial(){
		double temp[] = {0};
		coefficients = temp;
	}

	public Polynomial(double[] doublearr){
		coefficients = doublearr;
	}

	public Polynomial add(Polynomial p2){
		int maxLength = Math.max(coefficients.length, p2.coefficients.length);
		double[] result = new double[maxLength];
		for (int i = 0; i < result.length; i++){
			if (i >= coefficients.length){
				result[i] = p2.coefficients[i];
			}
			else if (i >= p2.coefficients.length){
				result[i] = coefficients[i];
			}
			else{
				result[i] = p2.coefficients[i] + coefficients[i];
			}
		}
		return new Polynomial(result);
	}
	public double evaluate(double x){
		double result = 0;
		for (int i = 0; i < coefficients.length; i++){
			result += coefficients[i] * Math.pow(x, i);
		}
		return result;
	}

	public boolean hasRoot(double root){
		return evaluate(root) == 0;
	}
}