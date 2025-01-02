package calculator.numeric.binary;

import calculator.operation.BinaryOperation;

/**
 * A subclass from BinaryOperation that does XOR operation
 */
public class Xor extends BinaryOperation {

	public Xor() {
		super("^",4);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		Integer result=(Integer)numObj1 ^(Integer)numObj2;
		return (E)result;
	}
}
