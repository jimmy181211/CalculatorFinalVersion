package calculator.bool.binary;

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
		Boolean a=(Boolean)numObj1,b=(Boolean)numObj2;
		Boolean result=a && !b || !a && b;
		return (E)result;
	}
}
