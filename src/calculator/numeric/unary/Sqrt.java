package calculator.numeric.unary;

import calculator.operation.UnaryOperation;

public class Sqrt extends UnaryOperation{
	public Sqrt() {
		super("sqrt");
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		Double result=Math.sqrt((Double)numObj);
		return (E)result;
	}

}
