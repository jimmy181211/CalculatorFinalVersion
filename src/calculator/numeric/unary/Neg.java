package calculator.numeric.unary;

import calculator.operation.UnaryOperation;

public class Neg extends UnaryOperation{
	public Neg() {
		super("~");
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		Double result=-(Double)numObj;
		return (E)result;
	}

}
