package calculator.numeric.unary.trigs;

import calculator.numeric.unary.Factorial;
import calculator.operation.UnaryOperationAng;

public class Tan extends UnaryOperationAng {

	public Tan() {
		super("tan");
	}

	public static Double tan(double angle) {
		angle %= Math.PI;
		// validation
		if (Math.abs(angle) == Math.PI / 2) {
			System.out.println(errors.getErr("00") + " for tanx");
			System.exit(0);
		}
		boolean isrecipical = false;
		boolean isneg = false;

		if (angle < 0) {
			isneg = true;
			angle = -angle;
		}
		if (angle > Math.PI / 4) {
			// tanx=cotpi/2-x=1/tanpi/2-x
			isrecipical = true;
			angle = Math.PI / 2 - angle;
		}

		double result = 0, coeff;
		for (int i = 0; i < 8; i++) {
			coeff = 2 * i + 1;
			result += Math.pow(-1, i) * Math.pow(angle, coeff) / Factorial.factorial((int) coeff);
		}
		if (isneg) {
			result = -result;
		}
		if (isrecipical) {
			result = 1 / result;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) tan(changeAngInMode((Double) numObj));
	}

}
