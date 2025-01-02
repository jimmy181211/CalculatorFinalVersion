package calculator.bool.binary;

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
		Boolean result=(Boolean)numObj1 && (Boolean)numObj2;
		return (E)result;
	}
	public static void main(String[] args) {
		Integer a=~1;
		System.out.println(a.toString());
	}
}
