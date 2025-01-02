package calculator.numeric.binary;

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
		Integer result=(Integer)numObj1 | (Integer)numObj2;
		return (E)result;
	}
}
