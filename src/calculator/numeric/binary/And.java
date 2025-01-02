package calculator.numeric.binary;

import calculator.operation.BinaryOperation;

/**
 * A subclass from BinaryOperation that does AND operation
 */
public class And extends BinaryOperation {
	public And() {
		super("+",4);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		Integer result=(Integer)numObj1 & (Integer)numObj2;
		return (E)result;
	}
}
