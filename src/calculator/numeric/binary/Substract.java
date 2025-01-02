package calculator.numeric.binary;

import calculator.operation.BinaryOperation;

public class Substract extends BinaryOperation{

	public Substract() {
		super("-",3);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T, C> E operate(T numObj1, C numObj2) {
		Double result=(Double)numObj1-(Double)numObj2;
		return (E)result;
	}

}
