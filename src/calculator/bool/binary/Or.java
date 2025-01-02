package calculator.bool.binary;

import calculator.operation.BinaryOperation;

/**
 * A subclass from BinaryOperation that does OR operation
 */
public class Or extends BinaryOperation {
	public Or() {
		super("||",3);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		Boolean result=(Boolean)numObj1 || (Boolean)numObj2;
		return (E)result;
	}
}
