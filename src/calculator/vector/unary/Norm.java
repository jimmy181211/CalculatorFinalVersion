package calculator.vector.unary;

import calculator.operation.UnaryOperation;

public class Norm extends UnaryOperation {

	public Norm() {
		super("norm");
	}
	
	public static Double norm(Double[] vec) {
		Double square=0.0;
		for(Double el:vec) {
			square+=Math.pow(el,2);
		}
		return Math.sqrt(square);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)norm((Double[])numObj);
	}

}
