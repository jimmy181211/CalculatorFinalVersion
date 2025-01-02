package calculator.numeric.unary.hypbolic;

import calculator.numeric.unary.Abs;
import calculator.operation.UnaryOperationAng;

public class Tanh extends UnaryOperationAng {
	public Tanh() {
		super("tanh");
	}

	public static Double tanh(double val) {
		if (Abs.abs(val) > 240) {
			System.out.println(errors.getErr("07") + " for tanh(x)");
			System.exit(0);
		}
		return (Math.pow(Math.E, val) - Math.pow(Math.E, -val)) / (Math.pow(Math.E, val) + Math.pow(Math.E, -val));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)tanh(changeAngInMode((Double) numObj));
	}
}
