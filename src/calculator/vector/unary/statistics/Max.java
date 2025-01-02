package calculator.vector.unary.statistics;

import calculator.operation.UnaryOperation;

public class Max extends UnaryOperation {

	public Max() {
		super("max");
	}
	
	public static Double max(Double[] arr) {
		Double max = Double.MIN_VALUE;
		for (Double e : arr) {
			if (e > max) {
				max = e;
			}
		}
		return max;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) max((Double[]) numObj);
	}

}
