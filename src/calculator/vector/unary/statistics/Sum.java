package calculator.vector.unary.statistics;

import calculator.operation.UnaryOperation;

public class Sum extends UnaryOperation {

	public Sum() {
		super("sum");
		// TODO Auto-generated constructor stub
	}
	
	public static Double sum(Double[] data) {
		Double sum = 0.0;
		for (Double e : data) {
			sum += e;
		}
		return sum;
	}
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)sum((Double[])numObj);
	}

}
