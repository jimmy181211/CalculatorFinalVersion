package calculator.complex.unary.hyperbolic;

import calculator.complex.ComplexNum;
import calculator.complex.binary.Divide;
import calculator.operation.UnaryOperation;

public class Tanh extends UnaryOperation {

	public Tanh() {
		super("tanh");
	}
	
	public static ComplexNum tanh(ComplexNum cangle) {
		return Divide.divide(Sinh.sinh(cangle), Cosh.cosh(cangle));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E, T> E operate(T numObj) {
		return (E)tanh((ComplexNum)numObj);
	}
}
