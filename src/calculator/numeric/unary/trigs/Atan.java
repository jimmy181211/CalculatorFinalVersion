package calculator.numeric.unary.trigs;

import calculator.operation.UnaryOperationAng;

public class Atan extends UnaryOperationAng {
	public Atan() {
		super("atan");
	}

	public static Double atan(double val) {
		boolean isneg = false;
		boolean ischange = false;
		if (val < 0) {
			isneg = true;
			val = -val;
		}
		if (val > 1) {
			ischange = true;
			val = 1 / val;
		}
		double result = 0;
		for (int i = 0; i < 10; i++) {
			result += Math.pow(-1, i) / (2 * i + 1) * Math.pow(val, 2 * i + 1);
		}
		if (isneg) {
			result = -result;
		}
		if (ischange) {
			result = Math.PI / 2 - result;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) changeAngOutMode(atan((Double) numObj));
	}
}
