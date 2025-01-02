package calculator.numeric.unary.trigs;

import calculator.operation.UnaryOperationAng;

public class Sec extends UnaryOperationAng {

	public Sec() {
		super("sec");
	}

	public static Double sec(double angle) {
		Double val = Cos.cos(angle);
		if (val == 0.0) {
			System.out.println(errors.getErr("00" + " for sec(x)"));
			System.exit(0);
		}
		return 1 / val;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) sec(changeAngInMode((Double) numObj));
	}
}
