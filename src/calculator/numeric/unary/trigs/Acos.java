package calculator.numeric.unary.trigs;

import calculator.operation.UnaryOperationAng;

public class Acos extends UnaryOperationAng{
	public static Double acos(double val) {
		return (Double) Math.PI / 2 - Asin.asin(val);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) changeAngOutMode(acos((Double) numObj));
	}

	public Acos() {
		super("acos");
	}
}
