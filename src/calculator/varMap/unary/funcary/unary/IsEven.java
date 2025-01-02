package calculator.varMap.unary.funcary.unary;

import calculator.operation.UnaryOperation;

public class IsEven extends UnaryOperation {

	public IsEven() {
		super("iseven");
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E) IsOdd.parity(true);
	}

}
