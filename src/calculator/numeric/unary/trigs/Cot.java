package calculator.numeric.unary.trigs;

import calculator.operation.UnaryOperationAng;

public class Cot extends UnaryOperationAng {

	public Cot() {
		super("cot");
	}

	public static Double cot(double angle) {
		angle %= Math.PI;
		if (Math.abs(angle) == Math.PI / 2) {
			return 0.0;
		}
		if (Math.abs(angle) == Math.PI) {
			System.out.println(errors.getErr("00") + " for cot(x)");
			System.exit(0);
		}
		return 1 / Tan.tan(angle);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) cot(changeAngInMode((Double) numObj));
	}
}
