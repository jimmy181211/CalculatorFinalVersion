package calculator.numeric.unary.trigs;

import calculator.numeric.unary.Factorial;
import calculator.operation.UnaryOperationAng;

public class Cos extends UnaryOperationAng {
	public Cos() {
		super("cos");
	}

	public static Double cos(double angle) {
		angle %= (Math.PI * 2);
		// get the smallest angle to reduce error
		boolean isneg = false;
		if (Math.abs(angle) > Math.PI / 2) {
			angle -= Math.PI;
			isneg = !isneg;
		}
		double result = 0;
		if (Math.abs(angle) > Math.PI / 4) {
			// because cos(-x)=cos(x) and sin(pi/2-x)=cos(x)
			result = Sin.sin(Math.PI / 2 - Math.abs(angle));
		} else {
			int coeff;
			for (int i = 0; i < 10; i++) {
				coeff = 2 * i;
				result += Math.pow(-1, i) * Math.pow(angle, coeff) / Factorial.factorial(coeff);
			}
		}
		return isneg ? -result : result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) cos(changeAngInMode((Double) numObj));
	}
}
