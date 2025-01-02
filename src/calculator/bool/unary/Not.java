package calculator.bool.unary;

import calculator.operation.UnaryOperation;

/**
 * A subclass from UnaryOperation that does NOT operation
 */
public class Not extends UnaryOperation{

	public Not(String operator) {
		super("!");
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		Boolean result=!(Boolean)numObj;
		return (E)result;
	}
}
