package calculator.vector.unary.statistics;

import calculator.operation.UnaryOperation;

public class Mean extends UnaryOperation {

	public Mean() {
		super("mean");
		// TODO Auto-generated constructor stub
	}

	public static Double mean(Double[] data) {
		return Sum.sum(data) / data.length;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)mean((Double[]) numObj);
	}

}
