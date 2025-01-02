package calculator.vector.unary.statistics;

import calculator.operation.UnaryOperation;

public class Variant extends UnaryOperation {

	public Variant() {
		super("var");
		// TODO Auto-generated constructor stub
	}

	public static Double variant(Double[] data) {
		Double sum = 0.0;
		for (Double e : data) {
			sum += Math.pow(e, 2);
		}
		return sum / data.length - Math.pow(Mean.mean(data), 2);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) variant((Double[]) numObj);
	}

}
