package calculator.vector.unary;

import calculator.operation.UnaryOperation;

public class Square extends UnaryOperation {

	public Square() {
		super("sq");
	}
	
	public static Double square(Double[] vec) {
		return Math.pow(Norm.norm(vec), 2);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)square((Double[])numObj);
	}

}
