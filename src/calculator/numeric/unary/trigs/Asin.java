package calculator.numeric.unary.trigs;

import calculator.numeric.binary.Combination;
import calculator.operation.UnaryOperationAng;

public class Asin extends UnaryOperationAng{
	public static Double asin(double val) {
		if (Math.abs(val) > 1) {
			System.out.println(errors.getErr("00" + " for asin(x)"));
			System.exit(0);
		}
		double result = 0.0;
		for (int i = 0; i < 10; i++) {
			result += (1 / Math.pow(2, 2 * i)) * Combination.combination(2 * i, i) * Math.pow(val, 2 * i + 1)
					/ (2 * i + 1);
		}
		return result;
	}

	public Asin() {
		super("asin");
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) changeAngOutMode(asin((Double) numObj));
	}
}
