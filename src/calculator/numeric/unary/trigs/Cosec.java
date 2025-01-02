package calculator.numeric.unary.trigs;

import calculator.operation.UnaryOperationAng;

public class Cosec extends UnaryOperationAng {

	public Cosec() {
		super("csc");
	}

	public static Double csc(double angle) {
		Double val = Sin.sin(angle);
		if (val == 0.0) {
			System.out.println(errors.getErr("00") + " for csc(x)");
			System.exit(0);
		}
		return 1 / val;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) csc(changeAngInMode((Double) numObj));
	}
}
