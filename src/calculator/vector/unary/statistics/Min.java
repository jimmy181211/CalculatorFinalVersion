package calculator.vector.unary.statistics;

import calculator.operation.UnaryOperation;

public class Min extends UnaryOperation{

	public Min() {
		super("min");
	}
	
	public static Double min(Double[] arr) {
		Double max = Double.MAX_VALUE;
		for (Double e : arr) {
			if (e < max) {
				max = e;
			}
		}
		return max;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) min((Double[]) numObj);
	}
}
